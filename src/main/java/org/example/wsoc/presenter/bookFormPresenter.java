package org.example.wsoc.presenter;

import org.example.wsoc.model.book;
import org.example.wsoc.util.connectionDB;
import org.example.wsoc.view.elements.form;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class bookFormPresenter {

    private static connectionDB conn = new connectionDB();
    public book dataEditBookForm = null;

    public bookFormPresenter(){
    }

    public boolean validateEditMode(){
        // Se valida que el objeto book no sea null para
        // entrar en modo edición o modo creación.
        if (dataEditBookForm != null){
            return false;
        }
        return true;
    }

    public Map<String, String> mapDataEditForm(book dataBookForm){
        Map<String, String> initialValue = new LinkedHashMap<>();
        initialValue.put("Título", dataBookForm.Titulo);
        initialValue.put("Autor", dataBookForm.Autor);
        initialValue.put("ISBN", dataBookForm.ISBN);
        initialValue.put("Año Publicación", Integer.toString(dataBookForm.getAnioPublicacion()));
        return initialValue;
    }

    public static boolean saveDataBook(book dataBook,Map<String, String> formValues,boolean editionMode){

        Connection connectionFormBook = null;
        try {
            connectionFormBook = conn.getConnection();
            // --- Aquí es donde realizarías tus operaciones con la base de datos ---
            // Por ejemplo, ejecutar una consulta:
            if (connectionFormBook != null) {
                String queryForm = "";
                if (editionMode)
                {
                    queryForm ="UPDATE Books " +
                                "SET Titulo= '" + formValues.get("Título") +"', Autor= '" + formValues.get("Autor")+
                                "', ISBN = '"+ formValues.get("ISBN") + "', AnioPublicacion= '" + formValues.get("Año Publicación") +"'"+
                                " WHERE Id= " + dataBook.Id;
                }else {
                    queryForm ="INSERT INTO Books " +
                            "(Titulo, Autor, ISBN, AnioPublicacion) " +
                            "VALUES('"+ formValues.get("Título") +"', '"+ formValues.get("Autor") +"', '"
                            + formValues.get("ISBN") +"', "+ formValues.get("Año Publicación") +")";
                }
                Statement stmt = connectionFormBook.createStatement();
                int affectedRows = stmt.executeUpdate(queryForm);
                if (affectedRows > 0)
                {
                    System.out.println(affectedRows);
                    return true;
                }else
                {
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
