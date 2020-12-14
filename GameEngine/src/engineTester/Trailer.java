package engineTester;

	import java.awt.BorderLayout;
	import java.awt.Canvas;
	import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.sun.jna.Native;
	import com.sun.jna.NativeLibrary;
	import uk.co.caprica.vlcj.binding.LibVlc;
	import uk.co.caprica.vlcj.player.MediaPlayerFactory;
	import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
	import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
	import uk.co.caprica.vlcj.runtime.RuntimeUtil;
	import uk.co.caprica.vlcj.runtime.x.LibXUtil;


	public class Trailer {   

	       public static void mp3(String[] args)
	       {
	    	   JFrame f = new JFrame();
	    	   f.setLocation(0,0);
	    	   f.setSize(1280, 1280);
	    	   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	   f.setVisible(true);
	    	   
	    	   
	    	   Canvas c = new Canvas();
	    	   c.setBackground(Color.BLACK);
	    	   JPanel p = new JPanel();
	    	   p.setLayout(new BorderLayout());
	    	   p.add(c);
	    	   f.add(p);
	    	   
	    	   NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),"F:/ee/VLC");
	    	  // RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
	    	   Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
	    	   
	    	   MediaPlayerFactory mpf = new MediaPlayerFactory();
	    	   EmbeddedMediaPlayer emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(f));
	    	   emp.setVideoSurface(mpf.newVideoSurface(c));
	    	  // emp.toggleFullScreen();
	    	   emp.setEnableMouseInputHandling(false);
	    	   emp.setEnableKeyInputHandling(false);
	    	   
	    	   String file = "f.mp4";
	    	   
	    	   emp.prepareMedia(file);
	    	   
	    	   emp.play();
	    	   
	    	   
	    	   Timer timer = new Timer(37000, new ActionListener() {
	    	        public void actionPerformed(ActionEvent e) {
	    	            f.setVisible(false);
	    	            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	            MainGameLoop.GameLoop(null);
	    	           
	    	        }
	    	    });
	    	   
	    	   timer.setRepeats(false);
	    	    timer.start();
	    	  // MainGameLoop.GameLoop(null);
	    	   
	       }

	        
	    }
	
	

