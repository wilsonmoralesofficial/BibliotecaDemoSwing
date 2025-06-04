package org.example.wsoc.view.bookListView;
import org.example.wsoc.presenter.bookListPresenter;
import org.example.wsoc.view.bookFormView.bookForm;
import org.example.wsoc.view.elements.button;
import org.example.wsoc.view.elements.initialFrame;
import org.example.wsoc.view.elements.table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bookList {

    private static JPanel JPanelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
    private static JPanel JTablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private static initialFrame InitialWindow = new initialFrame();
    private static table bookTable = new table();

    private static button bookButton =  new button();
    private  static bookListPresenter presenteList = new bookListPresenter();

    public bookList(){createInitialView();}

    public static void createInitialView(){

        String[] columns = {"Id","Titulo","Autor","ISBN","Año de Publicación"};
        String[][] datos = getBooksAvailable();
        createButtonsTable();
        createBookTable(datos,columns);
        InitialWindow.setVisible(true);
    }

    private static String[][] getBooksAvailable(){
        String[][] books = {{}};
        presenteList.getBooks();
        return books;
    }
    private static void createButtonsTable(){
        JPanelButton.add(createBookButtonRight("Registrar Libro",actionButtonCreateBook()));
        JPanelButton.add(editBookButtonRight("Editar Libro Selecionado",actionButtonEditBook()));
        InitialWindow.add(JPanelButton,BorderLayout.NORTH);
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
                bookListPresenter.editSelectedRow(bookTable.listTable);
                //System.out.println("Mostrar Formulario Editar");
            }
        };
    }

    public static void showBookListPanel(boolean show){
        InitialWindow.setVisible(show);
    }



    /*public static void main(String[] args) {
        //createInitialView();
    }
     */

}
