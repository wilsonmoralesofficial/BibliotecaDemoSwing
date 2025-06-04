package org.example.wsoc.util;

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
            DB_URL = System.getenv("DB_URL");
            DB_USERNAME = System.getenv("DB_USERNAME");
            DB_PASSWORD = System.getenv("DB_PASSWORD");

            // Opcional: Verificar si las variables se cargaron correctamente
            if (DB_URL == null || DB_USERNAME == null || DB_PASSWORD == null) {
                System.err.println("¡Error! Una o más variables de entorno de la base de datos no están configuradas.");
                System.err.println("Asegúrate de que DB_URL, DB_USERNAME y DB_PASSWORD estén definidas.");
                // Podrías lanzar una excepción o salir si es crítico
                // throw new RuntimeException("Variables de entorno de DB faltantes.");
            }
        }

        /**
         * Establece una conexión a la base de datos.
         * @return Objeto Connection si la conexión es exitosa, null en caso contrario.
         */
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
                e.printStackTrace();
            } catch (Exception e) { // Capturar cualquier otra excepción inesperada
                System.err.println("Error inesperado al intentar conectar a la base de datos:");
                e.printStackTrace();
            }
            return connection;
        }

        /**
         * Cierra una conexión a la base de datos de forma segura.
         * @param connection El objeto Connection a cerrar.
         */
        public static void closeConnection(Connection connection) {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Conexión a la base de datos cerrada.");
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión a la base de datos:");
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) {
            Connection conn = null;
            try {
                conn = getConnection(); // Obtener la conexión

                // --- Aquí es donde realizarías tus operaciones con la base de datos ---
                // Por ejemplo, ejecutar una consulta:
                // if (conn != null) {
                //     java.sql.Statement stmt = conn.createStatement();
                //     java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM Libros");
                //     while (rs.next()) {
                //         System.out.println("Libro ID: " + rs.getInt("ID_Libro") + ", Título: " + rs.getString("Titulo"));
                //     }
                //     rs.close();
                //     stmt.close();
                // }

            } finally {
                closeConnection(conn); // Asegúrate de cerrar la conexión en el bloque finally
            }
        }
}
