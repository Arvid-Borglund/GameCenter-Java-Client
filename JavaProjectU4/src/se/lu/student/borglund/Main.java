package se.lu.student.borglund;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;




public class Main {
	private JFrame frame;
	
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
	    frame = new JFrame();
	    frame.setSize(1280, 720);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    // create the GUI panel and add it to the frame
	    MyGuiCronus gui = new MyGuiCronus();
	    frame.getContentPane().add(gui);

	    frame.setVisible(true);

	    // Run the runGameCenter() method on a separate thread
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            gui.runCronusApp();
	        }
	    });
	}

}
