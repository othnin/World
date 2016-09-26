package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class RndMap extends JPanel{

	public static final Color FOREST = new Color(0, 102, 0);
	public static final Color LAKE = new Color(0, 153, 153);
	public static final Color PLAINS = new Color(102, 153, 0);
	public static final Color HILLS = new Color(51, 153, 0);
	
	public static final Color[] TERRAIN = {
			FOREST,
			LAKE,
			PLAINS,
			HILLS
	};
	
	public static final int NUM_ROWS = 20;
	public static final int NUM_COLS = 20;
	public static final int PREFERRED_GRID_SIZE_PIXELS = 40;
	
	private final Color[][] terrainGrid;
	
	public RndMap() {
		
		this.terrainGrid = new Color[NUM_ROWS][NUM_COLS];
		Random r = new Random();
		
		for (int i=0; i < NUM_ROWS; i++)
			for (int j=0; j < NUM_COLS; j++) {
				int randomTerrainIndex = r.nextInt(TERRAIN.length);
				Color randomColor = TERRAIN[randomTerrainIndex];
				this.terrainGrid[i][j] = randomColor;
			}
		int preferredWidth = NUM_COLS * PREFERRED_GRID_SIZE_PIXELS;
		int preferredHeight = NUM_ROWS * PREFERRED_GRID_SIZE_PIXELS;
		setPreferredSize(new Dimension(preferredWidth, preferredHeight));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//call the super method
		super.paintComponent(g);
		//clear the board
		g.clearRect(0, 0, getWidth(), getHeight());
		//draw the grid
		int rectWidth = getWidth()/ NUM_COLS;
		int rectHeight = getHeight() / NUM_ROWS;
		
		for (int i=0; i < NUM_ROWS; i++) 
			for (int j=0; j < NUM_COLS; j++) {
				//uppper left corner of this terrain rect
				int x = i * rectWidth;
				int y = j * rectHeight;
				Color terrainColor = terrainGrid[i][j];
				g.setColor(terrainColor);
				g.fillRect(x, y, rectWidth, rectHeight);
						
		}
		
	}
	
}
