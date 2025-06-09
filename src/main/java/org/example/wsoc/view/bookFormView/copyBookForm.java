package org.example.wsoc.view.bookFormView;

import org.example.wsoc.model.book;
import org.example.wsoc.model.bookCopy;
import org.example.wsoc.presenter.bookFormPresenter;
import org.example.wsoc.view.elements.button;
import org.example.wsoc.view.elements.form;
import org.example.wsoc.view.elements.initialFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class copyBookForm {

    private static JPanel JPanelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
    private static initialFrame InitialWindow = new initialFrame(); // Crea un frame
    private static button copyBookButtonForm =  new button();
    private static form copyBookForm = new form();





    public copyBookForm(){
        createInitialViewCopyBook();
    }

    public static void createInitialViewCopyBook(){

        JPanelButton.add(createSaveButtonPanel("Guardar Datos",actionButtonSaveCopyBook()));
        JPanelButton.add(createCancelButtonPanel("Cancelar",actionButtonCancelBook()));
        InitialWindow.add(JPanelButton, BorderLayout.NORTH);
        createFormCopyBook();
    }

    private static JButton createSaveButtonPanel(String textButton, ActionListener actioSave){
        JButton buttonCreate = copyBookButtonForm.createRightButtonPanel(textButton,actioSave);
        return buttonCreate;
    }

    private static JButton createCancelButtonPanel(String textButton, ActionListener actionCancel){
        JButton buttonEdit = copyBookButtonForm.createRightButtonPanel(textButton,actionCancel);
        return buttonEdit;
    }

    private static ActionListener actionButtonSaveCopyBook(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateSaveDataCopyBook(bookFormPresenter.saveDataCopyBook(copyBookForm.getFieldValues(),bookForm.currentBook));
                InitialWindow.setVisible(false);
                bookForm.showFormBookPanel(true);
                copyBookForm.setFieldValues(initValuesFormCopyBooks());
                bookForm.removeCopyBooksTable();
                bookForm.initializeTableCopyBook();
            }
        };
    }

    private static ActionListener actionButtonCancelBook(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InitialWindow.setVisible(false);
                bookForm.showFormBookPanel(true);
                copyBookForm.setFieldValues(initValuesFormCopyBooks());
                bookForm.auxJPanelContainer.remove(1);
                bookForm.initializeTableCopyBook();
            }
        };
    }

    public static void showFormCopyBookPanel(boolean show){
        InitialWindow.setVisible(show);
    }

    public static void createFormCopyBook(){
        // Definimos los campos que queremos en nuestro formulario
        String[] camposLibro = {"Numero de Inventario", "Estado Fisico", "Estanteria", "Disponible"};
        GridBagConstraints gbc = new GridBagConstraints(); //Tiene varias propiedades que se pueden modificar para controlar el diseño.
        // Configuración básica para GridBagConstraints
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.WEST;
        // Creamos una instancia de nuestro formulario dinámico
        copyBookForm.buildDynamicForm(camposLibro,gbc);
        // Añadimos el formulario y el panel de botones al frame
        InitialWindow.add(copyBookForm, BorderLayout.CENTER);
    }

    private static void validateSaveDataCopyBook(boolean saveSuccess){
        if (saveSuccess){
            JOptionPane.showMessageDialog(InitialWindow, "Datos guardados con exito");
        }else
        {
            JOptionPane.showMessageDialog(InitialWindow, "Ocurrio un error al almacenar los datos");
        }
    }

    public static Map<String, String> initValuesFormCopyBooks(){
        Map<String, String> initValues = new LinkedHashMap<>();
        initValues.put("Numero de Inventario", "");
        initValues.put("Estado Fisico", "");
        initValues.put("Estanteria", "");
        initValues.put("Disponible", "");
        return initValues;
    }
}
