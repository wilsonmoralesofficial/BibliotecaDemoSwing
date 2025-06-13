package org.example.wsoc.view.bookFormView;
import org.example.wsoc.model.book;
import org.example.wsoc.presenter.bookFormPresenter;
import org.example.wsoc.view.bookFormView.utils.bookFormUtils;
import org.example.wsoc.view.viewConstants;
import org.example.wsoc.view.elements.button;
import org.example.wsoc.view.elements.form;
import org.example.wsoc.view.elements.initialFrame;
import org.example.wsoc.view.elements.table;

import javax.swing.*;
import java.awt.*;

public class bookForm {

    //Contenedor de los botones
    public static JPanel JPanelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));

    //Contenedor de los Jpanes de formulario y la tabla de ejemplares de cada libro
    public static  JPanel auxJPanelContainer = new JPanel();

    //Contenedor de la tabla
    public static JPanel jPanelTable = new JPanel();

    //Contenedor del formulario
    private static final JPanel JPanelForm = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 2));

    // Crea un frame
    public static initialFrame InitialWindow = new initialFrame();

    //Instancia de la clase Form que maneja la logica de los formularios
    public static form booksForm = new form();

    //Instancia de la clase button que maneja la logica de la creación de botones
    public static button bookButtonForm =  new button();

    //Instancia de la clase bookFormPresenter que maneja la funcionalidad de la aplicación.
    // Solicitudes a bases de datos etc.
    public  static bookFormPresenter presenterForm = new bookFormPresenter();

    // Instancia del modelo book que tendra los datos de libro que se está mostrando actualmente en el formulario
    public static book currentBook = new book();

    //Instancia de la clase table que maneja la logica para la creación de tablas en el Frame.
    public static table bookCopyTable = new table();

    public static void createInitialView(){
        createPanelButton();
        createFormBook();
    }

    private static void createPanelButton(){
        JPanelButton.add(bookFormUtils.createSaveButtonPanel(viewConstants.textButtonSaveData,bookFormUtils.actionButtonSaveBook()));
        JPanelButton.add(bookFormUtils.createBackButtonPanel(viewConstants.textButtonBack,bookFormUtils.actionButtonBackBook()));
        InitialWindow.add(JPanelButton,BorderLayout.NORTH);
    }

    private static void createFormBook(){
        GridBagConstraints gbc = new GridBagConstraints(); //Tiene varias propiedades que se pueden modificar para controlar el diseño.
        // Configuración básica para GridBagConstraints
        gbc.insets = new Insets(4, 4, 4, 4); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.WEST;
        booksForm.buildDynamicForm(viewConstants.fieldsFormBook,gbc);// Contruimos el formulario desde la instancia de la clase
        addFormToPanelForm();
    }

    private static void addFormToPanelForm(){
        JPanelForm.add(booksForm,BorderLayout.CENTER);
        JPanelForm.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        auxJPanelContainer.setLayout(new BoxLayout(auxJPanelContainer, BoxLayout.Y_AXIS));
        auxJPanelContainer.add(JPanelForm);
        auxJPanelContainer.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public static void validateEditionMode(boolean editionMode,book dataBook){
        if (!editionMode) {
            booksForm.setFieldValues(bookFormUtils.initValuesBookForm());
        } else {
            bookFormUtils.editMode = true;
            initValuesEditBookForm(dataBook);
        }
        InitialWindow.add(auxJPanelContainer);
    }

    private static void initValuesEditBookForm(book dataBook){
        currentBook = dataBook;
        booksForm.setFieldValues(bookForm.presenterForm.mapDataEditForm(dataBook));
        JPanelButton.add(bookFormUtils.createCopyButtonPanel(viewConstants.textButtonCreateCopyBook,bookFormUtils.actionButtonCreateCopyBook()));
        bookFormUtils.initializeTableCopyBook(true);
    }

    public static void validateSaveDataBook(boolean saveSuccess){
        if (saveSuccess){
            JOptionPane.showMessageDialog(InitialWindow, viewConstants.textSaveSuccessDataMessageDialog);
        }else{
            JOptionPane.showMessageDialog(InitialWindow, viewConstants.textErrorToSaveDataMessageDialog);
        }
    }
}
