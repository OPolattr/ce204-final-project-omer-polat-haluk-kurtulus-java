package com.polat.bookClubManagement.main.app;

import com.polat.bookClubManagement.main.lib.database.ReadingEntryDAL;
import com.polat.bookClubManagement.main.lib.models.ReadingEntryWrapper;
import com.polat.bookClubManagement.main.lib.wrappers.ReadingScheduleWrapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class ReadingScheduleScreen extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable readingEntriesTable;
    private JButton addButton, updateButton, deleteButton, backButton;
    private String username;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReadingScheduleScreen frame = new ReadingScheduleScreen("testUser");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ReadingScheduleScreen(String username) {
        this.username = username;
        setTitle("Reading Schedule Management - " + username);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 610, 454);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        initializeComponents();
        loadReadingEntries();
    }

    private void initializeComponents() {
        contentPane.setLayout(null);
        // Table setup
        readingEntriesTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(readingEntriesTable);
        scrollPane.setBounds(10, 42, 576, 227);
        contentPane.add(scrollPane);

        // Buttons panel setup
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(10, 317, 566, 58);
        addButton = new JButton("Add");
        addButton.setBounds(49, 10, 92, 43);
        updateButton = new JButton("Update");
        updateButton.setBounds(173, 10, 92, 45);
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(293, 10, 92, 43);
        backButton = new JButton("Back");
        backButton.setBounds(422, 12, 92, 41);
        buttonsPanel.setLayout(null);

        buttonsPanel.add(addButton);
        buttonsPanel.add(updateButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(backButton);

        contentPane.add(buttonsPanel);
        
        lblNewLabel = new JLabel("Reading Schedule");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel.setBounds(223, 10, 239, 22);
        contentPane.add(lblNewLabel);
        
        ImageIcon icon2 = new ImageIcon(ReadingScheduleScreen.class.getResource("image.jpg"));
		JLabel label_loginbackground = new JLabel("");
		label_loginbackground.setBounds(0, 0, 826, 508);
		Image image2 = icon2.getImage().getScaledInstance(label_loginbackground.getWidth(),
		label_loginbackground.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iccon = new ImageIcon(image2);
		label_loginbackground.setIcon(iccon);
		contentPane.add(label_loginbackground);

        // Action Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openReadingEntryDialog(null);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = readingEntriesTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String bookTitle = readingEntriesTable.getValueAt(selectedRow, 0).toString();
                    ReadingEntryWrapper readingEntry = new ReadingEntryDAL().getReadingEntry(bookTitle);
                    openReadingEntryDialog(readingEntry);
                } else {
                    JOptionPane.showMessageDialog(ReadingScheduleScreen.this, "Please select an entry to update.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = readingEntriesTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String bookTitle = readingEntriesTable.getValueAt(selectedRow, 0).toString();
                    int response = JOptionPane.showConfirmDialog(ReadingScheduleScreen.this, "Are you sure you want to delete this entry?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        new ReadingEntryDAL().deleteReadingEntry(bookTitle);
                        loadReadingEntries();
                    }
                } else {
                    JOptionPane.showMessageDialog(ReadingScheduleScreen.this, "Please select an entry to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage(username).setVisible(true);
                dispose();
            }
        });
    }

    private void loadReadingEntries() {
        List<ReadingEntryWrapper> readingEntries = new ReadingEntryDAL().getAllReadingEntries();
        String[] columnNames = {"Book Title", "Start Date", "End Date", "Progress", "Member ID"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (ReadingEntryWrapper entry : readingEntries) {
            if (entry.getReadingScheduleMemberId().equals(username)) {
                Object[] row = {
                        entry.getBookTitle(),
                        entry.getStartDate().toString(),
                        entry.getEndDate().toString(),
                        entry.getProgress(),
                        entry.getReadingScheduleMemberId()
                };
                model.addRow(row);
            }
        }

        readingEntriesTable.setModel(model);
    }

    private void openReadingEntryDialog(ReadingEntryWrapper readingEntry) {
        JDialog dialog = new JDialog(this, "Reading Entry", true);
        dialog.setSize(400, 300);
        dialog.getContentPane().setLayout(new BorderLayout());

        JPanel fieldsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JLabel bookTitleLabel = new JLabel("Book Title:");
        JTextField bookTitleField = new JTextField();
        JLabel startDateLabel = new JLabel("Start Date:");
        JTextField startDateField = new JTextField();
        JLabel endDateLabel = new JLabel("End Date:");
        JTextField endDateField = new JTextField();
        JLabel progressLabel = new JLabel("Progress:");
        JTextField progressField = new JTextField();

        fieldsPanel.add(bookTitleLabel);
        fieldsPanel.add(bookTitleField);
        fieldsPanel.add(startDateLabel);
        fieldsPanel.add(startDateField);
        fieldsPanel.add(endDateLabel);
        fieldsPanel.add(endDateField);
        fieldsPanel.add(progressLabel);
        fieldsPanel.add(progressField);

        if (readingEntry != null) {
            bookTitleField.setText(readingEntry.getBookTitle());
            startDateField.setText(readingEntry.getStartDate().toString());
            endDateField.setText(readingEntry.getEndDate().toString());
            progressField.setText(readingEntry.getProgress());
            bookTitleField.setEditable(false);  // Book title should not be editable during update
        }

        dialog.getContentPane().add(fieldsPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(saveButton);
        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookTitle = bookTitleField.getText();
                Date startDate = Date.valueOf(startDateField.getText());
                Date endDate = Date.valueOf(endDateField.getText());
                String progress = progressField.getText();
                String memberId = username;

                ReadingEntryWrapper newReadingEntry = new ReadingEntryWrapper(bookTitle, startDate, endDate, progress, memberId);
                ReadingEntryDAL dal = new ReadingEntryDAL();

                if (readingEntry == null) {
                    dal.addReadingEntry(newReadingEntry);
                } else {
                    dal.updateReadingEntry(newReadingEntry);
                }

                loadReadingEntries();
                dialog.dispose();
            }
        });

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
