package com.renova.demoApplication.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private Long id;

    @OneToMany( mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Set<OrderItem> items = new HashSet<>();

    private Long user_id;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", items=" + items + "]";
	}

	public ShoppingCart(Long id, String userId, Set<OrderItem> items) {
		super();
		this.id = id;
		this.items = items;
	}

	public ShoppingCart() {
		super();
	}    
}