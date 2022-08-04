package ua.cc.spon.shlibot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.cc.spon.shlibot.repository.ProductRepository;
import ua.cc.spon.shlibot.repository.entity.Product;

/**
 * Implementation of {@link ProductService}.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

}
