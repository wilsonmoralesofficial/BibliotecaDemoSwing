package org.example.wsoc.view.bookListView;
import org.example.wsoc.model.book;
import org.example.wsoc.presenter.bookListPresenter;
import org.example.wsoc.view.bookFormView.bookForm;
import org.example.wsoc.view.elements.button;
import org.example.wsoc.view.elements.initialFrame;
import org.example.wsoc.view.elements.table;
import org.example.wsoc.view.viewConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class bookList {

    private static JPanel JPanelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
    private static final initialFrame InitialWindow = new initialFrame();
    private static final table bookTable = new table();
    public static String[][] dataJTable = {};
    private static final button bookButton =  new button();
    private static List<book> currentListBook = new ArrayList<>();

    public static void initViewBookList(){
        createInitialView(true);
    }

    public static void createInitialView(boolean getBooksFromDataBase){
        createButtonsTable();
        createBookTable(getBooksFromDataBase);
    }

    private static String[][] getBooksAvailable(){
        currentListBook = bookListPresenter.getBooks();
        return validateListBook(currentListBook);
    }

    private static String[][] validateListBook(List<book> currentBooks){
        List<String[]> booksDataString = new ArrayList<>();
        String[][] booksString = {};
        if (currentBooks == null)
        {
            JOptionPane.showMessageDialog(InitialWindow, viewConstants.textErrorToSaveDataMessageDialog);
        }else
        {
            iterateCurrentCopyBooks(currentBooks,booksDataString);
            booksString = booksDataString.toArray(new String[booksDataString.size()][]);
        }
        return booksString;
    }

    private static void iterateCurrentCopyBooks(List<book> currentBooks,List<String[]> booksDataString){
        for (book currentBook : currentBooks) {
            String[] stringBookData =
                    {Integer.toString(currentBook.Id),
                            currentBook.Titulo, currentBook.Autor, currentBook.ISBN,
                            Integer.toString(currentBook.AnioPublicacion)};
            booksDataString.add(stringBookData);
        }
    }

    private static void createButtonsTable(){
        JPanelButton.add(createBookButtonRight(actionButtonCreateBook()));
        JPanelButton.add(editBookButtonRight());
        JPanelButton.add(deleteBookButtonRight());
        InitialWindow.add(JPanelButton,BorderLayout.NORTH);
    }

    private static void createBookTable(boolean getBooksFromDataBase){
        dataJTable = getBooksFromDataBase ?getBooksAvailable():dataJTable;
        InitialWindow.add(bookTable.addJpanelTable(dataJTable, viewConstants.columnsBook));
        InitialWindow.setVisible(true);
    }

    private static JButton createBookButtonRight(ActionListener actionListenerButtonCreateRight){
        return bookButton.createRightButtonPanel(viewConstants.textButtonCreateBook,actionListenerButtonCreateRight);
    }

    private static JButton editBookButtonRight(){
        return bookButton.createRightButtonPanel(viewConstants.textButtonEditBook,actionButtonEditBook());
    }

    private static JButton deleteBookButtonRight(){
        return bookButton.createRightButtonPanel(viewConstants.textButtonDeleteBook,actionButtonDeleteBook());
    }
    public static ActionListener actionButtonCreateBook(){
        return e -> {
            InitialWindow.setVisible(false);
            bookForm.validateEditionMode(false,currentListBook.get(0));//Se envía cero pero realmente no se utilizará
            bookForm.InitialWindow.setVisible(true);
            removeBooksTable();
        };
    }

    public static ActionListener actionButtonEditBook(){
        return e -> {
            if (bookTable.listTable.getSelectedRow() != -1)
            {
                bookForm.validateEditionMode(bookListPresenter.editSelectedRow(bookTable.listTable.getSelectedRow(),currentListBook),currentListBook.get(bookTable.listTable.getSelectedRow()));
                bookForm.InitialWindow.setVisible(true);
                bookList.showBookListPanel(false);
            }else{
                JOptionPane.showMessageDialog(InitialWindow, viewConstants.textSelectBookToEditMessageDialog);
            }
        };
    }

    public static ActionListener actionButtonDeleteBook(){
        return e -> {
            if (bookTable.listTable.getSelectedRow() != -1){
                if (bookListPresenter.deleteDataBook(currentListBook.get(bookTable.listTable.getSelectedRow()))){
                    removeBooksTable();
                    createInitialView(true);
                    JOptionPane.showMessageDialog(InitialWindow, viewConstants.textDeleteBookSuccessMessageDialog);
                }else {
                    JOptionPane.showMessageDialog(InitialWindow, viewConstants.textDeleteBookErrorMessageDialog);
                }
            }else {
                JOptionPane.showMessageDialog(InitialWindow, viewConstants.textSelectABookToDeleteMessageDialog);
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
