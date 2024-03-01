package projectPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JCheckBox;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class RegisterForm extends JFrame {

	private JPanel contentPane;
	private JTextField nume;
	private JPasswordField parola;
	private JLabel lblNewLabel_1;
	private JTextField prenume;
	private JTextField strada;
	private JTextField numar;
	private JTextField oras;
	private JTextField judet;
	private JTextField email;
	private JTextField nrTelefon;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JPasswordField confirmareParola;
	private JLabel lblNewLabel_8;
	private static Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterForm frame = new RegisterForm();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterForm() {
		this.setVisible(true);
		this.setResizable(false);
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/salafitness","root","cosmincmc1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\andre\\eclipse-workspace\\Test\\bin\\user.png"));
		setTitle("Inregistrare Cont");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 462);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nume = new JTextField();
		nume.setBounds(71, 39, 142, 20);
		contentPane.add(nume);
		nume.setColumns(10);
		
		parola = new JPasswordField();
		parola.setBounds(323, 169, 142, 20);
		contentPane.add(parola);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nume");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(71, 24, 69, 14);
		contentPane.add(lblNewLabel_1_1);
		
		lblNewLabel_1 = new JLabel("Numar");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(71, 156, 69, 14);
		contentPane.add(lblNewLabel_1);
		
		prenume = new JTextField();
		prenume.setColumns(10);
		prenume.setBounds(71, 84, 142, 20);
		contentPane.add(prenume);
		
		strada = new JTextField();
		strada.setColumns(10);
		strada.setBounds(71, 122, 142, 20);
		contentPane.add(strada);
		
		numar = new JTextField();
		numar.setColumns(10);
		numar.setBounds(71, 169, 142, 20);
		contentPane.add(numar);
		
		oras = new JTextField();
		oras.setColumns(10);
		oras.setBounds(71, 211, 142, 20);
		contentPane.add(oras);
		
		judet = new JTextField();
		judet.setColumns(10);
		judet.setBounds(71, 256, 142, 20);
		contentPane.add(judet);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(71, 302, 142, 20);
		contentPane.add(email);
		
		nrTelefon = new JTextField();
		nrTelefon.setColumns(10);
		nrTelefon.setBounds(71, 343, 142, 20);
		contentPane.add(nrTelefon);
		
		lblNewLabel = new JLabel("Prenume");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(71, 70, 69, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_2 = new JLabel("Strada");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(71, 108, 69, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Judet");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(71, 244, 69, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Oras");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(71, 196, 69, 14);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Telefon*");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(71, 329, 69, 14);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Email");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(71, 287, 69, 14);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Parola");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(325, 156, 37, 14);
		contentPane.add(lblNewLabel_7);
		
		confirmareParola = new JPasswordField();
		confirmareParola.setBounds(323, 211, 142, 20);
		contentPane.add(confirmareParola);
		
		lblNewLabel_8 = new JLabel("Confirmare parola");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(323, 196, 142, 14);
		contentPane.add(lblNewLabel_8);
		
		JCheckBox prelucrareDate = new JCheckBox("De acord cu prelucrarea datelor");
		prelucrareDate.setBackground(new Color(255, 165, 0));
		prelucrareDate.setBounds(323, 301, 209, 23);
		contentPane.add(prelucrareDate);
		
		JButton registerBtn = new JButton("Inregistrare Cont");
		registerBtn.setBounds(323, 342, 209, 23);
		contentPane.add(registerBtn);
		
		JLabel lblNewLabel_9 = new JLabel("(*) Informatii optionale");
		lblNewLabel_9.setBounds(71, 387, 142, 14);
		contentPane.add(lblNewLabel_9);
		
		JLabel eroare_parola = new JLabel("Parolele \r\nnu coincid");
		eroare_parola.setForeground(new Color(255, 0, 0));
		eroare_parola.setFont(new Font("Tahoma", Font.PLAIN, 12));
		eroare_parola.setBounds(372, 157, 110, 14);
		eroare_parola.setVisible(false);
		contentPane.add(eroare_parola);
		
		JLabel eroareMail = new JLabel("Invalida");
		eroareMail.setForeground(new Color(255, 0, 0));
		eroareMail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		eroareMail.setBounds(167, 287, 46, 14);
		eroareMail.setVisible(false);
		contentPane.add(eroareMail);
		
		JLabel eroareBifare = new JLabel("Bifeaza pentru a putea continua");
		eroareBifare.setForeground(Color.RED);
		eroareBifare.setFont(new Font("Tahoma", Font.PLAIN, 12));
		eroareBifare.setBounds(323, 287, 186, 14);
		eroareBifare.setVisible(false);
		contentPane.add(eroareBifare);
		
		
		registerBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String nume1 = nume.getText();
				String prenume1 = prenume.getText();
				String strada1 = strada.getText();
				String numar1 = numar.getText();
				String oras1 = oras.getText();
				String judet1 = judet.getText();
				String email1 = email.getText();
				String nrTelefon1 = nrTelefon.getText();
				String parola1 = parola.getText();
				String confirmareParola1 = confirmareParola.getText();
				if (prelucrareDate.isSelected()) {
					eroareBifare.setVisible(false);
					if (parola1.equals(confirmareParola1)) {
						eroare_parola.setVisible(false);
						if (email1.contains("@") && email1.contains(".")) {
							
							eroareMail.setVisible(false);
							try {
								String query = "INSERT INTO clienti(nume,prenume,strada,numar,oras,judet,email,nr_telefon) VALUES(?,?,?,?,?,?,?,?)";
								PreparedStatement preparedStmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
								preparedStmt.setString(1, nume1);
								preparedStmt.setString(2, prenume1);
								preparedStmt.setString(3, strada1);
								preparedStmt.setString(4, numar1);
								preparedStmt.setString(5, oras1);
								preparedStmt.setString(6, judet1);
								preparedStmt.setString(7, email1);
								if (nrTelefon1.isEmpty()) {
								    preparedStmt.setNull(8, Types.VARCHAR); // sau un alt tip SQL conform schema bazei de date
								} else {
								    preparedStmt.setString(8, nrTelefon1);
								}
								preparedStmt.execute();
								
								
								 ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
		                            if (generatedKeys.next()) {
		                                int clientId = generatedKeys.getInt(1);

		                                // Inserarea în tabela parolaConturi
		                                String sqlParole = "INSERT INTO paroleCont (clientID, parola) VALUES (?, ?)";
		                                try (PreparedStatement pstmtParole = con.prepareStatement(sqlParole)) {
		                                    pstmtParole.setInt(1, clientId);
		                                    pstmtParole.setString(2, parola1);
		                                    pstmtParole.executeUpdate();
		                                    JOptionPane.showMessageDialog(contentPane, "Cont creat succes");
		                                    dispose();
		                                    LoginForm_modificat loginForm = new LoginForm_modificat();
		                                }
		                            }
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							eroareMail.setVisible(true);
						}
					} else {
						eroare_parola.setVisible(true);
					}
				} else {
					eroareBifare.setVisible(true);
				}

			}
		});
		
	}
}
