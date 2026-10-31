package com.utp.tienda.patrones.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Patrón Creacional: Singleton
 * Garantiza que la clase tenga una única instancia y proporciona un punto de acceso global a ella.
 */
public class DatabaseSingleton {

    // [BLOQUE 1: ATRIBUTO ESTÁTICO PRIVADO]
    private static DatabaseSingleton instance;
    private Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/bd_sistema_negocio";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    // [BLOQUE 2: CONSTRUCTOR PRIVADO]
    private DatabaseSingleton() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("[DatabaseSingleton] Error conectando a PostgreSQL: " + e.getMessage());
        }
    }

    // [BLOQUE 3: MÉTODO GLOBAL SINCRONIZADO]
    public static synchronized DatabaseSingleton getInstance() {
        try {
            if (instance == null || instance.getConnection() == null || instance.getConnection().isClosed()) {
                instance = new DatabaseSingleton();
            }
        } catch (SQLException e) {
            instance = new DatabaseSingleton();
        }
        return instance;
    }

    // [BLOQUE 4: ACCESO A LA CONEXIÓN]
    public Connection getConnection() {
        return this.connection;
    }
}

