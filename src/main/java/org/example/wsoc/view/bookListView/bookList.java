package org.example.wsoc.view.bookListView;
import org.example.wsoc.model.book;
import org.example.wsoc.presenter.bookListPresenter;
import org.example.wsoc.view.bookFormView.bookForm;
import org.example.wsoc.view.elements.button;
import org.example.wsoc.view.elements.initialFrame;
import org.example.wsoc.view.elements.table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class bookList {

    private static JPanel JPanelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
    private static initialFrame InitialWindow = new initialFrame();
    private static String[] columns = {"Id","Titulo","Autor","ISBN","Año de Publicación"};
    private static table bookTable = new table();
    public static String[][] dataJTable = {};
    private static button bookButton =  new button();
    private  static bookListPresenter presenterList = new bookListPresenter();
    private static List<book> currentListBook = new ArrayList<>();

    public bookList(){createInitialView();}

    public static void createInitialView(){
        dataJTable = getBooksAvailable();
        createButtonsTable();
        createBookTable(dataJTable,columns);
        InitialWindow.setVisible(true);
    }

    private static String[][] getBooksAvailable(){
        currentListBook = presenterList.getBooks();
        return validateListBook(currentListBook);
    }

    private static String[][] validateListBook(List<book> currentBooks){

        book bookData;
        List<String[]> booksDataString = new ArrayList<>();
        String[][] booksString = {};
        if (currentBooks == null)
        {
            JOptionPane.showMessageDialog(InitialWindow, "Ocurrio un error al realizar la consulta de datos, consulte a IT");
        }else
        {
            for (int i = 0; i < currentBooks.size(); i++){
                bookData = currentBooks.get(i);
                String[] stringBookData =
                        {Integer.toString(bookData.Id),
                                bookData.Titulo,bookData.Autor,bookData.ISBN,
                                Integer.toString(bookData.AnioPublicacion)};
                booksDataString.add(stringBookData);
            }

            booksString = booksDataString.toArray(new String[booksDataString.size()][]);
        }
        return booksString;
    }
    private static void createButtonsTable(){
        JPanelButton.add(createBookButtonRight("Registrar Libro",actionButtonCreateBook()));
        JPanelButton.add(editBookButtonRight("Editar Libro Selecionado",actionButtonEditBook()));
        JPanelButton.add(deleteBookButtonRight("Eliminar Libro Selecionado",actionButtonDeleteBook()));
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

    private static JButton deleteBookButtonRight(String textButton, ActionListener ac){
        JButton buttonEdit = bookButton.createRightButtonPanel(textButton,actionButtonDeleteBook());
        return buttonEdit;
    }
    public static ActionListener actionButtonCreateBook(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InitialWindow.setVisible(false);
                bookForm.validateEditionMode(false,currentListBook.get(0));//Se envía cero pero realmente no se utilizará
                bookForm.showFormBookPanel(true);
                removeBooksTable();
            }
        };
    }

    public static ActionListener actionButtonEditBook(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bookTable.listTable.getSelectedRow() != -1)
                {
                    Boolean editionMode = bookListPresenter.editSelectedRow(bookTable.listTable.getSelectedRow(),currentListBook);
                    bookForm.validateEditionMode(editionMode,currentListBook.get(bookTable.listTable.getSelectedRow()));
                    bookForm.showFormBookPanel(true);
                    bookList.showBookListPanel(false);
                }else{
                    JOptionPane.showMessageDialog(InitialWindow, "Seleccione un libro para poder editarlo.");
                }
            }
        };
    }

    public static ActionListener actionButtonDeleteBook(){

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (bookTable.listTable.getSelectedRow() != -1){
                    Boolean deleteSuccess = bookListPresenter.deleteDataBook(currentListBook.get(bookTable.listTable.getSelectedRow()));
                    if (deleteSuccess){
                        removeBooksTable();
                        createInitialView();
                        JOptionPane.showMessageDialog(InitialWindow, "Se eliminó el libro con exito.");
                    }else {
                        JOptionPane.showMessageDialog(InitialWindow, "Fallo en la eliminación del libro, consulte con IT");
                    }
                }else {
                    JOptionPane.showMessageDialog(InitialWindow, "Seleccione un libro para poder eliminarlo.");
                }
            }
        };
    }

    public static void showBookListPanel(boolean show){
        InitialWindow.setVisible(show);
    }

    public static void removeBooksTable(){
        InitialWindow.remove(JPanelButton);
        JPanelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        InitialWindow.remove(bookTable.listPanel);
    }

}
