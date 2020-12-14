package engineTester;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.lwjgl.input.Keyboard;

import engineTester.MainGameLoop.SoundEffect;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Cursor;
import java.awt.Font;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	
	static String clickSound;
	  
	static SoundEffect bgm = new SoundEffect();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	void setSystemLook()
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		
	}
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("The Pirate's Bay");
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage("E:/ECLIPSE/Project/src1.png"));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\ECLIPSE\\GameEngine\\src\\engineTester\\1.png"));
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setMinimumSize(new Dimension(1280, 720));
		frame.setMaximumSize(new Dimension(1280, 720));
		frame.setPreferredSize(new Dimension(1280, 720));
		frame.getContentPane().setSize(new Dimension(1280, 720));
		frame.getContentPane().setMinimumSize(new Dimension(1280, 720));
		frame.getContentPane().setMaximumSize(new Dimension(1280, 720));
		frame.getContentPane().setPreferredSize(new Dimension(1280, 720));
		frame.getContentPane().setLayout(null);
		
		JButton btnNewGame = new JButton("New Game");
		clickSound = ".//res//mainmenu.wav";
		
		bgm.setFile(clickSound);
		bgm.Play();
		bgm.loop();
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				bgm.stop();
				//Trailer.mp3(null);
				MainGameLoop.GameLoop(null);
				
			}
		});
		btnNewGame.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewGame.setBounds(440, 167, 400, 70);
		btnNewGame.setMinimumSize(new Dimension(500, 70));
		btnNewGame.setBackground(Color.WHITE);
		btnNewGame.setPreferredSize(new Dimension(500, 70));
		btnNewGame.setMaximumSize(new Dimension(1280, 720));
		frame.getContentPane().add(btnNewGame);
		
		JButton btnHighScore = new JButton("High Score");
		btnHighScore.setBounds(440, 267, 400, 70);
		btnHighScore.setMinimumSize(new Dimension(500, 70));
		btnHighScore.setMaximumSize(new Dimension(500, 70));
		btnHighScore.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHighScore.setBackground(Color.WHITE);
		frame.getContentPane().add(btnHighScore);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Help.HelpWindow();
			}
		});
		btnHelp.setBackground(Color.WHITE);
		btnHelp.setBounds(440, 367, 400, 70);
		frame.getContentPane().add(btnHelp);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound.music(null);
			}
		});
		btnOptions.setBackground(Color.WHITE);
		btnOptions.setBounds(440, 467, 400, 70);
		frame.getContentPane().add(btnOptions);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(440, 567, 400, 70);
		frame.getContentPane().add(btnExit);
		
		JLabel lblName = new JLabel("The Pirate's Bay");
		lblName.setForeground(Color.BLACK);
		lblName.setIcon(null);
		lblName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Papyrus", lblName.getFont().getStyle() | Font.BOLD, lblName.getFont().getSize() + 70));
		lblName.setPreferredSize(new Dimension(150, 40));
		lblName.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblName.setBounds(97, 11, 1080, 145);
		frame.getContentPane().add(lblName);
		
		JLabel lblBackgroumd = new JLabel("");
		lblBackgroumd.setIcon(new ImageIcon("E:\\ECLIPSE\\Project\\src\\one-piece-anime-artwork-i6.jpg"));
		lblBackgroumd.setBounds(0, 0, 1280, 720);
		frame.getContentPane().add(lblBackgroumd);
		frame.setBounds(0, 0, 640, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static class SoundEffect
	{
		Clip clip;
		public void setFile(String soundFileName)
		{
			try
			{
				File file = new File(soundFileName);
				AudioInputStream sound = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(sound);
				
			}
			catch(Exception e)
			{
				
			}
		}
		public void Play()
		{
			clip.setFramePosition(0);
			clip.start();
		}
		public void loop()
		{
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		public void stop()
		{
			clip.stop();
			clip.close();
		}
		
	}
	
}
