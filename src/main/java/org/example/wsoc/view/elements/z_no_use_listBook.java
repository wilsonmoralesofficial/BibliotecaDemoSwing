package org.example.wsoc.view.elements;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class z_no_use_listBook extends JFrame{
    public JScrollPane bookListScrollPane;
    public JTable bookListTable;
    public JPanel bookListPanel;

    public JPanel createTablePanel(String[][] Data ,String[] Columns){
        bookListPanel = new JPanel(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel(Data,Columns);
        bookListTable = new JTable(tableModel);
        bookListScrollPane = new JScrollPane(bookListTable);
        bookListPanel.add(bookListScrollPane);
        return bookListPanel;
    }

    public void addJpanel(String[][] Data ,String[] Columns){
        bookListPanel = createTablePanel(Data ,Columns);
        add(bookListPanel,BorderLayout.CENTER);
    }
}
