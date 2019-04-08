/* File: TunaDao.java
 * Author: Stanley Pieda
 * Date: August 2018
 * Description: Sample solution to Assignment 3
 * Updated to remove throws SQLException from method signatures.
 * References:
 * Ram N. (2013).  Data Access Object Design Pattern or DAO Pattern [blog] Retrieved from
 * http://ramj2ee.blogspot.in/2013/08/data-access-object-design-pattern-or.html
 */
package dataaccesslayer;

//import java.util.List; // not needed for now
import datatransfer.Tuna;

/**
 * Partially complete interface for DAO design pattern.
 * Has one insert method, and one find-by-UUID method.
 * @author Stanley Pieda
 */
public interface TunaDao {

	/** 
	 * Should return a reference to a Tuna object, loaded with data
	 * from the database, based on lookup using a UUID as String
	 * @param uuid String based UUID
	 * @return Tuna transfer object, or null if no match based on uuid found
	 * @author Stanley Pieda
	 */
	Tuna findByUUID(String uuid);
	
	/**
	 * Should accept a Tuna object reference, insert the encapsulated data into database.
	 * @param tuna with data for record insertion
	 * @author Stanley Pieda
	 */
	void insertTuna(Tuna tuna);
	
	/**
	 * Allow for shutdown of database resources
	 * @author Stanley Pieda
	 */
	void shutdown();
}
