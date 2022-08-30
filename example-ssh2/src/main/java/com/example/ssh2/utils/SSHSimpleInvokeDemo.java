package com.example.ssh2.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SSHSimpleInvokeDemo {

    public static void main(String[] args) throws IOException {

        String hostname = "10.240.23.112";
        String username = "root";
        String password = "zhengda@1";

        // 基本流程
        // 1. 创建连接对象
        // 2. 建立连接
        // 3. 进行身份认证
        // 4. 打开会话，执行相关命令
        // 5. 关闭连接，释放资源

        // 创建一个准备建立连接的对象，如果不指定端口，默认是22，可以构造的时候指定端口
        Connection conn = new Connection(hostname);

        // 建立连接，这个方法支持传入参数：ServerHostKeyVerifier verifier, int connectTimeout, int kexTimeout
        // verifier：验证主机秘钥的正确性
        // connectTimeout: 就是tcp连接超时，0：不超时，不能为负数
        // kexTimeout：ssh的连接超时，指的是这个方法开始调用直到密钥交换结束的时间，0：不超时，不能为负数
        conn.connect();

        // 进行身份认证，这个指的是使用账户密码的方式，如果不支持密码方式，可以采用公钥的方式等
        // 或者使用 Connection.getRemainingAuthMethods()看下支持哪些方式
        boolean isAuthenticated = conn.authenticateWithPassword(username, password);

        if (isAuthenticated == false) {
            throw new IOException("Authentication failed.");
        }
        // 下面这个地方需要注意
        // 1. 每打开一个Session只能执行一次execCommand方法
        // 2. 一个连接可以打开几次Session，服务器这边可能也是有限制的，比如OpenSSH,默认每个连接最大会话是10
        // 可以查看你的ssh 服务器配置
        Session sess = conn.openSession();
        // 如何想要一次执行多个命令，可以把命令连接起来执行如下，前面的命令执行成功就会执行后面的命令：进入用户目录，然后打印文件列表
        //sess.execCommand("cd /home/work/test && ls -l");
        //sess.execCommand("cat /home/work/test/readme.md");
        sess.execCommand("echo \"123456\" > test.txt");
        
        
        // 如何有返回内容的话，在输出流中，错误内容在错误流中
        // StreamGobbler已经实现了缓冲区，不需要额外的缓存区封装，如：BufferedOutputStream
        InputStream stdout = new StreamGobbler(sess.getStdout());
        String message = readMessage(stdout);
        System.out.println(message);

        // 如果有错误流，读取错误流，需要注意的是输入流、输出流、错误流共享缓冲区，所以别不管错误流内容
        // 不读错误流，一旦缓冲区被用尽，命令就会被阻塞，不能执行了。当然了缓冲区大小：30kb，一般也不是那么容易占满
        if (message == null) { // 如果为空，可能是错误了，也可能是像cd这种命令，输出流本身没有内容
            InputStream stderr = new StreamGobbler(sess.getStderr());
            message = readMessage(stderr);
            System.out.println(message);
        }

        // 不要太依赖下面这两个字段，判断是执行成功还是失败了，服务器不支持，这两个字段就可能为空，所以是否成功失败，参看上面
        //System.out.println("ExitCode: " + sess.getExitStatus());
        //System.out.println("signal: " + sess.getExitSignal());

        // 关闭会话
        sess.close();

        // 如果想再调用execCommand，就再调用一次conn.openSession();，打开会话
        // 如果受到服务器会话数限制，那就需要再新建一次连接了

        // 关闭连接
        conn.close();
        // 可能有同学奇怪，服务器有会话数限制，但比如我们用有些工具，如secure CRT之类工具，是可以一直进行会话的，不受最大次数限制
        // 那是因为可以选择建立交互式会话连接，这个工具包里提供的有接口
    }

    private static String readMessage(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            // 按行读取，按行拼接
            StringBuilder message = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                message.append(line).append(System.lineSeparator());
            }
            return message.length() > 0 ? message.toString() : null;
        }
    }
} 