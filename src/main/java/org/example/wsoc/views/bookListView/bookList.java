package org.example.wsoc.views.bookListView;
import org.example.wsoc.views.elements.button;
import org.example.wsoc.views.elements.initialFrame;
import org.example.wsoc.views.elements.table;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bookList {

    private JPanel JbookPanel;

    private static initialFrame InitialWindow = new initialFrame();
    private static table bookTable = new table();

    private static button bookButtonCreate =  new button();

    public void bookList(){

    }

    private static void createInitialView(){

        String[] columns = {"Titulo","Autor","ISBN","Año de Publicación"};
        String[][] datos = {{}};
        createBookButtonRight();
        //createBookTable(datos,columns);

        InitialWindow.setVisible(true);


    }

    private static void createBookTable(String[][]data,String[]columns){
        InitialWindow.add(bookTable.addJpanelTable(data,columns));
    }

    private static void createBookButtonRight(){
        InitialWindow.add(bookButtonCreate.createRightButtonPanel("Registrar Libro",actionButtonCreateBook()));
    }

    public static ActionListener actionButtonCreateBook(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action Listener");
            }
        };
    }
    public static void main(String[] args) {
        createInitialView();
    }
}
