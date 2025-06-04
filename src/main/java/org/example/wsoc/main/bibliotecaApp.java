package org.example.wsoc.main;

import javax.swing.*;
import org.example.wsoc.view.bookListView.bookList;
import org.example.wsoc.view.bookFormView.bookForm;
public class bibliotecaApp {

    public static void main(String[] args) {
        // Asegúrate de que toda la manipulación de la UI se ejecute en el Event Dispatch Thread (EDT)

            // 1. Crear la instancia de las Vistas
            bookList bookList = new bookList(); // Ininicializa el bookListPanel y lo muestra
            bookForm bookForm = new bookForm(); // Inicializa el bookFormPanel y
                                                // espera instrucciones de la bookList Class

            // 2. Crear las dependencias que el Presentador necesita
            // (En una app más grande, usarías Inyección de Dependencias aquí)
            //UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl(); // Ejemplo de un DAO

            // 3. Crear la instancia del Presentador, pasándole la Vista y sus dependencias
            //LoginPresenter loginPresenter = new LoginPresenter(loginView, usuarioDAO);

            // 4. (Opcional, pero buena práctica si el Presentador es quien gestiona la inicialización)
            //    Decirle al Presentador que inicialice la Vista
            //loginPresenter.initView(); // Podría configurar valores iniciales, mostrar errores, etc.

    }
}
