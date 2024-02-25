package com.nt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nt.Model.ProductInfo;
import com.nt.Service.ProductService;
import com.nt.global.GlobalData;

@Controller
public class CartController {

	@Autowired
	ProductService ProductService;
	
	@GetMapping("/addToCart/{id}")
	public String AddToCart(@PathVariable Long id) {
		GlobalData.cart.add(ProductService.UpdateProductById(id).get());
		return"redirect:/shop";
	}
	@GetMapping("/cart")
	public String Cart(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(ProductInfo::getPrice).sum());
		model.addAttribute("cart",GlobalData.cart);
		return"cart";
	}
	@GetMapping("/cart/removeItems/{index}")
	  public String cartItemsRemovede(@PathVariable Integer index) {
		GlobalData.cart.remove(index);
		return"redirect:/cart";
	}
	@GetMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(ProductInfo::getPrice).sum());
		return"checkout";
	}
	
}
