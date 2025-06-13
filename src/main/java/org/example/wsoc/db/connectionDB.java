package org.example.wsoc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDB {

        // Variables para almacenar las credenciales de la base de datos
        private static final String DB_URL;
        private static final String DB_USERNAME;
        private static final String DB_PASSWORD;

        // Bloque estático para cargar las variables de entorno al iniciar la clase
        static {

            //Se utilizan variables de ambiente de windows para almacenar
            // las credenciales de las bases de datos
            DB_URL = System.getenv("DB_URL");
            DB_USERNAME = System.getenv("DB_USERNAME");
            DB_PASSWORD = System.getenv("DB_PASSWORD");

            //DB_URL = "jdbc:mysql://localhost:3306/demoswingbiblioteca";
            //DB_USERNAME = "root";
            //DB_PASSWORD = "";

           //Verifica si las variables se cargaron correctamente
            if (DB_URL == null || DB_USERNAME == null || DB_PASSWORD == null) {
                System.err.println("¡Error! Una o más variables de entorno de la base de datos no están configuradas.");
                System.err.println("Asegúrate de que DB_URL, DB_USERNAME y DB_PASSWORD estén definidas.");
                // Podrías lanzar una excepción o salir si es crítico
                // throw new RuntimeException("Variables de entorno de DB faltantes.");
            }
        }

        // Establece una conexión a la base de datos.
        public static Connection getConnection() {
            Connection connection = null;
            try {

                // Establecer la conexión usando DriverManager
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

                if (connection != null) {
                    System.out.println("Conexión a la base de datos establecida correctamente.");
                } else {
                    System.out.println("No se pudo establecer la conexión a la base de datos.");
                }

            } catch (SQLException e) {
                System.err.println("Error de SQL al intentar conectar a la base de datos:");
            } catch (Exception e) { // Capturar cualquier otra excepción inesperada
                System.err.println("Error inesperado al intentar conectar a la base de datos:");
            }
            return connection;
        }

        public static void closeConnection(Connection connection) {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Conexión a la base de datos cerrada.");
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión a la base de datos:");
                }
            }
        }

}
