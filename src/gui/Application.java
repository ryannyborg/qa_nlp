package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import qa_nlp.Database;
import qa_nlp.LanguageProcessor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Application {

	private JFrame frame;
	private JTextField dbUsername;
	private JPasswordField dbPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblQuestionAnsweringSystem = new JLabel("Question Answering System");
		lblQuestionAnsweringSystem.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JTextPane textPane = new JTextPane();
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		
		JLabel lblAnswer = new JLabel("Answer");
		
		JButton btnSubmig = new JButton("Submit");
		btnSubmig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String answer = "";
				
				LanguageProcessor lp = new LanguageProcessor();
				
				System.out.print(textPane.getText());
				
				if(!dbUsername.getText().isEmpty()){
					if(!dbPassword.getText().isEmpty()){
						if(!textPane.getText().isEmpty()){
							answer = lp.ProcessQuestion(textPane.getText(), dbUsername.getText(), dbPassword.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Please enter a question.");
						}
					} else{
						JOptionPane.showMessageDialog(null, "Please enter database credentials for your password.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please enter database credentials for your username.");
				}
				
				textArea.setText(answer);
			}
		});
		
		JLabel lblAskMeA = new JLabel("Ask Me a Question!");
		
		JLabel lblUsername = new JLabel("Username");
		
		JLabel lblDatabaseCredentials = new JLabel("Database Credentials");
		lblDatabaseCredentials.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		dbUsername = new JTextField();
		dbUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		
		dbPassword = new JPasswordField();
		
		JButton rstSemanticNetwork = new JButton("Reset");
		rstSemanticNetwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database db = new Database();
				
				// resets the semantic network to initial state
				db.ResetSemanticNetwork();
			}
		});
		
		JLabel lblSemanticNetworkSettings = new JLabel("Semantic Network Settings");
		lblSemanticNetworkSettings.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(162)
							.addComponent(btnSubmig))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(184)
							.addComponent(lblAnswer)))
					.addPreferredGap(ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rstSemanticNetwork)
							.addGap(86))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSemanticNetworkSettings)
							.addGap(35))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(scrollPane, Alignment.LEADING)
						.addComponent(textPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(117)
							.addComponent(lblAskMeA))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(63)
							.addComponent(lblQuestionAnsweringSystem)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(66)
									.addComponent(lblUsername))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(35)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDatabaseCredentials)
										.addComponent(dbUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(dbPassword, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(69)
							.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))
					.addGap(82))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDatabaseCredentials)
							.addGap(18)
							.addComponent(lblUsername)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dbUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblPassword)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dbPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(34))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblQuestionAnsweringSystem)
							.addGap(18)
							.addComponent(lblAskMeA)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSubmig)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblAnswer))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSemanticNetworkSettings)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rstSemanticNetwork)))
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		
		
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
//		JTextPane textPane_1 = new JTextPane();
//		scrollPane.setColumnHeaderView(textPane_1);
//		textPane_1.setEditable(false);
		frame.getContentPane().setLayout(groupLayout);
	}
}
