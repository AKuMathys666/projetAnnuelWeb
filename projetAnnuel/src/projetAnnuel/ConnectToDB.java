package projetAnnuel;

import com.mongodb.client.*;
import com.mongodb.*;

import java.util.Arrays;
import java.util.List;

import org.bson.*;
import org.bson.conversions.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
//import java.net.HttpURLConnection;
//import java.net.URL;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConnectToDB 
{ 
	protected MongoClient mongo = new MongoClient();
	protected MongoDatabase database;
	protected boolean auth;
	protected String salt;
	
	 public static void main(String[] args) throws IOException, JSONException {
		 URL urll=new URL("http://localhost:8080/users");
		 HttpURLConnection con =(HttpURLConnection) urll.openConnection();
		 con.setRequestMethod("GET");

		 //Get Response
		 InputStream is = con.getInputStream();
		 BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		 StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		 String line;
		 while ((line = rd.readLine()) != null) {
			 response.append(line);
			 response.append('\r');
		 }
		 rd.close();

		 System.out.println(response);
		 
		 URL url=new URL("http://localhost:8080/auth/login");
		 HttpURLConnection co =(HttpURLConnection) url.openConnection();
		 co.setRequestProperty("Content-Type", "application/json");
		 co.setRequestProperty("Accept", "application/json");
//		 co.setRequestProperty("Authorization", Token); pour passer le token en param
		 co.setDoOutput(true);
		 co.setRequestMethod("POST");
		 
		 JSONObject cred   = new JSONObject();
		 cred.put("email","rrr");
		 cred.put("password", "rrr");
		 OutputStreamWriter wr = new OutputStreamWriter(co.getOutputStream());
		 wr.write(cred.toString());
		 wr.flush();
		 
		 StringBuilder sb = new StringBuilder();  
		 int HttpResult = co.getResponseCode(); 
		 if (HttpResult == HttpURLConnection.HTTP_OK) {
			 System.out.println("azer");  
		     BufferedReader br = new BufferedReader(
		             new InputStreamReader(co.getInputStream(), "utf-8"));
		     line = null;  
		     while ((line = br.readLine()) != null) {  
		         sb.append(line + "\n");  
		     }
		     br.close();
		     System.out.println("" + sb.toString());  
		 } else {
		     System.out.println(co.getResponseMessage());  
		 }
	 }
	
	public ConnectToDB(String dbName,String serverName,int port) //"al-janv-db","localhost",27017
	{  
		mongo = new MongoClient(new ServerAddress(serverName , port )); 
		database = mongo.getDatabase(dbName); 
		salt = "25zqsfmjvbq$v2";
		auth=false;
	} 
}

//public class Requete {
//    public static void main(String[] args) throws IOException {
//        URL url=new URL("http://localhost:8080/users");
//        HttpURLConnection co =(HttpURLConnection) url.openConnection();
//        co.setRequestMethod("GET");
//
//        //Get Response
//        InputStream is = co.getInputStream();
//        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
//        String line;
//        while ((line = rd.readLine()) != null) {
//            response.append(line);
//            response.append('\r');
//        }
//        rd.close();
//
//        System.out.println(response);
//    }
//}