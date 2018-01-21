package projetAnnuel;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

 
public class ImagePanel extends JPanel 
{
	 
    private static final long serialVersionUID = 1L;
 
    private Image img;
     
    public ImagePanel(Image img)
    {
        this.img = img;
    }
    public ImagePanel(String image,int wid,int hei) 
    {
       // this(new ImageIcon(img).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    	try
    	{
    		img= ImageIO.read(ImagePanel.class.getResource(image)).getScaledInstance(wid, hei, Image.SCALE_SMOOTH);
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    }
     
    public void paintComponent(Graphics g) 
    {
    	super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }
}