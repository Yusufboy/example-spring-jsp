package com.renova.demoApplication.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;


@Entity
@Table(name = "item")
public class OrderItem {

	@Id
    private String id;

    @OneToOne
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name ="cart_id" , nullable = false)
    @JsonIgnore
    private ShoppingCart cart;

    private int quantity;

    public void fromDto(Product p, ShoppingCart cart, int quantity ){
        this.id = UUID.randomUUID().toString().replace("-", "");
        product = p;
        this.cart = cart;
        this.quantity = quantity;

    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderItem(String id, Product product, ShoppingCart cart, int quantity) {
		super();
		this.id = id;
		this.product = product;
		this.cart = cart;
		this.quantity = quantity;
	}

	public OrderItem() {
		super();
	}

    
}