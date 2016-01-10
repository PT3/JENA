package affichage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

/* ColorChooserDemo.java requires no other files. */
public class SelColor extends JPanel implements ChangeListener
{
	protected JColorChooser colcho;
    protected JLabel banner;
 
    public SelColor() 
    {
        super(new BorderLayout());
        banner= new JLabel();
        banner.setOpaque(true);
        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.add(banner, BorderLayout.CENTER);
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
 
    private static void createAndShowGUI() 
    {
        JFrame frame = new JFrame("ColorChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new SelColor();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }
 
    public Color getColor()
    {
        return colcho.getColor();
    }
    
    public static void main(String[] args) 
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });
    }
}