import java.net.*;

import java.io.*;

public class ChatWith{
	private static final String STOPCHAT  	= "STOPTHISCHAT";
	private String username;
	private Socket socket  	            	= null;
	private ObjectInputStream  objIn   		= null;
	private ObjectOutputStream objOut  		= null;
	private BufferedReader stdin 			= null;
   

	/**
	 * This method connects to socket, prepares I/O streams and starts the I/O threads. So basically everything
	 * @param serverName
	 * @param serverPort
	 * @param userName
	 */
   private ChatWith(final String serverName, final int serverPort, final String userName){
	   this.username = userName;
	   System.out.println("Establishing connection. Please wait ...");
	   try {
		   this.socket = new Socket(serverName, serverPort);
		   System.out.println("Connected : " + this.socket);
		   //System.out.println("Please enter your name/username : ");
		   this.getSocketIOStreams(this.socket);
		   //boolean stopChatting = continueChat();
		   /*while (stopChatting) {
			   stopChatting = continueChat();
		   }*/
		   

		   this.stdin = new BufferedReader(new InputStreamReader(System.in));
		   System.out.println("Got console input streams in client");
		   
		   ReaderThread readerThread = new ReaderThread(this.objIn, STOPCHAT);
		   WriterThread writerThread = new WriterThread(this.objOut, this.stdin, STOPCHAT, userName);
		   
		   readerThread.run();
		   writerThread.run();
		   
		   //readerThread.notify();
		   
		   //close();
	   } catch (UnknownHostException uhe) {
		   System.out.println("Host unknown: " + uhe.getMessage());
	   } catch (IOException ioe) {
		   System.out.println("Unexpected exception: " + ioe.getMessage());
	   } /*catch (ClassNotFoundException cnfe) {
		   cnfe.printStackTrace();
	   }*/finally{
		   //close();
	   }
	   
   }

   /**
    * Gets the socket I/O streams
    * @param socket
    */
   private void getSocketIOStreams(final Socket socket) {
	   try {
		   System.out.println("Getting imput streams in client ...");
		   this.objIn = new ObjectInputStream(socket.getInputStream());
		   System.out.println("Getting output streams in client ...");
		   this.objOut = new ObjectOutputStream(socket.getOutputStream());
		   this.objOut.flush();
		   System.out.println("Got socket I/O streams in client");
	   } catch (IOException ioe) {
		   System.out.println("Error while opening Streams ...");
		   ioe.printStackTrace();
	   }
   }

   private void close() {
	   try {
		   if (this.objIn != null)
			   this.objIn.close();
		   if (this.objOut != null)
			   this.objOut.close();
		   if(this.stdin != null)
			   this.stdin.close();
		   if (this.socket != null)
			   this.socket.close();
	   } catch (IOException ioe) {
		   System.out.println("Error while closing ... "+ioe.getMessage());
	   }
   }
   
   private boolean continueChat() throws ClassNotFoundException, IOException{
	   boolean stopChatting = false;
	   String receivingTextMsgBody;
	   String sendingTextMsgBody;
	   BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	   MessageBean receivingMsgObj = null;
	   MessageBean sendingMsgObj = null;
	   while(!stopChatting){
		   // Sending stuff
		   System.out.print(username+ " :  ");
		   sendingTextMsgBody = stdin.readLine();	// user input from terminal
		   sendingMsgObj = new MessageBean(username, sendingTextMsgBody);
		   objOut.writeObject(sendingMsgObj);
		   objOut.flush();
		   
		   // Receiving stuff
		   receivingMsgObj = (MessageBean)objIn.readObject();
		   receivingTextMsgBody = receivingMsgObj.getTextMessage();
		   
		   if(STOPCHAT.equalsIgnoreCase(receivingTextMsgBody)){
			   stopChatting = true;
			   break;
		   }
		   System.out.print(receivingMsgObj.getSenderName() +" :  ");
		   System.out.println(receivingTextMsgBody);
	   }
	   return stopChatting;
   }
   
   public static void main(String args[]) {
	   ChatWith client = null;
	   if (args.length != 3)
		   System.out.println("Usage: java ChatWith host port YourUserName");
	   else
		   client = new ChatWith(args[0], Integer.parseInt(args[1]), args[2]);
   }
}

//For reading from the console and writing to Socket
class WriterThread implements Runnable{
	
	private ObjectOutputStream objOut;
	private BufferedReader consoleIn;
	private String STOPCHATMESSAGE;
	private String USERNAME;

	/**
	 * Constructor.
	 *  
	 * @param objOut - The ObjectOutputStream to write to ( e.g., write to Socket stream ).
	 * @param consoleIn - The BufferedReader to read from ( e.g., read from console ).
	 * @param STOPCHATMESSAGE - The String indicating to stop.
	 * @param userName - The client username to display.
	 */
	public WriterThread(final ObjectOutputStream objOut, final BufferedReader consoleIn,
			final String STOPCHATMESSAGE, final String userName) {
		super();
		this.objOut = objOut;
		this.consoleIn = consoleIn;
		this.STOPCHATMESSAGE = STOPCHATMESSAGE;
		this.USERNAME = userName;
	}

	public void run() {
		   boolean stopChatting = false;
		   String sendingTextMsgBody;
		   MessageBean sendingMsgObj = null;
		   
		   while (!stopChatting) {
			   try {
				   // Sending stuff
				   System.out.print(this.USERNAME + " :  ");
				   sendingTextMsgBody = this.consoleIn.readLine(); // user input from terminal
				   sendingMsgObj = new MessageBean(this.USERNAME, sendingTextMsgBody);
				   objOut.writeObject(sendingMsgObj);
				   objOut.flush();
				   if(this.STOPCHATMESSAGE.equalsIgnoreCase(sendingTextMsgBody)){
					   stopChatting = true;
					   break;
				   }
				   //Thread.sleep(500);
			   } catch (IOException ioe) {
				   stopChatting = true;
				   ioe.printStackTrace();
			   }/*catch (InterruptedException interrEx) {
					interrEx.printStackTrace();
			   }*/
		   }
	   }
}		

// For reading from the socket stream and display on console
class ReaderThread implements Runnable {

	private ObjectInputStream objIn;
	private String STOPCHATMESSAGE;

	/**
	 * Constructor.
	 * 
	 * @param objIn
	 *            - The object input stream to read from
	 * @param STOPCHATMESSAGE
	 *            - The String indicating to stop.
	 */
	public ReaderThread(final ObjectInputStream objIn, final String STOPCHATMESSAGE) {
		super();
		this.objIn = objIn;
		this.STOPCHATMESSAGE = STOPCHATMESSAGE;
	}

	public void run() {
		boolean stopChatting = false;
		String receivingTextMsgBody;
		MessageBean receivingMsgObj = null;

		while (!stopChatting) {
			try {
				// Receiving stuff
				receivingMsgObj = (MessageBean) objIn.readObject();
				receivingTextMsgBody = receivingMsgObj.getTextMessage();

				System.out.print(receivingMsgObj.getSenderName() + " :  ");
				System.out.println(receivingTextMsgBody);

				if (STOPCHATMESSAGE.equalsIgnoreCase(receivingTextMsgBody)) {
					stopChatting = true;
					break;
				}
			} catch (IOException ioe) {
				stopChatting = true;
				ioe.printStackTrace();
			} catch (ClassNotFoundException cnfe) {
				stopChatting = true;
				cnfe.printStackTrace();
			}
		}
	}
}