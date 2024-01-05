package org.mavenapi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Mainframe extends JFrame{

    private JPanel cards;
    private CardLayout cardLayout;
    public FetchAll fetchAll;


    public Mainframe(){
        //set up the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,600); //size of the screen
        setLocationRelativeTo(null); //center the frame on the screen

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        fetchAll = new FetchAll();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JLabel intro = new JLabel("CLASS ATTENDANCE");
        intro.setFont(new Font("Arial", Font.BOLD, 80));
        intro.setHorizontalAlignment(SwingConstants.CENTER);
        intro.setBorder(new EmptyBorder(20, 20, 50, 20));

        JLabel intro2 = new JLabel("Choose a section:");
        intro2.setFont(new Font("Arial", Font.BOLD, 20));
        intro2.setHorizontalAlignment(SwingConstants.CENTER);
        intro2.setBorder(new EmptyBorder(20, 20, 20, 20));


        JButton secAbtn = new JButton("Section A");
            secAbtn.setPreferredSize(new Dimension(300,30));         

        JButton secBbtn = new JButton("Section B");
            secBbtn.setPreferredSize(new Dimension(300,30));

        JButton secCbtn = new JButton("Section C");
            secCbtn.setPreferredSize(new Dimension(300,50));



        secAbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                openSectionAPanel();
            }
        });

        secBbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                openSectionBPanel();
            }
        });

        secCbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                openSectionCPanel();
            }
        });

        //display the frame
        setVisible(true);

        intro.setAlignmentX(Component.CENTER_ALIGNMENT);
        intro2.setAlignmentX(Component.CENTER_ALIGNMENT);
        secAbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        secBbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        secCbtn.setAlignmentX(Component.CENTER_ALIGNMENT);


        panel.add(intro);
        panel.add(intro2);
        panel.add(secAbtn);
        panel.add(secBbtn);
        panel.add(secCbtn);

        getContentPane().add(panel, BorderLayout.CENTER);
      
        cards.add(panel, "Panel");


        //SECTION A PANEL
        JPanel SectionAPanel = new JPanel();
        JLabel SectionALabel = new JLabel("This is Section A");
        SectionALabel.setHorizontalAlignment(SwingConstants.CENTER);
        SectionAPanel.add(SectionALabel); 

        cards.add(SectionAPanel, "SectionAPanel");

        //SECTION B PANEL
        JPanel SectionBPanel = new JPanel();
        JLabel SectionBLabel = new JLabel("This is Section B");
        SectionBLabel.setHorizontalAlignment(SwingConstants.CENTER);
        SectionBPanel.add(SectionBLabel); 

        cards.add(SectionBPanel, "SectionBPanel");

        //SECTION C PANEL
        JPanel SectionCPanel = new JPanel();
        JLabel SectionCLabel = new JLabel("This is Section C");
        SectionCLabel.setHorizontalAlignment(SwingConstants.CENTER);
        SectionCPanel.add(SectionCLabel); 

        cards.add(SectionCPanel, "SectionCPanel");


        getContentPane().add(cards);

        setVisible(true);
    }










    private void openSectionAPanel(){
        cardLayout.show(cards, "SectionAPanel");

        JPanel SectionAPanel = (JPanel) cards.getComponent(1);

        SectionAPanel.removeAll();

        Student[] students = fetchAll.fetchDataForSection("Section_A");

        //search area
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        JTextArea searchResultsArea = new JTextArea();


        searchButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String searchText = searchField.getText();
                String searchResult = "Search result for: "+searchText;
                searchResultsArea.setText(searchResult);
            }
        });

                     //back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(cards, "Panel");
            }

        });
   

        //search area
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(backButton);
        SectionAPanel.setLayout(new BorderLayout());
        SectionAPanel.add(searchPanel, BorderLayout.NORTH);
        SectionAPanel.add(searchResultsArea, BorderLayout.CENTER);



        searchField.setPreferredSize(new Dimension(200,20));
        searchButton.setPreferredSize(new Dimension(80, 20));
        backButton.setPreferredSize(new Dimension(80, 20));


        String [] columnNames = {"ID", "NAME", "ATTENDANCE"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 2);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);


        for (Student student : students) {
            // Simulating attendance data (replace this with actual attendance logic)
            StringBuilder attendanceStringBuilder = new StringBuilder();
            for (InnerAttendance attendance : student.getAttendance()) {
                attendanceStringBuilder.append("Date: ").append(attendance.getDate())
                        .append(", Present: ").append(attendance.isPresent()).append("\n");
            }
            System.out.println(attendanceStringBuilder);

            Object[] rowData = {student.getID(), student.getName(), attendanceStringBuilder.toString()};
            tableModel.addRow(rowData);
        }


        

        SectionAPanel.add(tableScrollPane, BorderLayout.SOUTH);

        SectionAPanel.revalidate();
        SectionAPanel.repaint();
    }








    private void openSectionBPanel(){
        cardLayout.show(cards, "SectionBPanel");

        JPanel SectionBPanel = (JPanel) cards.getComponent(2);

        SectionBPanel.removeAll();

        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        JTextArea searchResultsArea = new JTextArea();


        searchButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String searchText = searchField.getText();
                String searchResult = "Search result for: "+searchText;
                searchResultsArea.setText(searchResult);
            }
        });

                             //back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(cards, "Panel");
            }
        });

        //search area
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(backButton);

        SectionBPanel.setLayout(new BorderLayout());
        SectionBPanel.add(searchPanel, BorderLayout.NORTH);
        SectionBPanel.add(searchResultsArea, BorderLayout.CENTER);

        searchField.setPreferredSize(new Dimension(200,20));
        searchButton.setPreferredSize(new Dimension(80, 20));
        backButton.setPreferredSize(new Dimension(80, 20));


        String [] columnNames = {"ID", "NAME", "ATTENDANCE"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        SectionBPanel.add(tableScrollPane, BorderLayout.SOUTH);

        SectionBPanel.revalidate();
        SectionBPanel.repaint();

    }










    private void openSectionCPanel(){
        cardLayout.show(cards, "SectionCPanel");

        JPanel SectionCPanel = (JPanel) cards.getComponent(3);

        SectionCPanel.removeAll();

        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        JTextArea searchResultsArea = new JTextArea();


        searchButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String searchText = searchField.getText();
                String searchResult = "Search result for: "+searchText;
                searchResultsArea.setText(searchResult);
            }
        });

                             //back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(cards, "Panel");
            }
        });




        //search area
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(backButton);


        SectionCPanel.setLayout(new BorderLayout());
        SectionCPanel.add(searchPanel, BorderLayout.NORTH);
        SectionCPanel.add(searchResultsArea, BorderLayout.CENTER);



        searchField.setPreferredSize(new Dimension(200,20));
        searchButton.setPreferredSize(new Dimension(80, 20));
        backButton.setPreferredSize(new Dimension(80, 20));



        String [] columnNames = {"ID", "NAME", "ATTENDANCE"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        SectionCPanel.add(tableScrollPane, BorderLayout.SOUTH);

        SectionCPanel.revalidate();
        SectionCPanel.repaint();

    }








    public static void main(String[] args) {
        Mainframe frame = new Mainframe();

        frame.setVisible(true);
    }


}


