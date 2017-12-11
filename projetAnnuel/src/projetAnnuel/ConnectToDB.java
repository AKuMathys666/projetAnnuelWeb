package projetAnnuel;

import com.mongodb.client.*;
import com.mongodb.*;

import java.util.Arrays;
import java.util.List;

import org.bson.*;
import org.bson.conversions.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConnectToDB 
{ 
	protected MongoClient mongo = new MongoClient();
	protected MongoDatabase database;
	protected boolean auth;
	protected String salt;
	
	public ConnectToDB(String dbName,String serverName,int port) //"al-janv-db","localhost",27017
	{  
		mongo = new MongoClient(new ServerAddress(serverName , port )); 
		database = mongo.getDatabase(dbName); 
		salt = "25zqsfmjvbq$v2";
		auth=false;
	} 
}