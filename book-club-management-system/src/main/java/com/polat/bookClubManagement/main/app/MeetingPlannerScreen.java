package com.polat.bookClubManagement.main.app;

import com.polat.bookClubManagement.main.lib.database.MeetingDAL;
import com.polat.bookClubManagement.main.lib.database.MemberDAL;
import com.polat.bookClubManagement.main.lib.models.MeetingWrapper;
import com.polat.bookClubManagement.main.lib.models.MemberWrapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class MeetingPlannerScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField txtMeetingId, txtDate, txtTime, txtLocation, txtAgenda;
    private JComboBox<String> comboMeetingPlanner;
    private MeetingDAL meetingDAL;
    private MemberDAL memberDAL;
    private JLabel lblNewLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MeetingPlannerScreen frame = new MeetingPlannerScreen();
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
    public MeetingPlannerScreen() {
        meetingDAL = new MeetingDAL();
        memberDAL = new MemberDAL();

        setTitle("Meeting Planner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 801, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Top panel for form inputs
        JPanel topPanel = new JPanel();
        topPanel.setBounds(5, 5, 776, 176);
        topPanel.setLayout(new GridLayout(6, 2, 10, 10));

        topPanel.add(new JLabel("Meeting ID:"));
        txtMeetingId = new JTextField();
        topPanel.add(txtMeetingId);

        topPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        txtDate = new JTextField();
        topPanel.add(txtDate);

        topPanel.add(new JLabel("Time:"));
        txtTime = new JTextField();
        topPanel.add(txtTime);

        topPanel.add(new JLabel("Location:"));
        txtLocation = new JTextField();
        topPanel.add(txtLocation);

        topPanel.add(new JLabel("Agenda:"));
        txtAgenda = new JTextField();
        topPanel.add(txtAgenda);

        topPanel.add(new JLabel("Meeting Planner:"));
        comboMeetingPlanner = new JComboBox<>();
        loadMeetingPlanners();
        contentPane.setLayout(null);
        topPanel.add(comboMeetingPlanner);

        contentPane.add(topPanel);

        // Table for displaying meetings
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 191, 776, 255);
        contentPane.add(scrollPane);
        loadMeetings();

        // Bottom panel for buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBounds(5, 459, 771, 94);

        JButton btnAdd = new JButton("Add Meeting");
        btnAdd.setBounds(105, 20, 121, 51);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMeeting();
            }
        });
        bottomPanel.setLayout(null);
        bottomPanel.add(btnAdd);

        JButton btnUpdate = new JButton("Update Meeting");
        btnUpdate.setBounds(254, 20, 121, 51);
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMeeting();
            }
        });
        bottomPanel.add(btnUpdate);

        JButton btnDelete = new JButton("Delete Meeting");
        btnDelete.setBounds(411, 20, 121, 51);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMeeting();
            }
        });
        bottomPanel.add(btnDelete);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(562, 20, 121, 51);
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMeetings();
            }
        });
        bottomPanel.add(btnRefresh);

        contentPane.add(bottomPanel);
        
        ImageIcon icon2 = new ImageIcon(MeetingPlannerScreen.class.getResource("image.jpg"));
		JLabel label_loginbackground = new JLabel("");
		label_loginbackground.setBounds(0, 0, 826, 508);
		Image image2 = icon2.getImage().getScaledInstance(label_loginbackground.getWidth(),
		label_loginbackground.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iccon = new ImageIcon(image2);
		label_loginbackground.setIcon(iccon);
		contentPane.add(label_loginbackground);
    }

    private void loadMeetingPlanners() {
        List<MemberWrapper> members = memberDAL.getAllMembers();
        for (MemberWrapper member : members) {
            comboMeetingPlanner.addItem(member.getMemberId());
        }
    }

    private void loadMeetings() {
        List<MeetingWrapper> meetings = meetingDAL.getAllMeetings();
        String[] columnNames = {"Meeting ID", "Date", "Time", "Location", "Agenda", "Meeting Planner"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (MeetingWrapper meeting : meetings) {
            model.addRow(new Object[]{
                    meeting.getMeetingId(),
                    meeting.getDate().toString(),
                    meeting.getTime(),
                    meeting.getLocation(),
                    meeting.getAgenda(),
                    meeting.getMeetingPlannerId()
            });
        }

        table.setModel(model);
    }

    private void addMeeting() {
        String meetingId = txtMeetingId.getText();
        Date date = Date.valueOf(txtDate.getText());
        String time = txtTime.getText();
        String location = txtLocation.getText();
        String agenda = txtAgenda.getText();
        String meetingPlannerId = (String) comboMeetingPlanner.getSelectedItem();

        MeetingWrapper meetingWrapper = new MeetingWrapper(meetingId, date, time, location, agenda, meetingPlannerId);
        if (meetingDAL.addMeeting(meetingWrapper)) {
            JOptionPane.showMessageDialog(this, "Meeting added successfully.");
            loadMeetings();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add meeting.");
        }
    }

    private void updateMeeting() {
        String meetingId = txtMeetingId.getText();
        Date date = Date.valueOf(txtDate.getText());
        String time = txtTime.getText();
        String location = txtLocation.getText();
        String agenda = txtAgenda.getText();
        String meetingPlannerId = (String) comboMeetingPlanner.getSelectedItem();

        MeetingWrapper meetingWrapper = new MeetingWrapper(meetingId, date, time, location, agenda, meetingPlannerId);
        if (meetingDAL.updateMeeting(meetingWrapper)) {
            JOptionPane.showMessageDialog(this, "Meeting updated successfully.");
            loadMeetings();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update meeting.");
        }
    }

    private void deleteMeeting() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a meeting to delete.");
            return;
        }

        String meetingId = (String) table.getValueAt(selectedRow, 0);
        if (meetingDAL.deleteMeeting(meetingId)) {
            JOptionPane.showMessageDialog(this, "Meeting deleted successfully.");
            loadMeetings();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete meeting.");
        }
    }
}
