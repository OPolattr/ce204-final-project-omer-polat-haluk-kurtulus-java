package com.polat.bookClubManagement.main.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton memberManagementButton;
    private JButton readingScheduleButton;
    private JButton meetingPlannerButton;
    private JButton discussionForumButton;
    private JLabel welcomeLabel;
    private String username;
    private JLabel lblNewLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomePage frame = new HomePage("testUser");
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
    public HomePage(String username) {
        this.username = username;
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 627, 418);
        contentPane = new JPanel();
        setContentPane(contentPane);
        initializeComponents();
    }

    private void initializeComponents() {
        contentPane.setLayout(null);
        welcomeLabel = new JLabel("Welcome, " + username);
        welcomeLabel.setBounds(247, 32, 100, 25);
        contentPane.add(welcomeLabel);

        memberManagementButton = new JButton("Member Management");
        memberManagementButton.setBounds(74, 85, 196, 113);
        contentPane.add(memberManagementButton);

        readingScheduleButton = new JButton("Reading Schedule");
        readingScheduleButton.setBounds(353, 85, 196, 113);
        contentPane.add(readingScheduleButton);

        meetingPlannerButton = new JButton("Meeting Planner");
        meetingPlannerButton.setBounds(74, 233, 196, 113);
        contentPane.add(meetingPlannerButton);

        discussionForumButton = new JButton("Discussion Forum");
        discussionForumButton.setBounds(353, 233, 196, 113);
        contentPane.add(discussionForumButton);
        
        ImageIcon icon2 = new ImageIcon(HomePage.class.getResource("image.jpg"));
		JLabel label_loginbackground = new JLabel("");
		label_loginbackground.setBounds(0, 0, 826, 508);
		Image image2 = icon2.getImage().getScaledInstance(label_loginbackground.getWidth(),
		label_loginbackground.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iccon = new ImageIcon(image2);
		label_loginbackground.setIcon(iccon);
		contentPane.add(label_loginbackground);

        memberManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MemberManagementScreen(username).setVisible(true);
                dispose();
            }
        });

        readingScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReadingScheduleScreen(username).setVisible(true);
                dispose();
            }
        });

        meetingPlannerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MeetingPlannerScreen().setVisible(true);
                dispose();
            }
        });

        discussionForumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DiscussionForumScreen().setVisible(true);
                dispose();
            }
        });
    }
}
