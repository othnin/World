package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EmptyMapPane extends JPanel {

    private int columnCount = 20;
    private int rowCount = 20;
    private List<Rectangle> cells;
    private Point selectedCell;
    private int xOffset=0;
    private int yOffset=0;
    

    public EmptyMapPane() {
        cells = new ArrayList<>(columnCount * rowCount);
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
                    System.out.println("Empty Map Pane- New point, col = " + column + " row: " + row);
                    
                }
               
                repaint();

            }
        };
        addMouseListener(mouseHandler);
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
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

        if (cells.isEmpty()) {
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                    Rectangle cell = new Rectangle(
                            xOffset + (col * cellWidth),
                            yOffset + (row * cellHeight),
                            cellWidth,
                            cellHeight);
                    cells.add(cell);
                }
            }
        }
        //if the selectedCell isn't null paint it blue
        if (selectedCell != null) {

            int index = selectedCell.x + (selectedCell.y * columnCount);
            Rectangle cell = cells.get(index);
            g2d.setColor(Color.BLUE);
            g2d.fill(cell);
        }

        g2d.setColor(Color.GRAY);
        for (Rectangle cell : cells) {
            g2d.draw(cell);
        }

        g2d.dispose();
    }
}

