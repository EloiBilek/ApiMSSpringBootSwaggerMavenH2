/**
 * 
 */
package br.com.bb.service;

import java.util.List;

import br.com.bb.entity.Product;

/**
 * @author eloi
 */
public interface IProductService extends IOperations<Product> {

	public List<Product> listByCategory(Long idCategory);

}
