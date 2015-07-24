package client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.*;



public class Client {
	//Make private variables, private means only methods in the class can access them
	private JTextArea incoming;
	private JTextField outgoing;
	private String username;
	private Socket sock;
	private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private ServerObject myChat;
    private Login loginPage;
    private InGame inGame;
    private PlayerMob player;
    private ArrayList<PlayerMob> players;
    private Background background;
    private ArrayList<Background> backgrounds;
    private ArrayList<String> usernames;
    private Client networkStartup;
    private int userIndex = 0;
    private Boolean userIn = false;
    private int usersIn = 0;


    
	public void startUp(Client netStart,JPanel panel){
	
		networkStartup = netStart;
		//loginPage = new Login(); 									//Make new object loginPage
		loginPage = new Login();		//Its like new Login() but you tell the factory to do it
		loginPage.setPanel(panel);
		loginPage.setNetObject(netStart); 							//Send the ChatClient object to loginPage
		myChat = new ServerObject();								//Make new object called myChat
		player = new PlayerMob(netStart);
		players = new ArrayList<PlayerMob>();						//Make an array list to hold all other players from server
		//background = new Background();
		//backgrounds = new ArrayList<Background>();
		usernames = new ArrayList<String>();
		inGame = new InGame();			//Make new object inGame from the factory
		inGame.setPlayer(player);
		inGame.setNetObject(netStart);								//Send the ChatClient object to inGame
		setUpNetworking();											
		loginPage.login();
	}

	private void setUpNetworking(){
		//create a socket connection to the server on port 5000, and outputstream, inputstream 
		//and have a thread call the IncomingReader method
		try {
			sock = new Socket("localhost",5000);
			outputStream = new ObjectOutputStream(sock.getOutputStream());
			inputStream = new ObjectInputStream(sock.getInputStream());

			
			Thread remote = new Thread(new IncomingReader());
			remote.start();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}

	public void LoginPageLoginButtonListener (String usr, JPanel panel){
		username = usr;
		try{	

			if(username.length() > 0) {				
				initializeUsernames();
				Thread.sleep(100);	//This sleep gives time for the Incoming reader class to update the usernames from the server					
				Boolean duplicateUser = usernames.contains(username);

				if(!duplicateUser){
					createNewUser();
					startGame(panel);		
				} else {
					loginPage.duplicateUserMsg(username);
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void initializeUsernames() throws IOException{
		outputStream.writeUnshared(myChat);		//Sending the ChatObject to the server
		outputStream.flush();
	}
	
	public void createNewUser() throws IOException{
		myChat.setUsername(username);
		outputStream.writeUnshared(myChat);		//Sending the ChatObject to the server
		outputStream.flush();
	}
	
	public void startGame(JPanel panel){
		panel.removeAll();
		inGame.chat(panel);
	}

	public void InGameChatInitialize(JTextField out,JTextArea in){
		incoming = in;
		outgoing = out;
		try{
			myChat.setMessage(username + " has joined!");	
			outputStream.writeUnshared(myChat);
			outputStream.flush();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void InGameChatSendButtonListener (JTextField out,JTextArea in){
		outgoing = out;
		incoming = in;

		try{
			myChat.setMessage(username + ": " + outgoing.getText());			
			outputStream.writeUnshared(myChat);
			outputStream.flush();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

		outgoing.setText("");
		outgoing.requestFocus();
	}

	public void keyPressedR(Boolean cross){
		try{
			myChat.setCross(cross);
			
			myChat.setMessage(null);
			outputStream.writeUnshared(myChat);
			outputStream.flush();

		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void keyPressedB(Boolean block){
		try{
			myChat.setBlock(block);
			
			myChat.setMessage(null);
			outputStream.writeUnshared(myChat);
			outputStream.flush();

		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void keyPressed(Boolean vertMove,int yMove,int yCoordinate){
		try{
			myChat.setYMove(yMove);
			if(players.get(userIndex) != null) myChat.setYCoordinate(players.get(userIndex).getYCoordinate());
			if(players.get(userIndex) != null) myChat.setXCoordinate(players.get(userIndex).getXCoordinate());
			if(players.get(userIndex) != null && yMove > 0) {
				myChat.setFaceDown(true);
				myChat.setFaceRight(false);
				myChat.setFaceLeft(false);
				myChat.setFaceUp(false);
			}
			if(players.get(userIndex) != null && yMove < 0) {
				myChat.setFaceUp(true);
				myChat.setFaceLeft(false);
				myChat.setFaceDown(false);
				myChat.setFaceRight(false);
			}
			
			myChat.setMessage(null);
			outputStream.writeUnshared(myChat);
			outputStream.flush();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void keyPressed(int xMove,Boolean horMove,int xCoordinate){
		try{
			myChat.setXMove(xMove);
			if(players.get(userIndex) != null) myChat.setXCoordinate(players.get(userIndex).getXCoordinate());
			if(players.get(userIndex) != null) myChat.setYCoordinate(players.get(userIndex).getYCoordinate());
			if(players.get(userIndex) != null && xMove < 0) {
				myChat.setFaceLeft(true);
				myChat.setFaceRight(false);
				myChat.setFaceDown(false);
				myChat.setFaceUp(false);
			}
			if(players.get(userIndex) != null && xMove > 0) {
				myChat.setFaceRight(true);
				myChat.setFaceLeft(false);
				myChat.setFaceDown(false);
				myChat.setFaceUp(false);
			}
			
			myChat.setMessage(null);
			outputStream.writeUnshared(myChat);
			outputStream.flush();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void keyReleased(Boolean vertMove, int yMove,int yCoordinate){	
		try{
			myChat.setYMove(yMove);
			userIndex = usernames.indexOf(myChat.getUsername());
			if(players.get(userIndex) != null) myChat.setYCoordinate(players.get(userIndex).getYCoordinate());
			if(players.get(userIndex) != null) myChat.setXCoordinate(players.get(userIndex).getXCoordinate());
			myChat.setMessage(null);
			myChat.setRefreshCoordinates(false);
			outputStream.writeUnshared(myChat);
			outputStream.flush();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void keyReleased(int xMove,Boolean horMove, int xCoordinate){

		try{
			myChat.setXMove(xMove);
			userIndex = usernames.indexOf(myChat.getUsername());
			if(players.get(userIndex) != null) myChat.setXCoordinate(players.get(userIndex).getXCoordinate());
			if(players.get(userIndex) != null) myChat.setYCoordinate(players.get(userIndex).getYCoordinate());
			myChat.setMessage(null);
			myChat.setRefreshCoordinates(false);
			outputStream.writeUnshared(myChat);
			outputStream.flush();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void moveBackground(ServerObject serverObject, int indexOfPlayer ){
		try{
			inGame.background.readMove(serverObject,indexOfPlayer);
		}catch(NullPointerException ex){
			//do nothing
		}
	}




	public class IncomingReader implements Runnable{
		//reads object from server through inputStream if the object is read, we cast it as a ChatObject
		//then we append message inside chat object to the text area called incoming


		
		public void run(){
			int indexOfPlayer = 0;
			Object objFromInStream = null;

			try{
				//synchronized is needed so that we do not try to make more than one socket connection at a time.
				synchronized(inputStream){
					while((objFromInStream=inputStream.readUnshared()) != null ) {
						ServerObject serverObject = (ServerObject) objFromInStream;
					if(!serverObject.getUsername().equals("undefined")){
						appendMessageIfNotNull(serverObject);		
						//System.out.println("\n\n at the beginning " + System.currentTimeMillis());

						try{
							indexOfPlayer = serverObject.getArrayList().indexOf(serverObject.getUsername());
							logoutHandler(serverObject);
						}catch(NullPointerException ex){
							//do nothing
						}

						Boolean newUser = ((usernames.indexOf(serverObject.getUsername()) < 0 || usernames.indexOf(serverObject.getUsername()) >= usernames.size()));
						if(newUser){
							addNewUser(serverObject);
							/*for(PlayerMob player : players){
								player.updateCoordinates(serverObject);
							}*/
							System.out.println("usernames: " + usernames);
							System.out.println("indexOfPlayer: " + indexOfPlayer);
							//players.get(indexOfPlayer).updateCoordinates(serverObject);
							players.get(indexOfPlayer).updateOthersCoordinates(serverObject.getXCoordinate() - getXInWorld(indexOfPlayer), serverObject.getYCoordinate() - getYInWorld(indexOfPlayer));
							System.out.println("indexOfPlayer: " + indexOfPlayer);
							//System.out.println("\n\n\n" + getXInWorld(indexOfPlayer) + " " + getYInWorld(indexOfPlayer));
							
							/*if(thisUser(indexOfPlayer)  && usernames.size() > 1){
							players.get(indexOfPlayer).updateOthersCoordinates(serverObject.getXCoordinate() - getXInWorld(indexOfPlayer), serverObject.getYCoordinate());
						}else{
							players.get(indexOfPlayer).updateCoordinates(serverObject);
							//players.get(1).updateOtherCoordinates(serverObject);
						}*/
							
							/*if(thisUser(indexOfPlayer)  && usernames.size() > 1){
								players.get(indexOfPlayer).updateOthersCoordinates(serverObject.getXCoordinate() - getXInWorld(indexOfPlayer), serverObject.getYCoordinate());
							}else{
								players.get(indexOfPlayer).updateCoordinates(serverObject);
								//players.get(1).updateOtherCoordinates(serverObject);
							}*/
						}
						else{
							if(thisUser(indexOfPlayer))	moveEveryoneElse(indexOfPlayer, serverObject);
							otherPlayerMove(indexOfPlayer, serverObject);
						}

						//players.get(indexOfPlayer).updateCoordinates(serverObject);
						players.get(indexOfPlayer).updateFace(serverObject);
						players.get(indexOfPlayer).setClientServUsername(serverObject,myChat);
						
						inGame.setPlayers(players,indexOfPlayer);
						//System.out.println("\nat the end " + System.currentTimeMillis());
					}
				}
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}

		}
		
		public int getXInWorld(int indexOfPlayer){
			int xInWorld = 0; 
			if(players.size() > 1 && thisUserIndex() >= 0){
				//xInWorld = players.get(0).getXCoordinate() - players.get(indexOfPlayer).getXCoordinate();
				xInWorld = players.get(thisUserIndex()).getXCoordinate() - 400;
				//System.out.println("----getXInWorld thisUserIndex()-----\n thisUserIndex(): " + thisUserIndex() + players.get(thisUserIndex()).getXCoordinate() + "  " + players.get(indexOfPlayer).getXCoordinate() + "\n" + xInWorld);
			}
			return xInWorld;
		}
		
		public int getYInWorld(int indexOfPlayer){
			int yInWorld = 0; 
			if(players.size() > 1  && thisUserIndex() >= 0){
				yInWorld = players.get(thisUserIndex()).getYCoordinate() - 200;
				//yInWorld = players.get(0).getYCoordinate() - players.get(indexOfPlayer).getYCoordinate();
			}
			return yInWorld;
		}

		public boolean thisUser(int indexOfPlayer){
			return myChat.getUsername().equals(usernames.get(indexOfPlayer));
		}
		
		public int thisUserIndex(){
			int index = 0;
			for(String username : usernames){
				if(myChat.getUsername().equals(username)){
					return index;
				}
				index ++;
			}
			return -1;
		}
		

		public void moveEveryoneElse(int indexOfPlayer, ServerObject serverObject) throws IndexOutOfBoundsException {
				for(PlayerMob eryElse : players){
					if(eryElse == players.get(indexOfPlayer)){
						players.get(indexOfPlayer).readMove(serverObject, indexOfPlayer);
					}else{
						eryElse.worldMove(serverObject,indexOfPlayer);
					}
				}
		}
		
		public void otherPlayerMove(int indexOfPlayer, ServerObject serverObject) throws IndexOutOfBoundsException {
			players.get(indexOfPlayer).readMove(serverObject, indexOfPlayer);
		}
		
		public void addNewUser(ServerObject serverObject){
			usernames.add(serverObject.getUsername());
			PlayerMob aPlayer = new PlayerMob(networkStartup);
			players.add(aPlayer);
			inGame.drawFromServer(aPlayer);
		}

		public void appendMessageIfNotNull(ServerObject serverObject){
			if(incoming != null  && serverObject.getMessage() != null) incoming.append(serverObject.getMessage() + "\n");
		}

		public void logoutHandler(ServerObject serverObject){
			if(serverObject.getArrayList().size() > usernames.size() || serverObject.getArrayList().size() < usernames.size()){
				usersIn = 1;
			}

			if(usersIn == 1 && serverObject.getArrayList().size() < usernames.size()){
				ArrayList<String> tempUsers = new ArrayList<String>();
				tempUsers = usernames;
				tempUsers.removeAll(serverObject.getArrayList());

				int removeIndex = usernames.indexOf(tempUsers.get(0));
				usernames = serverObject.getArrayList();

				inGame.removeFromServer(players.get(removeIndex));
				players.remove(removeIndex);
				inGame.setPlayers(players,removeIndex);
				
				usersIn = 0;
			}
		}

	}//End of Incoming Reader (the thread) class
}//End of Client class
