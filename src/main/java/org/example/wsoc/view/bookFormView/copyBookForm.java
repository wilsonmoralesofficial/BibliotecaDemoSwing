package org.example.wsoc.view.bookFormView;
import org.example.wsoc.presenter.bookFormPresenter;
import org.example.wsoc.view.bookFormView.utils.bookFormUtils;
import org.example.wsoc.view.elements.button;
import org.example.wsoc.view.elements.form;
import org.example.wsoc.view.elements.initialFrame;
import org.example.wsoc.view.viewConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class copyBookForm {

    private static final JPanel JPanelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
    private static final initialFrame InitialWindow = new initialFrame(); // Crea un frame
    private static final button copyBookButtonForm =  new button();
    private static final form copyBookForm = new form();

    public static void createInitialViewCopyBook(){
        createPanelButton();
        createFormCopyBook();
    }

    private static void createPanelButton(){
        JPanelButton.add(createSaveButtonPanel(actionButtonSaveCopyBook()));
        JPanelButton.add(createBackButtonPanel(actionButtonBackBook()));
        InitialWindow.add(JPanelButton, BorderLayout.NORTH);
    }

    private static JButton createSaveButtonPanel(ActionListener actioSave){
        return copyBookButtonForm.createRightButtonPanel(viewConstants.textButtonSaveData,actioSave);
    }

    private static JButton createBackButtonPanel(ActionListener actionBack){
        return copyBookButtonForm.createRightButtonPanel(viewConstants.textButtonBack,actionBack);
    }
    private static ActionListener actionButtonSaveCopyBook(){
        return e -> {
            validateSaveDataCopyBook(bookFormPresenter.saveDataCopyBook(copyBookForm.getFieldValues(),bookForm.currentBook));
            InitialWindow.setVisible(false);
            copyBookForm.setFieldValues(initValuesFormCopyBooks());
            removeCopyBooksTable();
            bookFormUtils.initializeTableCopyBook(true);
            bookForm.InitialWindow.setVisible(true);
        };
    }
    private static ActionListener actionButtonBackBook(){
        return e -> {
            InitialWindow.setVisible(false);
            copyBookForm.setFieldValues(initValuesFormCopyBooks());
            removeCopyBooksTable();
            bookFormUtils.initializeTableCopyBook(false);
            bookForm.InitialWindow.setVisible(true);
        };
    }


    public static void createFormCopyBook(){
        copyBookForm.buildDynamicForm(viewConstants.fieldsFormCopyBook,ConfigureGridBagConstraints());
        InitialWindow.add(copyBookForm, BorderLayout.CENTER);
    }

    // Define cómo se colocan los componentes en la cuadricula.
    private static GridBagConstraints ConfigureGridBagConstraints(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }

    public static void showFormCopyBookPanel(boolean show){
        InitialWindow.setVisible(show);
    }

    private static void validateSaveDataCopyBook(boolean saveSuccess){
        if (saveSuccess){
            JOptionPane.showMessageDialog(InitialWindow, viewConstants.textSaveSuccessDataMessageDialog);
        }else{
            JOptionPane.showMessageDialog(InitialWindow, viewConstants.textErrorToSaveCopyBooksMessageDialog);
        }
    }

    public static Map<String, String> initValuesFormCopyBooks(){
        Map<String, String> initValues = new LinkedHashMap<>();
        for (int i = 0 ; i < viewConstants.fieldsFormCopyBook.length ; i++){
            initValues.put(viewConstants.fieldsFormCopyBook[i],viewConstants.blankText);
        }
        return initValues;
    }

    public static void removeCopyBooksTable(){
        bookForm.auxJPanelContainer.remove(bookForm.bookCopyTable.listPanel);
    }

}
