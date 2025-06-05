package org.example.wsoc.view.bookFormView;

import org.example.wsoc.model.book;
import org.example.wsoc.presenter.bookFormPresenter;
import org.example.wsoc.presenter.bookListPresenter;
import org.example.wsoc.view.bookListView.bookList;
import org.example.wsoc.view.elements.button;
import org.example.wsoc.view.elements.form;
import org.example.wsoc.view.elements.initialFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class bookForm {

    private static JPanel JPanelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));

    private static JPanel JPanelForm = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private static initialFrame InitialWindow = new initialFrame(); // Crea un frame

    private static form booksForm = new form();

    private static button bookButtonForm =  new button();

    private  static bookFormPresenter presenterForm = new bookFormPresenter();

    private static book currentBook = new book();

    private static boolean editMode = false;
    public bookForm(){
        createInitialView();
    }
    private static void createInitialView(){

        String[] columns = {"Titulo","Autor","ISBN","Año de Publicación"};
        String[][] datos = {{}};
        JPanelButton.add(createSaveButtonPanel("Guardar Datos",actionButtonSaveBook()));
        JPanelButton.add(createCancelButtonPanel("Cancelar",actionButtonCancelBook()));
        InitialWindow.add(JPanelButton,BorderLayout.NORTH);
        createFormTable();
    }

    private static JButton createSaveButtonPanel(String textButton, ActionListener actioSave){
        JButton buttonCreate = bookButtonForm.createRightButtonPanel(textButton,actioSave);

        return buttonCreate;
    }

    private static JButton createCancelButtonPanel(String textButton, ActionListener actionCancel){
        JButton buttonEdit = bookButtonForm.createRightButtonPanel(textButton,actionCancel);
        return buttonEdit;
    }

    private static ActionListener actionButtonSaveBook(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookFormPresenter.saveDataBook(currentBook,booksForm.getFieldValues(),editMode);
                showFormBookPanel(false);
                bookList.removeBooksTable();
                bookList.createInitialView();
                bookList.showBookListPanel(true);
            }
        };
    }

    private static ActionListener actionButtonCancelBook(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InitialWindow.setVisible(false);
                bookList.showBookListPanel(true);
            }
        };
    }

    private static void createFormTable(){

        // Definimos los campos que queremos en nuestro formulario
        String[] camposLibro = {"Título", "Autor", "ISBN", "Año Publicación"};
        GridBagConstraints gbc = new GridBagConstraints(); //Tiene varias propiedades que se pueden modificar para controlar el diseño

        // Configuración básica para GridBagConstraints
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.WEST;
        // Creamos una instancia de nuestro formulario dinámico
        booksForm.buildDynamicForm(camposLibro,gbc);

        // Añadimos el formulario y el panel de botones al frame
        InitialWindow.add(booksForm, BorderLayout.CENTER);

    }

    public static void showFormBookPanel(boolean show){
        InitialWindow.setVisible(show);

    }

    public static void validateEditionMode(boolean editionMode,book dataBook){
        if (editionMode){
            editMode = editionMode;
            currentBook = dataBook;
            booksForm.setFieldValues(getFormValues(dataBook));
        }else
        {
            editMode = editionMode;
            booksForm.setFieldValues(initValuesForm());
        }
    }

    public static Map<String, String> getFormValues(book dataBook){
        return presenterForm.mapDataEditForm(dataBook);
    }

    public static Map<String, String> initValuesForm(){
        Map<String, String> initValues = new LinkedHashMap<>();
        initValues.put("Título", "");
        initValues.put("Autor", "");
        initValues.put("ISBN", "");
        initValues.put("Año Publicación", "");
        return initValues;
    }


}
