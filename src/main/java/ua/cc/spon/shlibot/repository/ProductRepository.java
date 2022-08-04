package ua.cc.spon.shlibot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.cc.spon.shlibot.repository.entity.Product;

/**
 * {@link Repository} for handling with {@link Product} entity.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
