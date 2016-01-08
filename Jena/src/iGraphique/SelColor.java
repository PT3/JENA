package iGraphique;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;
 
/* ColorChooserDemo.java requires no other files. */
public class SelColor extends JPanel implements ChangeListener 
{
	private String texte;
    protected JColorChooser colcho;
    protected JLabel banner;
 
    public SelColor() 
    {
        super(new BorderLayout());
        
        banner= new JLabel();
        //Set up the banner at the top of the window
        
        
        
        banner.setOpaque(true);
        
 
       
        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.add(banner, BorderLayout.CENTER);
 
        //Set up color chooser for setting text color
        
        colcho = new JColorChooser(banner.getForeground());
        colcho.getSelectionModel().addChangeListener(this);
 
        add(bannerPanel, BorderLayout.CENTER);
        add(colcho, BorderLayout.PAGE_END);
    }
 
    public void stateChanged(ChangeEvent e) 
    {
        Color newColor = colcho.getColor();
        banner.setForeground(newColor);
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    
    private static void createAndShowGUI() 
    {
        //Create and set up the window.
        JFrame frame = new JFrame("ColorChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        JComponent newContentPane = new SelColor();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public Color getColor()
    {
        return colcho.getColor();
    }
    
    
    
    
    public static void main(String[] args) 
    {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
    	
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });
    }
}