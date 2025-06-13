package org.example.wsoc.presenter;

import org.example.wsoc.model.book;
import org.example.wsoc.db.connectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class bookListPresenter {


    public static boolean editSelectedRow(int selectedRow,List<book>booksTable){

        bookFormPresenter.dataEditBookForm = booksTable.get(selectedRow);
        return bookFormPresenter.dataEditBookForm != null;

    }

    public static List<book> getBooks(){
        List<book> data = new ArrayList<>();

        Connection connectionListBook = null;
        try {

             connectionListBook = connectionDB.getConnection();

             if (connectionListBook != null) {
                 java.sql.Statement stmt = connectionListBook.createStatement();
                 java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM books");
                 while (rs.next()) {
                     book bookDB = new book();
                     bookDB.Id = rs.getInt("Id");
                     bookDB.Titulo = rs.getString("Titulo");
                     bookDB.Autor = rs.getString("Autor");
                     bookDB.ISBN = rs.getString("ISBN");
                     bookDB.AnioPublicacion = rs.getInt("AnioPublicacion");
                     data.add(bookDB);
                 }
                 rs.close();
                 stmt.close();
             }

        } catch (SQLException e) {
            return null;
        } finally {
            connectionDB.closeConnection(connectionListBook);
        }

        return data;
    }

    public static boolean deleteDataBook(book dataBook){

        Connection connectionFormBook = null;
        try {

            connectionFormBook = connectionDB.getConnection();
            // --- Aquí es donde realizarías tus operaciones con la base de datos ---
            // Por ejemplo, ejecutar una consulta:
            if (connectionFormBook != null) {
                String queryForm = "DELETE FROM books WHERE Id = " + dataBook.Id + ";";
                String queryForm2 = " DELETE FROM copybooks WHERE IdLibro = " + dataBook.Id  + ";" ;
                Statement stmt = connectionFormBook.createStatement();
                Statement stmt2 = connectionFormBook.createStatement();
                int affectedRows = stmt.executeUpdate(queryForm);//executeUpdate() Forma estandar
                // de ejecutar datos.
                if (affectedRows > 0)
                {
                    //Alertas
                    System.out.println(affectedRows);
                    int affectedRows2 = stmt2.executeUpdate(queryForm2);
                    System.out.println(affectedRows2);
                    System.out.println(affectedRows2 > 0 ?"Data CopyBooks is Deleted":"Data Deleted Error");
                    return true;
                }else
                {
                    //Alertas
                    System.out.println(affectedRows);
                    return false;
                }

            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error ==>" + e);
            return false;
        } finally {
            connectionDB.closeConnection(connectionFormBook);
        }
    }
}
