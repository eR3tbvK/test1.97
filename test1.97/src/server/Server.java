package server;

import client.*;
import client.InGame.DrawingPanel;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JPanel;


public class Server {

	ArrayList<ObjectOutputStream> clientOutputStreams;
	ArrayList<String> usernames;
	ArrayList<ServerObject> serverObjects;
	ArrayList<ClientObject> clientObjects;
	ArrayList<Integer> xCoordinates;
	ArrayList<Integer> yCoordinates;
	ObjectOutputStream outStream;
	ServerObject serverObject;
	
	public static void main(String[] args){
		//ApplicationContext factory = new ClassPathXmlApplicationContext("spring.xml");
		//new ChatServer().go();  //Old way of doing it.
		//Server server = new Server();	//New and improved way
		Server server = new Server();
		server.go();
	}
	     


	public class ClientHandler implements Runnable {
		ObjectInputStream inStream;
		public ClientHandler(Socket clientSocket){
			try{
				Socket sock = clientSocket;
				inStream = new ObjectInputStream(sock.getInputStream());
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		//The object comes in from a client
		public void run(){
			Object fromClientObject = null;
			
			try{
				while ((fromClientObject = inStream.readUnshared()) != null){
					//System.out.println(System.currentTimeMillis());
					ClientObject clientObject = (ClientObject) fromClientObject;
					//the casted fressh new object that came in
					
					
					
					serverObject = getServerObject(clientObject);
					serverObject.setArrayList(usernames);
					Boolean undefinedUser = serverObject.getUsername().equals("undefined");
					int clientIndex = usernames.indexOf(serverObject.getUsername());

					if(undefinedUser){
						tellThisGuy(serverObjects,usernames.size());
					}
					if(!undefinedUser && serverObject.getArrayList().indexOf(serverObject.getUsername()) < 0){
						//serverObject = getServerObject(clientObject);
						System.out.println("New User Logged in: " + serverObject.getUsername());

						usernames.add(serverObject.getUsername());
						clientIndex = usernames.indexOf(serverObject.getUsername()); //update clientIndex since we just added to usernames
						
						serverObjects.add(serverObject);
						tellThisGuy(serverObjects,clientIndex);
						addCoordinates(serverObject);
					}
					else if(!undefinedUser && clientIndex >= 0){
						//serverObject = serverObjects.get(clientIndex);
						updateClientsInfo(clientIndex, serverObject);
					}
					serverObject.setArrayList(usernames);

					//tellEveryone();
				}
			}
			catch(SocketException e){
				System.err.println("User Logged out");
				removeLoggedOutUsers();

			}
			catch(IndexOutOfBoundsException ex){
				System.err.println("IndexOutOfBoundsException caught");
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			

		}
		
		
		
		public ServerObject getServerObject(ClientObject clientObject){
			ServerObject serverObject = null;
			
			
			String message = 	clientObject.getMessage();
			String username = 	clientObject.getUsername();
			Boolean r = 		clientObject.getR();
			Boolean down = 		clientObject.getDown();
			Boolean up = 		clientObject.getUp();
			Boolean left = 		clientObject.getLeft();
			Boolean right = 	clientObject.getRight();
			
			if(usernames.contains(username)){
				int objIndex = usernames.indexOf(username);
				serverObject = serverObjects.get(objIndex);
			}
			else{
				serverObject = new ServerObject();
			}
			
			serverObject.setUsername(username);
			serverObject.setMessage(message);
			serverObject.setCross(r);
			int speed = 5;
			
			if(up){
				serverObject.setYMove(-speed);
				
				serverObject.setFaceDown(false);
				serverObject.setFaceRight(false);
				serverObject.setFaceLeft(false);
				serverObject.setFaceUp(true);
			}
			if(down){
				serverObject.setYMove(speed);
				
				serverObject.setFaceDown(true);
				serverObject.setFaceRight(false);
				serverObject.setFaceLeft(false);
				serverObject.setFaceUp(false);
			}
			if(right){
				serverObject.setXMove(speed);
				
				serverObject.setFaceDown(false);
				serverObject.setFaceRight(true);
				serverObject.setFaceLeft(false);
				serverObject.setFaceUp(false);
			}
			if(left){
				serverObject.setXMove(-speed);
				
				serverObject.setFaceDown(false);
				serverObject.setFaceRight(false);
				serverObject.setFaceLeft(true);
				serverObject.setFaceUp(false);
			}
			if(!(right || left)){
				serverObject.setXMove(0);
			}
			if(!(up || down)){
				serverObject.setYMove(0);
			}
			

			
			return serverObject;
		}
		
		public void updateClientsInfo(int clientIndex, ServerObject serverObject){
			serverObjects.set(clientIndex, serverObject);
			xCoordinates.set(clientIndex,serverObject.getXCoordinate());
			yCoordinates.set(clientIndex,serverObject.getYCoordinate());
		}
		
		public void addCoordinates(ServerObject serverObject){
			xCoordinates.add(serverObject.getXCoordinate());
			yCoordinates.add(serverObject.getYCoordinate());
		}
		
	
	}

	
	
		public void go(){
			clientOutputStreams = new ArrayList<ObjectOutputStream>();
			usernames = new ArrayList<String>();
			serverObjects = new ArrayList<ServerObject>();
			xCoordinates = new ArrayList<Integer>();
			yCoordinates = new ArrayList<Integer>();

			try{
				@SuppressWarnings("resource")
				ServerSocket serverSock = new ServerSocket(5000);

				System.out.println("Server Up");
				Boolean timeSingleton = true;
				while(true){
					Socket clientSocket = serverSock.accept();
		            outStream = new ObjectOutputStream(clientSocket.getOutputStream());
		            clientOutputStreams.add(outStream);
					Thread t = new Thread(new ClientHandler(clientSocket));
					t.start();
					if(timeSingleton){
					try{
						Thread timeThread = new Thread(new Time());
						timeThread.start();
						timeSingleton = false;
					}
					catch(Exception ex){
						System.err.println("making the thread catch");
						ex.printStackTrace();
					}
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}

		public class Time implements Runnable{
			public void run(){
				while(true){
					sleepDelay(50);
					callTellEveryone();
					moveEveryone();
					
					for(ServerObject obj:serverObjects){
						System.out.println("xCoordinates: " + obj.getXCoordinate() + "yCoordinates: " + obj.getYCoordinate());
					}
					//System.out.println("xCoordinates: " + xCoordinates + "---" + "yCoordinates: " + yCoordinates);
					
				}
			}
			
			public void sleepDelay(int delayTime){
				try{
					Thread.sleep(delayTime);
				} catch (InterruptedException e){
					e.printStackTrace();
				}			
			}
			
			public void callTellEveryone(){
				try {
					if(serverObject != null) tellEveryone();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			public void moveEveryone(){
				Iterator<ServerObject> serverObj = serverObjects.iterator();
				ServerObject aObject = null;
				while(serverObj.hasNext()){
					aObject = (ServerObject) serverObj.next();
					aObject.setXCoordinate(aObject.getXCoordinate() + aObject.getXMove());
					aObject.setYCoordinate(aObject.getYCoordinate() + aObject.getYMove());
				}
				
			}
		}
		
		public void removeLoggedOutUsers(){
				Iterator<ObjectOutputStream> it = clientOutputStreams.iterator();
				ObjectOutputStream out = null;
				ServerObject conTest = new ServerObject();

					try{

							while(it.hasNext()){
								out = (ObjectOutputStream) it.next();
								int removedIndex = clientOutputStreams.indexOf(out);
								conTest.setArrayList(usernames);
								if(usernames.size() < removedIndex) 
								{
									conTest.setUsername(usernames.get(removedIndex));
									conTest.setXCoordinate(xCoordinates.get(removedIndex));
									conTest.setYCoordinate(yCoordinates.get(removedIndex));
								}
									
								try{
									synchronized(out){out.writeUnshared(conTest);}
								}catch(SocketException e){
									System.err.println("Removing terminated user from clientOutputStreams the index is:" + clientOutputStreams.indexOf(out));

									
								//if(usernames.size() < removedIndex){
									usernames.remove(removedIndex);;
									serverObjects.remove(removedIndex);;
									xCoordinates.remove(removedIndex);;
									yCoordinates.remove(removedIndex);;
									//}
									clientOutputStreams.remove(out);

								}
								out.reset();
							}
						
					}
					catch(SocketException e){

					}
					catch(Exception e){
						e.printStackTrace();
					}
		}
		
		//The object gets sent to the ONE client it has One Job Oneee Job
		public void tellThisGuy(ArrayList<ServerObject> serverObjects, int thisInt) throws IOException{
			Iterator<ServerObject> clientObject = serverObjects.iterator();
			ObjectOutputStream thisGuy = null;
			thisGuy = clientOutputStreams.get(thisInt);
		
			while(clientObject.hasNext()){
				synchronized(thisGuy){	thisGuy.writeUnshared(clientObject.next());
				thisGuy.reset();
				}
				
			}	
		}
		
		//The object gets sent out to every client
		public void tellEveryone() throws IOException{
			Iterator<ObjectOutputStream> it = clientOutputStreams.iterator();
			ObjectOutputStream out = null;

			while(it.hasNext()){
				out = (ObjectOutputStream) it.next();
				/*for(ServerObject Messenger: serverObjects){
					synchronized(out){
						out.writeUnshared(Messenger);
						out.reset();
					}
				}*/
				synchronized(out){
					out.writeUnshared(serverObject);
					out.reset();
				}
				
				
				
				
			}

		}
}