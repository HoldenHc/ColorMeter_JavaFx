/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colormeter;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HoldenHc
 */
public class PixelColor implements Runnable {

    
    /**
     * @param robot the robot to set
     */
    public void setRobot(Robot robot) {
        this.robot = robot;
    }
    private int x;
    private int y;
    private Color color;
    private BufferedImage image50x50;
    private Robot robot;
    private int sectionReduction = 0;
    
    public PixelColor(){
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(PixelColor.class.getName()).log(Level.SEVERE, null, ex);
        }
        //color = new Color(0,0,0);
    }
   
    
    @Override
    public void run() {
        
        while(true){
            setX(MouseInfo.getPointerInfo().getLocation().x);
            setY(MouseInfo.getPointerInfo().getLocation().y);
        
            setColor(getRobot().getPixelColor(getX(), getY()));
            int x1 = getX() - 25 + sectionReduction;
            int y1 = getY() - 25 + sectionReduction;
            int width = 50 - (2 * sectionReduction);
            int height = 50 - (2 * sectionReduction);
       
            //System.out.println("w " + x1 + " h " + y1);
            
            setImage50x50(getRobot().createScreenCapture(new Rectangle
                    (x1,
                     y1, 
                     width, 
                     height)));
            //image50x50.
            //ImageData id = image50x50.getGraphics().
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(PixelColor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public int[] getRGV(){
        int r = getColor().getRed();
        int g = getColor().getGreen();
        int b = getColor().getBlue();
        
        int[] rgv = {r, g, b};
        
        return rgv;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the image50x50
     */
    public BufferedImage getImage50x50() {
        return image50x50;
    }

    /**
     * @param image50x50 the image50x50 to set
     */
    public void setImage50x50(BufferedImage image50x50) {
        this.image50x50 = image50x50;
    }


    /**
     * @return the robot
     */
    public Robot getRobot() {
        return robot;
    }

    /**
     * @param sectionReduction the sectionReduction to set
     */
    public void setSectionReduction(int sectionReduction) {
        this.sectionReduction = sectionReduction;
    }
    
}
