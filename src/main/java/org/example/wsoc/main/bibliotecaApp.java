package org.example.wsoc.main;

import javax.swing.*;
import org.example.wsoc.view.bookListView.bookList;
import org.example.wsoc.view.bookFormView.bookForm;
public class bibliotecaApp {

    public static void main(String[] args) {
            bookList bookList = new bookList(); // Ininicializa el bookListPanel y lo muestra
            bookForm bookForm = new bookForm(); // Inicializa el bookFormPanel y
                                                // espera instrucciones de la bookList Class
                                                // Ambas clases interactuan entre si.

    }
}
