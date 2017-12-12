package projetAnnuel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import projetAnnuel.PanelWest.WorkspacesListener;




public class PanelNorth  extends JPanel 
{
	private static final long serialVersionUID = 1L;
    private int width;
    private int height;
    private float fontSize;
    
    private JLabel logged = new JLabel();
    private JLabel labelLogin = new JLabel();
    private JTextArea login = new JTextArea();
    private JLabel labelPassword = new JLabel();
    private JPasswordField password = new JPasswordField();
    private JButton submit = new JButton();
    private JTextArea dateEtHeure = new JTextArea();
    private JLabel erreurLogged = new JLabel();
    private JButton createAccount = new JButton();
    
    protected PanelEast panelEast;
    String token;
    
    public PanelNorth(int wid,int hei, float fonts)
    {
		width=wid;
		height=hei;
		fontSize=fonts;
	}

    public void init(PanelEast aPanelEast)
    {
    	panelEast = aPanelEast;
    	
		//Definition de 2 panels contenant les informations generals du lycée et la date et heure
		this.setBorder(BorderFactory.createEmptyBorder(height/100, height/100, 0, height/100));
		
		((FlowLayout)this.getLayout()).setVgap(0);
		((FlowLayout)this.getLayout()).setHgap(0);
		
		String monImage = "img/header-background-1.jpg";
		ImagePanel panelDate = new ImagePanel(monImage,width-(2*height/100), 15*height/100);
		
		panelDate.setPreferredSize(new Dimension(width-(2*height/100),15*height/100));
		
		//Ajout de ces derniers au panel nord
		this.add(panelDate);

		//Creation de la date
		SimpleDateFormat formatDate = null;
		
		//Definition du format de la date: jour, nbjour Mois année heures:minutes:secondes
		formatDate = new SimpleDateFormat("EEEE, d MMM yyyy HH:mm:ss");
		dateEtHeure = new JTextArea(formatDate.format(Calendar.getInstance().getTime()));
		dateEtHeure.setPreferredSize(new Dimension(50*width/100-(7*height/100),7*height/100));
		dateEtHeure.setMargin(new Insets(0,0,0,height/200));
		dateEtHeure.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		dateEtHeure.setLineWrap(true);
		dateEtHeure.setWrapStyleWord(true);
		dateEtHeure.setBackground(new Color(222,222,222));
      	dateEtHeure.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/30));
      	dateEtHeure.setOpaque(false);
      	
		//Creation d'un timer avec evenement listener qui sera declenché toute les 333 millisecondes
		javax.swing.Timer timerDate = new javax.swing.Timer(333, new ClockListener());
        timerDate.start();
        
        logged = new JLabel("Logged with username ");
        logged.setPreferredSize(new Dimension((50*width/100),5*height/100));
        logged.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/40));
        logged.setVisible(false);
        
        erreurLogged = new JLabel("Erreur: login ou mot de passe incorrect. ");
        erreurLogged.setPreferredSize(new Dimension((50*width/100),5*height/100));
        erreurLogged.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/40));
        erreurLogged.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        erreurLogged.setVisible(false);
        
        labelLogin = new JLabel("Login : ");
        labelLogin.setPreferredSize(new Dimension((5*width/100),5*height/100));
        labelLogin.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
        
      	login = new JTextArea();
      	login.setPreferredSize(new Dimension((13*width/100),5*height/100));
      	login.setMargin(new Insets(0,height/100,0,0));
      	login.setLineWrap(true);
      	login.setWrapStyleWord(true);
      	login.setBackground(new Color(222,222,222));
      	login.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
      	
      	labelPassword = new JLabel("Password : ");
      	labelPassword.setPreferredSize(new Dimension((9*width/100),5*height/100));
      	labelPassword.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
      	
      	password = new JPasswordField();
      	password.setPreferredSize(new Dimension((13*width/100),5*height/100));
      	password.setMargin(new Insets(0,height/100,0,0));
      	password.setBackground(new Color(222,222,222));
      	password.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
      	
      	submit = new JButton("Submit");
      	submit.setContentAreaFilled(false);
      	submit.setPreferredSize(new Dimension((10*width/100),5*height/100));;
      	submit.setMargin(new Insets(0,height/100,0,0));
      	submit.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
      	submit.setFocusPainted(false);
      	submit.setMargin(new Insets(1,1,1,1));
      	submit.setBackground(new Color(222,222,222));
      	submit.addActionListener(new SubmitListener());
		
      	createAccount = new JButton("Créer un compte");
      	createAccount.setContentAreaFilled(false);
      	createAccount.setPreferredSize(new Dimension((20*width/100),5*height/100));;
      	createAccount.setMargin(new Insets(0,height/100,0,0));
      	createAccount.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
      	createAccount.setFocusPainted(false);
      	createAccount.setMargin(new Insets(1,1,1,1));
      	createAccount.setBackground(new Color(222,222,222));
      	createAccount.addActionListener(new CreateListener());
      	
      	panelDate.add(logged);
      	panelDate.add(labelLogin);
      	panelDate.add(login);
      	panelDate.add(labelPassword);
      	panelDate.add(password);
      	panelDate.add(submit);
      	panelDate.add(dateEtHeure);
      	panelDate.add(createAccount);
      	panelDate.add(erreurLogged);
      	
      	submit.addActionListener(new SubmitListener());
	}
    
    class ClockListener implements ActionListener 		//implementation de l'evènement d'actualisation de la date
    {
    	public void actionPerformed(ActionEvent e) 
        {
        	//actualisation/redefinition de la date et de l'heure
            SimpleDateFormat df = new SimpleDateFormat("EEEE, d MMM yyyy HH:mm:ss");
            dateEtHeure.setText(df.format(Calendar.getInstance().getTime()));
        }
    }
    
    class SubmitListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
        	String tryLogin=login.getText();
        	char[] tryPasswd=password.getPassword();
        	String tryPassword = new String(tryPasswd);
//        	System.out.println("login "+ tryLogin + " password "+tryPassword);
        	login.setText("");
        	password.setText("");
        	
        	try
        	{
	        	URL url=new URL("http://localhost:8080/auth/login");
				HttpURLConnection co =(HttpURLConnection) url.openConnection();
				co.setRequestProperty("Content-Type", "application/json");
				co.setRequestProperty("Accept", "application/json");
				co.setDoOutput(true);
				co.setRequestMethod("POST");
				 
				JSONObject cred   = new JSONObject();
				cred.put("email",tryLogin);
				cred.put("password", tryPassword);
				OutputStreamWriter wr = new OutputStreamWriter(co.getOutputStream());
				wr.write(cred.toString());
				wr.flush();
				 
				StringBuilder sb = new StringBuilder();  
				int HttpResult = co.getResponseCode(); 
				if (HttpResult == HttpURLConnection.HTTP_OK) 
				{
					BufferedReader br = new BufferedReader(new InputStreamReader(co.getInputStream(), "utf-8"));
					String line = null;  
	   		     	while ((line = br.readLine()) != null) 
	   		     	{  
	   		     		sb.append(line + "\n");  
	   		     	}
	   		     	br.close();
	   		     	token = sb.toString();  
	   		     	
	   		     	logged.setText("Logged with username "+tryLogin);
	        		logged.setVisible(true);
	        		login.setVisible(false);
	        		labelLogin.setVisible(false);
	        		labelPassword.setVisible(false);
	        		password.setVisible(false);
	        		submit.setVisible(false);
	        		erreurLogged.setText("");
	        		erreurLogged.setVisible(false);
	   		 	} 
				else 
				{
					erreurLogged.setVisible(true);
	   		 	}
        	}
        	catch(Exception exc)
        	{
        		exc.printStackTrace();
        	}
        }
    }
    class CreateListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
        	panelEast.createAccount();
        }
    }
}
