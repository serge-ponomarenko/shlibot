package ua.cc.spon.shlibot.service;

import ua.cc.spon.shlibot.repository.entity.Product;
import ua.cc.spon.shlibot.repository.entity.UserList;

import java.util.List;
import java.util.Optional;

/**
 * {@link Service} for handling {@link Product} entity.
 */
public interface ProductService {

    /**
     * Save provided {@link Product} entity.
     *
     * @param product provided telegram user.
     */
    void save(Product product);

    List<Product> getAllByListIs(UserList list);

    Optional<Product> findById(int id);

    Product deleteById(int id);
}
