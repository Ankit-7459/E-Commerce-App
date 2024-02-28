package com.nt.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nt.Model.Role;
import com.nt.Model.User;
import com.nt.Repository.RoleRepository;
import com.nt.Repository.UserRepository;
import com.nt.Service.CategoryService;
import com.nt.Service.ProductService;
import com.nt.global.GlobalData;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeControler {
	@Autowired
	CategoryService CategoryService;
	@Autowired
	ProductService ProductService;
	
	@GetMapping({"/", "/index",})
	public String home(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "index";
	}
	@GetMapping("/Helper")
	public String Info(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		return"Info";
	}
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", CategoryService.ShowAllCategory());
		model.addAttribute("products",ProductService.FetchAllproduct());
		model.addAttribute("cartCount",GlobalData.cart.size());
		return"shop";
	}
	@GetMapping("/shop/category/{id}")
	public String shopByCategeries(Model model ,@PathVariable Integer id) {
		model.addAttribute("categories", CategoryService.ShowAllCategory());
		model.addAttribute("products",ProductService.getAllProductByCategoryId(id));
		model.addAttribute("cartCount",GlobalData.cart.size());
		return"shop";
	}
	@GetMapping("/shop/viewproduct/{id}")
	public String ViewProduct(Model model ,@PathVariable Long id) {
		model.addAttribute("product",ProductService.UpdateProductById(id).get());
		model.addAttribute("cartCount",GlobalData.cart.size());
		return"viewProduct";
	}
	

}
