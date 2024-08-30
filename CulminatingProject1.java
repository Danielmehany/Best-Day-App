import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Random;


public class CulminatingProject1 implements ActionListener {


    private int FRAME_WIDTH = 900;
    private int FRAME_HEIGHT = 900;
    private int FIELD_WIDTH = 165;
    private int FIELD_HEIGHT = 25;
    private int BUTTON_WIDTH = 200;
    private int BUTTON_HEIGHT = 30;


    private Color BACKGROUND_COLOR = new Color(212, 183, 8);
    private Color BUTTON_COLOR = new Color(255, 118, 0);
    private Font BUTTON_FONT = new Font("serif", Font.PLAIN, 15);
    private Font BUTTON2_FONT = new Font("seirf", Font.BOLD, 40);
    private Font TITLE_FONT = new Font("serif", Font.BOLD, 100);
    private Font LABEL_FONT = new Font("serif", Font.PLAIN, 24);


    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    private JLabel timeLabel;
    private JFrame secondFrame;
    private JLabel username;
    private JTextField userText;
    private JLabel password;
    private JPasswordField passwordText;
    private JButton button2;
    private JButton button3;
    private JFrame registerPage;
    private JLabel username2;
    private JTextField user2Text;
    private JLabel password2;
    private JPasswordField password2Text;
    private JLabel userTypeLabel;
    private JComboBox<String> userTypeComboBox;
    private JButton register;
    private HashMap<String, String> studentDatabase = new HashMap<>();
    private HashMap<String, String> teacherDatabase = new HashMap<>();
    private HashMap<String, String> studentSchedules = new HashMap<>();
    private HashMap<String, String> teacherSchedules = new HashMap<>();
    private HashMap<String, List<Task>> teachertaskList = new HashMap<>();
    private HashMap<String, List<Task>> studenttaskList = new HashMap<>(); // to make sure that the tasks posted in one portal do not appear in the other.
    private HashMap<String, Boolean> attendanceDatabase = new HashMap<>();
    private List<Announcement> announcements = new ArrayList<>();


    public CulminatingProject1() {
        tictactoe();
        createHomePage();
    }


    // Method to create the home page with the current time and start button
    private void createHomePage() {
        frame = new JFrame("BR BEST DAY APP");
        panel = new JPanel();


        // Create and update the current time label
        timeLabel = new JLabel();
        updateTime();
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();
        timeLabel.setFont(new Font("serif", Font.BOLD, 40));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);


        JButton button = new JButton("START PROGRAM");
        button.setFont(BUTTON_FONT);
        button.setBounds(610, 450, BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setBackground(BUTTON_COLOR);
        button.setFocusable(false);


        title = new JLabel("BR BEST DAY APP");
        title.setFont(TITLE_FONT);
        title.setForeground(Color.WHITE);


        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(200, 300, 200, 300));
        panel.setBackground(BACKGROUND_COLOR);
        panel.add(timeLabel, BorderLayout.NORTH);
        panel.add(title, BorderLayout.CENTER);


        frame.setLayout(new BorderLayout());
        frame.add(button, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame


        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close first frame
                createLoginPage();
            }
        });
    }


    // Method to update the current time on the home page
    private void updateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        timeLabel.setText(dtf.format(now));
        timeLabel.setForeground(Color.WHITE);
    }


    // We used GridBadConstraints and the GridBadLayout to organize the page into a grid.
    // Method to create the login page
    private void createLoginPage() {
        secondFrame = new JFrame("Sign In");
        secondFrame.setLayout(new GridBagLayout());
        secondFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        secondFrame.setLocationRelativeTo(null);
        secondFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame
        secondFrame.setBackground(BACKGROUND_COLOR);


        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        username = new JLabel("Username: ");
        username.setFont(LABEL_FONT);
        username.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(username, gbc);


        userText = new JTextField(20);
        userText.setFont(LABEL_FONT);
        gbc.gridx = 1;
        gbc.gridy = 0;
        userText.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        loginPanel.add(userText, gbc);


        password = new JLabel("Password: ");
        password.setFont(LABEL_FONT);
        password.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(password, gbc);


        passwordText = new JPasswordField();
        passwordText.setFont(LABEL_FONT);
        gbc.gridx = 1;
        gbc.gridy = 1;
        passwordText.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        loginPanel.add(passwordText, gbc);


        button2 = new JButton("Login");
        button2.setFont(BUTTON_FONT);
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(button2, gbc);
        button2.setBackground(BUTTON_COLOR);
        button2.setFocusable(false);
        button2.addActionListener(this);


        button3 = new JButton("Register");
        button3.setFont(BUTTON_FONT);
        gbc.gridx = 1;
        gbc.gridy = 3;
        loginPanel.add(button3, gbc);
        button3.setFocusable(false);
        button3.setBackground(BUTTON_COLOR);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createRegisterPage();
            }
        });


        secondFrame.getContentPane().setBackground(BACKGROUND_COLOR);
        secondFrame.add(loginPanel);
        secondFrame.setVisible(true);
    }




    // Method to create the registration page
    private void createRegisterPage() {
        registerPage = new JFrame("Register");
        registerPage.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        registerPage.setLocationRelativeTo(null);
        registerPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel registerPanel = new JPanel(new GridBagLayout());
        registerPanel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        username2 = new JLabel("Username: ");
        username2.setFont(LABEL_FONT);
        username2.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        registerPanel.add(username2, gbc);


        user2Text = new JTextField(20);
        user2Text.setFont(LABEL_FONT);
        gbc.gridx = 1;
        gbc.gridy = 0;
        user2Text.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        registerPanel.add(user2Text, gbc);


        password2 = new JLabel("Password: ");
        password2.setFont(LABEL_FONT);
        password2.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        registerPanel.add(password2, gbc);


        password2Text = new JPasswordField();
        password2Text.setFont(LABEL_FONT);
        gbc.gridx = 1;
        gbc.gridy = 1;
        password2Text.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        registerPanel.add(password2Text, gbc);


        userTypeLabel = new JLabel("User Type:");
        userTypeLabel.setFont(LABEL_FONT);
        userTypeLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        registerPanel.add(userTypeLabel, gbc);


        String[] userTypes = {"Student", "Teacher"};
        userTypeComboBox = new JComboBox<>(userTypes);
        userTypeComboBox.setFont(LABEL_FONT);
        gbc.gridx = 1;
        gbc.gridy = 2;
        userTypeComboBox.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        userTypeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userType = (String) userTypeComboBox.getSelectedItem();
                if (userType.equals("Student")) {
                    username2.setText("Username (must end with @my.werbr.org): ");
                } else if (userType.equals("Teacher")) {
                    username2.setText("Username (must end with @my.werbr.ca): ");
                }
            }
        });
        registerPanel.add(userTypeComboBox, gbc);


        register = new JButton("Register");
        register.setFont(BUTTON_FONT);
        register.setBackground(BUTTON_COLOR);
        gbc.gridx = 1;
        gbc.gridy = 3;
        registerPanel.add(register, gbc);
        register.setFocusable(false);
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    registerUser();
            }
        });


        registerPage.getContentPane().setBackground(BACKGROUND_COLOR);
        registerPage.add(registerPanel);
        registerPage.setVisible(true);
    }


    // Method to handle user registration
    private void registerUser() {
        String newUser = user2Text.getText();
        String newPass = new String(password2Text.getPassword());
        String userType = (String) userTypeComboBox.getSelectedItem();


        if (isValidEmail(newUser, userType) && isValidPassword(newPass)) {
            if (userType.equals("Student")) {
                if (!studentDatabase.containsKey(newUser)) {
                    studentDatabase.put(newUser, newPass);
                    studentSchedules.put(newUser, "");
                    JOptionPane.showMessageDialog(registerPage, "Registration successful!");
                    registerPage.dispose();
                } else {
                    JOptionPane.showMessageDialog(registerPage, "Username already exists!");
                }
            } else if (userType.equals("Teacher")) {
                if (!teacherDatabase.containsKey(newUser)) {
                    teacherDatabase.put(newUser, newPass);
                    teacherSchedules.put(newUser, "");
                    JOptionPane.showMessageDialog(registerPage, "Registration successful!");
                    registerPage.dispose();
                } else {
                    JOptionPane.showMessageDialog(registerPage, "Username already exists!");
                }
            }
        }
    }


    // Method to validate email based on user type
    private boolean isValidEmail(String email, String userType) {
        if (userType.equals("Student")) {
            if (email.endsWith("@my.werbr.org")) {
                return true;
            } else {
                JOptionPane.showMessageDialog(registerPage, "Invalid student email. Must end with @my.werbr.org");
                return false;
            }
        } else if (userType.equals("Teacher")) {
            if (email.endsWith("@my.werbr.ca")) {
                return true;
            } else {
                JOptionPane.showMessageDialog(registerPage, "Invalid teacher email. Must end with @my.werbr.ca");
                return false;
            }
        }
        return false;
    }


    // Method to validate password
    private boolean isValidPassword(String password) {
        if (password.length() >= 6 && password.matches(".*[A-Z].*")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(registerPage, "Invalid password. Must be at least 6 characters long and contain at least one uppercase letter.");
            return false;
        }
    }


    // Method to handle login action
    public void actionPerformed(ActionEvent e) {
        String username = userText.getText();
        String password = new String(passwordText.getPassword());


        if (username.endsWith("@my.werbr.org")) {
            if (studentDatabase.containsKey(username) && studentDatabase.get(username).equals(password)) {
                JOptionPane.showMessageDialog(registerPage, "Login Successful!");
                createStudentScreen(username);
                secondFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(registerPage, "Invalid Username or Password");
            }
        } else if (username.endsWith("@my.werbr.ca")) {
            if (teacherDatabase.containsKey(username) && teacherDatabase.get(username).equals(password)) {
                JOptionPane.showMessageDialog(registerPage, "Login Successful!");
                createTeacherScreen(username);
                secondFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(registerPage, "Invalid Username or Password");
            }
        } else {
            JOptionPane.showMessageDialog(registerPage, "Invalid Username. Must end with @my.werbr.ca or @my.werbr.org");
        }
    }


    // Method to create the student dashboard
    private void createStudentScreen(String username) {
        JFrame studentFrame = new JFrame("Student Dashboard");
        studentFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        studentFrame.setLocationRelativeTo(null);


        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(15, 0, 75));
        tabbedPane.setForeground(Color.WHITE);


        JPanel studentDailyPanel = createToDoListPanel(username, studenttaskList);
        JPanel studentAnnouncementPanel = createStudentAnnouncement();


        tabbedPane.addTab("Student's TO-DO List", studentDailyPanel);
        tabbedPane.addTab("Announcements", studentAnnouncementPanel);
        tabbedPane.addTab("Tic-Tac-Toe", tictactoePanel);


        // To change the colour after they are clicked
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                    if (i == tabbedPane.getSelectedIndex()) {
                        tabbedPane.setBackgroundAt(i, BACKGROUND_COLOR);
                        tabbedPane.setForegroundAt(i, Color.BLACK);
                    } else {
                        tabbedPane.setBackgroundAt(i, new Color(15, 0, 75));
                        tabbedPane.setForegroundAt(i, Color.WHITE);
                    }
                }
            }
        });


        studentFrame.add(tabbedPane, BorderLayout.CENTER);


        JButton logoutButton = new JButton("Log Out");
        logoutButton.setBounds(750, 800, 100, 30);
        logoutButton.setBackground(BUTTON_COLOR);
        logoutButton.setFont(BUTTON_FONT);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studentFrame.dispose();
                createHomePage();
            }
        });


        studentFrame.add(logoutButton, BorderLayout.SOUTH);
        studentFrame.setVisible(true);    
    }


    // Student Announcement Panel
    // We are making it so when the teacher posts an announcement it is displayed in the student portal
    private JPanel createStudentAnnouncement() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);


        JLabel title6 = new JLabel("Announcements");
        title6.setFont(new Font("serif", Font.BOLD, 24));
        title6.setForeground(Color.WHITE);
        panel.add(title6, BorderLayout.NORTH);


        JPanel annPanel = new JPanel();
        annPanel.setLayout(new BoxLayout(annPanel, BoxLayout.Y_AXIS));
        annPanel.setBackground(BACKGROUND_COLOR);
       
        JScrollPane scroll = new JScrollPane(annPanel);
        panel.add(scroll, BorderLayout.CENTER);


        int boxWidth = 600;


        // To post announcements from the teacher portal
        for (Announcement announcement : announcements) {
            JPanel annPanel2 = new JPanel();
            annPanel2.setLayout(new BorderLayout());
            annPanel2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            annPanel2.setBackground(BACKGROUND_COLOR); // We are making it so when an announcement is posted, it is posted in a separate box.
            annPanel2.setPreferredSize(new Dimension(boxWidth, 100));
            annPanel2.setMaximumSize(new Dimension(boxWidth, Integer.MAX_VALUE)); // Now the box increases vertically and not horizontally


            JTextArea textArea3 = new JTextArea(announcement.getText());
            textArea3.setFont(LABEL_FONT);
            textArea3.setBackground(BACKGROUND_COLOR);
            textArea3.setForeground(Color.WHITE);
            textArea3.setEditable(false);
            textArea3.setLineWrap(true);
            textArea3.setWrapStyleWord(true);
            textArea3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


            JLabel time8 = new JLabel(announcement.getTime2().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            time8.setFont(new Font("serif", Font.ITALIC, 12));
            time8.setForeground(Color.BLACK);
            time8.setHorizontalAlignment(SwingConstants.RIGHT);
            time8.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));


            annPanel2.add(textArea3, BorderLayout.CENTER);
            annPanel2.add(time8, BorderLayout.SOUTH);




            JPanel centerBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            centerBox.setBackground(BACKGROUND_COLOR);
            centerBox.add(annPanel2);


            annPanel.add(Box.createVerticalStrut(10)); // This adds space between each announcement
            annPanel.add(annPanel2);
        }


        return panel;
    }


    Random random = new Random();
    JPanel title_Panel = new JPanel();
    JPanel button_Panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    JPanel tictactoePanel;


    private JPanel tictactoe() {
        tictactoePanel = new JPanel(new BorderLayout());
        tictactoePanel.setBackground(BACKGROUND_COLOR);


        title_Panel = new JPanel(new BorderLayout());
        title_Panel.setBackground(BACKGROUND_COLOR);


        textfield.setBackground(BACKGROUND_COLOR);
        textfield.setForeground(Color.WHITE);
        textfield.setFont(LABEL_FONT);
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);


        title_Panel.add(textfield, BorderLayout.CENTER);
        tictactoePanel.add(title_Panel, BorderLayout.NORTH);


        button_Panel.setLayout(new GridLayout(3, 3));
        tictactoePanel.add(button_Panel, BorderLayout.CENTER);


        for(int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_Panel.add(buttons[i]);
            buttons[i].setFont(BUTTON2_FONT);
            buttons[i].setBackground(new Color(15, 0, 75));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for(int i = 0; i < 9; i++) {
                        if(e.getSource() == buttons[i]) {
                           
                            if(player1_turn) {
                                if(buttons[i].getText() == "") {
                                    buttons[i].setForeground(Color.WHITE);
                                    buttons[i].setText("X");
                                    player1_turn = false;
                                    textfield.setText("O Turn");
                                    check();
                                }
                            } else {
                                if(buttons[i].getText() == "") {
                                    buttons[i].setForeground(Color.RED);
                                    buttons[i].setText("O");
                                    player1_turn = true;
                                    textfield.setText("X Turn");
                                    check();
                                }  
                            }
                        }
                    }
                }
            });
        }
       
        firstTurn();
        return tictactoePanel;
    }


    // Method to create the teacher dashboard
    private void createTeacherScreen(String username) {
        JFrame teacherFrame = new JFrame("Teacher Dashboard");
        teacherFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        teacherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        teacherFrame.setLocationRelativeTo(null);


        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(15, 0, 75));
        tabbedPane.setForeground(Color.WHITE);


        // Create panels for each tab
        JPanel teacherDailyPanel = createToDoListPanel(username, teachertaskList);
        JPanel studentDailyPanel = createAttendencePanel();
        JPanel teacherWeeklyPanel = createAnnouncementPanel();


        tabbedPane.addTab("Attendence", studentDailyPanel);
        tabbedPane.addTab("Teacher's TO-DO LIST", teacherDailyPanel);
        tabbedPane.addTab("Announcements", teacherWeeklyPanel);


        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                    if (i == tabbedPane.getSelectedIndex()) {
                        tabbedPane.setBackgroundAt(i, BACKGROUND_COLOR);
                        tabbedPane.setForegroundAt(i, Color.BLACK);
                    } else {
                        tabbedPane.setBackgroundAt(i, new Color(15, 0, 75));
                        tabbedPane.setForegroundAt(i, Color.WHITE);
                    }
                }
            }
        });


        teacherFrame.add(tabbedPane, BorderLayout.CENTER);


        // Log out button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.setBounds(750, 800, 100, 30);
        logoutButton.setBackground(BUTTON_COLOR);
        logoutButton.setFont(BUTTON_FONT);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teacherFrame.dispose();
                createHomePage();
            }
        });


        teacherFrame.add(logoutButton, BorderLayout.SOUTH);
        teacherFrame.setVisible(true);
    }


    private JPanel createAnnouncementPanel()  {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);


        JLabel title5 = new JLabel("Announcements: ");
        title5.setFont(new Font("serif", Font.BOLD, 24));
        title5.setForeground(Color.WHITE);
        panel.add(title5, BorderLayout.NORTH);


        JTextArea textArea = new JTextArea(5, 30);
        textArea.setFont(LABEL_FONT);
        textArea.setBackground(BACKGROUND_COLOR);
        textArea.setForeground(Color.WHITE);
        textArea.setEditable(false); // so the user is not able to edit the textArea directly


        JScrollPane scroll = new JScrollPane(textArea);
        panel.add(scroll, BorderLayout. CENTER);


        JPanel post = new JPanel(new BorderLayout());
        JTextArea posting = new JTextArea(5, 30);
        posting.setForeground(Color.WHITE);
        posting.setBackground(new Color(228, 202, 34));
        posting.setFont(new Font("serif", Font.PLAIN, 16));


        String placeholder = "Type here to post announcement...";
        posting.setText(placeholder);
        posting.setForeground(Color.GRAY);


        posting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (posting.getText().equals(placeholder)) {
                    posting.setText("");
                    posting.setForeground(Color.WHITE);
                }
            }
        });


        posting.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (posting.getText().isEmpty()) {
                    posting.setForeground(Color.GRAY);
                    posting.setText(placeholder);
                }
            }
        });




        JButton announcementButton = new JButton("Post Announcement");
        announcementButton.setBackground(BUTTON_COLOR);
        announcementButton.setFont(BUTTON_FONT);
        announcementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text4 = posting.getText().trim();
                if(!text4.isEmpty() && !text4.equals(placeholder)) {
                    Announcement annoucement = new Announcement(text4);
                    announcements.add(annoucement);
                    updateAnn(textArea);
                    posting.setText(placeholder);
                    posting.setForeground(Color.GRAY);
                }
            }
        });


        updateAnn(textArea);
        post.add(posting, BorderLayout.CENTER);
        post.add(announcementButton, BorderLayout.SOUTH);
        panel.add(post, BorderLayout.SOUTH);
       
        return panel;
    }


    private JPanel createToDoListPanel(String username, HashMap<String, List<Task>> taskLists) {
        if (!taskLists.containsKey(username)) {
            taskLists.put(username, new ArrayList<>());
        }
        List<Task> taskList = taskLists.get(username);
   
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);
   
        JLabel title2 = new JLabel(username.endsWith("@my.werbr.org") ? "Student's Daily To-Do List" : "Teacher's Daily To-Do List");
        title2.setFont(new Font("serif", Font.BOLD, 24));
        title2.setForeground(Color.WHITE);
        panel.add(title2, BorderLayout.NORTH);
   
        JPanel tasksPanel = new JPanel();
        tasksPanel.setLayout(new BoxLayout(tasksPanel, BoxLayout.Y_AXIS));
        tasksPanel.setBackground(BACKGROUND_COLOR);
        JScrollPane scrollPane = new JScrollPane(tasksPanel);
        panel.add(scrollPane, BorderLayout.CENTER);
   
        JPanel taskPanel = new JPanel(new GridLayout(3, 2));
        JLabel taskDes = new JLabel("Task Description");
        taskDes.setFont(LABEL_FONT);
        taskDes.setForeground(Color.WHITE);
   
        taskPanel.add(taskDes);
        JTextArea taskDescription = new JTextArea();
        taskDescription.setFont(LABEL_FONT);
        taskPanel.setBackground(BACKGROUND_COLOR);
        taskPanel.add(new JScrollPane(taskDescription));
   
        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setFont(LABEL_FONT);
        dueDateLabel.setForeground(Color.WHITE);
        taskPanel.add(dueDateLabel);
   
        JTextField dueDate = new JTextField();
        dueDate.setFont(LABEL_FONT);
        taskPanel.add(dueDate);
   
        JButton taskButton = new JButton("Add Task");
        taskButton.setFont(BUTTON_FONT);
        taskButton.setBackground(BUTTON_COLOR);
        taskButton.setFocusable(false);
        taskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    String description = taskDescription.getText();
                    String dueDateString = dueDate.getText();
                    taskList.add(new Task(description, dueDateString));
                    taskDescription.setText("");
                    dueDate.setText("");
                    updateTask(tasksPanel, taskList);
            }
        });
   
        taskPanel.add(taskButton);
        panel.add(taskPanel, BorderLayout.SOUTH);
        updateTask(tasksPanel, taskList);
        return panel;
    }
   


    // Now we will create a method to update the task panel so when a checkbox is clicked the task is removed
    private void updateTask(JPanel taskPanel, List<Task> taskList) {
        taskPanel.removeAll();
        for(Task task : taskList) {
            JPanel tasksPanel = new JPanel(new BorderLayout());
            tasksPanel.setBackground(BACKGROUND_COLOR);
            JCheckBox checkBox = new JCheckBox();
            checkBox.setBackground(BACKGROUND_COLOR);
            checkBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    taskList.remove(task);
                    updateTask(taskPanel, taskList);
                }
            });
   
            JLabel taskLabel = new JLabel(task.description + " (Due: " + task.dueDate + ")");
            taskLabel.setFont(LABEL_FONT);
            taskLabel.setForeground(Color.WHITE);
            tasksPanel.add(checkBox, BorderLayout.WEST);
            tasksPanel.add(taskLabel, BorderLayout.CENTER);
            taskPanel.add(tasksPanel);
        }
   
        taskPanel.revalidate();
        taskPanel.repaint();
    }
   


    // Updating the announcement panel
    private void updateAnn(JTextArea textArea) {
        textArea.setText("");
        for (Announcement announcement : announcements) {
            textArea.append(announcement.getText() + " - " + announcement.getTime2().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
        }
    }


    // Adding an attendence tab for teachers
    private JPanel createAttendencePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);


        JLabel title3 = new JLabel("Attendence");
        title3.setFont(new Font("serif", Font.BOLD, 24));
        title3.setForeground(Color.WHITE);
        panel.add(title3, BorderLayout.NORTH);


        JPanel attendancePanel = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(attendancePanel);
        attendancePanel.setBackground(BACKGROUND_COLOR);


        for (String student : studentDatabase.keySet()) {
            JPanel studentPanel = new JPanel(new FlowLayout());
            studentPanel.setBackground(BACKGROUND_COLOR);


            JLabel studentLabel = new JLabel(student);
            studentLabel.setFont(LABEL_FONT);
            studentLabel.setForeground(Color.WHITE);


            JCheckBox attendanceCheckBox = new JCheckBox("Present");
            attendanceCheckBox.setSelected(attendanceDatabase.getOrDefault(student, false));
            attendanceCheckBox.setBackground(BACKGROUND_COLOR);
            attendanceCheckBox.setFont(LABEL_FONT);
            attendanceCheckBox.setForeground(Color.WHITE);


            attendanceCheckBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    attendanceDatabase.put(student, attendanceCheckBox.isSelected());
                }
            });
       
            studentPanel.add(studentLabel);
            studentPanel.add(attendanceCheckBox);
            attendancePanel.add(studentPanel);
        }


        panel.add(scrollPane, BorderLayout.CENTER);


        JButton saveButton = new JButton("Save Attendance");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel, "Attendance Saved!");
            }
        });
        saveButton.setFont(BUTTON_FONT);
        saveButton.setBackground(BUTTON_COLOR);
        saveButton.setFocusable(false);
        panel.add(saveButton, BorderLayout.SOUTH);


        return panel;
    }




    public static void main(String[] args) {
        new CulminatingProject1();
    }


    // Task class to handle task descriptions and due dates
    private static class Task {
        private String description;
        private String dueDate;


        public Task(String description, String dueDate) {
            this.description = description;
            this.dueDate = dueDate;
        }


        public String toString() {
            return "Task: " + description + ", Due Date: " + dueDate;
        }
    }


    class Announcement {
        private String text3;
        private LocalDateTime time2;


        public Announcement(String text3) {
            this.text3 = text3;
            this.time2 = LocalDateTime.now();
        }


        String getText() {
            return text3;
        }


        LocalDateTime getTime2() {
            return time2;
        }
    }


    public void firstTurn() {


        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if(random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("X turn");
        } else {
            player1_turn = false;
            textfield.setText("O turn");
        }
    }


    public void resetGame() {
        for(int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBackground(null);
        }


        firstTurn();
    }


    public void check() {
        boolean tie = true;


        if(
            (buttons[0].getText() == "X") &&
            (buttons[1].getText() == "X") &&
            (buttons[2].getText() == "X")
            ) {
                xWins(0, 1, 2);
        } else if(
            (buttons[3].getText() == "X") &&
            (buttons[4].getText() == "X") &&
            (buttons[5].getText() == "X")
            ) {
                xWins(3, 4, 5);
        } else if(
            (buttons[6].getText() == "X") &&
            (buttons[7].getText() == "X") &&
            (buttons[8].getText() == "X")
            ) {
                xWins(6, 7, 8);
        } else if(
            (buttons[0].getText() == "X") &&
            (buttons[3].getText() == "X") &&
            (buttons[6].getText() == "X")
            ) {
                xWins(0, 3, 6);
        } else if(
            (buttons[1].getText() == "X") &&
            (buttons[4].getText() == "X") &&
            (buttons[7].getText() == "X")
            ) {
                xWins(1, 4, 7);
        } else if(
            (buttons[2].getText() == "X") &&
            (buttons[5].getText() == "X") &&
            (buttons[8].getText() == "X")
            ) {
                xWins(2, 5, 8);
        } else if(
            (buttons[0].getText() == "X") &&
            (buttons[4].getText() == "X") &&
            (buttons[8].getText() == "X")
            ) {
                xWins(0, 4, 8);
        } else if(
            (buttons[2].getText() == "X") &&
            (buttons[4].getText() == "X") &&
            (buttons[6].getText() == "X")
            ) {
                xWins(2, 4, 6);
        } else {
            tie = false;
        }


        // O turn
        if(
            (buttons[0].getText() == "O") &&
            (buttons[1].getText() == "O") &&
            (buttons[2].getText() == "O")
            ) {
                oWins(0, 1, 2);
        } else if(
            (buttons[3].getText() == "O") &&
            (buttons[4].getText() == "O") &&
            (buttons[5].getText() == "O")
            ) {
                oWins(3, 4, 5);
        } else if(
            (buttons[6].getText() == "O") &&
            (buttons[7].getText() == "O") &&
            (buttons[8].getText() == "O")
            ) {
                oWins(6, 7, 8);
        } else if(
            (buttons[0].getText() == "O") &&
            (buttons[3].getText() == "O") &&
            (buttons[6].getText() == "O")
            ) {
                oWins(0, 3, 6);
        } else if(
            (buttons[1].getText() == "O") &&
            (buttons[4].getText() == "O") &&
            (buttons[7].getText() == "O")
            ) {
                oWins(1, 4, 7);
        } else if(
            (buttons[2].getText() == "O") &&
            (buttons[5].getText() == "O") &&
            (buttons[8].getText() == "O")
            ) {
                oWins(2, 5, 8);
        } else if(
            (buttons[0].getText() == "O") &&
            (buttons[4].getText() == "O") &&
            (buttons[8].getText() == "O")
            ) {
                oWins(0, 4, 8);
        } else if(
            (buttons[2].getText() == "O") &&
            (buttons[4].getText() == "O") &&
            (buttons[6].getText() == "O")
            ) {
                oWins(2, 4, 6);
        } else  {
            tie = false;
        }


        if(tie) {
            resetGame();
        }
    }


    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);


        for(int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }


        textfield.setText("X wins");
       
    }


    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);


        for(int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }


        textfield.setText("O wins");
    }


}
