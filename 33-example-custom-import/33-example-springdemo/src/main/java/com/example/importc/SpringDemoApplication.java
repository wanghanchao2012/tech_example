package com.example.importc;

import com.example.EnableSpringStudy;
import com.example.Student;
import com.example.config.CustomerConfig;
import com.example.config.MyImportBeanDefinitionRegistrar;
import com.example.importc.dto.Customer;
import com.example.importc.dto.PoolBean;
import com.example.importc.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 由于springboot扫描类的默认方式只能扫描到 **Application 同级别及其子文件夹下的文件
 * 所以引用类的路径高于 **Application文件时或不在同一包名下或第三方jar包时，
 * 需要手动引入并使spring能扫描到，此时需要用到@Import
 * **Application
 *
 * @Import 可以是一个普通的java bean 也可以是使用@Configuration修饰过的类
 * @Import 注解通常在**Application类上或@Configuration修饰的类上使用
 * <p>
 * <p>
 * 导入的实现方式有三种
 * 1.@Import方式
 * 2.实现ImportSelector接口，这种方式通常是做成一个jar包共第三方使用
 * 3.实现ImportBeanDefinitionRegistrar接口， register后可以直接使用实体
 */
@Import(value = {CustomerConfig.class, User.class, MyImportBeanDefinitionRegistrar.class})
@SpringBootApplication
@EnableSpringStudy
public class SpringDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    //普通类
    @Autowired
    User user;
    //@Configuration修饰的类的对象
    @Autowired
    Customer customer;
    //第三方jar包的类的对象
    @Autowired
    Student student;
    //通过ImportBeanDefinitionRegistrar注入的对象
    @Autowired
    PoolBean poolBean;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("------------user info-------------");
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println("------------customer info ----------------");
        System.out.println(customer.getUserName());
        System.out.println(customer.getPassword());
        System.out.println("------------student info ----------------");
        System.out.println(student.getId());
        System.out.println(student.getName());
        System.out.println("------------poolBean info ----------------");
        System.out.println(poolBean.getRedisHost());
        System.out.println(poolBean.getRedisPort());
        System.out.println(poolBean.getRedisDb());

    }
}
