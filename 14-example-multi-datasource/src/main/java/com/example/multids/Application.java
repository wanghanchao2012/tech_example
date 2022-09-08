package com.example.multids;

import com.example.multids.dao.DbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private DbDao dbDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dbDao.printMoney();
        dbDao.printUser();
    }
}
