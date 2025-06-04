package org.example.wsoc.views.elements;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class form extends JPanel{

    private Map<String, JTextField> fieldsMap = new LinkedHashMap<>();

    public form(){

    }
    public void createDynamicFormPanel(String[] fieldNames){

        setLayout(new GridBagLayout());// Usamos GridBagLayout para un diseño flexible y alineado
        fieldsMap = new LinkedHashMap<>();// Usamos LinkedHashMap para mantener el orden de los campos
        // y para almacenar los JTextField asociados a cada nombre de campo.
        GridBagConstraints gbc = configureGridBagConstraints();// Configuración básica para GridBagConstraints
        buildDynamicForm(fieldNames,gbc);

    }

    public GridBagConstraints configureGridBagConstraints(){
        GridBagConstraints gbc = new GridBagConstraints();//Tiene varias propiedades que se pueden
        // modificar para controlar el diseño
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.WEST; //Controla cómo se alinea un componente dentro de
        // su celda si el componente es más pequeño que el espacio que tiene asignado
        return gbc;
    }
    public void buildDynamicForm(String[] fieldNames,GridBagConstraints gbc){
        int row = 0;
        for (String fieldName : fieldNames) {
            // Crea la etiqueta descriptiva del campo
            createlabelField(fieldName,gbc,row);
            // Crea Campo de texto (JTextField)
            createTextField(fieldName,gbc,row);
            row++; // Siguiente fila para el siguiente campo
        }
    }

    public void createlabelField(String fieldName,GridBagConstraints gbc,int row){
        JLabel label = new JLabel(fieldName + ":");
        gbc.gridx = 0; // Columna 0 para las etiquetas
        gbc.gridy = row; // Fila actual
        gbc.weightx = 0; // No se expande horizontalmente
        gbc.fill = GridBagConstraints.NONE; // No rellena espacio
        add(label, gbc);
    }
    public void createTextField(String fieldName,GridBagConstraints gbc, int row){
        JTextField textField = new JTextField(20); // Ancho inicial de 20 columnas
        fieldsMap.put(fieldName, textField); // Guardamos la referencia al JTextField
        gbc.gridx = 1; // Columna 1 para los campos de texto
        gbc.gridy = row; // Fila actual
        gbc.weightx = 1.0; // Se expande horizontalmente para ocupar espacio disponible
        gbc.fill = GridBagConstraints.HORIZONTAL; // Rellena el espacio horizontalmente
        add(textField, gbc);
    }

    public Map<String, String> getFieldValues() {
        Map<String, String> values = new LinkedHashMap<>();
        for (Map.Entry<String, JTextField> entry : fieldsMap.entrySet()) {
            values.put(entry.getKey(), entry.getValue().getText());
        }
        return values;
    }

}
