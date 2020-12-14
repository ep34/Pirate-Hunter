package entites;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class GameOver {

	private JFrame frame;
	
	
	/**
	 * Launch the application.
	 */
	public static void gameOver(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOver window = new GameOver();
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
	public GameOver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//frame.setState(Frame.ICONIFIED);
		frame.setBounds(100, 100, 1371, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("E:\\ECLIPSE\\GameEngine\\res\\gameOver.png"));
		lblNewLabel_1.setBounds(391, 38, 325, 66);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\ECLIPSE\\GameEngine\\res\\crush.jpg"));
		lblNewLabel.setBounds(0, 0, 1280, 720);
		frame.getContentPane().add(lblNewLabel);
	}
}
