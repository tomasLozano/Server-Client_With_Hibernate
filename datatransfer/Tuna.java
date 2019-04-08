/* File: Tuna.java
 * Author: Stanley Pieda
 * Modified: 
 * Date: August 2018
 * Description: Sample solution to Assignment 2
 * Modifications:
 *     ToDo: update with JPA annotations for Hibernate to use
 *     Note: Use Javadoc comments, then annotations, then method header.
 */
package datatransfer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;


/**
 * Transfer object for Tuna data
 * @author Stanley Pieda
 */
@Entity
public class Tuna implements Serializable{
	/** Explicit serialVersionUID to avoid generating one automatically */
	private static final long serialVersionUID = 1L;
	
	/** ID value for database */
	private Integer id;
	
	/** recordNumber for database, originally matched a dataset file line number */
	private int recordNumber;
	
	/** omega field */
	private String omega;
	
	/** delta field */
	private String delta;
	
	/** theta field */
	private String theta;
	
	/** uuid field, contains UUID as String */
	private String uuid;
	
	/** see lab hand-out notes from assignment 1 */
	private boolean isLastTuna;
	
	/**
	 * Default constructor, sets id and recordNumber to zero, omega, delta, theta, and uuid to empty Strings
	 * @author Stanley Pieda
	 */
	public Tuna() {
		this(0,0,"","","","");
	}
	
	/**
	 * Telescoping constructor.
	 * @param id The id as Integer
	 * @param recordNumber The recordNumber as int
	 * @param omega The omega as String
	 * @param lambda The delta as String
	 * @param theta The theta as String
	 * @param uuid The UUID as String
	 * @author Stanley Pieda
	 */
	public Tuna(Integer id, int recordNumber, String omega, String delta, String theta, String uuid) {
		this.id = id;
		this.recordNumber = recordNumber;
		this.omega = omega;
		this.delta = delta;
		this.theta = theta;
		this.uuid = uuid;
	}
	
	/** Getter for id */
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	/** Setter for id */
	public void setId(Integer id) {
		this.id = id;
	}
	/** Getter for recordNumber */
	@Column (length = 42)
	public int getRecordNumber() {
		return recordNumber;
	}
	/** Setter for recordNumber */
	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}
	/** Getter for omega */
	@Column (length = 42)
	public String getOmega() {
		return omega;
	}
	/** Setter for omega */
	public void setOmega(String omega) {
		this.omega = omega;
	}
	/** Getter for delta */
	@Column (length = 42)
	public String getDelta() {
		return delta;
	}
	/** Setter for delta */
	public void setDelta(String delta) {
		this.delta = delta;
	}
	/** Getter for theta */
	@Column (length = 42)
	public String getTheta() {
		return theta;
	}
	/** Setter for theta */
	public void setTheta(String theta) {
		this.theta = theta;
	}
	/** Getter for uuid */
	@Column (length = 42)
	public String getUUID() {
		return uuid;
	}
	/** Setter for uuid */
	public void setUUID(String uuid) {
		this.uuid = uuid;
	}
	/** Getter for isLastTuna, can be used by consumer to detect end of buffer */
	@Transient
	public boolean isLastTuna() {
		return isLastTuna;
	}
	/** Setter for isLastTuna, can be used by producer when placing last Tuna into buffer */
	public void setLastTuna(boolean isLastTuna) {
		this.isLastTuna = isLastTuna;
	}
	
	/** Overridden toString() to provide formatting for console output. */
	@Override
	public String toString() {
		return String.format("%d, %d, %s, %s, %s, %s", id, recordNumber, omega, delta, theta, uuid);
	}
}
