package com.zahra.prodandcat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zahra.prodandcat.models.Category;
import com.zahra.prodandcat.models.Product;
import com.zahra.prodandcat.services.CategoryService;
import com.zahra.prodandcat.services.ProductService;

@Controller
public class MainController {
	@Autowired 
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	
	// create category
	@RequestMapping("/categories/new")
	public String newCate(@ModelAttribute("cat") Category cat) {
		return "newC.jsp";
	}
	
	@RequestMapping(value="/categories/new", method=RequestMethod.POST)
	public String createC(@Valid @ModelAttribute("cat") Category cat, BindingResult result) {
		if (result.hasErrors()) {
			return"newC.jsp";
		} else {
			categoryService.createCategory(cat);
			Long id = cat.getId();
			return "redirect:/categories/"+id;
		}
	}
	
	// create product
	@RequestMapping("/products/new")
	public String newProd(@ModelAttribute("pro") Product pro) {
		return "newP.jsp";
	}
	
	@RequestMapping(value="/products/new", method=RequestMethod.POST)
	public String createP(@Valid @ModelAttribute("pro") Product pro, BindingResult result) {
		if (result.hasErrors()) {
			return"newP.jsp";
		} else {
			productService.createProduct(pro);
			Long id = pro.getId();
			return "redirect:/products/"+id;
		}
	}
	
	// show category page
	@RequestMapping("/categories/{id}")
	public String showC(@PathVariable("id") Long id, Model model) {
		Category cat = categoryService.findCategory(id);
		List<Product> pros = cat.getProducts();
		List<Product> allPros = productService.getTheOtherPro(cat);
		model.addAttribute("cat", cat);
		model.addAttribute("pros", pros);
		model.addAttribute("allPros", allPros);
		return "CPage.jsp";
	}
	
	//add product to category
	@RequestMapping(value="/add/pro/{catid}", method=RequestMethod.PUT)
	public String addProToCat(@Valid @ModelAttribute("cat") Category cat,
			@PathVariable("catid") Long id, @RequestParam("products")Product pro,
			BindingResult result) {
		categoryService.addPro(id, pro);
		return "redirect:/categories/"+id;
	}
	
	
	// show product page
	@RequestMapping("/products/{id}")
	public String showP(@PathVariable("id") Long id, Model model) {
		Product pro = productService.findProduct(id);
		List<Category> cats = pro.getCategories();
		List<Category> allcats = categoryService.getTheOtherCat(pro);
		model.addAttribute("cats",cats);
		model.addAttribute("pro", pro);
		model.addAttribute("allcats", allcats);
		return "PPage.jsp";
	}
	
//	add category to product
	@RequestMapping(value="/add/cat/{proid}", method=RequestMethod.PUT)
	public String addCatToPro(@Valid @ModelAttribute("pro") Product pro,
			@PathVariable("proid") Long id, @RequestParam("categories")Category cat,
			BindingResult result) {
		productService.addCat(id, cat);
		return "redirect:/products/"+id;
	}
}
