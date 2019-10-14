package com.example.login;

import com.example.login.model.User;
import com.example.login.model.data.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userRepository;

    /** Obtiene el usuario en la base de datos y carga sus caracteristicas en un objeto UserDetails
     *
     * @param username Coleccion de documentos
     * @return UserDetails object utilizado para realizar la autentificación
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        List<GrantedAuthority> autoridad = new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getAuthority());
        autoridad.add(grantedAuthority);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),"{noop}"+user.getPassword(),autoridad);
        return userDetails;
    }
}