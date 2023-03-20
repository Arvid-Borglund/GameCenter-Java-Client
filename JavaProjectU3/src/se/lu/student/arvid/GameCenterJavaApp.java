package se.lu.student.arvid;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class GameCenterJavaApp {
	


	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameCenterJavaApp window = new GameCenterJavaApp();
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
	public GameCenterJavaApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	    frame = new JFrame();
	    frame.setSize(1280, 720);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    // create the GUI panel and add it to the frame
	    MyGUI gui = new MyGUI();
	    frame.getContentPane().add(gui);

	    frame.setVisible(true);

	    // Run the runGameCenter() method on a separate thread
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            gui.runGameCenter();
	        }
	    });
	}

	

}
