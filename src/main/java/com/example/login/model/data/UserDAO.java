package com.example.login.model.data;

import com.example.login.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Long> {

      //busca los usuarios por nombre de usuario
      User findByUsername(String username);
}
