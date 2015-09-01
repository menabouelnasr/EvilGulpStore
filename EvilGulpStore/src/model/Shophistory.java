package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the SHOPHISTORY database table.
 * 
 */
@Entity
@Table(name="Shophistory", schema="TESTDB")
@NamedQuery(name="Shophistory.findAll", query="SELECT s FROM Shophistory s")
public class Shophistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String color;

	private String description;

	private double price;

	private String productname;

	private int quantity;

	private int userid;

	public Shophistory() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductname() {
		return this.productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}