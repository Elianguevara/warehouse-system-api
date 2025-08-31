package com.eguevara.warehouse.warehouse_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Representa la tabla 'User' en la base de datos.
 * Esta clase es para la autenticación y autorización de usuarios en el sistema.
 * Contiene información básica como el nombre de usuario y la contraseña (que debe estar encriptada).
 */
@Entity
@Table(name = "User")
@Data // Genera getters, setters, toString, equals y hashCode de Lombok.
@NoArgsConstructor // Genera un constructor sin argumentos.
@AllArgsConstructor // Genera un constructor con todos los campos.
public class User {

    @Id // Marca el campo como clave primaria.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera un valor automáticamente.
    private Integer id;

    private String username;
    private String password; // La contraseña se almacena de forma segura (hasheada).
    private String email;
}