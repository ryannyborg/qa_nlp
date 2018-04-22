package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import qa_nlp.LanguageProcessor;

public class Application {

	private JFrame frame;

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
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblQuestionAnsweringSystem = new JLabel("Question Answering System");
		lblQuestionAnsweringSystem.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JLabel lblWhatWouldYou = new JLabel("What would you like to know?");
		
		JTextPane textPane = new JTextPane();
		
		JLabel lblAnswer = new JLabel("Answer");
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		
		JButton btnSubmig = new JButton("Submit");
		btnSubmig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LanguageProcessor lp = new LanguageProcessor();
				lp.GetUserInput(textPane.getText());
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(98)
							.addComponent(lblQuestionAnsweringSystem))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(121)
							.addComponent(lblWhatWouldYou))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(171)
							.addComponent(btnSubmig))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
								.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(194)
							.addComponent(lblAnswer)))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(lblQuestionAnsweringSystem)
					.addGap(18)
					.addComponent(lblWhatWouldYou)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSubmig)
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addComponent(lblAnswer)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
