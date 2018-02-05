package Paquete;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.Toolkit;

public class Launcher extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Launcher frame = new Launcher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Launcher() {
		setTitle("ArgieBOT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 313, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 0, 0, 5));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(2, 1, 0, 5));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel label = new JLabel("Username");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label);
		
		userField = new JTextField();
		userField.setHorizontalAlignment(SwingConstants.CENTER);
		userField.setColumns(1);
		panel_2.add(userField);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel label_1 = new JLabel("Password");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(passwordField);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"1.) - Follow (Seguir) [README.TXT]", "2.) - UnFollow (Deseguir)", "3.) - Comment (Comentar)", "4.) - Like (Me gusta)"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(list);
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 0, 0, 0));
		
		JButton Boton = new JButton("START");
		Boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int Opcion = list.getSelectedIndex() + 1;				
				String user = userField.getText();
				String pass = passwordField.getText();
				main.setter(Opcion,user,pass);
				if(Opcion > 1)				
					main.main(null);
				else
					Follow_Launcher.main(null);
			}
		});
		panel_3.add(Boton);
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		JTextArea txtrQwer = new JTextArea();
		txtrQwer.setEditable(false);
		txtrQwer.setText("ArgieBOT made by Cospel\r\n\r\nTelegram: @Cospel1");
		panel_5.add(txtrQwer);
	}

}
