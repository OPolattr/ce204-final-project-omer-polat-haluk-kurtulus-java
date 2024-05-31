package com.polat.bookClubManagement.main.app;

import com.polat.bookClubManagement.main.lib.database.MemberDAL;
import com.polat.bookClubManagement.main.lib.models.MemberWrapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MemberManagementScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private MemberDAL memberDAL;
    private JTable memberTable;
    private DefaultTableModel tableModel;
    private String username;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        String username = JOptionPane.showInputDialog(null, "Enter your username:");
        if (username != null && !username.trim().isEmpty()) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        MemberManagementScreen frame = new MemberManagementScreen(username);
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Username is required to proceed.");
        }
    }

    /**
     * Create the frame.
     */
    public MemberManagementScreen(String username) {
        this.username = username;
        memberDAL = new MemberDAL();
        initComponents();
        loadMembers();
    }

    private void initComponents() {
        setTitle("Book Club Member Management - Logged in as " + username);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Create a table model and set a Column Identifiers to this model
        tableModel = new DefaultTableModel();
        String[] columns = {"Member ID", "Name", "Email", "Reading Preferences"};
        tableModel.setColumnIdentifiers(columns);
        contentPane.setLayout(null);

        // Create a JTable with the table model
        memberTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(memberTable);
        scrollPane.setBounds(5, 66, 771, 367);
        contentPane.add(scrollPane);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(73, 155, 190));
        buttonPanel.setBounds(0, 464, 776, 77);

        JButton addButton = new JButton("Add Member");
        addButton.setBounds(37, 5, 116, 50);
        JButton updateButton = new JButton("Update Member");
        updateButton.setBounds(175, 5, 116, 50);
        JButton deleteButton = new JButton("Delete Member");
        deleteButton.setBounds(320, 5, 116, 50);
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(464, 5, 116, 50);
        buttonPanel.setLayout(null);

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        contentPane.add(buttonPanel);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new HomePage(username).setVisible(true);
                dispose();
        	}
        });
        btnBack.setBounds(613, 5, 116, 50);
        buttonPanel.add(btnBack);
        
        JLabel lblNewLabel = new JLabel("Member Management");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel.setBounds(266, 29, 308, 26);
        contentPane.add(lblNewLabel);
        
        ImageIcon icon2 = new ImageIcon(MemberManagementScreen.class.getResource("image.jpg"));
		JLabel label_loginbackground = new JLabel("");
		label_loginbackground.setBounds(0, 0, 826, 553);
		Image image2 = icon2.getImage().getScaledInstance(label_loginbackground.getWidth(),
		label_loginbackground.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iccon = new ImageIcon(image2);
		label_loginbackground.setIcon(iccon);
		contentPane.add(label_loginbackground);

        // Add action listeners to the buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMemberDialog();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMemberDialog();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMember();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMembers();
            }
        });
    }

    private void loadMembers() {
        tableModel.setRowCount(0);
        List<MemberWrapper> members = memberDAL.getAllMembers();
        for (MemberWrapper member : members) {
            tableModel.addRow(new Object[]{member.getMemberId(), member.getName(), member.getEmail(), member.getReadingPreferences()});
        }
    }

    private void addMemberDialog() {
        JTextField memberIdField = new JTextField(15);
        JTextField nameField = new JTextField(15);
        JTextField emailField = new JTextField(15);
        JTextField readingPreferencesField = new JTextField(15);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Member ID:"));
        panel.add(memberIdField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Reading Preferences:"));
        panel.add(readingPreferencesField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Member", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            MemberWrapper member = new MemberWrapper(memberIdField.getText(), nameField.getText(), emailField.getText(), readingPreferencesField.getText());
            if (memberDAL.addMember(member)) {
                loadMembers();
                JOptionPane.showMessageDialog(this, "Member added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add member.");
            }
        }
    }

    private void updateMemberDialog() {
        int selectedRow = memberTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a member to update.");
            return;
        }

        String memberId = (String) tableModel.getValueAt(selectedRow, 0);
        MemberWrapper member = memberDAL.getMember(memberId);

        if (member == null) {
            JOptionPane.showMessageDialog(this, "Failed to retrieve member details.");
            return;
        }

        JTextField nameField = new JTextField(member.getName(), 15);
        JTextField emailField = new JTextField(member.getEmail(), 15);
        JTextField readingPreferencesField = new JTextField(member.getReadingPreferences(), 15);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Reading Preferences:"));
        panel.add(readingPreferencesField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Member", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            member.setName(nameField.getText());
            member.setEmail(emailField.getText());
            member.setReadingPreferences(readingPreferencesField.getText());

            if (memberDAL.updateMember(member)) {
                loadMembers();
                JOptionPane.showMessageDialog(this, "Member updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update member.");
            }
        }
    }

    private void deleteMember() {
        int selectedRow = memberTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a member to delete.");
            return;
        }

        String memberId = (String) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this member?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (memberDAL.deleteMember(memberId)) {
                loadMembers();
                JOptionPane.showMessageDialog(this, "Member deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete member.");
            }
        }
    }
}
