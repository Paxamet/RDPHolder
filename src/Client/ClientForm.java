package Client;
import java.awt.EventQueue;

import javax.swing.JFrame;

import DBManager.DBManager;
import DBManager.DataLines;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTree;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClientForm {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientForm window = new ClientForm();
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
	public ClientForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				onWindowOpened(e);
			}
			@Override
			public void windowClosing(WindowEvent e) {
				onWindowClosing(e);
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("RDPHolder");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTree tree = new JTree();
		tree.setBounds(230, 0, 204, 261);
		frame.getContentPane().add(tree);
		
		JButton btnNewButton = new JButton("test write");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dbm.AddNewRDPLine(new DataLines.RDPLine());
			}
		});
		btnNewButton.setBounds(10, 22, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	
	////implementations
	
	private static DBManager dbm = new DBManager();
	
	public void onWindowOpened(WindowEvent we) {
		dbm.Connect();
	}
	
	
	public void onWindowClosing(WindowEvent we) {
		dbm.Close();
	}
}
