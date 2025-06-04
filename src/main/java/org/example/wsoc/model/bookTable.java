package org.example.wsoc.model;

import java.util.List;

public class bookTable {

    // Nombres de las columnas que se mostrarán en el encabezado de la JTable
    private final String[] columnNames = {"ID", "Nombre", "Edad", "Ciudad"};

    // La lista que contiene los objetos Cliente, que son los datos de la tabla
    public static List<book> data;
    /**
     * Retorna el objeto Cliente en la fila especificada.
     * Útil para obtener el objeto completo cuando una fila es seleccionada en la JTable.
     * @param rowIndex El índice de la fila.
     * @return El objeto Cliente en la fila especificada, o null si el índice es inválido.
     */
    public static book getBookAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            return data.get(rowIndex);
        }
        return null;
    }
}
