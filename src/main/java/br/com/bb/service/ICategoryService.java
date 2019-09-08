/**
 * 
 */
package br.com.bb.service;

import br.com.bb.entity.Category;

/**
 * @author eloi
 */
public interface ICategoryService extends IOperations<Category> {

	public Category findByNameCharacter(String nameChar);

}
