package com.example.ssh2.utils;


import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * @descriptions: shell命令和scp文件传输
 */
@Slf4j
@Component
public class ScpHelper {

    @Value("${scp.ip}")
    private String scpIp;

    @Value("${scp.port:22}")
    private Integer scpPort;

    @Value("${scp.userName:root}")
    private String userName;

    @Value("${scp.password}")
    private String password;

    private Connection connection;

    /**
     * 初始化
     */
    public synchronized void initConnection() {
        if (Objects.isNull(connection)) {
            try {
                connection = new Connection(scpIp, scpPort);
                connection.connect();
            } catch (Exception e) {
                log.error("SCP-初始化SCP连接失败，请配置后再使用该工具！");
                throw new RuntimeException("SCP-初始化SCP连接失败，请配置后再使用该工具！", e);
            }
            boolean b = authenticate(userName, password);
            if (!b) {
                try {
                    throw new RuntimeException("SCP-认证失败，无法执行相关命令！");
                } finally {
                    connection.close();
                }
            }
        }
    }

    /**
     * ssh用户登录验证，使用用户名和密码来认证
     *
     * @param userName
     * @param password
     * @return
     */
    public boolean authenticate(String userName, String password) {
        try {
            return connection.authenticateWithPassword(userName, password);
        } catch (IOException e) {
            log.error("SCP-认证异常", e);
        }
        return false;
    }


    private void before() {
        initConnection();
    }

    /**
     * 执行简单的Shell命令
     *
     * @param cmd
     */
    public void execSimpleCmd(String cmd) {
        before();
        Session session = null;
        try {
            session = connection.openSession();
            session.execCommand(cmd);
        } catch (Exception e) {
            log.error("SCP-执行shell命令失败", e);
        } finally {
            if (Objects.nonNull(session)) {
                session.close();
            }

        }
    }

    /**
     * 执行复杂的Shell命令
     *
     * @param cmd
     */
    public void execCmd(String cmd) {
        before();
        Session session = null;
        try {
            session = connection.openSession();
            // 建立虚拟终端
            session.requestPTY("bash");
            // 打开一个Shell
            session.startShell();
            // 准备输入命令
            PrintWriter out = new PrintWriter(session.getStdin());
            // 输入待执行命令
            out.println(cmd);
            out.println("exit");
            // 关闭输入流
            out.close();
            // 等待，除非1.连接关闭；2.输出数据传送完毕；3.进程状态为退出；4.超时
            session.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS, 30000);
        } catch (Exception e) {
            log.error("SCP-执行shell命令失败", e);
        } finally {
            if (Objects.nonNull(session)) {
                session.close();
            }
        }
    }

    /**
     * 下载到本地硬盘
     *
     * @param remoteFile 远程文件绝对路径 如：/home/users/error.txt
     * @param localPath  下载本地目录 如：C://Downloads//
     */
    public void getFile(String remoteFile, String localPath) {
        try {
            before();
            SCPClient scpClient = connection.createSCPClient();
            scpClient.get(remoteFile, localPath);
        } catch (Exception e) {
            log.error("SCP-下载到本地文件失败", e);
        } finally {
            connection.close();
        }
    }

    /**
     * 下载到字节流
     *
     * @param remoteFile 远程文件绝对路径 如：/home/users/error.txt
     */
    public ByteArrayOutputStream getFile(String remoteFile) {
        try {
            before();
            SCPClient scpClient = connection.createSCPClient();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            scpClient.get(remoteFile, outputStream);
            return outputStream;
        } catch (Exception e) {
            log.error("SCP-下载到字节流失败", e);
        } finally {
            connection.close();
        }
        return null;
    }

    /**
     * 本地硬盘文件上传
     *
     * @param localFile             本地硬盘文件目录
     * @param remoteTargetDirectory 上传的目录
     */
    public void putFile(String localFile,
                        String remoteTargetDirectory) {
        try {
            before();
            execCmd("mkdir -p " + remoteTargetDirectory);
            SCPClient scpClient = connection.createSCPClient();
            scpClient.put(localFile, remoteTargetDirectory);
        } catch (Exception e) {
            log.error("SCP-上传本地文件失败", e);
        } finally {
            connection.close();
        }
    }

    /**
     * form表单文件上传
     *
     * @param multipartFile         表单文件
     * @param remoteTargetDirectory 上传的目录
     */
    public void putFile(MultipartFile multipartFile,
                        String newFileName,
                        String remoteTargetDirectory) {
        try {
            before();
            execCmd("mkdir -p " + remoteTargetDirectory);
            SCPClient scpClient = connection.createSCPClient();
            InputStream inputStream = multipartFile.getInputStream();
            newFileName = StringUtils.isBlank(newFileName) ? multipartFile.getOriginalFilename() : newFileName;
            scpClient.put(IOUtils.toByteArray(inputStream), newFileName, remoteTargetDirectory);
        } catch (Exception e) {
            log.error("SCP-上传本地文件失败", e);
        } finally {
            connection.close();
        }
    }


    /**
     * 输入流上传
     *
     * @param inputStream           输入流
     * @param remoteFileName        远程文件名称
     * @param remoteTargetDirectory 上传的目录
     */
    public void putFile(InputStream inputStream,
                        String remoteFileName,
                        String remoteTargetDirectory) {
        try {
            before();
            execCmd("mkdir -p " + remoteTargetDirectory);
            SCPClient scpClient = connection.createSCPClient();
            scpClient.put(IOUtils.toByteArray(inputStream), remoteFileName, remoteTargetDirectory);
        } catch (Exception e) {
            log.error("SCP-上传本地文件失败", e);
        } finally {
            connection.close();
        }
    }
}

