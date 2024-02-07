package Client;
import java.awt.EventQueue;

import javax.swing.JFrame;

import DBManager.DBManager;
import DBManager.DataLines;
import DBManager.DataLines.RDPLine;
import DBManager.r_RDP;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;

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
		
		JButton btnNewButton = new JButton("test write");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//dbm.AddNewRDPLine(new DataLines.RDPLine());
				//System.out.println(r_RDP.getTableName(r_RDP.class));
				DataLines.RDPLine testData = new RDPLine("brc1", "", -1);
				dbm.getRDPRec().addNewLine(testData);
			}
		});
		btnNewButton.setBounds(10, 22, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JTree RDPTree = new JTree(fillRDPTree());
		RDPTree.setBounds(248, 0, 186, 261);
		frame.getContentPane().add(RDPTree);
	}
	
	
	////implementations
	
	private static DBManager dbm = new DBManager();
	
	public void onWindowOpened(WindowEvent we) {
		if(dbm.connect()) {
			dbm.initTables();
			dbm.checkTables();
		}
	}
	
	
	public void onWindowClosing(WindowEvent we) {
		dbm.close();
	}
	
	public Hashtable fillRDPTree() {
		Hashtable root = new Hashtable();
		Hashtable subTree = new Hashtable();
		root.put("Узел 1", subTree);
		subTree.put("Elem 1", new Integer(101));
		subTree.put("Elem 2", new Integer(102));
		Hashtable subTree2 = new Hashtable();
		root.put("Узел 2", subTree2);
		subTree2.put("Elem 2_1", new Integer(201));
		subTree2.put("Elem 2_2", new Integer(202));
		subTree2.put("Elem 2_3", new Integer(203));
		subTree2.put("Elem 2_4", new Integer(204));
		return root;
	}
}
