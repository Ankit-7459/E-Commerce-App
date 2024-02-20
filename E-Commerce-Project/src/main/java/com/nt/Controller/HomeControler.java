package com.nt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nt.Service.CategoryService;
import com.nt.Service.ProductService;

@Controller
public class HomeControler {
	@Autowired
	CategoryService CategoryService;
	@Autowired
	ProductService ProductService;
	
	@GetMapping({"/", "/home"})
	public String home(Model model) {
		return"index";
	}
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", CategoryService.ShowAllCategory());
		model.addAttribute("products",ProductService.FetchAllproduct());
		return"shop";
	}
	@GetMapping("/shop/category/{id}")
	public String shopByCategeries(Model model ,@PathVariable Integer id) {
		model.addAttribute("categories", CategoryService.ShowAllCategory());
		model.addAttribute("products",ProductService.getAllProductByCategoryId(id));
		return"shop";
	}
	@GetMapping("/shop/viewproduct/{id}")
	public String ViewProduct(Model model ,@PathVariable Long id) {
		model.addAttribute("product",ProductService.UpdateProductById(id).get());
		return"viewProduct";
	}
}
