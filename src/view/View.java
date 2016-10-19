package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public final class View extends JFrame implements ActionListener {

	private JLabel inputLabel = null;
	private JLabel queryLabel = null;
	private JButton inputButton = null;
	private JButton queryButton = null;
	public View(){
		initComponents();
		SetupListeners();
		SetupGui();
	}
	
	private void SetupListeners() {
		
		queryButton.addActionListener(this);
		inputButton.addActionListener(this);
	}

	private void SetupGui() {
	this.setTitle("MOH Expert System");
	this.setLayout(new GridLayout(3, 2));
	this.setSize(250, 250);
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.addWindowListener( new WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
			
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	
		
	}

	private void initComponents() {
		inputLabel = new JLabel("");
		queryLabel = new JLabel("");
		queryButton = new JButton("");
		inputButton = new JButton("");
	}

	public void setQueryText(String str){
		this.queryLabel.setText(str);
	}
	public void setInputText(String str){
		this.inputLabel.setText(str);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
