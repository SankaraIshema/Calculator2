import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator extends JFrame {
		
	private String[] tabString  = {"1", "2", "3", "4", "5", "6", "7", 
										"8", "9", "0", ".", "=", "C", "+", "-", "*", "/"};
	private String operator = "";
	private double tempNumber = 0.0;
	private boolean moreThanOneNumber = false;
	private boolean newNumber         = false;
	
	private JButton[] tabButton = new JButton[tabString.length];
	private JPanel motherPane   = new JPanel();
	private JLabel screenLabel  = new JLabel();
	private JPanel screenPane   = new JPanel();
	private JPanel operatorPane = new JPanel();
	private JPanel numberPane   = new JPanel();
	private Dimension dim1      = new Dimension(50, 40);
	private Dimension dim2      = new Dimension(50, 31);
	
	
	public Calculator() {
		this.setTitle("Calculator");
		this.setSize(240, 260);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		initCalculator();
		this.setContentPane(motherPane);
		this.setVisible(true);
	}
	
	public void initCalculator() {
		
		Font police = new Font("DS-digital", Font.BOLD, 20);
		screenLabel = new JLabel("0");
		screenLabel.setFont(police);
		screenLabel.setPreferredSize(new Dimension(220, 20));
		screenLabel.setHorizontalAlignment(JLabel.CENTER);
		
		numberPane.setPreferredSize  (new Dimension(165, 225));
		operatorPane.setPreferredSize(new Dimension(55, 225));
		screenPane.setPreferredSize  (new Dimension(220, 30));
		
		for(int i = 0; i < tabString.length; i++) {
			tabButton[i] = new JButton(tabString[i]);
			tabButton[i].setPreferredSize(dim1);
			switch(i) {
			case 11 : 
				tabButton[i].addActionListener(new EqualListener());
				numberPane.add(tabButton[i]);
				break;
				
			case 12 : 
				tabButton[i].addActionListener(new ResetListener());
				tabButton[i].setPreferredSize(dim2);
				operatorPane.add(tabButton[i]);
				break;
				
			case 13 : 
				tabButton[i].addActionListener(new AdditionListener());
				tabButton[i].setPreferredSize(dim2);
				operatorPane.add(tabButton[i]);
				break;
				
			case 14 :
				tabButton[i].addActionListener(new SubstractionListener());
				tabButton[i].setPreferredSize(dim2);
				operatorPane.add(tabButton[i]);
				break;
				
			case 15 :
				tabButton[i].addActionListener(new MultiplicationListener());
				tabButton[i].setPreferredSize(dim2);
				operatorPane.add(tabButton[i]);
				break;
				
			case 16 :
				tabButton[i].addActionListener(new DivisionListener());
				tabButton[i].setPreferredSize(dim2);
				operatorPane.add(tabButton[i]);
				break;
				
			default :
				tabButton[i].addActionListener(new NumberListener());
				numberPane.add(tabButton[i]);
				break;
			}
		}
		screenPane.add(screenLabel);
		screenPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		motherPane.add(screenPane,   BorderLayout.NORTH);
		motherPane.add(numberPane,   BorderLayout.CENTER);
		motherPane.add(operatorPane, BorderLayout.EAST);
	}
	
	public void Calculate() {
		switch(operator) {
		case "+" :
			tempNumber = tempNumber + Double.parseDouble(screenLabel.getText());
			
			screenLabel.setText(Double.toString(tempNumber));
			
			break;
			
		case "-" :
			tempNumber = tempNumber - Double.parseDouble(screenLabel.getText());
			
			screenLabel.setText(Double.toString(tempNumber));
			
			break;
			
		case "*" :
			tempNumber = tempNumber * Double.parseDouble(screenLabel.getText());
			
			screenLabel.setText(Double.toString(tempNumber));
			
			break;
			
		case "/" :
			try {
				tempNumber = tempNumber / Double.parseDouble(screenLabel.getText());
				
				screenLabel.setText(Double.toString(tempNumber));
				
			} 
			catch (ArithmeticException e) { screenLabel.setText("0"); }
			
			break;
		}
	}
	
	class EqualListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Calculate();
			moreThanOneNumber = false;
			newNumber = true;
			
		}		
	}
	
	class ResetListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			screenLabel.setText("0");
			moreThanOneNumber = false;
			newNumber = true;
			tempNumber = 0;
			operator = "";
		}		
	}
	
	class AdditionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			operator = "+";
			newNumber = true;
			
			if(!moreThanOneNumber) {
				tempNumber = Double.parseDouble(screenLabel.getText());
				moreThanOneNumber = true;
			}
			else {
				Calculate();
				screenLabel.setText(Double.toString(tempNumber));
			}
		}		
	}
	
	class SubstractionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			operator = "-";
			newNumber = true;
			
			if(!moreThanOneNumber) {
				tempNumber = Double.parseDouble(screenLabel.getText());
				moreThanOneNumber = true;
			}
			else {
				Calculate();
				screenLabel.setText(Double.toString(tempNumber));
			}
		}		
	}
	
	class MultiplicationListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			operator = "*";
			newNumber = true;
			
			if(!moreThanOneNumber) {
				tempNumber = Double.parseDouble(screenLabel.getText());
				moreThanOneNumber = true;
			}
			else {
				Calculate();
				screenLabel.setText(Double.toString(tempNumber));
			}
			
		}		
	}
	
	class DivisionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			operator = "/";
			newNumber = true;
			
			if(!moreThanOneNumber) {
				tempNumber = Double.parseDouble(screenLabel.getText());
				moreThanOneNumber = true;
			}
			else {
				Calculate();
				screenLabel.setText(Double.toString(tempNumber));
			}
		}		
	}
	
	class NumberListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String str = ((JButton)arg0.getSource()).getText();
			
			if(!newNumber) {
				if(!screenLabel.getText().equals("0")) {
					str = screenLabel.getText() + str;
				}
			}	
			else {
				newNumber = false;
			}
			screenLabel.setText(str);
		}		
	}	
}
