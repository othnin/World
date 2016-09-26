package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import geography.Tile;

public abstract class MapPane extends JPanel{
	private int columnCount = 20;
    private int rowCount = 20;
    private List<Rectangle> cells;
    private Point selectedCell;
    private int xOffset=0;
    private int yOffset=0;
    private Tile[][] MapFile;
    
    public MapPane(Tile[][] inMapFile) {
    	System.out.println("Start MapPane abstract");
    	MapFile = inMapFile;
        cells = new ArrayList<>(columnCount * rowCount);
        repaint();
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
                	System.out.println("Inside first if e.getX() : "+ e.getX() + " XOffeset: "+ xOffset + " e.getY: " + e.getY() + " yOffset: " + yOffset);
                    int column = (e.getX() - xOffset) / cellWidth;
                    int row = (e.getY() - yOffset) / cellHeight;

                    if (column >= 0 && row >= 0 && column < columnCount && row < rowCount) {
                        selectedCell = new Point(column, row);
                        
                    }
                    System.out.println("New point, col = " + column + " row: " + row);
                    
                }
               
                repaint();

            }
        };
        addMouseListener(mouseHandler);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    @Override
    public void invalidate() {
    	System.out.println("Invalidate");
        cells.clear();
        selectedCell = null;
        super.invalidate();
    }
    
    protected void paintComponets(Graphics g) {}
	
}
