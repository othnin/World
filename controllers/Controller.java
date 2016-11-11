package controllers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import models.Model;
import view.LoadMapPane;
import view.StatsPane;
import view.View;

public class Controller {

	static Boolean mapLoaded = false;
	
	protected Model m_model;
	protected View view;
	private ActionListener startBtnActionListener, stopBtnActionListener, loadFileMapActionListener;
	
	
	public Controller(View view, Model model) {
		this.view = view;
		m_model = model;
		controls();
	}
		
	public void controls() {
		
		startBtnActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (mapLoaded) {
					StatsPane statPage = new StatsPane(m_model);
					m_model.startSimulation();					
				}
				else
					System.out.println("Error: Need to load a map");					
			}
		};
		view.getStartButton().addActionListener(startBtnActionListener);
		
		stopBtnActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				m_model.stopSimulation();
			}
		};
		view.getStopButton().addActionListener(stopBtnActionListener);
			
		loadFileMapActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (mapLoaded) {
					System.out.println("Map already loaded, TODO: Make a remove map option");
					return;
				}
				File fl = view.displayLoadFile();
				m_model.readMapFile(fl.getAbsolutePath());
				mapLoaded = true;
				displayMap();
			}
		};
		view.getLoadMapMenu().addActionListener(loadFileMapActionListener);
				
	}  //end controls
	
	public void displayMap() {
		//TODO: (tight coupling) had to make emptyMapPane public in view and reference it here. Would like to be able to remove w/o knowing explicitly what it is
		//BorderLayout layout = (BorderLayout) view.theFrame.getLayout();
		//view.theFrame.remove(layout.getLayoutComponent(BorderLayout.CENTER));
	//	JPanel toRemove = view.getCenterPanel();
		
		view.theFrame.getContentPane().remove(view.emptyMapPane);
		LoadMapPane loadMapPane = new LoadMapPane(m_model, view);
		
		view.theFrame.getContentPane().add(BorderLayout.CENTER, loadMapPane);
		view.theFrame.revalidate();
	//	view.theFrame.pack();
		
	}

}
