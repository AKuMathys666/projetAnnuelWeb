package projetAnnuel;

import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

import org.bson.Document;

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
	
	protected ImagePanel panelDate;
	protected PanelNorth panelNorth;
	protected ConnectToDB myConnectionToDb;
	
	
	public PanelEast(int wid,int hei, float fonts)
	{
		width=wid;
		height=hei;
		fontSize=fonts;
	}
    
    public void createAccount()
    {
    	GridLayout grid = new GridLayout(5,2,height/100,height/100);
    	panelDate.setBorder(BorderFactory.createEmptyBorder(5*height/100, 5*height/100, 30*height/100, 40*width/100));
    	panelDate.setLayout(grid);
    	
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
      	
      	panelDate.add(labelLogin);
      	panelDate.add(login);
      	panelDate.add(labelPassword);
      	panelDate.add(password);
      	panelDate.add(labelFirstName);
      	panelDate.add(firstName);
      	panelDate.add(labelLastName);
      	panelDate.add(lastName);
      	panelDate.add(submit);
      	panelDate.add(erreurLogged);
    }
	
	public void init(PanelNorth aPanelNorth, ConnectToDB aConnectionToDb)
    {
		panelNorth=aPanelNorth;
    	myConnectionToDb = aConnectionToDb;
		
		String monImage = "img/leftBackground.jpg";
		panelDate = new ImagePanel(monImage,(88*width/100)-(2*height/100), 82*height/100);
		
		panelDate.setPreferredSize(new Dimension((88*width/100)-(2*height/100),82*height/100));
		
		//Ajout de ces derniers au panel nord
		this.add(panelDate);

		((FlowLayout)this.getLayout()).setVgap(height/100);
		((FlowLayout)this.getLayout()).setHgap(0);
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
        	int loginSize=tryLogin.length();
        	int passwordSize=tryPassword.length();
        	int firstNameSize=tryFirstName.length();
        	int lastNameSize=tryLastName.length();
        	if (loginSize==0)
        	{
        		erreurLogged.setText(erreurLogged.getText()+"Login is empty. ");
        	}
        	if (passwordSize==0)
        	{
        		erreurLogged.setText(erreurLogged.getText()+"Password is empty. ");
        	}
        	if (firstNameSize==0)
        	{
        		erreurLogged.setText(erreurLogged.getText()+"First name is empty. ");
        	}
        	if (lastNameSize==0)
        	{
        		erreurLogged.setText(erreurLogged.getText()+"Last name is empty. ");
        	}
        	System.out.println(erreurLogged.getText().length()+erreurLogged.getText());
        	if(erreurLogged.getText().length() == 0)
        	{
        		MongoCollection<Document> user = myConnectionToDb.database.getCollection("users");
        		MongoCollection<Document> tasks = myConnectionToDb.database.getCollection("tasks");
        		MessageDigest messageDigest=null;
        		try 
        		{
        		    messageDigest = MessageDigest.getInstance("SHA");
        		    messageDigest.update((tryPassword+myConnectionToDb.salt).getBytes());
        		} 
        		catch (NoSuchAlgorithmException excep) 
        		{
        		    excep.printStackTrace();
        		}
        		String encryptedPassword = (new BigInteger(messageDigest.digest())).toString(16);
        		Document document = new Document("email",tryLogin);
        		document.append("password",encryptedPassword);
        		document.append("first_name",tryFirstName);
        		document.append("last_name",tryLastName);
        		user.insertOne(document);
        		erreurLogged.setText("");
        	}
        }
    }
}