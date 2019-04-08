/* File TunaServer.java
 * Author: Todd Kelley
 * Modified By: Stanley Pieda
 * Modifed On: August 2018
 * Description: RMI Server startup.
 */
package dataaccesslayer;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.query.Query;

import datatransfer.Tuna;

/**
 * @author Stanley Pineda
 * Modified by Tomas Lozano
 * Student Number: 040869662
 * Course: CST8277_300
 * Modified on: November 2nd 2018
 * Description: Implements TunaDao, starting a hibernate and doing a function depending on the command input by the client
 * If it is insert, it inserts the data in a database
 * If it is search, looks up a tuna by the uuid
 * If it is disconnect, destroy the registry and closes the connection
 */
public enum TunaDaoImpl implements TunaDao {

	/** Only use one constant for Singleton Design Pattern */
	INSTANCE;
	
	private SessionFactory factory;
	private final StandardServiceRegistry registry = buildRegistry();

	/**
	 * Class constructor. Starts Hibernate
	 */
	private TunaDaoImpl(){
		try {
			// A SessionFactory is set up once for an application!
			MetadataImplementor meta = 
					(MetadataImplementor) new MetadataSources( registry )
					.addAnnotatedClass(Tuna.class)
					.buildMetadata();
			factory = meta.buildSessionFactory();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Problem starting hibernate, attempting to shutdown");
			shutdown();
		}
	}

	/**
	 * Configures and build a registry
	 * @author Stanley Pineda
	 * Modified by Tomas Lozano
	 * 
	 * @return registry, to pass to the metadata when building the Hibernate
	 */
	private StandardServiceRegistry buildRegistry() {
		StandardServiceRegistry registry = null;
		try {
			// A SessionFactory is set up once for an application!
			registry =  new StandardServiceRegistryBuilder()
					.configure() // configures settings from hibernate.cfg.xml
					.build();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Problem starting hibernate, attempting to shutdown");
			shutdown();
		}
		return registry;
	}

	
	// shut down the factory, and the registry (StandardServiceRegistry)
	public void shutdown() {
		System.out.println("Closing factory");
		try {
			if(factory != null && factory.isClosed() == false) {
				factory.close();
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Destroying registry");
		try {
			if(registry != null) {
				StandardServiceRegistryBuilder.destroy(registry);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Tuna findByUUID(String uuid) {
		System.out.println("dao findbyuuid");
		
		Session s = null;
		
		s= factory.openSession();
		
		@SuppressWarnings("rawtypes")
		Query query = s.createQuery("from Tuna where UUID = :uuid ");
		query.setParameter("uuid", uuid);
		
		List<?> list = query.list();
		Tuna tuna = (Tuna)list.get(0);
		
		return tuna;
	}

	@Override
	public void insertTuna(Tuna tuna) {
		System.out.println("dao insert");
		Session s = null;
		Transaction tx = null;
		try {
			s = factory.openSession();
			tx = s.beginTransaction();
			s.save(tuna);
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("insert fail");
			if (tx!=null) tx.rollback();
			throw e;
		}
		finally {
			s.close();
		}
		System.out.println("insert success");
	}
}
