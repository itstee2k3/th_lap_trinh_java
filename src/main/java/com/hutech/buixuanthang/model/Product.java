package com.hutech.buixuanthang.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;


@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    private String description;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String mainImage;

    @OneToMany(mappedBy = "product")
    private Set<ProductImages> additionalImages;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}