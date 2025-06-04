package org.example.wsoc.views.bookListView;
import org.example.wsoc.views.bookFormView.bookForm;
import org.example.wsoc.views.elements.button;
import org.example.wsoc.views.elements.initialFrame;
import org.example.wsoc.views.elements.table;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bookList {

    private static JPanel JPanelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
    private static JPanel JTablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private static initialFrame InitialWindow = new initialFrame();
    private static table bookTable = new table();

    private static button bookButton =  new button();

    public void bookList(){

    }

    public static void createInitialView(){

        String[] columns = {"Titulo","Autor","ISBN","Año de Publicación"};
        String[][] datos = {{}};
        JPanelButton.add(createBookButtonRight("Registrar Libro",actionButtonCreateBook()));
        JPanelButton.add(editBookButtonRight("Editar Libro",actionButtonEditBook()));
        InitialWindow.add(JPanelButton,BorderLayout.NORTH);
        createBookTable(datos,columns);
        InitialWindow.setVisible(true);
        bookForm bookForm = new bookForm();


    }

    private static void createBookTable(String[][]data,String[]columns){
        InitialWindow.add(bookTable.addJpanelTable(data,columns));
    }

    private static JButton createBookButtonRight(String textButton, ActionListener actionListenerButtonCreateRight){
        JButton buttonCreate = bookButton.createRightButtonPanel(textButton,actionListenerButtonCreateRight);
        return buttonCreate;

    }

    private static JButton editBookButtonRight(String textButton, ActionListener ac){
        JButton buttonEdit = bookButton.createRightButtonPanel(textButton,actionButtonEditBook());
        return buttonEdit;
    }
    public static ActionListener actionButtonCreateBook(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InitialWindow.setVisible(false);
                bookForm.showFormBookPanel(true);
            }
        };
    }

    public static ActionListener actionButtonEditBook(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Mostrar Formulario Editar");
            }
        };
    }

    public static void showBookListPanel(){
        InitialWindow.setVisible(true);
    }
    public static void main(String[] args) {
        createInitialView();
    }
}
