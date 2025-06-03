package org.example.wsoc.views.componentsView;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap; // Usamos LinkedHashMap para mantener el orden de los campos
// y para almacenar los JTextField asociados a cada nombre de campo.
import java.util.Map;

public class formTest extends JPanel {
    private Map<String, JTextField> fieldsMap;

    /**
     * Constructor para crear un formulario dinámico.
     * @param fieldNames Un array de Strings con los nombres de los campos deseados.
     */
    public formTest(String[] fieldNames) {
        // Usamos GridBagLayout para un diseño flexible y alineado
        setLayout(new GridBagLayout());
        fieldsMap = new LinkedHashMap<>();
        GridBagConstraints gbc = new GridBagConstraints(); //Tiene varias propiedades que se pueden modificar para controlar el diseño

        // Configuración básica para GridBagConstraints
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.WEST; // Alinear componentes a la izquierda

        int row = 0;
        for (String fieldName : fieldNames) {
            // Etiqueta del campo
            JLabel label = new JLabel(fieldName + ":");
            gbc.gridx = 0; // Columna 0 para las etiquetas
            gbc.gridy = row; // Fila actual
            gbc.weightx = 0; // No se expande horizontalmente
            gbc.fill = GridBagConstraints.NONE; // No rellena espacio
            add(label, gbc);

            // Campo de texto (JTextField)
            JTextField textField = new JTextField(20); // Ancho inicial de 20 columnas
            fieldsMap.put(fieldName, textField); // Guardamos la referencia al JTextField
            gbc.gridx = 1; // Columna 1 para los campos de texto
            gbc.gridy = row; // Fila actual
            gbc.weightx = 1.0; // Se expande horizontalmente para ocupar espacio disponible
            gbc.fill = GridBagConstraints.HORIZONTAL; // Rellena el espacio horizontalmente
            add(textField, gbc);

            row++; // Siguiente fila para el siguiente campo
        }
    }

    /**
     * Obtiene los valores actuales de todos los campos del formulario.
     * @return Un LinkedHashMap donde la clave es el nombre del campo y el valor es el texto ingresado.
     */
    public Map<String, String> getFieldValues() {
        Map<String, String> values = new LinkedHashMap<>();
        for (Map.Entry<String, JTextField> entry : fieldsMap.entrySet()) {
            values.put(entry.getKey(), entry.getValue().getText());
        }
        return values;
    }

    /**
     * Opcional: Establece los valores iniciales de los campos del formulario.
     * Útil para editar registros existentes.
     * @param initialValues Un mapa con los nombres de los campos y sus valores.
     */
    public void setFieldValues(Map<String, String> initialValues) {
        for (Map.Entry<String, String> entry : initialValues.entrySet()) {
            JTextField textField = fieldsMap.get(entry.getKey());
            if (textField != null) {
                textField.setText(entry.getValue());
            }
        }
    }

    // --- Método main para probar la clase ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Formulario Dinámico de Prueba");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null); // Centrar la ventana

            // Definimos los campos que queremos en nuestro formulario
            String[] camposLibro = {"Título", "Autor", "ISBN", "Año Publicación", "Género"};

            // Creamos una instancia de nuestro formulario dinámico
            formTest formPanel = new formTest(camposLibro);

            // Creamos un botón para obtener los valores del formulario
            JButton submitButton = new JButton("Obtener Datos");
            submitButton.addActionListener(e -> {
                Map<String, String> datos = formPanel.getFieldValues();
                System.out.println("Datos del formulario:");
                datos.forEach((campo, valor) -> System.out.println(campo + ": " + valor));

                // Puedes añadir lógica aquí para guardar los datos en una base de datos, etc.
                JOptionPane.showMessageDialog(frame, "Datos obtenidos. Ver consola.");
            });

            // Creamos un panel para los botones
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(submitButton);

            // Añadimos el formulario y el panel de botones al frame
            frame.add(formPanel, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            frame.setVisible(true);

            // Ejemplo de cómo establecer valores iniciales (para una edición, por ejemplo)
            Map<String, String> valoresIniciales = new LinkedHashMap<>();
            valoresIniciales.put("Título", "El Gran Gatsby");
            valoresIniciales.put("Autor", "F. Scott Fitzgerald");
            valoresIniciales.put("Año Publicación", "1925");
            formPanel.setFieldValues(valoresIniciales);
        });
    }
}
