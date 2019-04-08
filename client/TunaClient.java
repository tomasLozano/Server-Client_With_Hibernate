/* File: EchoClientDatabase.java
 * Provided by: Todd Kelley (2016) Personal Communication
 * Modified by: Stanley Pieda
 * Modified Date: Oct 9, 2017
 * Description: Echoclient that communicates to server using RMI and
 *              serialized Strings.
 */
package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.UUID;

import datatransfer.Tuna;
import server.TunaService;

import java.net.*;
import java.rmi.NotBoundException;

/**
 * @author Tomas Lozano
 * Student Number: 040869662
 * Course: CST8277_300
 * Modified on: November 2nd 2018
 * Description: Creates a connection with a server and asks for a command from the user.
 */
public class TunaClient {

	/**
	 * Creates a connection with a server and asks for a command from the user.
	 *  * If the user input insert, asks for the tuna information to upload it into the database,
	 * If the user input search, looks for a tuna according to its uuid,
	 * If the user input disconnect, closes the client connection
	 */
	public static void main(String[] args) {
		int port = 8082;
		String serverName = new String("localhost");
		Scanner scan = new Scanner(System.in);

		String command = "";
		boolean flag = false;
		TunaService ts = null;

		try {

			System.out.println("Attempting to connect to rmi://"+serverName+":"+port+"/TunaService");
			ts = (TunaService) Naming.lookup("rmi://"+serverName+":"+port+"/TunaService");

			do {
				System.out.println("Choose a command: insert, search or disconnect");
				command = scan.nextLine();

				if (command.equals("insert")) {
					int RecordNum = 0;

					System.out.println("Insert new Tuna data\n");

					System.out.print("Tuna Number: ");
					String TunaNum = scan.nextLine();
					RecordNum = Integer.parseInt(TunaNum);

					System.out.print("Omega:");
					String omega = scan.nextLine();
					System.out.print("Delta:");
					String delta = scan.nextLine();
					System.out.print("Theta:");
					String theta = scan.nextLine();

					UUID uuid = UUID.randomUUID();
					Tuna tuna = new Tuna(null, RecordNum, omega, delta, theta, uuid.toString());

					ts.insert(tuna);
					System.out.println("Tuna inserted");

				} else if (command.equals("search")) {
					String uuidSearch = null;

					System.out.println("Search tuna by UUID");
					System.out.print("What tuna are you looking for:");
					uuidSearch = scan.nextLine();
					if (uuidSearch == null || uuidSearch.equals("")) {
						System.out.println("Please enter a valid number");
					}else {
						Tuna tuna = ts.findByUUID(uuidSearch);
						System.out.println(tuna);
					}

				} else if (command.equals("disconnect") || command.equals("dis")) {

					System.out.println("Disconecting from Server");
					flag = true;
				} else {
					System.out.println("Pleae input the correct command");
				}
			} while (!flag);

			scan.close();
			System.out.println("Client shutting down");

		}	catch (MalformedURLException e) {
			System.out.println();
			System.out.println("MalformedURLException");
			e.printStackTrace();
		}
		catch (RemoteException e) {
			System.out.println();
			System.out.println("RemoteException");
			e.printStackTrace();
		}
		catch (NotBoundException e) {
			System.out.println();
			System.out.println("NotBoundException");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println();
			System.out.println(e.getClass().getName());
			e.printStackTrace();
		}
	}
}
