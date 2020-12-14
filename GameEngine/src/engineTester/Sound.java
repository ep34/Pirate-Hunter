package engineTester;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import shaders.StaticShader;

import java.awt.Font;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Sound {
	
	private static final float RED = 0.5f;
	private static final float GREEN = 0.5f;
	private static final float BLUE = 0.5f;
	
	String clickSound;
	SoundEffect se = new SoundEffect();
	private JFrame frmThePiratesBay;

	/**
	 * Launch the application.
	 */
	public static void music(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sound window = new Sound();
					window.frmThePiratesBay.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void fog()
	{
		StaticShader shader = new StaticShader();
		 shader.loadSkyColor(RED, GREEN, BLUE);	
	}
	public void disbleFog()
	{
		StaticShader shader = new StaticShader();
		 shader.disableSkyColor();	
	}
	
	/**
	 * Create the application.
	 */
	public Sound() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmThePiratesBay = new JFrame();
		frmThePiratesBay.getContentPane().setBackground(Color.BLACK);
		setSystemLook();
		frmThePiratesBay.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\ECLIPSE\\GameEngine\\src\\engineTester\\1.png"));
		frmThePiratesBay.setTitle("The Pirate's Bay");
		frmThePiratesBay.setBounds(100, 100, 450, 300);
		frmThePiratesBay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmThePiratesBay.getContentPane().setLayout(null);
		
		JLabel lblMusic = new JLabel("MUSIC:");
		lblMusic.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 16));
		lblMusic.setBounds(33, 44, 74, 23);
		frmThePiratesBay.getContentPane().add(lblMusic);
		
		JButton btnNewButton = new JButton("OFF");
		btnNewButton.setBounds(112, 45, 89, 23);
		frmThePiratesBay.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ON");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				se.setFile(clickSound);
				se.Play();
			}
		});
		btnNewButton_1.setBounds(232, 45, 89, 23);
		frmThePiratesBay.getContentPane().add(btnNewButton_1);
		
		JLabel lblSound = new JLabel("Sound");
		lblSound.setForeground(Color.GREEN);
		lblSound.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSound.setBounds(32, 19, 46, 14);
		frmThePiratesBay.getContentPane().add(lblSound);
		
		JLabel lblEnviroment = new JLabel("Enviroment:");
		lblEnviroment.setForeground(Color.ORANGE);
		lblEnviroment.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnviroment.setBounds(32, 108, 75, 14);
		frmThePiratesBay.getContentPane().add(lblEnviroment);
		
		
		
		JButton btnAddFog = new JButton("Add Fog");
		btnAddFog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 fog();
			}
		});
		btnAddFog.setBounds(72, 142, 89, 23);
		frmThePiratesBay.getContentPane().add(btnAddFog);
		
		JButton btnDefault = new JButton("Default");
		btnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disbleFog();
			}
		});
		btnDefault.setBounds(202, 142, 89, 23);
		frmThePiratesBay.getContentPane().add(btnDefault);
		clickSound = ".//res//sound.wav";
	}
	public void setSystemLook() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public class SoundEffect
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
		
	}
}

