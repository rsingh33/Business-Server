package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Login {
	// Load the database file and integrate it into database
	ClassLoader cl = this.getClass().getClassLoader();
	Socket clientSocket;
	String[] users = new String[10];
	int netBuy = 0;
	int netSell = 0;
	int netInterest = 0;
	ArrayList<Socket> socketList = new ArrayList<Socket>();
	ArrayList<String> onlineUsers = new ArrayList<String>();

	public static void main(String[] args) {
		// Server is started on port number 10000
		Login login = new Login();
		new Thread(login.new ServerTask(null)).start();

	}

	// login method takes in three arguments username password and member id
	// checks for username and password in the database and processes
	// accordingly
	public void login(String userName, String password, String memberId) {

		boolean match = false;

		try {
			//  database file is loaded from the project
			InputStream inputStream = cl
					.getResourceAsStream("login/resources/database.txt");
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream));
			String strLine;
			String[] dataReader;
			dataReader = new String[10];
			String readData = "";
    //read from the database file and store it in memory
			while ((strLine = bufferedReader.readLine()) != null) {
				readData = readData + strLine + ":";
			}

			bufferedReader.close();
		
			dataReader = readData.split(":");
// loops through the database and checks for the match 
			for (int i = 0; i < dataReader.length - 2; i += 3) {
//if username and password match then send reply message as login successfull
				if (userName.equals(dataReader[i])) {
					match = true;
					boolean already = false;

					if (password.equals(dataReader[i + 1])) {
						for (int j = 0; j < onlineUsers.size(); j++) {
							if (onlineUsers.get(j).equals(userName)) {
								already = true;
								break;
							}
						}
						
		// if the user already logged in the do not add to list and don't send the message			
						if (already) {
							Message message1 = new Message();
							message1.setMessage("loginResponse");
							message1.setLoginResp("Already Logged in");
							Gson gson = new Gson();
							String test = gson.toJson(message1);
							OutputStream os1 = clientSocket.getOutputStream();
							OutputStreamWriter osw1 = new OutputStreamWriter(
									os1);
							BufferedWriter bw1 = new BufferedWriter(osw1);
							bw1.write(test + "\n");
							bw1.flush();

						} else {
							onlineUsers.add(userName);
							LoginLogoutMessage message = new LoginLogoutMessage();
							message.setMessage("loginResponse");

							if (onlineUsers.contains("user1")) {
								users[0] = "user1";
								message.getOnlineUsers().setUsers(users);

							}

							if (onlineUsers.contains("user2")) {
								users[1] = "user2";
								message.getOnlineUsers().setUsers(users);

							}

							if (onlineUsers.contains("user3")) {
								users[2] = "user3";
								message.getOnlineUsers().setUsers(users);

							}
							if (onlineUsers.contains("user4")) {
								users[3] = "user4";
								message.getOnlineUsers().setUsers(users);

							}
							if (onlineUsers.contains("user5")) {
								users[4] = "user5";
								message.getOnlineUsers().setUsers(users);

							}

							if (onlineUsers.contains("user6")) {
								users[5] = "user6";
								message.getOnlineUsers().setUsers(users);

							}
							if (onlineUsers.contains("user7")) {
								users[6] = "user7";
								message.getOnlineUsers().setUsers(users);

							}
							if (onlineUsers.contains("user8")) {
								users[7] = "user8";
								message.getOnlineUsers().setUsers(users);

							}
							if (onlineUsers.contains("user9")) {
								users[8] = "user9";
								message.getOnlineUsers().setUsers(users);

							}
							if (onlineUsers.contains("user10")) {
								users[9] = "user10";
								message.getOnlineUsers().setUsers(users);
							}
							Gson gson = new Gson();
							String test = gson.toJson(message);
							for (int k = 0; k < socketList.size(); k++) {

								OutputStream os1 = socketList.get(k)
										.getOutputStream();
								OutputStreamWriter osw1 = new OutputStreamWriter(
										os1);
								BufferedWriter bw1 = new BufferedWriter(osw1);
								bw1.write(test + "\n");
								bw1.flush();
							}

						}

					} 
					// the password is incorrect reply back with incorrect message
					else {

						Message message2 = new Message();
						message2.setMessage("loginResponse");
						message2.setLoginResp("Password Incorrect");
						Gson gson = new Gson();
						String test = gson.toJson(message2);
						OutputStream os1 = clientSocket.getOutputStream();
						OutputStreamWriter osw1 = new OutputStreamWriter(os1);
						BufferedWriter bw1 = new BufferedWriter(osw1);
						bw1.write(test + "\n");
						bw1.flush();

					}

				}
			}
			// if username does not match send back the message check ypur credentials
			if (!match) {

				Message message3 = new Message();
				message3.setMessage("loginResponse");
				message3.setLoginResp("Incorrect Details");
				Gson gson = new Gson();
				String test = gson.toJson(message3);
				OutputStream os1 = clientSocket.getOutputStream();
				OutputStreamWriter osw1 = new OutputStreamWriter(os1);
				BufferedWriter bw1 = new BufferedWriter(osw1);
				bw1.write(test + "\n");
				bw1.flush();
			}

			inputStream.close();
		} catch (Exception f) {
			f.printStackTrace();
		}

	}

	class ServerTask implements Runnable {

		String msgReceived;

		public ServerTask(String message) {
			msgReceived = message;
			System.out.println("server started");
		}

		@Override
		public void run() {
			ServerSocket serverSocket = null;

			try {
				serverSocket = new ServerSocket(11000);
				Socket clientSocket;

				while (true) {
					clientSocket = serverSocket.accept();
					if (clientSocket != null) {
						new Thread(new Service(clientSocket, msgReceived))
								.start();
					}

				}

			} catch (StreamCorruptedException sc) {
				System.out.println(sc);
			} catch (IOException i) {
				i.printStackTrace();
			}
		}
	}
// Service class is there to spawn a new thread whenever a new client comes in
	class Service implements Runnable {

		String msgReceived;
		Message message = new Message();

		Service(Socket client, String message) {

			clientSocket = client;
			msgReceived = message;
		}

		public void run() {

			while (clientSocket != null) {

				OutputStream os;

				BufferedReader buffer;
				try {

					buffer = new BufferedReader(new InputStreamReader(
							clientSocket.getInputStream()));
					msgReceived = buffer.readLine();
					Gson gson = new GsonBuilder().create();
					message = gson.fromJson(msgReceived, Message.class);

					if (message.getMessage().equalsIgnoreCase("login")) {
						if (!(socketList.contains(clientSocket)))
							socketList.add(clientSocket);

						login(message.getValues().getUserid(), message
								.getValues().getPassword(), message.getValues()
								.getMemberid());

					} else if (message.getMessage().equalsIgnoreCase("logout")) {

						String id = message.getLogout();

						LoginLogoutMessage message2 = new LoginLogoutMessage();
						message2.setMessage("LogoutResponse");

						for (int i = 0; i < onlineUsers.size(); i++) {
							if (id.equals(onlineUsers.get(i))) {
								onlineUsers.remove(i);

							}
						}

						for (int i = 0; i < users.length; i++) {
							System.out.println(i);
							System.out.println(users[i]);
							System.out.println(id);
							if (users[i].equalsIgnoreCase(id)) {
								users[i] = null;
								break;
							}
						}

						if (onlineUsers.contains("user1")) {
							users[0] = "user1";
							message2.getOnlineUsers().setUsers(users);

						}

						if (onlineUsers.contains("user2")) {
							users[1] = "user2";
							message2.getOnlineUsers().setUsers(users);

						}

						if (onlineUsers.contains("user3")) {
							users[2] = "user3";
							message2.getOnlineUsers().setUsers(users);

						}
						if (onlineUsers.contains("user4")) {
							users[3] = "user4";
							message2.getOnlineUsers().setUsers(users);

						}
						if (onlineUsers.contains("user5")) {
							users[4] = "user5";
							message2.getOnlineUsers().setUsers(users);

						}

						if (onlineUsers.contains("user6")) {
							users[5] = "user6";
							message2.getOnlineUsers().setUsers(users);

						}

						if (onlineUsers.contains("user7")) {
							users[6] = "user7";
							message2.getOnlineUsers().setUsers(users);

						}
						if (onlineUsers.contains("user8")) {
							users[7] = "user8";
							message2.getOnlineUsers().setUsers(users);

						}
						if (onlineUsers.contains("user9")) {
							users[8] = "user9";
							message2.getOnlineUsers().setUsers(users);

						}
						if (onlineUsers.contains("user10")) {
							users[9] = "user10";
							message2.getOnlineUsers().setUsers(users);

						}
						String test = gson.toJson(message2);
						int index = 0;
						for (int i = 0; i < socketList.size(); i++) {
							if (socketList.get(i).equals(clientSocket)) {
								index = i;
							} else {
								OutputStream os1 = socketList.get(i)
										.getOutputStream();
								OutputStreamWriter osw1 = new OutputStreamWriter(
										os1);
								BufferedWriter bw1 = new BufferedWriter(osw1);
								bw1.write(test + "\n");

								bw1.flush();
							}
						}

						socketList.remove(index);
						clientSocket.close();

						return;
					} else if (message.getMessage()
							.equalsIgnoreCase("setPrice")) {
						netInterest = 0;
						DateFormat dateFormat = new SimpleDateFormat(
								"yyyy/MM/dd HH:mm:ss");
						Date date = new Date();

						String newPriceStr = message.getSetPrice();

						Message message1 = new Message();
						message1.setMessage("PriceResponse");
						message1.setSetPrice(newPriceStr);
						message1.setTime(dateFormat.format(date));
						String test = gson.toJson(message1);

						for (int i = 0; i < socketList.size(); i++) {

							OutputStream os1;

							os1 = socketList.get(i).getOutputStream();

							OutputStreamWriter osw1 = new OutputStreamWriter(
									os1);
							BufferedWriter bw1 = new BufferedWriter(osw1);
							bw1.write(test + "\n");
							bw1.flush();
						}

					} else if (message.getMessage()
							.equalsIgnoreCase("interest")) {

						if (message.getBuy() != null) {
							int buy = Integer.parseInt(message.getBuy());
							System.out.println(buy);
							netInterest = netInterest + buy;
						}

						else if (message.getSell() != null) {
							int sell = Integer.parseInt(message.getSell());
							System.out.println(sell);
							netInterest = netInterest - sell;
						}

						System.out.println(netInterest);
						Message message1 = new Message();
						message1.setMessage("PriceResponse");
						message1.setInterest("" + netInterest);
						String test = gson.toJson(message1);

						for (int i = 0; i < socketList.size(); i++) {
							OutputStream os1 = socketList.get(i)
									.getOutputStream();
							OutputStreamWriter osw1 = new OutputStreamWriter(
									os1);
							BufferedWriter bw1 = new BufferedWriter(osw1);
							bw1.write(test + "\n");
							bw1.flush();
						}

					} else {

						String message = "Please enter a valid username and password \n";
						os = clientSocket.getOutputStream();
						OutputStreamWriter osw = new OutputStreamWriter(os);
						BufferedWriter bw = new BufferedWriter(osw);
						bw.write(message);
						bw.flush();

					}
				} catch (SocketException e) {
					e.printStackTrace();
					try {
						clientSocket.close();
						break;
					} catch (IOException ss) {
						// TODO Auto-generated catch block
						ss.printStackTrace();
					}

				} catch (com.google.gson.JsonSyntaxException c) {
					c.printStackTrace();
				} catch (com.google.gson.stream.MalformedJsonException l) {
					l.printStackTrace();
				}

				catch (IOException e) {

					e.printStackTrace();
				} catch (NullPointerException n) {
					n.printStackTrace();
					try {
						clientSocket.close();
						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

	}

}
