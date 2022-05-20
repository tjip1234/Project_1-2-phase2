package Bots; /**
 * @author Department of Data Science and Knowledge Engineering (DKE)
 * @version 2022.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

/**
 * This class takes care of all the graphics to display a certain state.
 * Initially, you do not need to modify (or event understand) this class in Phase 1. You will learn more about GUIs in Period 2, in the Introduction to Computer Science 2 course.
 */
public class UI extends JPanel
{
    private JFrame window;
    public static final int generalBound = 5;
    private int[][] state;
    private int size;
    public static final int xCenter = 450;
    public static final int yCenter = 430;
    double[][] coords;
    double[] groupBest;


    /**
     * Constructor for the GUI. Sets everything up
     * @param x x position of the GUI
     * @param y y position of the GUI
     * @param _size size of the GUI
     */
    public UI(int x, int y, int _size)
    {
        size = _size;
        setPreferredSize(new Dimension(x * size, y * size));

        window = new JFrame("Optimization visualizer");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(this);
        window.pack();
        window.setVisible(true);
    }

    /**
     * This function is called BY THE SYSTEM if required for a new frame, uses the state stored by the UI class.
     */
    public void paintComponent(Graphics g)
    {
        Graphics2D localGraphics2D = (Graphics2D) g;
        localGraphics2D.setColor(Color.lightGray);
        localGraphics2D.fill(getVisibleRect());

        //draw lines
        localGraphics2D.setColor(Color.gray);
        localGraphics2D.setStroke(new BasicStroke(3));
        // center coords : 450, 430
        localGraphics2D.drawLine(450, 0, 450, 860);
        localGraphics2D.drawLine(0, 430, 900, 430);
        localGraphics2D.setColor(Color.red);
        localGraphics2D.setStroke(new BasicStroke(3));
        if (coords != null){
            for (int i = 0; i < coords.length; i++) {
                addPoint(coords[i][0], coords[i][1], localGraphics2D);
            }
        }
        addGroupBest(groupBest[0], groupBest[1], localGraphics2D);
        
    }

    /**
     * This function should be called to update the displayed state (makes a copy)
     * @param _state information about the new state of the GUI
     */
    public void setState(double[][] coords, double[] groupBest)
    {
        this.coords = coords;
        this.groupBest = groupBest;
        //System.out.println(Arrays.deepToString(coords));
        repaint();
    }
    public void addPoint(double x, double y, Graphics2D localGraphics2D){
        y = -y;
        localGraphics2D.drawLine(xCenter+ (int) ((x*xCenter)/generalBound), yCenter + (int) ((y*yCenter)/generalBound), xCenter+(int) ((x*xCenter)/generalBound), yCenter+ (int) ((y*yCenter)/generalBound));
    }

    public void addGroupBest(double x, double y,  Graphics2D localGraphics2D){
        y = -y;
        localGraphics2D.setColor(Color.blue);
        localGraphics2D.setStroke(new BasicStroke(12));
        localGraphics2D.drawLine(xCenter+ (int) ((x*xCenter)/generalBound), yCenter + (int) ((y*yCenter)/generalBound), xCenter+(int) ((x*xCenter)/generalBound), yCenter+ (int) ((y*yCenter)/generalBound));
    }
}
