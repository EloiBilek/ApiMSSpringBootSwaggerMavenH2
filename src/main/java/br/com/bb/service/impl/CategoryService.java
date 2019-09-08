/**
 * 
 */
package br.com.bb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bb.entity.Category;
import br.com.bb.repository.ICategoryRepository;
import br.com.bb.service.ICategoryService;

/**
 * @author eloi
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;

	@Override
	public Optional<Category> findById(long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category create(Category entity) {
		return categoryRepository.save(entity);
	}

	@Override
	public Category update(Category entity) {
		return categoryRepository.saveAndFlush(entity);
	}

	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);
	}

	@Override
	public void deleteById(long entityId) {
		categoryRepository.deleteById(entityId);
	}

	@Override
	public Category findByNameCharacter(String nameChar) {
		return categoryRepository.findByNameCharacter(nameChar);
	}

	
}
