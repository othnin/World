package view;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import animals.Animals;
import models.Model;
import view.View;

public class LoadMapPane extends JPanel implements Observer{

    /**
	 * 
	 */
	//TODO: Create a resource location for this info
	public static final Color Woods = new Color(0, 102, 0);
	public static final Color Water = new Color(0, 153, 153);
	public static final Color Plains = new Color(102, 153, 0);
	public static final Color Hills = new Color(51, 153, 0);
	
	public enum TerrainType {
		Woods, Water, Plains, Hills
	}
	
	public static final String Zebra = "src/images/animal icons/zebra.png";
	public static final String Hippo ="src/images/animal icons/hippopotamus.png";
	public static final String Elephant = "src/images/animal icons/elephant.png";
	public static final String Grass="src/images/plant icons/grass.png";

	
	
	
	private static final long serialVersionUID = 6758049210720061999L;
	private int columnCount;
    private int rowCount;
    private List<Rectangle> cells;
    private Point selectedCell;
    private int xOffset=0;
    private int yOffset=0;
    private Model m_model;
    private View m_view;
    

    public LoadMapPane(Model model, View view) {
    	m_model = model;
    	m_view = view;
    	m_model.addObserver(this);
    	columnCount = m_model.getMapSizeX();
    	rowCount = m_model.getMapSizeY();
    	mouseEvent();
        DrawMap();
    }
    
    public void mouseEvent() {
    	MouseAdapter mouseHandler;
        mouseHandler = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                int width = getWidth();
                int height = getHeight();
                int cellWidth = width / columnCount;
                int cellHeight = height / rowCount;
                selectedCell = null;
                if (e.getX() >= xOffset && e.getY() >= yOffset) {
                    int column = (e.getX() - xOffset) / cellWidth;
                    int row = (e.getY() - yOffset) / cellHeight;
                    if (column >= 0 && row >= 0 && column < columnCount && row < rowCount) {
                        selectedCell = new Point(column, row);
                    }
                }
                repaint();
            }
        };
        addMouseListener(mouseHandler); 
    }
    
    public void DrawMap() {
    	cells = new ArrayList<>(columnCount * rowCount);
    	repaint();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    @Override
    public void invalidate() {
        cells.clear();
        selectedCell = null;
        super.invalidate();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        
        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / columnCount;
        int cellHeight = height / rowCount;
        xOffset = (width - (columnCount * cellWidth)) / 2;
        yOffset = (height - (rowCount * cellHeight)) / 2;
   
       	for (int row =0; row < rowCount; row++) {
       		for (int col=0; col < columnCount; col++) {
       			int xLocation = xOffset + (col * cellWidth);
       			int yLocation = yOffset + (row * cellHeight);
       			Rectangle cell = new Rectangle( xLocation, yLocation, cellWidth, cellHeight);
                cells.add(cell);
                
                String terrString = m_model.getTerrainType(col, row);
                //TODO: Tried to get to work with enums
                Color tileColor = getColor(terrString);
                g2d.setColor(tileColor);
       			g2d.fill(cell);
       			//TODO: Add a plus icon to the view if there is more than one animal in the tile.
       			//TODO: Streamline the paint by moving the image loads elsewhere.
       			if (!m_model.getAnimals(col, row).isEmpty()) {
       				try {
       					for (Animals animal: m_model.getAnimals(col, row)) {
       						String animalName = animal.getClass().getName();
       						switch (animalName) {
       							case("animals.Zebra") : Image image = new ImageIcon(Zebra).getImage();
       										g2d.drawImage(image, xLocation, yLocation, cellWidth/2, cellHeight/2, this);
       										break;      									  
       							case("animals.Hippo"): Image image1 = new ImageIcon(Hippo).getImage();
       									   g2d.drawImage(image1, xLocation, yLocation, cellWidth/2, cellHeight/2, this);
       									   break;
       							case("animals.Elephant"): Image image3 = new ImageIcon(Elephant).getImage();
       										  g2d.drawImage(image3, xLocation, yLocation, cellWidth/2, cellHeight/2, this);
							   			      break;
       							default: System.out.println("Error: animal type: " + animalName + " Not found in LoadMapPane draw");
       						}	
       						//System.out.println(animalName + "id: " + animal.getID());
       					}
       				} 
       				catch(ConcurrentModificationException e) {  //TODO: Was getting some errors here. Not sure if resolved.
       					System.out.println(e.getMessage());		
       				}
       			}
       			if (m_model.getPlant(col, row) != null) {
       				String plantName = m_model.getPlant(col, row).toString();
       				switch (plantName) {
       					case ("Grass") : Image image = new ImageIcon(Grass).getImage();
       								g2d.drawImage(image, xLocation+cellWidth/2, yLocation+cellHeight/2, cellWidth/2, cellHeight/2, this);
       								break;
       					default: System.out.println("Error: plant type: " + plantName + " Not found in LoadMapPane draw");
       				}
       			}
        	}
       	}        
        if (selectedCell != null) {
            g2d.setColor(Color.BLUE);
            g2d.drawRect(  xOffset + (selectedCell.x * cellWidth), yOffset + (selectedCell.y * cellHeight), cellWidth, cellHeight); 
            //TODO: Use a popup box.
            String terrain = m_model.getTerrainType(selectedCell.x, selectedCell.y);
            String animals;
            String plants;
            int animalID;
            if  (m_model.getAnimals(selectedCell.x, selectedCell.y).isEmpty()) 
            	animals = "None";
            else 
            	animals = m_model.getAnimals(selectedCell.x, selectedCell.y).toString();
           // 	animalID =  m_model.getAnimals(selectedCell.x, selectedCell.y)).
            if (m_model.getPlant(selectedCell.x, selectedCell.y) == null  )
            	plants = "None";
            else
            	plants = m_model.getPlant(selectedCell.x, selectedCell.y).toString();
            m_view.setTextBox("(" + selectedCell.x + "," + selectedCell.y + ") Terrain: " + terrain
            			 + ", Animals: " + animals 
            			+ ", Plants: " + plants);
            selectedCell = null;
        }
        g2d.dispose();
    }
   

    
    Color getColor(String color) {
    	switch (color) {
    		case "Plains":
    			return Plains;
    		case "Woods":
    			return Woods;
    		case "Water":
    			return Water;
    		case "Hills":
    			return Hills;
    		default:
    			System.out.println("Error: terrain type: " + color + " Not found in LoadMapPane draw");
    			return null;
    	}
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Model.MsgObj) {
			try {
				Method method = arg1.getClass().getMethod("getMsg");
				method.invoke(arg1);
				Object msgs = method.invoke(arg1);
				String message = (String)msgs; 
				m_view.setTextBox(message);
				
				
			} catch (NoSuchMethodException e) { e.printStackTrace();}
			  catch (IllegalArgumentException e) { e.printStackTrace();}
			  catch (IllegalAccessException e) { e.printStackTrace();}
			  catch (InvocationTargetException e) { e.printStackTrace();}
		} else
			DrawMap();
		
	}
}

