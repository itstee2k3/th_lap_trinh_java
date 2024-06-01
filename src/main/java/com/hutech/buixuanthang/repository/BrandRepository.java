package com.hutech.buixuanthang.repository;


import com.hutech.buixuanthang.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}

