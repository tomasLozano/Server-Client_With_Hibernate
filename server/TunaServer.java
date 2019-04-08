/* File: EchoServer.java
 * Provided by: Todd Kelley (2016) Personal Communication
 * Modified by: Stanley Pieda
 * Modified Date: Feb 13, 2018
 * Description: Starts the RMI Registry and loads the EchoServerService for use.
 *              Updated: When server admin uses enter key resources will be cleaned
 *                       up before the server shuts down.
 */
package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/*
 * Referenced works on shutting down RMI within the application:
 * https://coderanch.com/t/210114/java/Shut-RMI-Registry
 * https://community.oracle.com/thread/1180058?start=0&tstart=0
 */

/**
 * @author Stanley Pineda
 * Modified by Tomas Lozano
 * Student Number: 040869662
 * Course: CST8277_300
 * Modified on: November 2nd 2018
 * Description: Creates a RMI registry, exports the remote service and pauses waiting for a client. Then closes the hibernate resources
 */
public class TunaServer{

	/**
	 * Creates a RMI registry, exports the remote service and pauses waiting for a client. Then closes the hibernate resources
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int portNum = 8082;
		
		// Remote service
		TunaServiceImpl tunaServiceImpl = new TunaServiceImpl();

		try {
			// Creates a RMI registry, saving reference to it
			// message to users (this is done already on, next line)
			Registry registry = LocateRegistry.createRegistry(portNum);
			System.out.println( "Registry created" );

			// Exports the remote service
			// It rebind using portNum with service name /TunaService
			// message to users (this is done already on next line)
			UnicastRemoteObject.exportObject(tunaServiceImpl, 0);
			System.out.println( "Exported" );
			Naming.rebind("//localhost:" + portNum + "/TunaService", tunaServiceImpl);

			System.out.println("Press any key to shutdown remote object and end program");
			Scanner input = new Scanner(System.in);
			input.nextLine(); // pause, let server-side admin close down connections
			
			tunaServiceImpl.shutdown(); // close down Hibernate resources

			System.out.println("un-exporting remote object");
			UnicastRemoteObject.unexportObject(tunaServiceImpl, true); // remove remote object

			input.close();

		} catch (Exception e) {
			System.out.println("Trouble: " + e);
			e.printStackTrace();
		}
	}

}
