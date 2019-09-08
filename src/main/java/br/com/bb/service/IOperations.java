/**
 * 
 */
package br.com.bb.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author eloi
 */
public interface IOperations<T extends Serializable> {

	// read
	Optional<T> findById(final long id);

	List<T> findAll();

	// write
	T create(final T entity);

	T update(final T entity);

	// delete
	void delete(final T entity);

	void deleteById(final long entityId);

}
