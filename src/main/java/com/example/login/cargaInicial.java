package com.example.login;

import com.example.login.model.User;
import com.example.login.model.data.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class cargaInicial {

    @Autowired
    private UserDAO usuarioDao;

    @Bean
    public CommandLineRunner demo(UserDAO usuarioDao) {

        return (args -> {
            User u1 = new User();
            u1.setUsername("Cristian");
            u1.setPassword("123123");
            usuarioDao.save(u1);
        });

    }

}
