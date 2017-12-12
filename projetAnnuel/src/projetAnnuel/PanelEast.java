package projetAnnuel;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

public class PanelEast extends JPanel 
{
	 
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private float fontSize;
	
	private JLabel labelLogin;
	private JTextArea login;
  	private JLabel labelPassword;
  	private JPasswordField password;
  	private JLabel labelFirstName;
  	private JTextArea firstName;
  	private JLabel labelLastName;
  	private JTextArea lastName;
  	private JButton submit;
  	private JLabel erreurLogged;
  	
  	public JPanel[] panelArray= new JPanel[10];
	protected ImagePanel panelBackground;
	protected PanelNorth panelNorth;
	
	
	public PanelEast(int wid,int hei, float fonts)
	{
		width=wid;
		height=hei;
		fontSize=fonts;
	}
    
    public void createAccount()
    {
    	GridLayout grid = new GridLayout(5,2,height/100,height/100);
    	panelArray[0].setVisible(true);
    	panelArray[0].setBorder(BorderFactory.createEmptyBorder(5*height/100, 5*height/100, 30*height/100, 40*width/100));
    	panelArray[0].setOpaque(false);
    	((FlowLayout)panelArray[0].getLayout()).setVgap(height/100);
		((FlowLayout)panelArray[0].getLayout()).setHgap(0);
		panelArray[0].setLayout(grid);
		
    	labelLogin = new JLabel("Email : ");
        labelLogin.setPreferredSize(new Dimension((10*width/100),5*height/100));
        labelLogin.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
        
        login = new JTextArea();
      	login.setPreferredSize(new Dimension((15*width/100),5*height/100));
      	login.setMargin(new Insets(0,height/100,0,0));
      	login.setLineWrap(true);
      	login.setWrapStyleWord(true);
      	login.setBackground(new Color(222,222,222));
      	login.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
      	
      	labelPassword = new JLabel("Password : ");
      	labelPassword.setPreferredSize(new Dimension((10*width/100),5*height/100));
      	labelPassword.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
      	
      	password = new JPasswordField();
      	password.setPreferredSize(new Dimension((15*width/100),5*height/100));
      	password.setMargin(new Insets(0,height/100,0,0));
      	password.setBackground(new Color(222,222,222));
      	password.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
      	
      	labelFirstName = new JLabel("First Name : ");
      	labelFirstName.setPreferredSize(new Dimension((10*width/100),5*height/100));
      	labelFirstName.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
        
      	firstName = new JTextArea();
        firstName.setPreferredSize(new Dimension((15*width/100),5*height/100));
        firstName.setMargin(new Insets(0,height/100,0,0));
        firstName.setLineWrap(true);
        firstName.setWrapStyleWord(true);
        firstName.setBackground(new Color(222,222,222));
      	firstName.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
      	
      	labelLastName = new JLabel("Last Name : ");
      	labelLastName.setPreferredSize(new Dimension((10*width/100),5*height/100));
      	labelLastName.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
        
        lastName = new JTextArea();
        lastName.setPreferredSize(new Dimension((15*width/100),5*height/100));
        lastName.setMargin(new Insets(0,height/100,0,0));
        lastName.setLineWrap(true);
        lastName.setWrapStyleWord(true);
        lastName.setBackground(new Color(222,222,222));
        lastName.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
        
        submit = new JButton("Submit");
      	submit.setContentAreaFilled(false);
      	submit.setPreferredSize(new Dimension((10*width/100),5*height/100));
      	submit.setMargin(new Insets(0,height/100,0,0));
      	submit.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
      	submit.setFocusPainted(false);
      	submit.setMargin(new Insets(1,1,1,1));
      	submit.setBackground(new Color(222,222,222));
      	submit.addActionListener(new SubmitCreationListener());
      	
      	erreurLogged = new JLabel("");
        erreurLogged.setPreferredSize(new Dimension((10*width/100),5*height/100));
        erreurLogged.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
      	
        panelArray[0].add(labelLogin);
        panelArray[0].add(login);
        panelArray[0].add(labelPassword);
        panelArray[0].add(password);
        panelArray[0].add(labelFirstName);
        panelArray[0].add(firstName);
        panelArray[0].add(labelLastName);
        panelArray[0].add(lastName);
        panelArray[0].add(submit);
        panelArray[0].add(erreurLogged);
    }
	
	public void init(PanelNorth aPanelNorth)
    {
		panelNorth=aPanelNorth;
		
		String monImage = "img/leftBackground.jpg";
		panelBackground = new ImagePanel(monImage,(88*width/100)-(2*height/100), 82*height/100);
		
		panelBackground.setPreferredSize(new Dimension((88*width/100)-(2*height/100),82*height/100));
		
		//Ajout de ces derniers au panel nord
		this.add(panelBackground);

		((FlowLayout)this.getLayout()).setVgap(height/100);
		((FlowLayout)this.getLayout()).setHgap(0);
		
		for (int i=0;i<10;i++)
		{
			panelArray[i]= new JPanel();
			panelArray[i].setPreferredSize(new Dimension((88*width/100)-(3*height/100),81*height/100));
			panelBackground.add(panelArray[i]);
			panelArray[i].setVisible(false);
		}
	}
	
	public ImagePanel resetPanel()
	{
		String monImage = "img/leftBackground.jpg";
		panelBackground = new ImagePanel(monImage,(88*width/100)-(2*height/100), 82*height/100);
		
		panelBackground.setPreferredSize(new Dimension((88*width/100)-(2*height/100),82*height/100));
		
		//Ajout de ces derniers au panel nord
		this.add(panelBackground);

		((FlowLayout)this.getLayout()).setVgap(height/100);
		((FlowLayout)this.getLayout()).setHgap(0);
		
		return panelBackground;
	}
	
	public void displayTimer()
    {
		for (int i=0;i<10;i++)
		{
			panelArray[i].setVisible(false);
			panelArray[i].setOpaque(false);
		}
		panelArray[1].setVisible(true);
		panelArray[1].removeAll();
		JTextArea mytext = new JTextArea("displayTimer In progress");
		mytext.setPreferredSize(new Dimension((25*width/100),5*height/100));
		mytext.setMargin(new Insets(0,height/100,0,0));
		mytext.setLineWrap(true);
		mytext.setWrapStyleWord(true);
		mytext.setBackground(new Color(222,222,222));
		mytext.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
		panelArray[1].add(mytext);
    }
	
	public void displayDashboard()
    {
		for (int i=0;i<10;i++)
		{
			panelArray[i].setVisible(false);
		}
		panelArray[2].setVisible(true);
		panelArray[2].removeAll();
		JTextArea mytext = new JTextArea("displayDashboard In progress");
		mytext.setPreferredSize(new Dimension((25*width/100),5*height/100));
		mytext.setMargin(new Insets(0,height/100,0,0));
		mytext.setLineWrap(true);
		mytext.setWrapStyleWord(true);
		mytext.setBackground(new Color(222,222,222));
		mytext.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
		panelArray[2].add(mytext);
    }
	
	public void displayReports()
    {
		for (int i=0;i<10;i++)
		{
			panelArray[i].setVisible(false);
		}
		panelArray[3].setVisible(true);
		panelArray[3].removeAll();
		JTextArea mytext = new JTextArea("displayReports In progress");
		mytext.setPreferredSize(new Dimension((25*width/100),5*height/100));
		mytext.setMargin(new Insets(0,height/100,0,0));
		mytext.setLineWrap(true);
		mytext.setWrapStyleWord(true);
		mytext.setBackground(new Color(222,222,222));
		mytext.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
		panelArray[3].add(mytext);
    }
	
	public void displayInsights()
    {
		for (int i=0;i<10;i++)
		{
			panelArray[i].setVisible(false);
		}
		panelArray[4].setVisible(true);
		panelArray[4].removeAll();
		JTextArea mytext = new JTextArea("displayInsights In progress");
		mytext.setPreferredSize(new Dimension((25*width/100),5*height/100));
		mytext.setMargin(new Insets(0,height/100,0,0));
		mytext.setLineWrap(true);
		mytext.setWrapStyleWord(true);
		mytext.setBackground(new Color(222,222,222));
		mytext.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
		panelArray[4].add(mytext);
    }
	
	public void displaySavedReports()
    {
		for (int i=0;i<10;i++)
		{
			panelArray[i].setVisible(false);
		}
		panelArray[5].setVisible(true);
		panelArray[5].removeAll();
		JTextArea mytext = new JTextArea("displaySavedReports In progress");
		mytext.setPreferredSize(new Dimension((25*width/100),5*height/100));
		mytext.setMargin(new Insets(0,height/100,0,0));
		mytext.setLineWrap(true);
		mytext.setWrapStyleWord(true);
		mytext.setBackground(new Color(222,222,222));
		mytext.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
		panelArray[5].add(mytext);
    }
	
	public void displayProjects()
    {
		for (int i=0;i<10;i++)
		{
			panelArray[i].setVisible(false);
		}
		panelArray[6].setVisible(true);
		panelArray[6].removeAll();
		JTextArea mytext = new JTextArea("displayProjects In progress");
		mytext.setPreferredSize(new Dimension((25*width/100),5*height/100));
		mytext.setMargin(new Insets(0,height/100,0,0));
		mytext.setLineWrap(true);
		mytext.setWrapStyleWord(true);
		mytext.setBackground(new Color(222,222,222));
		mytext.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
		panelArray[6].add(mytext);
    }
	
	public void displayClients()
    {
		for (int i=0;i<10;i++)
		{
			panelArray[i].setVisible(false);
		}
		panelArray[7].setVisible(true);
		panelArray[7].removeAll();
		JTextArea mytext = new JTextArea("displayClients In progress");
		mytext.setPreferredSize(new Dimension((25*width/100),5*height/100));
		mytext.setMargin(new Insets(0,height/100,0,0));
		mytext.setLineWrap(true);
		mytext.setWrapStyleWord(true);
		mytext.setBackground(new Color(222,222,222));
		mytext.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
		panelArray[7].add(mytext);
    }
	
	public void displayTeam()
    {
		for (int i=0;i<10;i++)
		{
			panelArray[i].setVisible(false);
		}
		panelArray[8].setVisible(true);
		panelArray[8].removeAll();
		JTextArea mytext = new JTextArea("displayTeam In progress");
		mytext.setPreferredSize(new Dimension((25*width/100),5*height/100));
		mytext.setMargin(new Insets(0,height/100,0,0));
		mytext.setLineWrap(true);
		mytext.setWrapStyleWord(true);
		mytext.setBackground(new Color(222,222,222));
		mytext.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
		panelArray[8].add(mytext);
    }
	
	public void displayWorkspaces()
    {
		for (int i=0;i<10;i++)
		{
			panelArray[i].setVisible(false);
		}
		panelArray[9].setVisible(true);
		panelArray[9].removeAll();
		JTextArea mytext = new JTextArea("displayWorkspaces In progress");
		mytext.setPreferredSize(new Dimension((25*width/100),5*height/100));
		mytext.setMargin(new Insets(0,height/100,0,0));
		mytext.setLineWrap(true);
		mytext.setWrapStyleWord(true);
		mytext.setBackground(new Color(222,222,222));
		mytext.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
		panelArray[9].add(mytext);
    }
	
	class SubmitCreationListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
        	String tryLogin=login.getText();
        	char[] tryPasswd=password.getPassword();
        	String tryPassword=new String(tryPasswd);
        	String tryFirstName=firstName.getText();
        	String tryLastName=lastName.getText();
        	if (tryLogin.length()==0)
        	{
        		erreurLogged.setText(erreurLogged.getText()+"Login is empty. ");
        	}
        	if (tryPassword.length()==0)
        	{
        		erreurLogged.setText(erreurLogged.getText()+"Password is empty. ");
        	}
        	if (tryFirstName.length()==0)
        	{
        		erreurLogged.setText(erreurLogged.getText()+"First name is empty. ");
        	}
        	if (tryLastName.length()==0)
        	{
        		erreurLogged.setText(erreurLogged.getText()+"Last name is empty. ");
        	}
        	System.out.println(erreurLogged.getText().length()+erreurLogged.getText());
        	if(erreurLogged.getText().length() == 0)
        	{
        		try
        		{
	        		URL url=new URL("http://localhost:8080/users");
	       		 	HttpURLConnection co =(HttpURLConnection) url.openConnection();
	       		 	co.setRequestProperty("Content-Type", "application/json");
	       		 	co.setRequestProperty("Accept", "application/json");
	       		 	co.setDoOutput(true);
	       		 	co.setRequestMethod("POST");
	       		 
	       		 	JSONObject cred   = new JSONObject();
	       		 	cred.put("email",tryLogin);
	       		 	cred.put("password", tryPassword);
	       		 	cred.put("first_name",tryFirstName);
	       		 	cred.put("last_name", tryLastName);
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
//	       		 		System.out.println("" + sb.toString());  
	       		 		SwingUtilities.getWindowAncestor(submit).setVisible(false); //not working, WHY?
	       		 	} 
	       		 	else 
	       		 	{
	       		 		System.out.println(co.getResponseMessage());  
	       		 	}
        		}
        		catch(Exception exc)
        		{
        			exc.printStackTrace();
        		}
        	}
        }
    }
}