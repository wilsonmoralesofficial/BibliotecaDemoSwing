package org.example.wsoc.views.elements;
import javax.swing.*;
import java.awt.*;


public class initialFrame extends JFrame{



    public initialFrame (){
        super("Bibloteca"); // Crea una nueva ventana inicialmente invisible.
        configureInitialWindow();
        // Siempre utilizar setVisible(true); al concluir las implementaciones
    }

    public void configureInitialWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Termina el programa al moemnto de cerra la ventana.
        setSize(700, 500); // Asigna las dimensiones de la ventana.
        setLocationRelativeTo(null); // Centra la ventana.
        setLayout(new BorderLayout());
    }

}
