package engineTester;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import renderEngine.DisplayManager;


public class Testing {
	
	private volatile boolean isRunning = false;

    private int frameWidth = 1380;
    private int frameHeight = 720;
    private int displayWidth = 1280;
    private int displayHeight = 720;

    private Thread glThread;
    
    public static void cool()
    {
    	try {
			UIManager.setLookAndFeel(UIManager.getLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void test(String[] args) {
        new Testing().runTester();
       
    }

    public void runTester() {
        final JFrame frame = new JFrame("The Pirate's Bay");
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we){
            	 cool();
                int result = JOptionPane.showConfirmDialog(frame, "Do you want to quit the game?");
                if(result == JOptionPane.OK_OPTION){
                    frame.setVisible(false);
                    frame.dispose(); //canvas's removeNotify() will be called
                }
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());

       // JButton button = new JButton("BUTTON");
        JPanel buttonPanel = new JPanel();
       // buttonPanel.add(button);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        Canvas canvas = new Canvas() {
            @Override
            public void addNotify() {
                super.addNotify();
                startGL();
            }

            @Override
            public void removeNotify() {
                stopGL();
                super.removeNotify();
            }
        };
        canvas.setPreferredSize(new Dimension(displayWidth, displayHeight));
        canvas.setIgnoreRepaint(true);

        try {
            Display.setParent(canvas);
        } catch (LWJGLException e) {
            //handle exception
            e.printStackTrace();
        }
        JPanel canvasPanel = new JPanel();
        canvasPanel.add(canvas);
        mainPanel.add(canvasPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);

        //frame.pack();
        frame.setVisible(true);
    }
    
    
    
    
    public void startGL() {
        glThread = new Thread(new Runnable() {
            @Override
            public void run() {
                isRunning = true;
               MainGameLoop.GameLoop(null);
				//Display.setDisplayMode(new DisplayMode(displayWidth, displayHeight));
				//Display.create();

                // init OpenGL here

                while(isRunning) {
                    // render OpenGL here
                    DisplayManager.updateDisplay();
                }

                DisplayManager.closeDisplay();
            }
        }, "LWJGL Thread");

        glThread.start();
    }

    public void stopGL() {
        isRunning = false;
        try {
            glThread.join();
        } catch (InterruptedException e) {
            //handle exception
            e.printStackTrace();
        }
    }

}


