package networking;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Messenger{

	client clnt = new client();
	private JFrame frame;
	private JTextField Inputbox;
	private JTextArea ChatBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Messenger window = new Messenger();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public Messenger() throws IOException{
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()  throws IOException{
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 555, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Inputbox = new JTextField();
		Inputbox.setBounds(50, 230, 213, 20);
		frame.getContentPane().add(Inputbox);
		Inputbox.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(clnt.isConnected())
				{
					
					try {
						clnt.sendMessage(Inputbox.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Inputbox.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "you are not connected to the server!");
				}
				
				
			}
		});
		btnSend.setBounds(273, 229, 89, 23);
		frame.getContentPane().add(btnSend);
		
		
		
		ChatBox = new JTextArea();
		ChatBox.setBounds(33, 11, 306, 190);
		frame.getContentPane().add(ChatBox);
		
		JLabel connectionLabel = new JLabel("Disconected");
		connectionLabel.setBounds(349, 16, 104, 14);
		frame.getContentPane().add(connectionLabel);
		
		
		
		JButton connectionButton = new JButton("Connect");
		connectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					clnt.startConnection("localhost",6666);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(clnt.isConnected())
				{
					
					connectionLabel.setText("connected");
					CheckForIncomingMessages();
				}
				
			}
		});
		connectionButton.setBounds(349, 43, 89, 23);
		frame.getContentPane().add(connectionButton);
		
		JButton btnReceive = new JButton("receive");
		btnReceive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println(clnt.receiveMessage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnReceive.setBounds(364, 117, 89, 23);
		frame.getContentPane().add(btnReceive);
		
		
	}
	
	
	void CheckForIncomingMessages()
	{
		new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		         
		         while (true) {
		        	 
		        	 String inputLine = null;
						try {
							inputLine = clnt.receiveMessage();
						} catch (IOException e) {
							
							e.printStackTrace();
						}
		        	 
				  if (inputLine!=null)
				  {
					  ChatBox.getText();
				 ChatBox.setText(ChatBox.getText() + "\n" + inputLine);
				  }
				  }
		    	 
		     }
		}).start();
		
	}
	
	

}
