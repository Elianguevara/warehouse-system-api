package com.eguevara.warehouse.warehouse_system.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;

/**
 * Clase utilitaria para generar una clave secreta segura para JWT.
 * Ejecuta este main para obtener una clave aleatoria y copiarla
 * en tu archivo application.properties.
 */
public class SecretKeyGenerator {

    public static void main(String[] args) {
        // Genera una clave segura de 512 bits (64 bytes) para el algoritmo HS512.
        Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        
        // Codifica la clave en una cadena Base64 para que pueda ser almacenada
        // de forma segura en el archivo de propiedades.
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Clave JWT generada (copia y pega la cadena a continuación):");
        System.out.println(encodedKey);
        System.out.println("-------------------------------------------------------------------");
    }
}