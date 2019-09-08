/**
 * 
 */
package br.com.bb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bb.entity.Product;
import br.com.bb.repository.IProductRepository;
import br.com.bb.service.IProductService;

/**
 * @author eloi
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	@Override
	public Optional<Product> findById(long id) {
		return productRepository.findById(id);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product create(Product entity) {
		return productRepository.save(entity);
	}

	@Override
	public Product update(Product entity) {
		return productRepository.saveAndFlush(entity);
	}

	@Override
	public void delete(Product entity) {
		productRepository.delete(entity);
	}

	@Override
	public void deleteById(long entityId) {
		productRepository.deleteById(entityId);
	}

	@Override
	public List<Product> listByCategory(Long idCategory) {
		return productRepository.listByCategory(idCategory);
	}

}
