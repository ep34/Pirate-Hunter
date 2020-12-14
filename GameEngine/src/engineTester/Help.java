package engineTester;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class Help {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void HelpWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help window = new Help();
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
	public Help() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("The Pirate's Bay - Help");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\ECLIPSE\\Project\\src\\1.png"));
		frame.setBounds(230, 250, 854, 480);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblKeyMapping = new JLabel("Press W A S D to Move");
		lblKeyMapping.setHorizontalTextPosition(SwingConstants.CENTER);
		lblKeyMapping.setForeground(Color.BLACK);
		lblKeyMapping.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblKeyMapping.setHorizontalAlignment(SwingConstants.CENTER);
		lblKeyMapping.setBounds(156, 69, 197, 22);
		frame.getContentPane().add(lblKeyMapping);
		
		JLabel lblMouseTurn = new JLabel("Move Mouse to Turn Camera");
		lblMouseTurn.setForeground(Color.BLACK);
		lblMouseTurn.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMouseTurn.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblMouseTurn.setHorizontalAlignment(SwingConstants.CENTER);
		lblMouseTurn.setBounds(496, 69, 247, 22);
		frame.getContentPane().add(lblMouseTurn);
		
		JLabel lblKeyMappingImg = new JLabel("");
		lblKeyMappingImg.setBackground(Color.WHITE);
		lblKeyMappingImg.setIcon(new ImageIcon("E:\\ECLIPSE\\Project\\src\\kisspng-logo-brand-wasd-ro-key-logo-5b3c426be1e1b0.0472461815306758199252.png"));
		lblKeyMappingImg.setBounds(106, 146, 302, 205);
		frame.getContentPane().add(lblKeyMappingImg);
		
		JLabel lblMouseTurnImg = new JLabel("");
		lblMouseTurnImg.setIcon(new ImageIcon("E:\\ECLIPSE\\Project\\src\\mouse-arrows-move-control-device-512.png"));
		lblMouseTurnImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMouseTurnImg.setBounds(513, 146, 205, 205);
		frame.getContentPane().add(lblMouseTurnImg);
		
		JButton btnExit = new JButton("");
		btnExit.setInheritsPopupMenu(true);
		btnExit.setIgnoreRepaint(true);
		btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnExit.setIcon(new ImageIcon("E:\\ECLIPSE\\Project\\src\\8.png"));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.WHITE);
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnExit.setBounds(10, 11, 40, 40);
		frame.getContentPane().add(btnExit);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\ECLIPSE\\Project\\src\\one-piece-anime-artwork-i6.jpg"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 838, 441);
		frame.getContentPane().add(lblNewLabel);
	}
}
