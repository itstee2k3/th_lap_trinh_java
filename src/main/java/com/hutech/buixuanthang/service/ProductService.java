package com.hutech.buixuanthang.service;

import com.hutech.buixuanthang.model.Product;
import com.hutech.buixuanthang.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productReporsitory;

    public List<Product> getList() {
        return productReporsitory.findAll();
    }

    public Product GetProduct(Long id) {
        return productReporsitory.getReferenceById(id);
    }

    public void DeleteProduct(Long id) {
        productReporsitory.deleteById(id);
    }

    public void EditOrAddProduct(Product product) {
        productReporsitory.save(product);
    }
}
