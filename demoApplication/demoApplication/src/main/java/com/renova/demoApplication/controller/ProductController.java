package com.renova.demoApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renova.demoApplication.service.ProductService;

@Controller
@RequestMapping("/e-commerce")
public class ProductController {

    private final ProductService productService;
    
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String viewProducts(ModelMap model) {
        model.addAttribute("products", productService.getProducts());
        return "view-products";
    }

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
}