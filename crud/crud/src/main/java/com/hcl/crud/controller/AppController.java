package com.hcl.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.crud.model.Product;
import com.hcl.crud.service.ProductService;


@Controller
public class AppController {
	@Autowired
    private ProductService service;
	@RequestMapping("/")
	public String viewHomePage(@ModelAttribute Product product, Model model) {
		
	    List<Product> products = service.listAll();
	  
	    model.addAttribute("listProducts",products);
	     
	    return "index";
	}
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
	    Product product = new Product();
	    model.addAttribute("product", product);
	     
	    return "new-product";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
	    service.save(product);
	     
	    return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_product");
	    Product product = service.get(id);
	    mav.addObject("product", product);
	     
	    return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
	    service.delete(id);
	    return "redirect:/";       
	}
	
	@RequestMapping("403")
	public String error403() {
		return "403";
	}
	
	 @GetMapping("/login")
	    public String viewLoginPage() {
	        return "login";
	    }
	}



