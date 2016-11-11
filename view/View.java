package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class View {

	public JPanel mainPanel;
	public JFrame theFrame;
	JMenuBar menuBar;
	JMenu menu,fileMenu, editMenu;
	JMenuItem menuItem; 
	JTextArea outText;
	private Component modalToComponent;
	JButton startBtn, stopBtn;
	JMenuItem loadMapMenuItem, rndMapMenuItem;
	public EmptyMapPane emptyMapPane;
	
	public View() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
		
		theFrame = new JFrame("World Simulation");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


		//start menu
		menuBar = new JMenuBar();			

		//create menus
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		
		//create menu items
		loadMapMenuItem = new JMenuItem("Load Map");
		loadMapMenuItem.setMnemonic(KeyEvent.VK_L);
		
		rndMapMenuItem = new JMenuItem("Random Map");
		rndMapMenuItem.setMnemonic(KeyEvent.VK_R);

	//	MenuItemListener menuItemListener = new MenuItemListener();
	//	loadMapMenuItem.addActionListener(new controller.);
	//	rndMapMenuItem.addActionListener(menuItemListener);
		
		fileMenu.add(loadMapMenuItem);
		fileMenu.add(rndMapMenuItem);
		menuBar.add(fileMenu);
		menuBar.add(editMenu);

		theFrame.setJMenuBar(menuBar);
		//	end menu

		//top
		JPanel topPanel = new JPanel();
		startBtn = new JButton("Start");
		stopBtn = new JButton("Stop");
		topPanel.add(startBtn);
		topPanel.add(stopBtn);
		theFrame.getContentPane().add(BorderLayout.NORTH, topPanel);

		//center
		emptyMapPane = new EmptyMapPane();
		theFrame.add(BorderLayout.CENTER, emptyMapPane);
		
		//bottom
		outText = new JTextArea(10,40);
		outText.setLineWrap(true);
		outText.setEditable(false);

		JScrollPane scroller = new JScrollPane(outText);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		background.add(scroller);
		theFrame.getContentPane().add(BorderLayout.SOUTH,scroller);
		outText.setText("Output text here\n");

		theFrame.pack();
		theFrame.setVisible(true);
	}
	
	public JButton getStartButton() {
		return startBtn;
	}
	
	public JButton getStopButton() {
		return stopBtn;
	}
	
	public JMenuItem getLoadMapMenu() {
		return loadMapMenuItem;
	}
	
	public JPanel getCenterPanel() {
		//TODO: View.getCenterPanel doesnt work. Need to return the current JPanel.
		return (JPanel) theFrame.getComponent(1);
		//BorderLayout layout = (BorderLayout) mainPanel.getLayout();
		//return (JPanel) layout.getLayoutComponent(BorderLayout.CENTER);
	}
	
	public File displayLoadFile() {	
		final JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(modalToComponent) == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			return file;
		}
		return null;
	}

	public void setTextBox(String msg) {
		outText.append(msg + "\n");
	}
}
