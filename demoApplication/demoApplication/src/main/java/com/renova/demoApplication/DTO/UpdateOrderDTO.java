package com.renova.demoApplication.DTO;

public class UpdateOrderDTO {

    private Long id;
    private int quantity;
    private int cart_id;
	public Long getId() {
		return id;
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public UpdateOrderDTO(Long id, Integer quantity,Integer cart_id) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.cart_id = cart_id;
	}
	public UpdateOrderDTO() {
		super();
	}
    
    
}