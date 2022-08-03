package com.zahra.prodandcat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zahra.prodandcat.models.Category;
import com.zahra.prodandcat.models.Product;
import com.zahra.prodandcat.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	
    public List<Product> allProducts() {
        return productRepository.findAll();
    }
    
    
    public Product createProduct(Product p) {
        return productRepository.save(p);
    }


    public Product findProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            return null;
        }
    }
    
    public List<Product> getTheOtherPro(Category cat) {
    	return productRepository.findByCategoriesNotContains(cat);
    }


	public void addCat(Long productId, Category thisCategory) {
		
	    Product thisProduct = findProduct(productId);
	    
	    thisProduct.getCategories().add(thisCategory);
	    
	    productRepository.save(thisProduct);
		
	}
    
}
