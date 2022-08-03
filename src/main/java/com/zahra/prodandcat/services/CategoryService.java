package com.zahra.prodandcat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zahra.prodandcat.models.Category;
import com.zahra.prodandcat.models.Product;
import com.zahra.prodandcat.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }
    
    
    public Category createCategory(Category c) {
        return categoryRepository.save(c);
    }


    public Category findCategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            return null;
        }
    }
    
    public void addPro(Long categoryId, Product thisProduct) {
        // retrieve an instance of a category using another method in the service.
        Category thisCategory = findCategory(categoryId);
        
        // add the product to this category's list of products
        thisCategory.getProducts().add(thisProduct);
        
        // Save thisCategory, since you made changes to its product list.
        categoryRepository.save(thisCategory);
    }
    
    public List<Category> getTheOtherCat(Product pro) {
    	return categoryRepository.findByProductsNotContains(pro);
    }
    
}
