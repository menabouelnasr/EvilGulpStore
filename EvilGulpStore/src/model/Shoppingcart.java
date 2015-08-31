package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the SHOPPINGCART database table.
 * 
 */
@Entity
@Table(name="Shoppingcart", schema="TESTDB")
@NamedQuery(name="Shoppingcart.findAll", query="SELECT s FROM Shoppingcart s")
public class Shoppingcart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String color;

	private String description;

	private double price;

	private String productname;

	private int quantity;

	public Shoppingcart() {
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

}