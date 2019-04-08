/* File: EchoServerService.java
 * Provided by: Todd Kelley (2016) Personal Communication
 * Modified by: Stanley Pieda
 * Modified Date: Feb 13, 2018
 * Description: Interface with remote method for clients to use.
 *              Update: Added more programmer comments.
 */
package server;
import java.rmi.Remote;

import datatransfer.Tuna;

/**
 * Methods for use by remote clients are listed here
 * @author Stanley Pieda
 */
public interface TunaService extends Remote {

	/**
	 * Should allow remote client to insert a Tuna into data store
	 * @param tuna the Tuna to be inserted
	 * @throws java.rmi.RemoteException
	 */
	public void insert(Tuna tuna) throws java.rmi.RemoteException;

	/**
	 * Should allow remote client to lookup a Tuna based on UUID
	 * @param uuid A String based UUID
	 * @return A Tuna object, retrieved with given uuid, or null if uuid not in database
	 * @throws java.rmi.RemoteException
	 */
	public Tuna findByUUID(String uuid) throws java.rmi.RemoteException;
}
