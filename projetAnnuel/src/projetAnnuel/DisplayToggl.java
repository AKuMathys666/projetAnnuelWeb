package projetAnnuel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DisplayToggl extends JFrame
{
	//Declaration des panels principaux
	private JPanel panelPrincipal;
	private PanelNorth panelNorth;
	private PanelEast panelEast;
	private PanelWest panelWest;
	//Déclaration et définition de la dimension tailleFenetre a celle de l'ecran
	private Dimension tailleFenetre =Toolkit.getDefaultToolkit().getScreenSize();
	
	private int width = (int)tailleFenetre.getWidth();
	private int height = (int)tailleFenetre.getHeight();
	
	private float ratio;
	private float fontSize;
	
	protected String token;
	
	public DisplayToggl(String siteWebIP,String monLogin, String monPasswd)					//Constructeur d'afficheur de l'ecran
	{
		
		ratio = this.monRatio(width,height);
		fontSize=(float)width*ratio;
		
		//Initialisation des panels principaux 
		panelPrincipal 	= new JPanel();	
		panelNorth 		= new PanelNorth(width,height,fontSize);
		panelEast 		= new PanelEast(width,height,fontSize);
		panelWest 		= new PanelWest(width,height,fontSize);
		
		//panelPrincipal.setBackground(new Color(243,206,55));
		panelPrincipal.setBackground(new Color(48,77,95));
		panelNorth.setBackground(new Color(48,77,95));
		panelEast.setBackground(new Color(48,77,95));
		panelWest.setBackground(new Color(48,77,95));

		//Définition de la taille du panel principale
		//env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		//vc = env.getDefaultScreenDevice();
		this.setUndecorated(true); 
        this.setResizable(false); 
       // vc.setFullScreenWindow(this); 
        this.setSize(new Dimension(width,height));
		//On initialise le conteneur avec tous les composants
		
		initComposant();
		//On ajoute le conteneur
		this.setContentPane(panelPrincipal);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		//Raffraichissement de l'écran toutes les 5 minutes
		javax.swing.Timer timerDate = new javax.swing.Timer(300000, new RefreshListener());
        timerDate.start();
	}
	
	private void initComposant()							//Implementation des composant de 
	{														//La fenetre principale
		//Definition du panel principale en borderLayout
		panelPrincipal.setLayout(new BorderLayout());

		//Ajout des panels Nord, Est et West au panel principale
		panelPrincipal.add(panelNorth,BorderLayout.NORTH);	
		panelPrincipal.add(panelEast,BorderLayout.EAST);
		panelPrincipal.add(panelWest,BorderLayout.WEST);
		
		//Definition de la taille des panels principaux
		panelNorth.setPreferredSize(new Dimension(width,16*height/100));
		panelEast.setPreferredSize(new Dimension(88*width/100,83*height/100));
		panelWest.setPreferredSize(new Dimension(12*width/100,height*(83/100)));
		
		//Initialisation du contenue du panel nord
		//initPanelNord();
		panelNorth.init(panelEast);
		//Initialisation du contenue du panel Est
		//initPanelEast();
		panelEast.init(panelNorth);
		//Initialisation du contenue du panel West
		//initPanelWest();
		panelWest.init(panelEast);
	}
		
	private float monRatio(int width,int height){
		float ratio = (float)width/height;
		float mult;
		if (ratio <= 1.2){
			mult=(float)1;
		}
		else if(ratio <=1.5){
			mult=(float)95/100;
		}
		else if(ratio <=1.6){
			mult=(float)90/100;
		}
		else if(ratio <=1.71){
			mult=(float)85/100;
		}
		else if(ratio <=1.8){
			mult=(float)80/100;
		}
		else if(ratio <=1.9){
			mult=(float)80/100;
		}
		else {
			mult=(float)75/100;
		}
		return mult;
	}
	//WIDTH		530		1280	600		650		700		750		800		850		900		950		1000
	//HEIGHT	500		1024	500		500		500		500		500		500		500		500		500
	//RATIO		1.06	1.25	1.2		1.3		1.4		1.5		1.6		1.7		1.8		1.9		2
	//MULT		1		1		1		0.95	0.95	0.95	0.90	0.85	0.80	0.80	0.75
	
	//WIDTH		750		825		900		975		1050	1125	1200	1275	1350	1425	1500
	//HEIGHT	750		750		750		750		750		750		750		750		750		750		750
	//RATIO		1		1.1		1.2		1.3		1.4		1.5		1.6		1.7		1.8		1.9		2
	//MULT		1		1		1		1		1		0.95	0.90	0.85	0.80	0.80	0.75
	
	class RefreshListener implements ActionListener {		
        public void actionPerformed(ActionEvent e) {
        	//actualisation de l'affichage
    		panelEast 		= new PanelEast(width,height,fontSize);
    		panelWest 		= new PanelWest(width,height,fontSize);
    		
    		panelEast.setBackground(new Color(48,77,95));
    		panelWest.setBackground(new Color(48,77,95));
    		
    		initComposant();
        }
    }
	
}
