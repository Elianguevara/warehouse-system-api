package com.eguevara.warehouse.warehouse_system.config;

import com.eguevara.warehouse.warehouse_system.model.User;
import com.eguevara.warehouse.warehouse_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verifica si el usuario ya existe para evitar duplicados
        if (userRepository.findByUsername("admin").isEmpty()) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin123")); // ¡Cifra la contraseña!
            user.setEmail("elian.guevara689@gmail.com");

            userRepository.save(user);
            logger.info("Usuario 'admin' creado exitosamente.");
        }
    }
}