package org.example.wsoc.presenter;

import org.example.wsoc.model.book;
import org.example.wsoc.util.connectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class bookListPresenter {

    private static connectionDB conn = new connectionDB();
    public bookListPresenter(){

    }

    public static boolean editSelectedRow(int selectedRow,List<book>booksTable){

        bookFormPresenter bookFormPresenterInstance = new bookFormPresenter();
        bookFormPresenterInstance.dataEditBookForm = booksTable.get(selectedRow);
        if (bookFormPresenterInstance.dataEditBookForm != null){
            return true;
        }
        return false;

    }

    public static List<book> getBooks(){
        List<book> data = new ArrayList<>();

        Connection connectionListBook = null;
        try {

             connectionListBook = conn.getConnection();

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

            connectionFormBook = conn.getConnection();
            // --- Aquí es donde realizarías tus operaciones con la base de datos ---
            // Por ejemplo, ejecutar una consulta:
            if (connectionFormBook != null) {
                String queryForm = "DELETE FROM Books WHERE Id = " + dataBook.Id;
                Statement stmt = connectionFormBook.createStatement();
                int affectedRows = stmt.executeUpdate(queryForm);//executeUpdate() Forma estandar
                // de ejecutar datos.
                if (affectedRows > 0)
                {
                    //Alertas
                    System.out.println(affectedRows);
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
            System.out.println(e);
            return false;
        } finally {
            connectionDB.closeConnection(connectionFormBook);
        }
    }
}
