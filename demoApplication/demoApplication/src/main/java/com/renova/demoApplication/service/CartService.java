package com.renova.demoApplication.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renova.demoApplication.DTO.OrderDTO;
import com.renova.demoApplication.DTO.UpdateOrderDTO;
import com.renova.demoApplication.model.OrderItem;
import com.renova.demoApplication.model.Product;
import com.renova.demoApplication.model.ShoppingCart;
import com.renova.demoApplication.repository.CartRepository;
import com.renova.demoApplication.repository.OrderRepository;
import com.renova.demoApplication.repository.ProductRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private long cartIndex;

    public long getCartIndex() {
		return cartIndex;
	}

	public void setCartIndex(long cartIndex) {
		this.cartIndex = cartIndex;
	}

	@Autowired
    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository,
                       OrderRepository orderRepository
                       ) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        cartIndex=cartRepository.count();
    }

    public String addToCart(OrderDTO order){
        Optional<ShoppingCart> cartFromRepo = cartRepository.findById(cartIndex);
        ShoppingCart cart;
        if ( ! cartFromRepo.isPresent() ){
            cart = new ShoppingCart();
            cartRepository.save(cart);
        }
        else{
            cart = cartFromRepo.get();
        }

        OrderItem item = new OrderItem();
        Product pt = productRepository.findProductById(order.getProductId())
                .orElseThrow( () -> new IllegalStateException("product not found"));
        item.fromDto(pt, cart, order.getQuantity());

        if(pt.getQuantity() < order.getQuantity()) {
        	throw new IllegalStateException("product quantity is not enough!");
        }
        
        boolean found=false;
        for(OrderItem tempItem : orderRepository.findAll()) {
        	if(tempItem.getProduct().getId() == order.getProductId() && item.getCart().getId() == tempItem.getCart().getId()) {
        		tempItem.setQuantity(tempItem.getQuantity() + order.getQuantity());
        		found = true;
        		orderRepository.save(tempItem);
        	}		
        }
        
        if(!found)
        	orderRepository.save(item);
        return "SUCCESS";
    }

    public String addNewCart(){
    	cartIndex = cartIndex+1;
    	ShoppingCart cart = new ShoppingCart();
        cartRepository.save(cart);
        return "SUCCESS";
    }
    
    public HashMap<String, Object> getProductsFromSpecificCart(Long id){
        HashMap<String, Object> hs = new HashMap<>();
        hs.put("products" , new ArrayList<>());
        hs.put("total" , 0);

        ArrayList<OrderItem> orders = new ArrayList<OrderItem>();
        for(OrderItem tempOrder : orderRepository.findAll()) {
        	if(tempOrder.getCart().getId() == id)
        		orders.add(tempOrder);
        }
        
        hs.put("products" ,  orders);
        int price = 0;
        for ( OrderItem order : orders ){
            price += order.getQuantity() * order.getProduct().getPrice();
        }
        hs.put("total", price);
        return hs;
    }
    
    public HashMap<String, Object> getProducts(){
        HashMap<String, Object> hs = new HashMap<>();
        hs.put("products" , new ArrayList<>());
        hs.put("total" , 0);

        //HashSet<OrderItem> orders = (HashSet<OrderItem>) cartRepository.findById(cartIndex);
        ArrayList<OrderItem> orders = new ArrayList<OrderItem>();
        for(OrderItem tempOrder : orderRepository.findAll()) {
        	if(tempOrder.getCart().getId() == cartIndex)
        		orders.add(tempOrder);
        }
        
        hs.put("products" ,  orders);
        int price = 0;
        for ( OrderItem order : orders ){
            price += order.getQuantity() * order.getProduct().getPrice();
        }
        hs.put("total", price);
        return hs;
    }

    public String updateOrder(UpdateOrderDTO updateOrderDTO){
    	for(OrderItem tempOrder : orderRepository.findAll()) {
        	if(tempOrder.getCart().getId() == updateOrderDTO.getCart_id() && tempOrder.getProduct().getId() == updateOrderDTO.getId()) {
        		tempOrder.setQuantity(updateOrderDTO.getQuantity());
        		orderRepository.save(tempOrder);
        	}	
        }
        return "SUCCESS";
    }


    public String deleteOrder(Long id, int cart_id){
    	for(OrderItem tempOrder : orderRepository.findAll()) {
        	if(tempOrder.getCart().getId() == cart_id && tempOrder.getProduct().getId() == id) {
        		orderRepository.delete(tempOrder);
        	}
    	}
        return "SUCCESS";
    }

}