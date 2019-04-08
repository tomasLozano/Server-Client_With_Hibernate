/* File: EchoServerServiceImpl.java
 * Provided by: Todd Kelley (2016) Personal Communication
 * Modified by: Stanley Pieda
 * Modified Date: Feb 13, 2018
 * Description: Implementation of EchoServerService interface, also
 *              uses Hibernate to persist client information to database
 *              for every message received. Also sends the client all
 *              of it's previous messages in response to 'dumpit' String
 *              Updated: Modified to close down resources.
 */
package server;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import dataaccesslayer.TunaDaoImpl;
import datatransfer.Tuna;

/**
 * @author Stanley Pineda
 * Modified by Tomas Lozano
 * Student Number: 040869662
 * Course: CST8277_300
 * Modified on: November 2nd 2018
 * Description: Implements TunaService, calls the method from TunaDaoImpl depending on the input from the client
 */
public class TunaServiceImpl extends RemoteServer implements TunaService{
	private static final long serialVersionUID = 1L;
	
	/**
	 * TunaServiceImpl Constructor
	 */
	public TunaServiceImpl(){}

	/**
	 * Calls shutdown from the TunaDaoImpl class
	 */
	public void shutdown() {
		TunaDaoImpl dao = TunaDaoImpl.INSTANCE;
		dao.shutdown();
	}

	@Override
	public void insert(Tuna tuna) throws RemoteException {
		TunaDaoImpl dao = TunaDaoImpl.INSTANCE;
		dao.insertTuna(tuna);
	}

	@Override
	public Tuna findByUUID(String uuid) throws RemoteException {
		TunaDaoImpl dao = TunaDaoImpl.INSTANCE;
		return dao.findByUUID(uuid);
	}
}
