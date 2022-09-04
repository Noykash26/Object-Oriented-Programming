import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GUI {

	private static JFrame frame;
	private static JTextField numOfNurses;
	private static JTextField timeToEndTheShift;
	private static JLabel lblNurseNum;
	private static JLabel lblclosingER;
	private static JLabel lblwelcome;
	private static int numnurses;
	private static int timeToEnd;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() {
		initialize();
	}

	public static void ReadInput() // enter the duration time and number of guard
	{
		timeToEnd = Integer.parseInt(timeToEndTheShift.getText());
		numnurses = Integer.parseInt(numOfNurses.getText());
		if (numnurses > 4) // if the number is not in range
		{
			JOptionPane.showMessageDialog(frame, "the maximum num of nurse is 4. please try again");
			numnurses = Integer.parseInt(numOfNurses.getText());
		}
		if (numnurses <= 0) {
			JOptionPane.showMessageDialog(frame, "the minimum num of nurse is 0. please try again");
			numnurses = Integer.parseInt(numOfNurses.getText());
		}
		if (timeToEnd < 0) {
			JOptionPane.showMessageDialog(frame, "the time set is 0.");
			timeToEnd = 0;
		}
		if (timeToEnd > 24) {
			JOptionPane.showMessageDialog(frame, "the time set is 24.");
			timeToEnd = 24;
		}
	}

	private static void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 240, 245));
		frame.setBounds(50, 50, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnStart = new JButton("start the day ");
		btnStart.setBackground(Color.LIGHT_GRAY);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReadInput();
				try {
					String voters = "voters data.txt";
					String idList = "id list.txt";
					ER m = new ER("patients.txt", numnurses,timeToEnd);
					m.startER();
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});
		btnStart.setFont(new Font("3ds", Font.BOLD, 24));
		btnStart.setBounds(126, 271, 231, 35);
		btnStart.setForeground(Color.DARK_GRAY);
		frame.getContentPane().add(btnStart);

		JButton btnExit = new JButton("X");
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setFont(new Font("3ds", Font.BOLD, 22));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setBounds(387, 11, 75, 35);
		frame.getContentPane().add(btnExit);

		numOfNurses = new JTextField();
		numOfNurses.setFont(new Font("Tahoma", Font.PLAIN, 20));
		numOfNurses.setText("0");
		numOfNurses.setBounds(325, 75, 75, 45);
		frame.getContentPane().add(numOfNurses);
		numOfNurses.setColumns(10);

		timeToEndTheShift = new JTextField();
		timeToEndTheShift.setFont(new Font("Tahoma", Font.PLAIN, 20));
		timeToEndTheShift.setText("8");
		timeToEndTheShift.setBounds(325, 154, 75, 45);
		frame.getContentPane().add(timeToEndTheShift);
		timeToEndTheShift.setColumns(10);

		lblwelcome = new JLabel("Welcome to Fatma ER system!");
		lblwelcome.setFont(new Font("Corbel", Font.BOLD, 20));
		lblwelcome.setForeground(Color.BLACK);
		lblwelcome.setBounds(72, 19, 291, 45);
		frame.getContentPane().add(lblwelcome);

		lblNurseNum = new JLabel("Nurse Number:");
		lblNurseNum.setFont(new Font("3ds", Font.PLAIN, 20));
		lblNurseNum.setForeground(Color.DARK_GRAY);
		lblNurseNum.setBounds(45, 71, 239, 40);
		frame.getContentPane().add(lblNurseNum);

		lblclosingER = new JLabel("Closing ER time:");
		lblclosingER.setFont(new Font("3ds", Font.PLAIN, 20));
		lblclosingER.setForeground(Color.DARK_GRAY);
		lblclosingER.setBounds(45, 155, 260, 40);
		frame.getContentPane().add(lblclosingER);

	}
}