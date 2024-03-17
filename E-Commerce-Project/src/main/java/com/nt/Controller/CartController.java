package com.nt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nt.Model.Payment;
import com.nt.Model.ProductInfo;
import com.nt.Repository.PaymentRepo;
import com.nt.Service.ProductService;
import com.nt.global.GlobalData;

@Controller
public class CartController {
	 @Autowired
	    private PaymentRepo Payment_Repo;


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
	@GetMapping("/cart/removeItem/{index}")
	  public String cartItemsRemovede(@PathVariable int index) {
		GlobalData.cart.remove(index);
		return"redirect:/cart";
	}
	@GetMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(ProductInfo::getPrice).sum());
		return"checkout";
	}
	

	
	@GetMapping("/payment")
	    
	    public String payment(){
	        return "payNow";
	    }
	    @PostMapping("/payment")
	    public String saveUser(Payment newPayment) {
	        System.out.println("*********");
	        System.out.println("Data Inserted");
	        System.out.println("**********");
	        Payment_Repo.save(newPayment);
	        System.out.println("***********************************************************************************************************************************************************");
	        System.out.println("User product data......"+newPayment.toString());
	        System.out.println("***********************************************************************************************************************************************************");
	        System.out.println("***********************************************************************************************************************************************************");
	        System.out.println("***********************************************************************************************************************************************************");
	        
	        return "OrderSuccessful";
	    }       
}
