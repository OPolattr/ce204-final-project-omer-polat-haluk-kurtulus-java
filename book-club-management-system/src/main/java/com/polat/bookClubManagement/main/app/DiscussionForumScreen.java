package com.polat.bookClubManagement.main.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.polat.bookClubManagement.main.lib.models.TopicWrapper;

public class DiscussionForumScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JTextField topicIdField;
    private JTextField titleField;
    private JTextArea contentArea;
    private JTextField authorIdField;
    private DefaultListModel<TopicWrapper> topicListModel;
    private JList<TopicWrapper> topicList;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DiscussionForumScreen frame = new DiscussionForumScreen();
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
    public DiscussionForumScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        // Topic List Panel
        topicListModel = new DefaultListModel<>();
        topicList = new JList<>(topicListModel);
        topicList.setPreferredSize(new Dimension(200, 0));
        contentPane.add(new JScrollPane(topicList), BorderLayout.WEST);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        contentPane.add(formPanel, BorderLayout.CENTER);

        // Add form fields
        addFormField(formPanel, "Topic ID:", topicIdField = new JTextField());
        addFormField(formPanel, "Title:", titleField = new JTextField());
        addFormField(formPanel, "Content:", new JScrollPane(contentArea = new JTextArea(5, 20)));
        addFormField(formPanel, "Author ID:", authorIdField = new JTextField());

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton addButton = new JButton("Add Topic");
        addButton.addActionListener(new AddButtonListener());
        buttonPanel.add(addButton);

        JButton updateButton = new JButton("Update Topic");
        updateButton.addActionListener(new UpdateButtonListener());
        buttonPanel.add(updateButton);

        JButton deleteButton = new JButton("Delete Topic");
        deleteButton.addActionListener(new DeleteButtonListener());
        buttonPanel.add(deleteButton);
    }

    private void addFormField(JPanel panel, String labelText, JComponent field) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout(5, 5));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(100, 30));
        fieldPanel.add(label, BorderLayout.WEST);
        fieldPanel.add(field, BorderLayout.CENTER);
        panel.add(fieldPanel);
    }

    // Listener for Add Button
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String topicId = topicIdField.getText();
            String title = titleField.getText();
            String content = contentArea.getText();
            String authorId = authorIdField.getText();

            TopicWrapper newTopic = new TopicWrapper(topicId, title, content, authorId, new java.sql.Date(System.currentTimeMillis()), "1"); // DiscussionForumId is set to "1" as a placeholder
            topicListModel.addElement(newTopic);

            clearForm();
        }
    }

    // Listener for Update Button
    private class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = topicList.getSelectedIndex();
            if (selectedIndex != -1) {
                TopicWrapper selectedTopic = topicListModel.getElementAt(selectedIndex);
                selectedTopic.setTopicId(topicIdField.getText());
                selectedTopic.setTitle(titleField.getText());
                selectedTopic.setContent(contentArea.getText());
                selectedTopic.setAuthorId(authorIdField.getText());
                selectedTopic.setCreationDate(new java.sql.Date(System.currentTimeMillis()));

                topicList.repaint();
                clearForm();
            }
        }
    }

    // Listener for Delete Button
    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = topicList.getSelectedIndex();
            if (selectedIndex != -1) {
                topicListModel.remove(selectedIndex);
                clearForm();
            }
        }
    }

    private void clearForm() {
        topicIdField.setText("");
        titleField.setText("");
        contentArea.setText("");
        authorIdField.setText("");
    }
}
