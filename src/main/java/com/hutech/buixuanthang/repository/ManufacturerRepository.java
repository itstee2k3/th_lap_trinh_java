package com.hutech.buixuanthang.repository;

import com.hutech.buixuanthang.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long>{
}
