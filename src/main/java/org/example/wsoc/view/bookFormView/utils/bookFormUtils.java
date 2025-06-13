package org.example.wsoc.view.bookFormView.utils;
import org.example.wsoc.model.book;
import org.example.wsoc.model.bookCopy;
import org.example.wsoc.presenter.bookFormPresenter;
import org.example.wsoc.view.bookFormView.bookForm;
import org.example.wsoc.view.bookFormView.copyBookForm;
import org.example.wsoc.view.bookListView.bookList;
import org.example.wsoc.view.viewConstants;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class bookFormUtils {

    public static boolean editMode = false;
    // Variable que maneja si se está en modo edición o en modo de creación
    // "editMode = true -- Modo Edición" "editMode = false -- Modo Creación"
    public static String[][] dataJTableCopyBook = {};
    //Datos de la tabla de ejemplares
    public static List<bookCopy> currentListCopyBook = new ArrayList<>();
    //Listado de ejemplares del libro actual

    public static void initializeTableCopyBook(boolean getCopyBooksFromDataBase){
        if (getCopyBooksFromDataBase)dataJTableCopyBook = getCopyBooksAvailable();
        createBookCopyTable(dataJTableCopyBook);
    }

    private static String[][] getCopyBooksAvailable(){
        currentListCopyBook = bookFormPresenter.getCopyBooks(bookForm.currentBook);
        return validateListCopyBook(currentListCopyBook);
    }

    private static void createBookCopyTable(String[][]data){
        bookForm.jPanelTable = new JPanel();
        bookForm.jPanelTable = bookForm.bookCopyTable.addJpanelTable(data, viewConstants.columnsCopyBook);
        bookForm.auxJPanelContainer.add(bookForm.jPanelTable);
    }

    private static String[][] validateListCopyBook(List<bookCopy> currentCopyBooks){
        List<String[]> copyBooksDataString = new ArrayList<>();
        String[][] copyBooksString = {};
        if (currentCopyBooks == null)
        {
            JOptionPane.showMessageDialog(bookForm.InitialWindow,viewConstants.textErrorToGetCopyBooksMessageDialog);
        }else
        {
            iterateCurrentCopyBooks(currentCopyBooks,copyBooksDataString);
            copyBooksString = copyBooksDataString.toArray(new String[copyBooksDataString.size()][]);
        }
        return copyBooksString;
    }

    private static void iterateCurrentCopyBooks(List<bookCopy> currentCopyBooks,List<String[]> copyBooksDataString){
        for (bookCopy currentCopyBook : currentCopyBooks) {
            String[] stringBookData =
                    {Integer.toString(currentCopyBook.Id),currentCopyBook.NumeroInventario,currentCopyBook.EstadoFisico,
                            currentCopyBook.UbicacionEstanteria,currentCopyBook.Disponible};
            copyBooksDataString.add(stringBookData);
        }
    }

    public static void manageDataUpdateCopyBooksTable(boolean backAction){
        bookList.removeBooksTable();
        bookList.createInitialView(backAction);
        bookList.showBookListPanel(true);
        copyBookForm.removeCopyBooksTable();
    }

    public static JButton createSaveButtonPanel(String textButton, ActionListener actioSave){
        return createCopyButtonPanel(textButton, actioSave);
    }

    public static JButton createBackButtonPanel(String textButton, ActionListener actionBack){
        return createCopyButtonPanel(textButton, actionBack);
    }

    public static JButton createCopyButtonPanel(String textButton, ActionListener actionBack){
        return bookForm.bookButtonForm.createRightButtonPanel(textButton,actionBack);
    }

    public static ActionListener actionButtonSaveBook(){
        return e -> {
            if (editMode)bookForm.JPanelButton.remove(2);
            bookForm.validateSaveDataBook(bookFormPresenter.saveDataBook(bookForm.currentBook,bookForm.booksForm.getFieldValues(),editMode));
            bookForm.InitialWindow.setVisible(false);
            bookFormUtils.manageDataUpdateCopyBooksTable(true);
        };
    }

    public static ActionListener actionButtonBackBook(){
        return e -> {
            if (editMode)bookForm.JPanelButton.remove(2);
            bookForm.InitialWindow.setVisible(false);
            bookFormUtils.manageDataUpdateCopyBooksTable(false);
        };
    }

    public static ActionListener actionButtonCreateCopyBook(){
        return e -> {
            bookForm.InitialWindow.setVisible(false);
            copyBookForm.showFormCopyBookPanel(true);
        };
    }

    public static Map<String, String> initValuesBookForm(){
        Map<String, String> initValues = new LinkedHashMap<>();
        for (int i = 0; i < viewConstants.fieldsFormBook.length ; i++)
        {
            initValues.put(viewConstants.fieldsFormBook[i],viewConstants.blankText);
        }
        return initValues;
    }
}
