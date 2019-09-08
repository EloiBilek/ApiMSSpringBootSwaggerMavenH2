/**
 * 
 */
package br.com.bb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bb.entity.Category;

/**
 * @author eloi
 */
@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

	@Query(value = "SELECT c1.id, c1.name FROM category c1 "
			+ "JOIN (SELECT c2.*, MAX(LENGTH(LCASE(c2.name)) - LENGTH(REPLACE(LCASE(c2.name), ?1, ''))) AS count "
			+ "FROM category c2 GROUP BY c2.id ORDER BY count DESC LIMIT 1) as c3 "
			+ "on c1.id = c3.id ", nativeQuery = true)
	public Category findByNameCharacter(String nameChar);

}
