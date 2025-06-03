package org.example.wsoc.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class bookList extends JFrame{
    private JTable bookListTable;
    private JScrollPane bookScrollPane;
    private JPanel bookListPanel;

    public bookList(){
        setTitle("Listado de Libros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        String[] col = {"Id","Titulo","Autor","ISBN","Año de Publicación"};
        Object[][] books = {
                {"1","PADRE RICO PADRE POBRE","Rober Kiyosaky","3243423","1999"}
        };

        DefaultTableModel bookModelTableDefault = new DefaultTableModel(books,col);

        bookListTable = new JTable(bookModelTableDefault);
        bookListTable.setFillsViewportHeight(true);
        bookListTable.setRowHeight(25);
        bookListTable.getTableHeader().setReorderingAllowed(false);

        bookScrollPane = new JScrollPane(bookListTable);

        setLayout(new BorderLayout());
        add(bookScrollPane, BorderLayout.NORTH);
        add(bookScrollPane, BorderLayout.CENTER);

    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            new bookList().setVisible(true);
        });
    }
}
