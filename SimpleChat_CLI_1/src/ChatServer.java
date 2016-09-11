import java.net.*;
import java.io.*;

public class ChatServer implements Runnable {
	private static final String STOPCHAT  				= "STOPTHISCHAT";
	private static final String SERVER  				= "Groot";
	
	private Thread thread 								= null;
	private ServerSocket serverSocket 					= null;
	//private ChatServerThread eachClientHandlerThread 	= null;
	
	private Socket clientSocket 		= null;
	private int clientSocketPortID 		= -1;
	private ObjectInputStream  objIn   	= null;
	private ObjectOutputStream objOut  	= null;
	private BufferedReader stdin 		= null;

	public ChatServer(int port) {
		try {
			System.out.println("Binding to port " + port + ", please wait  ...");
			serverSocket = new ServerSocket(port);
			System.out.println("Server started: " + serverSocket);
			//startThread();
			
			this.clientSocket = serverSocket.accept();
			System.out.println("Client accepted: " + this.clientSocket);
			getSocketIOStreams();
			//continueChat();
			
			
			// For reading from the socket stream and display on console
			Thread receivingAndDisplayingThread = new Thread(){
				boolean stopChatting = false;
				String receivingTextMsgBody;
				MessageBean receivingMsgObj = null;

				public void run(){
					try{
						while (!stopChatting) {
							// Receiving stuff
							receivingMsgObj = (MessageBean) objIn.readObject();
							receivingTextMsgBody = receivingMsgObj.getTextMessage();

							System.out.print(receivingMsgObj.getSenderName() + " :  ");
							System.out.println(receivingTextMsgBody);

							if (STOPCHAT.equalsIgnoreCase(receivingTextMsgBody)) {
								stopChatting = true;
								break;
							}
						}
					}catch(IOException ioe){
						stopChatting = true;
						ioe.printStackTrace();
					} catch (ClassNotFoundException cnfe) {
						stopChatting = true;
						cnfe.printStackTrace();
					}
				}
			};
			
			
			// For reading from the console and writing to Socket
			Thread sendingThread = new Thread() {
				boolean stopChatting = false;
				String sendingTextMsgBody;
				//BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
				MessageBean sendingMsgObj = null;

				public void run() {
					while(!stopChatting){
						try {
							// Sending stuff
							System.out.print(SERVER + " :  ");
							sendingTextMsgBody = stdin.readLine(); // user input from terminal
							sendingMsgObj = new MessageBean(SERVER, sendingTextMsgBody);
							objOut.writeObject(sendingMsgObj);
							objOut.flush();
							if(STOPCHAT.equalsIgnoreCase(sendingTextMsgBody)){
								stopChatting = true;
								break;
							}
							//Thread.sleep(500);
						} catch (IOException ioe) {
							stopChatting = true;
							ioe.printStackTrace();
						}/* catch (InterruptedException interrEx) {
							interrEx.printStackTrace();
						}*/
					}
				}
			};
			
			receivingAndDisplayingThread.start();
			sendingThread.start();
			
			//close();
		} catch (IOException ioe) {
			System.out.println(ioe);
		}/* catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}*/
		finally{
			//close();
		}
	}
	
	public void close() {
		try {
			if (objIn != null)
				objIn.close();
			if (objOut != null)
				objOut.close();
			if(stdin != null)
				stdin.close();
			if (clientSocket != null)
				clientSocket.close();
		} catch (IOException ioe) {
			System.out.println("Error while closing ...");
			ioe.printStackTrace();
		}
	}
	
	private void getSocketIOStreams(){
		try{
			System.out.println("Getting socket output stream in Server ...");
			objOut = new ObjectOutputStream(this.clientSocket.getOutputStream());
			objOut.flush();
			System.out.println("Getting socket input stream in Server ...");
			objIn = new ObjectInputStream(this.clientSocket.getInputStream());
			System.out.println("Got socket I/O streams in Server");
			stdin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Got console input streams in ServerThread");
		}catch(IOException ioe){
			System.out.println("Error while opening Streams ...");
			ioe.printStackTrace();
		}
	}
	
	private boolean continueChat() throws ClassNotFoundException, IOException {
		boolean stopChatting = false;
		String receivingTextMsgBody;
		String sendingTextMsgBody;
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		MessageBean receivingMsgObj = null;
		MessageBean sendingMsgObj = null;
		while (!stopChatting) {
			// Receiving stuff
			receivingMsgObj = (MessageBean) objIn.readObject();
			receivingTextMsgBody = receivingMsgObj.getTextMessage();
			
			System.out.print(receivingMsgObj.getSenderName() + " :  ");
			System.out.println(receivingTextMsgBody);
			
			// Sending stuff
			System.out.print(SERVER+ " :  ");
			sendingTextMsgBody = stdin.readLine(); // user input from terminal
			sendingMsgObj = new MessageBean(SERVER, sendingTextMsgBody);
			objOut.writeObject(sendingMsgObj);
			objOut.flush();
			
			if (STOPCHAT.equalsIgnoreCase(receivingTextMsgBody)) {
				stopChatting = true;
				break;
			}
		}
		return stopChatting;
	}
	

	public void run() {
		while (thread != null) {
			try {
				System.out.println("Waiting for a client ...");
				addThread(serverSocket.accept());
			} catch (IOException ioe) {
				System.out.println("Acceptance Error: " + ioe);
			}
		}
	}

	
	public void addThread(Socket clientSocket) {
		/*
		System.out.println("Client accepted: " + clientSocket);
		eachClientHandlerThread = new ChatServerThread(this, clientSocket);
		//eachClientHandlerThread.getIOStreams();
		eachClientHandlerThread.start();
		*/
	}

	public void startThread() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		if (thread != null) {
			thread.stop();
			thread = null;
		}
	}

	public void closeSocket() throws IOException {
		if (serverSocket != null)
			serverSocket.close();
	}

	public static void main(String args[]) {
		ChatServer server = null;
		if (args.length != 1)
			System.out.println("Usage: java ChatServer port");
		else
			server = new ChatServer(Integer.parseInt(args[0]));
	}
}