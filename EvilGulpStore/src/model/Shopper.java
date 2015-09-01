package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SHOPPERS database table.
 * 
 */
@Entity
@Table(name="SHOPPERS", schema="TESTDB")
@NamedQuery(name="Shopper.findAll", query="SELECT s FROM Shopper s")
public class Shopper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String email;

	private String firstname;

	private String lastname;

	private String password;

	public Shopper() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}