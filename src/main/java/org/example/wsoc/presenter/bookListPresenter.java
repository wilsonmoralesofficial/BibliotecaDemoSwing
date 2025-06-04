package org.example.wsoc.presenter;

import org.example.wsoc.model.book;
import org.example.wsoc.model.bookTable;
import org.example.wsoc.util.connectionDB;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.sql.Connection;
import java.sql.SQLException;


public class bookListPresenter {

    private static connectionDB conn = new connectionDB();
    public bookListPresenter(){

    }

    public static void editSelectedRow(JTable table){

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // valueIsAdjusting es true mientras el usuario está arrastrando la selección.
                // Queremos actuar solo cuando la selección finaliza.
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow(); // Obtiene el índice de la fila seleccionada

                    if (selectedRow != -1) { // Asegúrate de que una fila esté realmente seleccionada
                        // Obtiene el objeto Cliente de tu modelo de tabla
                        // Es importante usar el método getClienteAt de tu modelo si lo tienes
                        book selectedBook = bookTable.getBookAt(selectedRow);

                        // --- **Paso Clave 3: Mandar los datos al formulario de destino** ---
                        if (selectedBook != null) {
                            System.out.println(selectedBook);
                            //detallesClienteFrame.cargarDatosCliente(clienteSeleccionado);
                        }
                    }
                }
            }
        });
    }

    public static book[][] getBooks(){
        book[][] data = {};

        Connection connectionListBook = null;
        try {

             connectionListBook = conn.getConnection();
            // --- Aquí es donde realizarías tus operaciones con la base de datos ---
            // Por ejemplo, ejecutar una consulta:
             if (connectionListBook != null) {
                 java.sql.Statement stmt = connectionListBook.createStatement();
                 java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM books");
                 while (rs.next()) {
                     //System.out.println(rs);
                     System.out.println("Libro ID: " + rs.getInt("Id") + ", Título: " + rs.getString("Titulo"));
                 }
                 rs.close();
                 stmt.close();
             }

        } catch (SQLException e) {
            return data;
        } finally {
            connectionDB.closeConnection(connectionListBook);
        }

        return data;
    }
}
