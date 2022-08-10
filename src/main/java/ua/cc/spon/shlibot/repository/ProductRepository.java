package ua.cc.spon.shlibot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.cc.spon.shlibot.repository.entity.Product;
import ua.cc.spon.shlibot.repository.entity.UserList;

import java.util.List;
import java.util.Optional;

/**
 * {@link Repository} for handling with {@link Product} entity.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> getAllByListIs(UserList list);

    Optional<Product> findById(int id);

    Product deleteById(int id);

}
