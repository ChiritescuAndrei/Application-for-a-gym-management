package projectPackage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm_modificat {

    private JPanel contentPane;
    private JTextField username;
    private JPasswordField passwordField;
    private JFrame frame;
    static Connection con;
    static String emailCont;
    static String parolaCont;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginForm_modificat window = new LoginForm_modificat();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginForm_modificat() throws SQLException {
        initialize();
    }
    /**
     * @wbp.parser.entryPoint
     */
    private void initialize() throws SQLException {
        frame = new JFrame("Conectare Cont");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\andre\\eclipse-workspace\\Test\\bin\\user.png"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 654, 462);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(100, 149, 237));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        username = new JTextField();
        username.setBounds(189, 164, 215, 20);
        contentPane.add(username);
        username.setColumns(10);

        JButton loginButton = new JButton("Login");
        loginButton.setBorder(UIManager.getBorder("Button.border"));
        loginButton.setBounds(189, 241, 106, 23);
        contentPane.add(loginButton);

        passwordField = new JPasswordField();
        passwordField.setBounds(189, 210, 215, 20);
        contentPane.add(passwordField);

        JLabel lblNewLabel_1 = new JLabel("Parola");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(189, 195, 69, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Email");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_1_1.setBounds(189, 148, 69, 14);
        contentPane.add(lblNewLabel_1_1);

        JButton registerButton = new JButton("Register");
        registerButton.setBorder(UIManager.getBorder("Button.border"));
        registerButton.setBounds(298, 241, 106, 23);
        contentPane.add(registerButton);

        // Initialize database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/salafitness", "root", "cosmincmc1");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Login button action
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String uname = username.getText();
                String passw = new String(passwordField.getPassword());
                emailCont = uname;
                parolaCont = passw;
                try {
                    String query = "SELECT COUNT(*) FROM clienti JOIN paroleCont ON clienti.clientID = paroleCont.clientID WHERE email = ? AND parola = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(query)) {
                        pstmt.setString(1, uname);
                        pstmt.setString(2, passw);

                        try (ResultSet rs = pstmt.executeQuery()) {
                            if (rs.next() && rs.getInt(1) > 0) {
                                JOptionPane.showMessageDialog(frame, "Connectat cu succes");
                                frame.dispose();
                                Cont_modificat cont = new Cont_modificat(emailCont, parolaCont,con);                              
                            } else {
                                JOptionPane.showMessageDialog(frame, "Nu exista contul");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Eroare la conectarea la baza de date: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Register button action
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterForm register = new RegisterForm();
                register.setVisible(true);
                register.setLocationRelativeTo(null);
                frame.dispose();
            }
        });

        // Set the frame to be visible after all components are added
        frame.setVisible(true);
    }

}
