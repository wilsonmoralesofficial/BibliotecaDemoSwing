package org.example.wsoc.view.bookFormView;

import org.example.wsoc.model.book;
import org.example.wsoc.model.bookCopy;
import org.example.wsoc.presenter.bookFormPresenter;
import org.example.wsoc.view.bookListView.bookList;
import org.example.wsoc.view.elements.button;
import org.example.wsoc.view.elements.form;
import org.example.wsoc.view.elements.initialFrame;
import org.example.wsoc.view.elements.table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class bookForm {

    public static JPanel JPanelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
    //Contenedor de los botones

    public static  JPanel auxJPanelContainer = new JPanel();
    //Contenedor de los Jpanes de formulario y la tabla de ejemplares de cada libro

    private static JPanel jPanelTable = new JPanel();
    //Contenedor de la tabla

    private static JPanel JPanelForm = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 2));
    //Contenedor del formulario

    private static initialFrame InitialWindow = new initialFrame();
    // Crea un frame
    private static form booksForm = new form();
    //Instancia de la clase Form que maneja la logica de los formularios

    private static button bookButtonForm =  new button();
    //Instancia de la clase button que maneja la logica de la creación de botones

    private  static bookFormPresenter presenterForm = new bookFormPresenter();
    //Instancia de la clase bookFormPresenter que maneja la funcionalidad de la aplicación.
    // Solicitudes a bases de datos etc.

    public static book currentBook = new book();
    // Instancia del modelo book que tendra los datos de libro que se está mostrando actualmente en el formulario
    private static table bookCopyTable = new table();
    //Instancia de la clase table que maneja la logica para la creación de tablas en el Frame.
    public static String[][] dataJTableCopyBook = {};
    //Datos de la tabla de ejemplares
    private static List<bookCopy> currentListCopyBook = new ArrayList<>();
    //Listado de ejemplares del libro actual
    private static boolean editMode = false;
    // Variable que maneja si se está en modo edición o en modo de creación
    // "editMode = true -- Modo Edición" "editMode = false -- Modo Creación"


    public bookForm(){
        createInitialView();
    }

    private static void createInitialView(){
        JPanelButton.add(createSaveButtonPanel("Guardar Datos",actionButtonSaveBook()));
        JPanelButton.add(createCancelButtonPanel("Volver",actionButtonCancelBook()));
        InitialWindow.add(JPanelButton,BorderLayout.NORTH);
        createFormBook();
    }

    private static JButton createSaveButtonPanel(String textButton, ActionListener actioSave){
        JButton buttonCreate = bookButtonForm.createRightButtonPanel(textButton,actioSave);
        return buttonCreate;
    }

    private static JButton createCancelButtonPanel(String textButton, ActionListener actionCancel){
        JButton buttonEdit = bookButtonForm.createRightButtonPanel(textButton,actionCancel);
        return buttonEdit;
    }

    public static JButton createCopyButtonPanel(String textButton, ActionListener actionCancel){
        JButton buttonEdit = bookButtonForm.createRightButtonPanel(textButton,actionCancel);
        return buttonEdit;
    }

    private static ActionListener actionButtonSaveBook(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editMode)JPanelButton.remove(2);
                validateSaveDataBook(bookFormPresenter.saveDataBook(currentBook,booksForm.getFieldValues(),editMode));
                showFormBookPanel(false);
                manageDataUpdateCopyBooksTable();
            }
        };
    }

    private static void validateSaveDataBook(boolean saveSuccess){
        if (saveSuccess){
            JOptionPane.showMessageDialog(InitialWindow, "Datos guardados con exito");
        }else
        {
            JOptionPane.showMessageDialog(InitialWindow, "Ocurrio un error al almacenar los datos");
        }
    }

    private static ActionListener actionButtonCancelBook(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editMode)JPanelButton.remove(2);
                InitialWindow.setVisible(false);
                manageDataUpdateCopyBooksTable();
            }
        };
    }

    private static ActionListener actionButtonCreateCopyBook(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFormBookPanel(false);
                copyBookForm.showFormCopyBookPanel(true);
            }
        };
    }
    private static void createFormBook(){

        // Definimos los campos que queremos en nuestro formulario
        String[] camposLibro = {"Título", "Autor", "ISBN", "Año Publicación"};
        GridBagConstraints gbc = new GridBagConstraints(); //Tiene varias propiedades que se pueden modificar para controlar el diseño.
        // Configuración básica para GridBagConstraints
        gbc.insets = new Insets(4, 4, 4, 4); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.WEST;
        // Creamos una instancia de nuestro formulario dinámico
        booksForm.buildDynamicForm(camposLibro,gbc);
        // Añadimos el formulario y el panel de botones al frame

        addFormToPanelForm();
    }

    private static void addFormToPanelForm(){
        JPanelForm.add(booksForm,BorderLayout.CENTER);
        JPanelForm.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        auxJPanelContainer.setLayout(new BoxLayout(auxJPanelContainer, BoxLayout.Y_AXIS));
        auxJPanelContainer.add(JPanelForm);
        auxJPanelContainer.add(Box.createRigidArea(new Dimension(0, 10)));
    }
    public static void showFormBookPanel(boolean show){
        InitialWindow.setVisible(show);
    }

    public static void validateEditionMode(boolean editionMode,book dataBook){
        if (editionMode){
            editMode = true;
            currentBook = dataBook;
            booksForm.setFieldValues(getFormValues(dataBook));
            JPanelButton.add(createCopyButtonPanel("Agregar Ejemplar",actionButtonCreateCopyBook()));
            initializeTableCopyBook();
        }else
        {
            editMode = false;
            booksForm.setFieldValues(initValuesBookForm());
        }
        InitialWindow.add(auxJPanelContainer);
    }

    public static Map<String, String> getFormValues(book dataBook){
        return presenterForm.mapDataEditForm(dataBook);
    }

    public static Map<String, String> initValuesBookForm(){
        Map<String, String> initValues = new LinkedHashMap<>();
        initValues.put("Título", "");
        initValues.put("Autor", "");
        initValues.put("ISBN", "");
        initValues.put("Año Publicación", "");
        return initValues;
    }

    public static void initializeTableCopyBook(){
        String[] colBooksCopy = {"Id ","Numero de Inventario","Estado Fisico","Ubicación Estanteria","Disponible"};
        dataJTableCopyBook = getCopyBooksAvailable();
        createBookCopyTable(dataJTableCopyBook,colBooksCopy);
    }

    private static String[][] getCopyBooksAvailable(){
        currentListCopyBook = presenterForm.getCopyBooks(currentBook);
        return validateListCopyBook(currentListCopyBook);
    }

    private static void createBookCopyTable(String[][]data,String[]columns){
        jPanelTable = new JPanel();
        jPanelTable = bookCopyTable.addJpanelTable(data,columns);
        auxJPanelContainer.add(jPanelTable);
    }

    private static String[][] validateListCopyBook(List<bookCopy> currentCopyBooks){

        bookCopy bookData;
        List<String[]> copyBooksDataString = new ArrayList<>();
        String[][] copyBooksString = {};
        if (currentCopyBooks == null)
        {
            JOptionPane.showMessageDialog(InitialWindow, "Ocurrio un error al realizar la consulta de datos, consulte a IT");
        }else
        {
            for (int i = 0; i < currentCopyBooks.size(); i++){
                bookData = currentCopyBooks.get(i);
                String[] stringBookData =
                        {Integer.toString(bookData.Id),
                                bookData.NumeroInventario,bookData.EstadoFisico,
                                bookData.UbicacionEstanteria,bookData.Disponible};
                copyBooksDataString.add(stringBookData);
            }

            copyBooksString = copyBooksDataString.toArray(new String[copyBooksDataString.size()][]);
        }
        return copyBooksString;
    }

    public static void removeCopyBooksTable(){
        auxJPanelContainer.remove(bookCopyTable.listPanel);
    }

    public static void manageDataUpdateCopyBooksTable(){
        bookList.removeBooksTable();
        bookList.createInitialView();
        bookList.showBookListPanel(true);
        removeCopyBooksTable();
    }

}
