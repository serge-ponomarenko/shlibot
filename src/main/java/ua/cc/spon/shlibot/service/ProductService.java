package ua.cc.spon.shlibot.service;

import ua.cc.spon.shlibot.repository.entity.Product;

/**
 * {@link Service} for handling {@link Product} entity.
 */
public interface ProductService {

    /**
     * Save provided {@link Product} entity.
     *
     * @param  product provided telegram user.
     */
    void save(Product product);

}
