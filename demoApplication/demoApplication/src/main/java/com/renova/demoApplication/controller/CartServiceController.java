package com.renova.demoApplication.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.renova.demoApplication.DTO.OrderDTO;
import com.renova.demoApplication.DTO.UpdateOrderDTO;
import com.renova.demoApplication.service.CartService;
import com.renova.demoApplication.service.ProductService;

@Controller
@RequestMapping(path = "/e-commerce")
public class CartServiceController {
    private final CartService cartService;
    private final ProductService productService;

    @PostMapping( path = "addCart")
    public String addCart(ModelMap model){
    	cartService.addNewCart();
    	model.addAttribute("products", productService.getProducts());
        return "view-products";
    }
    
    @PostMapping( path = "add")
    public String addProduct(ModelMap model,@RequestParam Long id, @RequestParam  int quantity){
    	OrderDTO tempDto = new OrderDTO(id,quantity);
    	cartService.addToCart(tempDto);
    	model.addAttribute("orders", cartService.getProducts().get("products"));
    	model.addAttribute("totalPrice", cartService.getProducts().get("total"));
        return "cart";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getOrders(ModelMap model){
    	model.addAttribute("orders", cartService.getProducts().get("products"));
    	model.addAttribute("totalPrice", cartService.getProducts().get("total"));
        return "cart";
    }
    
    @PostMapping( path = "previous_carts")
    public String getPrevCarts(ModelMap model){
    	List<Long> ids = new ArrayList<Long>();
    	for(Long i=(long) 1;i<=cartService.getCartIndex();i++)
    		ids.add(i);
    	model.addAttribute("ids", ids);
        return "previous_carts";
    }
    
    @PostMapping( path = "previous_cart")
    public String getPrevCart(ModelMap model,Long id){
    	model.addAttribute("orders", cartService.getProductsFromSpecificCart(id).get("products"));
    	model.addAttribute("totalPrice", cartService.getProductsFromSpecificCart(id).get("total"));
        return "previous_cart";
    }

    @RequestMapping(value = "/previous_carts", method = RequestMethod.GET)
    public String getPreviousCarts(ModelMap model,Long id){
    	System.out.println(cartService.getProductsFromSpecificCart(id).get("products"));
    	model.addAttribute("orders", cartService.getProductsFromSpecificCart(id).get("products"));
    	model.addAttribute("totalPrice", cartService.getProductsFromSpecificCart(id).get("total"));
        return "previous_carts";
    }

    @PostMapping( path = "update")
    public String updateOrder(ModelMap model, @RequestParam Long id, @RequestParam int quantity,@RequestParam int cart_id){
    	UpdateOrderDTO updateOrderDTO = new UpdateOrderDTO(id,quantity,cart_id);
        cartService.updateOrder( updateOrderDTO );
        model.addAttribute("orders", cartService.getProducts().get("products"));
    	model.addAttribute("totalPrice", cartService.getProducts().get("total"));
        return "cart";
    }

    @PostMapping( path = "delete")
    public String deleteOrder(ModelMap model,@RequestParam Long id,@RequestParam int cart_id){
        cartService.deleteOrder(id,cart_id);
        model.addAttribute("orders", cartService.getProducts().get("products"));
    	model.addAttribute("totalPrice", cartService.getProducts().get("total"));
        return "cart";
    }

	public CartServiceController(CartService cartService,ProductService productService) {
		super();
		this.cartService = cartService;
		this.productService = productService;
	}
}