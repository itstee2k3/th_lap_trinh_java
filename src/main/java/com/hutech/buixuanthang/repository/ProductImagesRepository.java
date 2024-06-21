package com.hutech.buixuanthang.repository;

import com.hutech.buixuanthang.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductImagesRepository extends JpaRepository<Product, Long> {
}
