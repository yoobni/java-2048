//library & framework import
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	private JFrame frame;
	//Launch 2048 
	public static void main(String[] args) {
		//Runnable interface implementation. If not have return value, use this.
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//exception process
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//create main board
	public Main() {
		CreateBoard();
	}
	//start main board
	private void CreateBoard() {
		//Implementation board frame
		frame = new JFrame("2048");
		System.out.println("Set Main Board");
		frame.getContentPane().setBackground(new Color(249, 247, 234));
		frame.getContentPane().setFont(new Font("돋움", Font.BOLD, 12));
		//screen size parser
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((screenSize.width-600)/2, (screenSize.height-400)/2, 600, 600);
		frame.getContentPane().setLayout(null);
		//create explanation button
		JButton btnExplanation = new JButton("Explanation");
		btnExplanation.setFont(new Font("돋움", Font.BOLD, 30));
		//event listener. If click this button, show explanation dialog
		btnExplanation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, "Hello player, this is a 2048 explanation.\n"
					+ "\n\njust in case you didn't know how to play.\n"
					+ "\n1. Use your arrow keys to move the tiles.\n"
					+ "\n2. If you click right-arrow-key, tiles move to down\n"
					+ "\n3. When two tiles with the same number touch, the merge into one!\n"
					+ "\n\nCreate on : 2016. 11. 23\n"
					+ "\nAuthor : youben Go\n "
				);
			}
		});
		//locate explanation button 
		btnExplanation.setBounds(50, 400, 200, 150);
		frame.getContentPane().add(btnExplanation);
		//create exit button
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Castellar", Font.BOLD, 30));
		//event listener. If click this button, exit java program
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("System exit");
				System.exit(0); //exit command
			}
		});
		//locate exit button
		btnExit.setBounds(350, 400, 200, 150);
		btnExit.setBackground(new Color(249, 247, 234));
		frame.getContentPane().add(btnExit);
		//create game start button
		JButton btn2048 = new JButton("Start 2048");
		btn2048.setFont(new Font("Castellar", Font.BOLD, 70));
		//event listener. If click this button, show 2048 and dispose main
		btn2048.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//game start!
				new Java2048();
				//exit main window
				frame.dispose();
			}
		});
		//locate game start button
		btn2048.setBounds(50, 50, 500, 300);
		frame.getContentPane().add(btn2048);
	}

}
