package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PAYMENT database table.
 * 
 */
@Entity
@Table(name="Payment", schema="TESTDB")
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String baddress;

	private String bcity;

	private String bstate;

	private String bzip;

	private String card;

	private String saddress;

	private String scity;

	private String sstate;

	private String szip;

	private int userid;

	public Payment() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBaddress() {
		return this.baddress;
	}

	public void setBaddress(String baddress) {
		this.baddress = baddress;
	}

	public String getBcity() {
		return this.bcity;
	}

	public void setBcity(String bcity) {
		this.bcity = bcity;
	}

	public String getBstate() {
		return this.bstate;
	}

	public void setBstate(String bstate) {
		this.bstate = bstate;
	}

	public String getBzip() {
		return this.bzip;
	}

	public void setBzip(String bzip) {
		this.bzip = bzip;
	}

	public String getCard() {
		return this.card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getSaddress() {
		return this.saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public String getScity() {
		return this.scity;
	}

	public void setScity(String scity) {
		this.scity = scity;
	}

	public String getSstate() {
		return this.sstate;
	}

	public void setSstate(String sstate) {
		this.sstate = sstate;
	}

	public String getSzip() {
		return this.szip;
	}

	public void setSzip(String szip) {
		this.szip = szip;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}