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
		
	private String[] tabString  = {"1", "2", "3", "4", "5", "6", "7", "8", 
										"9", "0", ".", "=", "C", "+", "-", "*", "/"};
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
		
		//We call the method that creates the calculator's graphic interface.
		initCalculator();
		this.setContentPane(motherPane);
		this.setVisible(true);
	}
	
	//INITCALCULATOR()
	//The method that creates the calculator's graphic interface.
	public void initCalculator() {
		Font police = new Font("DS-digital", Font.BOLD, 20);
		screenLabel = new JLabel("0");
		screenLabel.setFont(police);
		screenLabel.setHorizontalAlignment(JLabel.CENTER);
		screenLabel.setPreferredSize (new Dimension(220, 20));
		
		
		numberPane.setPreferredSize  (new Dimension(165, 225));
		operatorPane.setPreferredSize(new Dimension(55, 225));
		screenPane.setPreferredSize  (new Dimension(220, 30));
		
		/* We create, set their sizes and distribute the buttons,
		 * each in it's designated panel.
		 * But most importantly we add the designated actionListeners 
		 * to each and everyone of them. Those actionListeners are internal classes
		 * that we set up below.
		 */
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
	
	//CALCULATE()
	//The method that will transform the user inputs into mathematical operations.
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
	
	//THE ACTIONLISTNERS
	
	//EQUAL
	class EqualListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Calculate();
			moreThanOneNumber = false;
			newNumber = true;	
		}		
	}
	
	//RESET
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
	
	//ADDITION
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
	
	//SUBSTRACTION
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
	
	//MULTIPLICATION
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
	
	//DIVISION
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
	
	//REGULAR NUMBERS
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
