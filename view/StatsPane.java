package view;



import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import javax.swing.JTextField;

import models.Model;

public class StatsPane implements Observer{

	private Model m_model;
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JPanel controlPanel;
	private JTextField numAnimalsText;

	
	public StatsPane(Model model) {
		m_model = model;
		m_model.addObserver(this);
		
		mainFrame = new JFrame("World Statistics");
	    mainFrame.setSize(300,300);
	    mainFrame.setLayout(new GridLayout(3, 1));
        headerLabel = new JLabel("", JLabel.CENTER);        
	    controlPanel = new JPanel();
	    controlPanel.setLayout(new FlowLayout());

	    mainFrame.add(headerLabel);
	    mainFrame.add(controlPanel);
	    headerLabel.setText("Statistics in World"); 
	    JLabel  numAnimalslabel= new JLabel("Number of Animals ", JLabel.RIGHT);
	    //JLabel futureLabel = new JLabel("Future: ", JLabel.CENTER);
	    numAnimalsText = new JTextField(6);
	   //   final JTextField futureText = new JPasswordField(6);      

	      JButton futureButton = new JButton("Future Use");
	      /*
	      futureButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	           //Do some future function
	         }
	      }); 
*/
	      
	      controlPanel.add(numAnimalslabel);
	      controlPanel.add(numAnimalsText);
	//      controlPanel.add(futureLabel);       
	//      controlPanel.add(futureText);
	      controlPanel.add(futureButton);
	      mainFrame.setVisible(true);  
	      

	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Model.StatsObj) {
			try {
				Method method = arg1.getClass().getMethod("getNumAnimals");
				method.invoke(arg1);
				Object msgs = method.invoke(arg1);
				Integer numAnimals = (Integer)msgs;
				numAnimalsText.setText(numAnimals.toString());
			} catch (NoSuchMethodException e) { e.printStackTrace();}
			  catch (IllegalArgumentException e) { e.printStackTrace();}
			  catch (IllegalAccessException e) { e.printStackTrace();}
			  catch (InvocationTargetException e) { e.printStackTrace();}
		} 
		
	}
	
}
