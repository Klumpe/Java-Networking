package networking;
import java.net.*;
import java.io.*;

public class client {
		private Socket clientSocket;
		private PrintWriter out;
		private BufferedReader in;
		
		  public void startConnection(String ip, int port) throws IOException{
		        clientSocket = new Socket(ip, port);
		        out = new PrintWriter(clientSocket.getOutputStream(), true);
		        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		    }
		  
		  public boolean isConnected()
		  {
			  if(clientSocket.isConnected())
			  {
				  return true;
			  }
			  else return false;
		  }
		 
		    public void sendMessage(String msg) throws IOException{
		        out.println(msg);
		    //    String resp = in.readLine();
		      //  return resp;
		    }
		    
		    public String receiveMessage() throws IOException
		    {

		    	String message = in.readLine();		
				return message;
				
		    }
		 
		    public void stopConnection() throws IOException{
		        in.close();
		        out.close();
		        clientSocket.close();
		    }
		    
		    public static void main(String[] args) throws IOException
		    {
		    //	client clnt = new client();
		    //	clnt.startConnection("localhost",6666);
		    //	String response = clnt.sendMessage("hello");
		   // 	System.out.println(response);
		    //	
		    	
		    }
}
