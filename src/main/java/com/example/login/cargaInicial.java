package com.example.login;

import com.example.login.model.User;
import com.example.login.model.data.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/** Se ejecuta al lanzar la aplicacion*/
@Configuration
public class cargaInicial {
    /** Se conecta a la base de datos*/
    @Autowired
    private UserDAO usuarioDao;

    /** Ingresa un usuario a la base de datos
     *
     * @param usuarioDao Coleccion de documentos
     * @return una linea de comando que agrega los datos
     */
    @Bean
    public CommandLineRunner demo(UserDAO usuarioDao) {

        return (args -> {
            User u1 = new User();
            u1.setUsername("Cristian");
            u1.setPassword("123123");
            u1.setEmail("email@example.com");
            usuarioDao.save(u1);
        });

    }

}
