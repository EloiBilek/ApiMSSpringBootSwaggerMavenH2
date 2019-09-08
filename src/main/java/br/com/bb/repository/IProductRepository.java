/**
 * 
 */
package br.com.bb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.bb.entity.Product;

/**
 * @author eloi
 */
@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p WHERE p.category.id = :idCategory")
	public List<Product> listByCategory(@Param("idCategory") Long idCategory);

}
