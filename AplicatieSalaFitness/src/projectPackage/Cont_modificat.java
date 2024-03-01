package projectPackage;

//alte importuri necesare

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JCalendar;

public class Cont_modificat{

    private JPanel contentPane;
    private JFrame frame;
    private String emailCont;
    private String parolaCont;
    private Connection con;
    private JTable table;
    private int flag = 0;
    private String[] abonament;
    private JTable tabelClase;
    private int nrRezervari = 0;
    
    //Date pentru rezervare
    private String className;
    private Time startTime;
    private Time endTime;
    private BigDecimal price;
    private Date dateDesfasurare;
    private String coachName;
    private  int clientIDCurent;
    private JTable tableRezervari;
    private JTextField textField;
    private JTextField textAntrenorPopular;
    private String numeAntrenoPopular;
    private String rezervariClient;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Cont_modificat window = new Cont_modificat("z@z.com","da",DriverManager.getConnection("jdbc:mysql://localhost:3306/salafitness", "root", "cosmincmc1"));
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Cont_modificat(String email, String parola,Connection con) throws SQLException {
        this.emailCont = email;
        this. parolaCont = parola;
        this.con = con;									
        initialize();
    }

    /**
     * @wbp.parser.entryPoint
     */
    private void initialize() {
    	
        frame = new JFrame("Client");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 654, 462);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(100, 149, 237));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        frame.setContentPane(contentPane);

        JButton btnCont = new JButton("Setari Cont");
        btnCont.setBackground(new Color(255, 255, 0));
        btnCont.setBounds(92, 0, 120, 23);
        contentPane.add(btnCont);

        JButton btnAbonament = new JButton("Abonament");
        btnAbonament.setBackground(new Color(255, 255, 0));
        btnAbonament.setBounds(209, 0, 107, 23);
        contentPane.add(btnAbonament);

        JButton btnClase2 = new JButton("Clase");
        btnClase2.setBackground(new Color(255, 255, 0));
        btnClase2.setBounds(316, 0, 89, 23);
        contentPane.add(btnClase2);

        JButton btnRezervari = new JButton("Rezervari");
        btnRezervari.setBackground(new Color(255, 255, 0));
        btnRezervari.setBounds(403, 0, 89, 23);
        contentPane.add(btnRezervari);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 23, 638, 400);
        contentPane.add(layeredPane);
        layeredPane.setLayout(new CardLayout(0, 0));

        JPanel Setari_Cont = new JPanel();
        Setari_Cont.setBackground(new Color(100, 149, 237));
        layeredPane.add(Setari_Cont, "SetariCont");
        Setari_Cont.setLayout(null);
        
    
     // Inițializarea JTable și setarea proprietăților sale
        table = new JTable();
        table.setBackground(new Color(100, 149, 237));
        table.setBounds(40, 89, 264, 239);    
        table.setRowHeight(30);
        table.setShowGrid(false);

        // Crearea și setarea modelului tabelului
        String[] columnNames = {"Atribut", "Valoare"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table.setModel(model);

        // Interogarea și popularea datelor în tabel
        String query = "SELECT clienti.Nume, clienti.Prenume, clienti.Strada, clienti.Numar, clienti.Oras, clienti.Judet, clienti.Email, clienti.Nr_telefon "
                + "FROM clienti JOIN paroleCont ON clienti.ClientID = paroleCont.ClientID "
                + "WHERE clienti.Email = ? AND paroleCont.parola = ?";

   try (PreparedStatement pstmt = con.prepareStatement(query)) {
       pstmt.setString(1, emailCont);
       pstmt.setString(2, parolaCont);

       try (ResultSet rs = pstmt.executeQuery()) {
           ResultSetMetaData metaData = rs.getMetaData();
           int columnCount = metaData.getColumnCount();

           while (rs.next()) {
               for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                   String columnName = metaData.getColumnName(columnIndex);
                   String value = rs.getString(columnIndex);
                   if (value == null) {
                       value = "Neprecizat";
                   }
                   model.addRow(new Object[]{columnName, value});
               }
           }
       }
   } catch (SQLException e) {
       e.printStackTrace();
       // Gestionarea erorilor
   }


        Setari_Cont.add(table);
        JLabel lblnformatii = new JLabel("Informatii Personale");
        lblnformatii.setForeground(SystemColor.desktop);
        lblnformatii.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        lblnformatii.setBounds(40, 58, 186, 20);
        Setari_Cont.add(lblnformatii);
        
        // Butonul de ștergere cont
        JButton btnStergereCont = new JButton("Stergere Cont");
        btnStergereCont.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] optiuni = { "Da", "Nu" };
                int confirm = JOptionPane.showOptionDialog(
                        frame,
                        "Sigur vrei sa stergi datele contului ?",
                        "Confirmare Ștergere",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        optiuni,
                        optiuni[0]);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        // Identifică clientID-ul
                        String clientIDQuery = "SELECT ClientID FROM clienti WHERE Email = ? AND ClientID = (SELECT ClientID FROM paroleCont WHERE parola = ?)";
                        PreparedStatement pstmtClientID = con.prepareStatement(clientIDQuery);
                        pstmtClientID.setString(1, emailCont);
                        pstmtClientID.setString(2, parolaCont);
                        ResultSet rs = pstmtClientID.executeQuery();
                        
                        if (rs.next()) {
                            String clientID = rs.getString("ClientID");
                            clientIDCurent = Integer.parseInt(clientID);
                         // Șterge din tabelul paroleCont
                            String deleteParoleContQuery = "DELETE FROM paroleCont WHERE ClientID = ?";
                            PreparedStatement pstmtDeleteParoleCont = con.prepareStatement(deleteParoleContQuery);
                            pstmtDeleteParoleCont.setString(1, clientID);
                            pstmtDeleteParoleCont.executeUpdate();
                            
                            // Șterge din tabelul abonamente
                            String deleteAbonamenteQuery = "DELETE FROM abonamente WHERE ClientID = ?";
                            PreparedStatement pstmtDeleteAbonamente = con.prepareStatement(deleteAbonamenteQuery);
                            pstmtDeleteAbonamente.setString(1, clientID);
                            pstmtDeleteAbonamente.executeUpdate();
                            
                            // Șterge din tabelul rezervari
                            String deleteRezervariQuery = "DELETE FROM rezervari WHERE ClientID = ?";
                            PreparedStatement pstmtDeleteRezervari = con.prepareStatement(deleteRezervariQuery);
                            pstmtDeleteRezervari.setString(1, clientID);
                            pstmtDeleteRezervari.executeUpdate();
                            
                            

                            // Șterge din tabelul clienti
                            String deleteClientiQuery = "DELETE FROM clienti WHERE ClientID = ?";
                            PreparedStatement pstmtDeleteClienti = con.prepareStatement(deleteClientiQuery);
                            pstmtDeleteClienti.setString(1, clientID);
                            pstmtDeleteClienti.executeUpdate();
                            System.out.println("Contul a fost șters cu succes!");
                            frame.dispose();
                            @SuppressWarnings("unused")
							LoginForm_modificat login = new LoginForm_modificat();
                            JOptionPane.showMessageDialog(frame, "Contul a fost șters cu succes!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Contul nu a fost găsit sau parola este incorectă!", "Eroare", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        // Gestionarea erorilor SQL
                    }
                }
            }
        });


        btnStergereCont.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnStergereCont.setBackground(new Color(255, 0, 0));
        btnStergereCont.setForeground(new Color(0, 0, 0));
        btnStergereCont.setBounds(418, 328, 152, 23);
        Setari_Cont.add(btnStergereCont);
        
        JButton btnNewButton = new JButton("Schimbare Parola");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton.setBackground(new Color(119, 136, 153));
        btnNewButton.setForeground(new Color(0, 0, 0));
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPasswordField parolaNoua = new JPasswordField(10);
                JPasswordField confirmareParolaNoua = new JPasswordField(10);

                JPanel panel = new JPanel();
                panel.add(new JLabel("Parolă nouă:"));
                panel.add(parolaNoua);
                panel.add(Box.createHorizontalStrut(15)); // un spațiu
                panel.add(new JLabel("Confirmare parolă nouă:"));
                panel.add(confirmareParolaNoua);

                Object[] options = {"OK", "Renunțare"};
                int result = JOptionPane.showOptionDialog(null, panel, 
                         "Schimbare Parolă", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                         null, options, options[0]);
                
                if (result == JOptionPane.YES_OPTION) {
                    char[] parola = parolaNoua.getPassword();
                    char[] confirmareParola = confirmareParolaNoua.getPassword();

                    if (Arrays.equals(parola, confirmareParola)) {
                        String parolaNouaText = new String(parola);

                        // Update în baza de date
                        String updateQuery = "UPDATE paroleCont SET parola = ? WHERE ClientID = (SELECT ClientID FROM clienti WHERE Email = ? AND parola = ?)";
                        try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
                            pstmt.setString(1, parolaNouaText); // Noua parolă
                            pstmt.setString(2, emailCont); // Email-ul existent
                            pstmt.setString(3, parolaCont); // Parola veche

                            int affectedRows = pstmt.executeUpdate();
                            if (affectedRows > 0) {
                                System.out.println("Parola schimbată cu succes!");
                                JOptionPane.showMessageDialog(null, "Parola schimbată cu succes!");
                                frame.dispose();
                                @SuppressWarnings("unused")
								LoginForm_modificat login = new LoginForm_modificat();
                            } else {
                                System.out.println("Nu s-a schimbat nicio înregistrare. Verificați datele!");
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            // Gestionarea erorilor
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Parolele nu se potrivesc!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnNewButton.setBounds(418, 294, 152, 23);
        Setari_Cont.add(btnNewButton);
        
        
        JLabel lblNr = new JLabel();
        lblNr.setForeground(new Color(240, 255, 240));
        lblNr.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNr.setBounds(504, 91, 39, 20);
         
       
       
        
        
        
        JTable tableRezervari = new JTable();
        tableRezervari.setBounds(10, 104, 580, 161);
        DefaultTableModel model3 = new DefaultTableModel();
        model3.setColumnIdentifiers(new Object[]{"NumeClasa", "Ora_incepere", "Ora_terminare", "Data_desfasurare", "NumeAntrenor"});
        
        JButton btnAfisareRezervari = new JButton("Afiseaza rezervari");
        btnAfisareRezervari.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 	rezervariClient = "SELECT cl.ClasaID,cl.Nume AS NumeClasa, cl.Ora_incepere, cl.Ora_terminare, ci.Data_desfasurare, CONCAT(a.Nume, ' ', a.Prenume) AS NumeAntrenor "
                         + "FROM rezervari r "
                         + "JOIN clase cl ON r.ClasaID = cl.ClasaID "
                         + "JOIN claseincaperi ci ON cl.ClasaID = ci.ClasaID "
                         + "JOIN antrenori a ON cl.AntrenorID = a.AntrenorID "
                         + "JOIN clienti c ON r.ClientID = c.ClientID "
                         + "WHERE c.Email = ?";

              // Assume 'con' is your active database connection and 'clientEmail' is the email of the client
              try (PreparedStatement pst = con.prepareStatement(rezervariClient)) {
                  pst.setString(1, emailCont); // Replace with the client's email
                  model3.setRowCount(0);
                  ResultSet rs = pst.executeQuery();

                  while (rs.next()) {
                      String numeClasa = rs.getString("NumeClasa");
                      Time oraIncepere = rs.getTime("Ora_incepere");
                      Time oraTerminare = rs.getTime("Ora_terminare");
                      Date dataDesfasurare = rs.getDate("Data_desfasurare");
                      String numeAntrenor = rs.getString("NumeAntrenor");

                      model3.addRow(new Object[]{numeClasa, oraIncepere, oraTerminare, dataDesfasurare, numeAntrenor});
                  }
              } catch (SQLException e2) {
                  e2.printStackTrace(); 
              }
              tableRezervari.setRowHeight(tableRezervari.getRowHeight() + 10);

           // Set intercell spacing for more space between rows and columns
           tableRezervari.setIntercellSpacing(new Dimension(10, 10));
           tableRezervari.setModel(model3);
           // Adjust the column widths
           TableColumnModel columnModel = tableRezervari.getColumnModel();

           // Set width for all columns except the last one
           for (int i = 0; i < columnModel.getColumnCount() - 1; i++) {
               columnModel.getColumn(i).setPreferredWidth(100); // You can adjust the width as needed
           }

           // Set a larger width for the last column (for trainer's name)
           columnModel.getColumn(columnModel.getColumnCount() - 1).setPreferredWidth(200);
        		
        	}
        });
        btnAfisareRezervari.setBounds(10, 55, 140, 23);
        

       
        JButton btnNewButton_1 = new JButton("Numar rezervari active");      
        tableRezervari.setVisible(true);
        btnNewButton_1.addActionListener(new ActionListener() {
        	
        	
        	
        	public void actionPerformed(ActionEvent e) {
        		
        		 String query3 = "SELECT COUNT(rezervari.RezervareID) AS NumberOfReservations " +
        	                "FROM clienti " +
        	                "JOIN paroleCont ON clienti.ClientID = paroleCont.ClientID " +
        	                "JOIN rezervari ON clienti.ClientID = rezervari.ClientID " +
        	                "WHERE clienti.Email = ? AND paroleCont.Parola = ? AND rezervari.Status = 'Activ';";

        	        try (PreparedStatement pst = con.prepareStatement(query3)) {

        			     pst.setString(1, emailCont);
        			     pst.setString(2, parolaCont);
        			
        			     ResultSet rs = pst.executeQuery();
        			
        			     if (rs.next()) {
        			         int numberOfReservations = rs.getInt("NumberOfReservations");
        			         nrRezervari = numberOfReservations;
        			         lblNr.setText(String.valueOf(nrRezervari));
        			         if(nrRezervari == 0) {       			 
        			         	btnAfisareRezervari.setVisible(false);
        			         } else {
        			         	btnAfisareRezervari.setVisible(true);
        			         }
        			     }
        			 } catch (SQLException ex) {
        			     ex.printStackTrace();
        			 }
        		
        		
        	}
        });
        Setari_Cont.add(lblNr);
        
        btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
        btnNewButton_1.setBounds(314, 92, 170, 23);
        Setari_Cont.add(btnNewButton_1);

        JPanel Abonament = new JPanel();
        Abonament.setBackground(new Color(100, 149, 237));
        layeredPane.add(Abonament, "Abonament");
        Abonament.setLayout(null);
        
        JLabel lblStatusAbonament_label = new JLabel("Status Abonament:");
        lblStatusAbonament_label.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblStatusAbonament_label.setBounds(10, 63, 165, 14);
        Abonament.add(lblStatusAbonament_label);
        
        JLabel lblTipAbonament_label = new JLabel("Tip Abonament:");
        lblTipAbonament_label.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblTipAbonament_label.setBounds(10, 88, 141, 20);
        Abonament.add(lblTipAbonament_label);
        
        JLabel lblDataExpirare_label = new JLabel("Data Expirare: ");
        lblDataExpirare_label.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblDataExpirare_label.setBounds(10, 112, 141, 23);
        Abonament.add(lblDataExpirare_label);
        
        
        
        JLabel statusAbonament = new JLabel();
        JLabel tipAbonament = new JLabel();
        JLabel dataExpirare = new JLabel();
        
        
        JComboBox<String> listaTip = new JComboBox<String>();
        listaTip.setToolTipText("Selecteaza tip Upgrade");
        listaTip.setModel(new DefaultComboBoxModel<String>(new String[] {"Selectează tip Upgrade", "BRONZE", "SILVER", "GOLD", "PLATINUM"}));
        listaTip.setBounds(410, 160, 174, 28);
        Abonament.add(listaTip);
        
        JButton btnUpgrade = new JButton("Upgrade Abonament");
        btnUpgrade.setBounds(410, 200, 165, 23);
        Abonament.add(btnUpgrade);
        
       
        JButton btnCumparaAbonament = new JButton("Cumpara Abonament");
        btnCumparaAbonament.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crează dropdown-ul pentru tipul abonamentului
            	JComboBox<String> tipAbonament2 = new JComboBox<>(new String[]{"BRONZE", "SILVER","GOLD", "PLATINUM"});
            	JTextField pret = new JTextField("180 lei"); // Prețul inițial pentru BRONZE
            	pret.setEditable(false);

            	tipAbonament2.addActionListener(new ActionListener() {
            	    public void actionPerformed(ActionEvent e) {
            	        String selected = (String) tipAbonament2.getSelectedItem();
            	        switch (selected) {
            	            case "BRONZE":
            	                pret.setText("180 lei");
            	                break;
            	            case "SILVER":
            	                pret.setText("238 lei");
            	                break;
            	            case "GOLD":
            	                pret.setText("260 lei");
            	                break;
            	            case "PLATINUM":
            	                pret.setText("300 lei");
            	                break;
            	        }
            	    }
            	});

                JCalendar calendar = new JCalendar();
                calendar.setLocale(new Locale("ro", "RO"));
                // Setează calendarul la data curentă
                calendar.setDate(new Date());
                calendar.getMonthChooser().getComboBox().setFont(new Font("Arial", Font.BOLD, 14)); // Modifică fontul pentru luna
                calendar.getYearChooser().getSpinner().setFont(new Font("Arial", Font.BOLD, 14)); // Modifică fontul pentru an

                JTextField selectedDateField = new JTextField(10);
                selectedDateField.setEditable(false);
                updateDateField(selectedDateField, calendar.getDate()); // Inițializează cu data curentă

                // Adaugă un listener la calendar pentru a actualiza JTextField-ul
                calendar.addPropertyChangeListener("calendar", evt -> {
                    updateDateField(selectedDateField, calendar.getDate());
                });
                // Crează câmpul pentru preț
               

                // Crează un array de obiecte pentru a le afișa în dialog
                Object[] message = {
                    "Tip Abonament:", tipAbonament2,
                    "Data Începere:", calendar,
                    "Data Selectata",selectedDateField,
                    "Preț:", pret
                };

                // Afișează dialogul
                int option = JOptionPane.showConfirmDialog(null, message, "Configurare Abonament", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    // Procesează informațiile introduse
                    String tip = (String) tipAbonament2.getSelectedItem();
                    Date dataInceput = calendar.getDate(); // Obține data selectată din calendar
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dataInceput);

                    // Add one month
                    cal.add(Calendar.MONTH, 1);

                    // Get the new date, which is one month after dataInceput
                    Date dataExpirare2 = cal.getTime();
                    String text = pret.getText();
                    String[] parts = text.split(" ");
                    float pretAbonament =  Integer.parseInt(parts[0]);
                    java.sql.Date sqlDataInceput = new java.sql.Date(dataInceput.getTime());
                    java.sql.Date sqlDataExpirare = new java.sql.Date(dataExpirare2.getTime());
                   
                  
                    String sql2 = "INSERT INTO abonamente (ClientID, Tip, Pret, Data_incepere, Data_Expirare, Status) " +
                            "SELECT c.ClientID, ?, ?, ?, ?, 'ACTIV' " +
                            "FROM Clienti c " +
                            "INNER JOIN paroleCont p ON c.ClientID = p.ClientID " +
                            "WHERE c.Email = ? AND p.Parola = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(sql2)) {
                    	    
                    	    pstmt.setString(1, tip);
                    	    pstmt.setFloat(2, pretAbonament);
                    	    pstmt.setDate(3, sqlDataInceput);
                    	    pstmt.setDate(4, sqlDataExpirare);
                    	    pstmt.setString(5, emailCont);
                    	    pstmt.setString(6, parolaCont);

                    	    pstmt.executeUpdate();
                    	    flag = 1;
                    	    System.out.println("Executat cu succes");
                    	    flag = 1;
                    	} catch (SQLException e1) {
                    	    e1.printStackTrace();
                    	   
                    	}
                    
                    abonament = fetchAbonament();
                    btnCumparaAbonament.setVisible(false);
                    lblStatusAbonament_label.setVisible(true);
                    lblTipAbonament_label.setVisible(true);
                    lblDataExpirare_label.setVisible(true);
                    statusAbonament.setVisible(true);
                    tipAbonament.setVisible(true);
                    dataExpirare.setVisible(true);
                    listaTip.setVisible(true);
                    btnUpgrade.setVisible(true);
                    
                    listaTip.setVisible(true);	
            		statusAbonament.setText(abonament[0].toUpperCase());
            		statusAbonament.setFont(new Font("Times New Roman", Font.BOLD, 14));
            		statusAbonament.setBounds(185, 58, 313, 29);
            			if(abonament[0].toUpperCase().equals("ACTIV")) {
            				btnCumparaAbonament.setVisible(false);
            				statusAbonament.setForeground(new Color(0, 255, 0));
            			} else{
            				btnCumparaAbonament.setVisible(true);
            				statusAbonament.setForeground(new Color(255, 0, 0));
            			}
            			
            		Abonament.add(statusAbonament);

            		tipAbonament.setText(abonament[1].toUpperCase());
            		tipAbonament.setForeground(Color.BLACK);
            		tipAbonament.setFont(new Font("Times New Roman", Font.BOLD, 14));
            		tipAbonament.setBounds(154, 93, 95, 14);
            		Abonament.add(tipAbonament);
            		
            		String dataOriginala = abonament[2];
            		SimpleDateFormat formatOriginal = new SimpleDateFormat("yyyy-MM-dd");
            		try {
            			
            		    // Parsează data originală într-un obiect Date
            		    Date data = (Date) formatOriginal.parse(dataOriginala);

            		    // Creează un nou format pentru a afișa data într-un mod mai lizibil
            		    SimpleDateFormat formatLizibil = new SimpleDateFormat("d MMMM yyyy", new Locale("ro", "RO"));
            		    String dataExpirareString = formatLizibil.format(data);

            		    // Creează JLabel-ul cu data formatată
            		    dataExpirare.setText(dataExpirareString);
            		    dataExpirare.setForeground(Color.BLACK);
            		    dataExpirare.setFont(new Font("Times New Roman", Font.BOLD, 14));
            		    dataExpirare.setBounds(137, 119, 130, 14); // Ajustează dimensiunea dacă este necesar
            		    Abonament.add(dataExpirare);
            		} catch (ParseException e1) {
            		    e1.printStackTrace();
            		    // Gestionarea erorilor de parsare aici
            		}
                
                }
            }
        });

        btnCumparaAbonament.setBounds(410, 234, 165, 23);
        Abonament.add(btnCumparaAbonament);
        
        btnUpgrade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tipAbonamentNou = (String) listaTip.getSelectedItem();
               
                System.out.println("Valoarea selectată este: " + tipAbonamentNou);

                // Interogarea SQL pentru actualizarea tipului de abonament
                String updateQuery = "UPDATE abonamente SET Tip = ? WHERE ClientID = (SELECT c.ClientID FROM clienti c JOIN paroleCont p ON c.ClientID = p.ClientID WHERE c.Email = ? AND p.parola = ?)";

                try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
                    pstmt.setString(1, tipAbonamentNou);
                    pstmt.setString(2, emailCont);
                    pstmt.setString(3, parolaCont);

                    int affectedRows = pstmt.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("Tipul de abonament a fost actualizat cu succes în baza de date.");
                        tipAbonament.setText(tipAbonamentNou.toUpperCase());
                        
                        tipAbonament.revalidate();
                        tipAbonament.repaint();
                    } else {
                        System.out.println("Nu s-a actualizat niciun rând. Verificați datele și ClientID.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Gestionarea erorilor aici
                }
            }
        });
        abonament = fetchAbonament();
		if(abonament != null || flag == 1) {
		
		listaTip.setVisible(true);	
		statusAbonament.setText(abonament[0].toUpperCase());
		statusAbonament.setFont(new Font("Times New Roman", Font.BOLD, 14));
		statusAbonament.setBounds(185, 58, 313, 29);
			if(abonament[0].toUpperCase().equals("ACTIV")) {
				btnCumparaAbonament.setVisible(false);
				statusAbonament.setForeground(new Color(0, 255, 0));
			} else{
				btnCumparaAbonament.setVisible(true);
				statusAbonament.setForeground(new Color(255, 0, 0));
			}
			
		Abonament.add(statusAbonament);

		tipAbonament.setText(abonament[1].toUpperCase());
		tipAbonament.setForeground(Color.BLACK);
		tipAbonament.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tipAbonament.setBounds(154, 93, 95, 14);
		Abonament.add(tipAbonament);
		
		String dataOriginala = abonament[2];
		SimpleDateFormat formatOriginal = new SimpleDateFormat("yyyy-MM-dd");
		try {
			
		    // Parsează data originală într-un obiect Date
		    Date data = (Date) formatOriginal.parse(dataOriginala);

		    // Creează un nou format pentru a afișa data într-un mod mai lizibil
		    SimpleDateFormat formatLizibil = new SimpleDateFormat("d MMMM yyyy", new Locale("ro", "RO"));
		    String dataExpirareString = formatLizibil.format(data);

		    // Creează JLabel-ul cu data formatată
		    dataExpirare.setText(dataExpirareString);
		    dataExpirare.setForeground(Color.BLACK);
		    dataExpirare.setFont(new Font("Times New Roman", Font.BOLD, 14));
		    dataExpirare.setBounds(137, 119, 130, 14); // Ajustează dimensiunea dacă este necesar
		    Abonament.add(dataExpirare);
		} catch (ParseException e) {
		    e.printStackTrace();
		    // Gestionarea erorilor de parsare aici
		}
	} else {
		
		listaTip.setVisible(false);
		statusAbonament.setText("NU AVETI NICIUN ABONAMENT ACHIZIONAT !");
		statusAbonament.setFont(new Font("Times New Roman", Font.BOLD, 14));
		statusAbonament.setBounds(50, 65, 600, 20); 
		statusAbonament.setForeground(Color.BLACK);
		Abonament.add(statusAbonament);
		
		btnCumparaAbonament.setBounds(50, 163, 165, 23);
		
		btnUpgrade.setVisible(false);
		tipAbonament.setVisible(false);
		dataExpirare.setVisible(false);
		lblStatusAbonament_label.setVisible(false);
		lblTipAbonament_label.setVisible(false);
		lblDataExpirare_label.setVisible(false);
		 
	}
	    

        JPanel Clase = new JPanel();
        Clase.setBackground(new Color(100, 149, 237));
        layeredPane.add(Clase, "Rezervari");
        Clase.setLayout(null);
        
        String query2 = "SELECT c.Nume, c.Ora_incepere, c.Ora_terminare, c.Pret, ci.Data_desfasurare, a.Nume, a.Prenume " +
                "FROM clase c " +
                "JOIN antrenori a ON c.AntrenorID = a.AntrenorID " +
                "JOIN claseincaperi ci ON c.ClasaID = ci.ClasaID " +
                "WHERE YEAR(ci.Data_desfasurare) = 2024 " +
                "ORDER BY ci.Data_desfasurare ASC;";
        
        String[] columnNames2 = {"Clasa", "Ora incepere", "Ora final", "Pret", "Data desfasurare", "Nume Antrenor"};
        DefaultTableModel model2 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        model2.setColumnIdentifiers(columnNames2);

        // ...
        SimpleDateFormat dateFormatter = new SimpleDateFormat("d MMMM", new Locale("ro", "RO"));
        try (Statement stmt2 = con.createStatement();
             ResultSet rs2 = stmt2.executeQuery(query2)) {
            while (rs2.next()) {
                String className = rs2.getString(1);
                Time startTime = rs2.getTime(2);
                Time endTime = rs2.getTime(3);
                BigDecimal price = rs2.getBigDecimal(4);
                Date date = rs2.getDate(5);
                String formattedDate = dateFormatter.format(date);
                String coachName = rs2.getString(6) + " " + rs2.getString(7);

                model2.addRow(new Object[]{className, startTime, endTime, price, formattedDate, coachName});
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions
        }

        tabelClase = new JTable(model2);
        // ... [rest of your JTable setup] ...
        
       

        // Revalidate and repaint the table if necessary
        tabelClase.revalidate();
        tabelClase.repaint();

        tabelClase.setForeground(new Color(0, 0, 0));
        tabelClase.setBounds(10, 77, 618, 208);
        tabelClase.setRowHeight(tabelClase.getRowHeight() + 10);

     // Adjust the column widths
     TableColumnModel columnModel2 = tabelClase.getColumnModel();

     // Set width for all columns except the last one
     for (int i = 0; i < columnModel2.getColumnCount() - 1; i++) {
         columnModel2.getColumn(i).setPreferredWidth(100); // You can adjust the width as needed
     }

     // Set a larger width for the last column (for trainer's name)
     columnModel2.getColumn(columnModel2.getColumnCount() - 1).setPreferredWidth(200); // Adjust as needed

     // Set intercell spacing for more space between columns
     tabelClase.setIntercellSpacing(new Dimension(10, 1)); // Adjust the width (first parameter) as needed
     tabelClase.addMouseListener(new MouseAdapter() {
    	    public void mouseClicked(MouseEvent e) {
    	        // Get the selected row index
    	        int selectedRow = tabelClase.getSelectedRow();
    	        if (selectedRow >= 0) {
    	            // Retrieve values from the selected row
    	        	
    	        	SimpleDateFormat inputFormatter = new SimpleDateFormat("d MMMM", new Locale("ro", "RO"));
    	        	SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
    	        	String dateString = (String) tabelClase.getValueAt(selectedRow, 4);
    	        	Calendar calendar = Calendar.getInstance();
    	        	int year = calendar.get(Calendar.YEAR);
    	            className = (String) tabelClase.getValueAt(selectedRow, 0);
    	            startTime = (Time) tabelClase.getValueAt(selectedRow, 1);
    	            endTime = (Time) tabelClase.getValueAt(selectedRow, 2);
    	            price = (BigDecimal) tabelClase.getValueAt(selectedRow, 3);
    	          
    	             coachName = (String) tabelClase.getValueAt(selectedRow, 5);
    	             try {
    	            	    java.util.Date date = inputFormatter.parse(dateString + " " + year);
    	            	    String formattedDate = outputFormatter.format(date);
    	            	    dateDesfasurare = java.sql.Date.valueOf(formattedDate);
    	            	} catch (ParseException e2) {
    	            	    e2.printStackTrace(); // Handle parsing error
    	            	}
    	             
    	             System.out.println(className + " " + startTime + " " + endTime + " " + price + " " + dateDesfasurare + " " + coachName);
    	             
    	             
    	             
    	             
    	             
    	        }
    	    }
    	});
        
     	
        JScrollPane scrollPaneClase = new JScrollPane(tabelClase);
        scrollPaneClase.setBounds(10, 49, 618, 236);
        Clase.add(scrollPaneClase);
        
        JLabel lblNewLabel = new JLabel("Clase disponibile");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(10, 17, 208, 21);
        Clase.add(lblNewLabel);
        
        JLabel label = new JLabel("New label");
        label.setBounds(22, 111, 46, 14);
        Clase.add(label);
        
        JButton btnNewButton_2 = new JButton("Rezerva Clasa");
        btnNewButton_2.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        	        // Assuming the class name or some unique identifier is in the first column of the table.
        	        int selectedRowIndex = tabelClase.getSelectedRow();
        	        if (selectedRowIndex != -1) {
        	            // Get class name or identifier from the JTable
        	            String classNameOrIdentifier = (String) tabelClase.getValueAt(selectedRowIndex, 0);

        	            // Now, use the classNameOrIdentifier to find the ClasaID from the database
        	            String findClassIdQuery = "SELECT ClasaID FROM clase WHERE Nume = ?";

        	            // Placeholder for ClasaID
        	            int classId = -1;

        	            // Placeholder for ClientID
        	            int clientId = -1;

        	            try (PreparedStatement findClassStmt = con.prepareStatement(findClassIdQuery)) {

        	                findClassStmt.setString(1, classNameOrIdentifier);
        	                ResultSet classRs = findClassStmt.executeQuery();
        	                if (classRs.next()) {
        	                    classId = classRs.getInt("ClasaID");
        	                }

        	                // Assuming you have the client's email and password
        	               
        	                // Find the ClientID using the email and password
        	                String findClientIdQuery = "SELECT clienti.ClientID FROM clienti JOIN paroleCont ON clienti.ClientID = paroleCont.ClientID WHERE Email = ? AND Parola = ?";

        	                try (PreparedStatement findClientStmt = con.prepareStatement(findClientIdQuery)) {
        	                    findClientStmt.setString(1, emailCont);
        	                    findClientStmt.setString(2, parolaCont);

        	                    ResultSet clientRs = findClientStmt.executeQuery();
        	                    if (clientRs.next()) {
        	                        clientId = clientRs.getInt("ClientID");
        	                    }
        	                }

        	                // Now you have both ClasaID and ClientID, proceed to make the reservation
        	                if (classId != -1 && clientId != -1) {
        	                    String insertReservationQuery = "INSERT INTO rezervari (ClientID, ClasaID, Status, Data_rezervare) VALUES (?, ?, ?, ?)";

        	                    try (PreparedStatement insertStmt = con.prepareStatement(insertReservationQuery)) {
        	                        insertStmt.setInt(1, clientId);
        	                        insertStmt.setInt(2, classId);
        	                        String statusValue = "activ"; // This value must match one of the ENUM options or VARCHAR length.
        	                        insertStmt.setString(3, statusValue);
        	                        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        	                        insertStmt.setTimestamp(4, currentTimestamp);

        	                        int affectedRows = insertStmt.executeUpdate();
        	                        if (affectedRows > 0) {
        	                            JOptionPane.showMessageDialog(null, "Reservare facuta cu succes!", "Success", JOptionPane.INFORMATION_MESSAGE);
        	                        } else {
        	                            JOptionPane.showMessageDialog(null, "Reservation failed.", "Error", JOptionPane.ERROR_MESSAGE);
        	                        }
        	                    }
        	                }

        	            } catch (SQLException ex) {
        	                ex.printStackTrace();
        	                // Handle exceptions
        	            }
        	        }
        	    }
        	});
        btnNewButton_2.setBounds(10, 299, 142, 23);
        Clase.add(btnNewButton_2);
        
        JLabel lblNewLabel_2 = new JLabel("Antrenorul cu cea mai multa experienta: ");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setBounds(177, 302, 256, 14);
        
        textField = new JTextField();
        textField.setBounds(434, 296, 194, 20);
        textField.setEditable(false);
        Clase.add(textField);
        textField.setColumns(10);
        
        Clase.add(lblNewLabel_2);
        
        String sql7 = "SELECT a.Nume AS CoachName, a.Prenume AS CoachSurname " +
                "FROM antrenori a JOIN ( " +
                "    SELECT cl.AntrenorID, SUM(cl.Locuri_disponibile) AS TotalAvailableSpots " +
                "    FROM clase cl " +
                "    GROUP BY cl.AntrenorID " +
                ") sub ON a.AntrenorID = sub.AntrenorID " +
                "WHERE sub.TotalAvailableSpots = ( " +
                "    SELECT MAX(sub2.TotalAvailableSpots) " +
                "    FROM ( " +
                "        SELECT cl2.AntrenorID, SUM(cl2.Locuri_disponibile) AS TotalAvailableSpots " +
                "        FROM clase cl2 " +
                "        GROUP BY cl2.AntrenorID " +
                "    ) sub2 " +
                ") " +
                "LIMIT 1";
        try ( PreparedStatement pst = con.prepareStatement(sql7)) {

               ResultSet rs = pst.executeQuery();
               if (rs.next()) {
                   String coachName = rs.getString("CoachName");
                   String coachSurname = rs.getString("CoachSurname");
                   numeAntrenoPopular = coachName + " " + coachSurname;
               }
           } catch (SQLException ex) {
               ex.printStackTrace(); // Handle SQL exceptions
           }
        
        
        
        JLabel lblNewLabel_2_1 = new JLabel("Antrenorul cel mai popular:");
        lblNewLabel_2_1.setForeground(Color.BLACK);
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_2_1.setBounds(244, 333, 178, 14);
        Clase.add(lblNewLabel_2_1);
        
        textAntrenorPopular = new JTextField();
        textAntrenorPopular.setEditable(false);
        textAntrenorPopular.setColumns(10);
        textAntrenorPopular.setBounds(434, 327, 194, 20);
        textAntrenorPopular.setText(numeAntrenoPopular);
        Clase.add(textAntrenorPopular);
        String coachFullName = "";
        String sql5 = "SELECT a.Nume, a.Prenume FROM antrenori a WHERE a.Salariu = (" +
                "SELECT MAX(Salariu) FROM antrenori) AND EXISTS (" +
                "SELECT 1 FROM clase cl " +
                "JOIN claseincaperi ci ON cl.ClasaID = ci.ClasaID " +
                "WHERE cl.AntrenorID = a.AntrenorID AND YEAR(ci.Data_desfasurare) = 2024)";
        try (PreparedStatement pst = con.prepareStatement(sql5)) {

        	    ResultSet rs = pst.executeQuery();
        	    if (rs.next()) {
        	        String nume = rs.getString("Nume");
        	        String prenume = rs.getString("Prenume");
        	        coachFullName = nume + " " + prenume;
        	       
        	        textField.setText(coachFullName);
        	    }
        	} catch (SQLException e) {
        	    e.printStackTrace(); // Handle SQL exceptions here
        	}
             
        JPanel Rezervari = new JPanel();
        Rezervari.setBackground(new Color(100, 149, 237));
        layeredPane.add(Rezervari, "Clase");
        Rezervari.setLayout(null);
        
      
       
  
        JScrollPane scrollPaneRezervari = new JScrollPane(tableRezervari);
        scrollPaneRezervari.setBounds(10, 104, 580, 175);
        Rezervari.add(scrollPaneRezervari);
        
        JLabel lblNewLabel_3_1 = new JLabel("Interval orar");
        lblNewLabel_3_1.setBounds(164, 87, 86, 14);
        Rezervari.add(lblNewLabel_3_1);
        
      
       
        Rezervari.add(btnAfisareRezervari);
        
        JButton btnNewButton_3 = new JButton("Ultima rezervare ");
        
        JLabel label5 = new JLabel();
        label5.setFont(new Font("Tahoma", Font.BOLD, 11));
        label5.setBounds(164, 301, 426, 23);
        Rezervari.add(label5);
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String sql = "SELECT cl.Nume AS NumeClasa, ci.Data_desfasurare " +
        	             "FROM clase cl " +
        	             "JOIN claseincaperi ci ON cl.ClasaID = ci.ClasaID " +
        	             "JOIN incaperi i ON ci.IncapereID = i.IncapereID " +
        	             "JOIN rezervari r ON cl.ClasaID = r.ClasaID " +
        	             "JOIN clienti c ON r.ClientID = c.ClientID " +
        	             "JOIN paroleCont pc ON c.ClientID = pc.ClientID " +
        	             "WHERE c.Email = ? " +
        	             "AND pc.Parola = ? " + // Assuming you want to filter by password
        	             "AND r.Data_rezervare = ( " +
        	             "    SELECT MAX(r2.Data_rezervare) " +
        	             "    FROM rezervari r2 " +
        	             "    WHERE r2.ClientID = c.ClientID )" ;
        	            

            try (
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, emailCont);
                pst.setString(2, parolaCont);

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                	
                    String className = rs.getString("NumeClasa");
                    Date classDate = rs.getDate("Data_desfasurare");
                    System.out.println("Numele clasei pentru ultima rezervare a clientului: " + className);
                    System.out.println("Data desfășurării clasei: " + classDate);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String formattedDate = dateFormat.format(classDate);
                    String labelText = "Clasa: "+ className + " | Data clasei: " + formattedDate;
               
                    label5.setText(labelText);
                    Rezervari.add(label);
                } else {
                    System.out.println("Nu s-au găsit rezultate.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Gestionarea excepțiilor SQL
            }
        		
        		
        		
        	}
        });
        btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
        btnNewButton_3.setBounds(10, 301, 140, 23);
        Rezervari.add(btnNewButton_3);
        
        JButton btnNewButton_3_1 = new JButton("Numele urmatoarei clase");
        
        
        JLabel label6 = new JLabel();
        label6.setFont(new Font("Tahoma", Font.BOLD, 11));
        label6.setBounds(213, 335, 415, 23);
        btnNewButton_3_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		rezervariClient = "SELECT cl.ClasaID,cl.Nume AS NumeClasa, cl.Ora_incepere, cl.Ora_terminare, ci.Data_desfasurare, CONCAT(a.Nume, ' ', a.Prenume) AS NumeAntrenor "
                        + "FROM rezervari r "
                        + "JOIN clase cl ON r.ClasaID = cl.ClasaID "
                        + "JOIN claseincaperi ci ON cl.ClasaID = ci.ClasaID "
                        + "JOIN antrenori a ON cl.AntrenorID = a.AntrenorID "
                        + "JOIN clienti c ON r.ClientID = c.ClientID "
                        + "WHERE c.Email = ?";
        		
        		String sqlUrmatoareaClasa = "SELECT cl.Nume AS NumeClasa, r.Data_rezervare "
                        + "FROM (" + rezervariClient + ") AS r2 "
                        + "JOIN clase cl ON r2.ClasaID = cl.ClasaID "
                        + "JOIN rezervari r ON cl.ClasaID = r.ClasaID "
                        + "JOIN claseincaperi ci ON cl.ClasaID = ci.ClasaID "
                        + "WHERE ci.Data_desfasurare > CURRENT_TIMESTAMP "
                        + "ORDER BY ci.Data_desfasurare ASC, cl.Ora_incepere ASC "
                        + "LIMIT 1";
        		


        		try (PreparedStatement pst = con.prepareStatement(sqlUrmatoareaClasa)) {
        			 	pst.setString(1, emailCont);
        			    ResultSet rs = pst.executeQuery();

        			    if (rs.next()) {
        			        String numeClasa = rs.getString("NumeClasa");
        			        Timestamp dataRezervare = rs.getTimestamp("Data_rezervare");
        			        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        			        String formattedDate = dateFormat.format(dataRezervare);
        			        String labelText =  numeClasa + " | Momentul rezervarii: " + formattedDate;
        			        label6.setText(labelText);

        			        // Continuați cu procesarea datelor din următoarea clasă disponibilă
        			        System.out.println("Numele Clasei: " + numeClasa);
        			        System.out.println("Data Rezervarii: " + dataRezervare);
        			    } else {
        			        // Nu există următoare clasă disponibilă
        			        System.out.println("Nu există următoare clasă disponibilă.");
        			    }
        			} catch (SQLException e6) {
        			    e6.printStackTrace();
        			}
        }
        			    
        });
        btnNewButton_3_1.setHorizontalAlignment(SwingConstants.LEFT);
        btnNewButton_3_1.setBounds(10, 335, 193, 23);
        Rezervari.add(btnNewButton_3_1);
        
      
        Rezervari.add(label6);
        
        // ActionListener pentru butoane
        btnCont.addActionListener(e -> {
            ((CardLayout) layeredPane.getLayout()).show(layeredPane, "SetariCont");
        });

        btnAbonament.addActionListener(e -> {
            ((CardLayout) layeredPane.getLayout()).show(layeredPane, "Abonament");
        });

        btnClase2.addActionListener(e -> {
            ((CardLayout) layeredPane.getLayout()).show(layeredPane, "Rezervari");
        });

        btnRezervari.addActionListener(e -> {
            ((CardLayout) layeredPane.getLayout()).show(layeredPane, "Clase");
        });
    }
    
    private String[] fetchAbonament() {
        String[] abonament = null; // Inițializat cu null pentru a gestiona cazul în care nu există rezultate

        try {
            String query = "SELECT Status, Tip, Data_expirare FROM abonamente WHERE ClientID = (SELECT ClientID FROM clienti WHERE Email = ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, emailCont);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String status = rs.getString("Status");
                String tip = rs.getString("Tip");
                String dataExpirare = rs.getString("Data_Expirare");
                System.out.println(status + " " + tip + " " + dataExpirare);
                abonament = new String[]{status, tip, dataExpirare}; // Inițializează array-ul cu rezultate
            } else {
                System.out.println("Nu există abonament!");
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestionarea erorilor
        }
        
        return abonament; // Returnează array-ul (poate fi null dacă nu există rezultate)
    }
    private void updateDateField(JTextField field, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", new Locale("ro", "RO"));
        field.setText(sdf.format(date));
    }
}
