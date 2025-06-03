package org.example.wsoc.models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class bookTableModel extends AbstractTableModel{
    private final String[] columnNames = {"id", "titulo", "autor", "isbn", "anioPublicacion"};
    private List<book> bookList;

    public bookTableModel() {
        this.bookList = new ArrayList<book>();
    }

    public void addBook(book Book){
        bookList.add(Book);
        fireTableRowsInserted(bookList.size() - 1, bookList.size() - 1);
    }

    public int getRowCount(){
        return bookList.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        book Book = bookList.get(row);
        switch (col) {
            case 0: return Book.getId();
            case 1: return Book.getTitulo();
            case 2: return Book.getAutor();
            case 3: return Book.getISBN();
            case 4: return Book.getAnioPublicacion();
            default: return null;
        }
    }


    public void setValueAt(Object value, int row, int col) {
        book Book = bookList    .get(row);

        try {
            switch (col) {
                case 1:
                    Book.setTitulo((String) value);
                    fireTableCellUpdated(row, col);
                case 2:
                    Book.setAutor((String) value);
                    fireTableCellUpdated(row, col);
                case 3:
                    Book.setISBN((String) value);
                    fireTableCellUpdated(row, col);
                case 4:
                    Book.setAnioPublicacion((String) value);
                    fireTableCellUpdated(row, col);
            }
        }catch (ClassCastException e)
        {
            System.err.println(e);
        }
    }

}
