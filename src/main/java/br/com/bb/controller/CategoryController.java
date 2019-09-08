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

import br.com.bb.entity.Category;
import br.com.bb.service.ICategoryService;

/**
 * @author eloi
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@GetMapping("/{id}")
	Category findById(@PathVariable final long id) {
		return categoryService.findById(id).get();
	}

	@GetMapping("/listAll")
	List<Category> findAll() {
		return categoryService.findAll();
	}

	@GetMapping("/findByNameCharacter/{nameChar}")
	Category findByNameCharacter(@PathVariable final String nameChar) {
		return categoryService.findByNameCharacter(nameChar);
	}

	@PostMapping()
	Category create(@RequestBody final Category entity) {
		return categoryService.create(entity);
	}

	@PutMapping()
	Category update(@RequestBody final Category entity) {
		return categoryService.update(entity);
	}

	@DeleteMapping()
	void delete(@RequestBody final Category entity) {
		categoryService.delete(entity);
	}
}
