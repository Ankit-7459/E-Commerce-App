package com.nt.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nt.Model.CategoryInfo;
import com.nt.Model.ProductInfo;
import com.nt.Service.CategoryService;
import com.nt.Service.ProductService;
import com.nt.productdto.Productdto;
@Controller
public class AdminController {
	public static String uploadDir=System.getProperty("user.dir")+ "/src/main/resources/static/productImages";
	@Autowired
	CategoryService Service;
	@Autowired
	ProductService ProductService;
  
	@GetMapping("/admin")
	public String adminHome() {
		
		return "adminHome";
	}
	@GetMapping("/admin/categories")
      public String getCategories(Model model) {
	model.addAttribute("categories",Service.ShowAllCategory());
		return "categories";
	}
	@GetMapping("/admin/categories/add")
	
    public String getAddcategories(Model model) {
		CategoryInfo categorys=new CategoryInfo();
		model.addAttribute("category",categorys);
		return "categoriesAdd";
	}
	@PostMapping("/admin/categories/add")
	
    public String postCatAdd(@ModelAttribute ("category") CategoryInfo category) {
		Service.addCategory(category);
		return "redirect:/admin/categories";
    }
	@GetMapping("/admin/categories/update/{id}")
    public String UpdateCategory(@PathVariable Integer id ,Model model ) {
		Optional<CategoryInfo> category=Service.UpdateCategory(id);
		if(category.isPresent()) {
			model.addAttribute("category",category.get());
		}
		return	"categoriesAdd";
	}
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable Integer id) {
		
		Service.removeCategoryById(id);
		return	"redirect:/admin/categories";
	}
	
	//Admin Product Section
	
	@GetMapping("/admin/products")
    public String Product(Model model) {
	 List<ProductInfo> Product= ProductService.FetchAllproduct();
	 model.addAttribute("products",Product );
		return "products";
	}
   @GetMapping("/admin/products/add")
	
    public String ProductAdd(Model model) {
	   Productdto productdto=new Productdto();
		model.addAttribute("productDTO",productdto);
		model.addAttribute("categories",Service.ShowAllCategory());
		return "productsAdd";
	}
   @PostMapping("/admin/products/add")
	public String ProductAddPost(@ModelAttribute("productDTO") Productdto productDTO,
			                       @RequestParam("productImage")MultipartFile file,
			                       @RequestParam("imgName") String  imageName )throws IOException{
		ProductInfo product=new ProductInfo();
		
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(Service.UpdateCategory(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		String imageuuid;
		if(!file.isEmpty()) {
			imageuuid=file.getOriginalFilename();
			Path filenamePath=Paths.get(uploadDir,imageuuid);
			Files.write(filenamePath, file.getBytes());
		}
		else {
			imageuuid=imageName;
		}
		product.setImageName(imageuuid);
		 ProductService.ProductAdd(product);
		return  "redirect:/admin/products";
   }
   
   @GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		
	   ProductService.removeProductById(id);
		return	"redirect:/admin/products";
	}
   @GetMapping("/admin/product/update/{id}")
   public String UpdateProduct(@PathVariable Long id ,Model model ) {
		ProductInfo product=ProductService.UpdateProductById(id).get();
		Productdto Productdto=new Productdto();
		Productdto.setId(product.getId());
		Productdto.setName(product.getName());
		Productdto.setCategoryId(product.getCategory().getId());
		Productdto.setPrice(product.getPrice());
		Productdto.setWeight(product.getWeight());
		Productdto.setDescription(product.getDescription());
		Productdto.setImageName(product.getImageName());
		model.addAttribute("categories", Service.ShowAllCategory());
		model.addAttribute("productDTO", Productdto);
		
	   return"productsAdd";
   }
}
