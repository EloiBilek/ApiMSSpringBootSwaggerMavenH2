package br.com.bb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bb.entity.Product;
import br.com.bb.service.IProductService;

/**
 * @author eloi
 */
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;

	@GetMapping("/{id}")
	Product findById(@PathVariable final long id) {
		return productService.findById(id).get();
	}

	@GetMapping("/listAll")
	List<Product> findAll() {
		return productService.findAll();
	}

	@GetMapping("/listByCategory/{id}")
	List<Product> findByCategory(@PathVariable final long id) {
		return productService.listByCategory(id);
	}

	@PostMapping()
	Product create(@RequestBody final Product entity) {
		return productService.create(entity);
	}

	@PutMapping()
	Product update(@RequestBody final Product entity) {
		return productService.update(entity);
	}

	@DeleteMapping()
	void delete(@RequestBody final Product entity) {
		productService.delete(entity);
	}

}
