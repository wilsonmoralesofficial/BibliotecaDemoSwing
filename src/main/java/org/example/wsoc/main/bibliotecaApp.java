package org.example.wsoc.main;

import org.example.wsoc.view.bookFormView.copyBookForm;
import org.example.wsoc.view.bookListView.bookList;
import org.example.wsoc.view.bookFormView.bookForm;
public class bibliotecaApp {

    public static void main(String[] args) {
            bookList bookList = new bookList(); // Ininicializa el panel bookList y lo muestra
            bookForm bookForm = new bookForm(); // Inicializa el bookFormPanel y
             // espera instrucciones de la bookList class
             // Ambas clases interactuan entre si.
            copyBookForm copyBookForm = new copyBookForm();// Inicializa el panel copyBookForm y
            // espera instrucciones de la bookForm class
            // Ambas clases interactuan entre si.
    }
}
