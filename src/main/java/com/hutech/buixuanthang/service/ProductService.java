package com.hutech.buixuanthang.service;

import com.hutech.buixuanthang.model.Product;
import com.hutech.buixuanthang.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> getList() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.getReferenceById(id);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.getReferenceById(id);
        if (product != null) {
            deleteProductImage(product.getMainImage());
            productRepository.deleteById(id);
        }
    }

    public void editOrAddProduct(Product product, MultipartFile imageProduct) {
        if (imageProduct != null && !imageProduct.isEmpty()) {
            String oldImage = product.getMainImage();
            String newFileName = saveImage(imageProduct);
            product.setMainImage(newFileName);
            if (oldImage != null && !oldImage.isEmpty()) {
                deleteProductImage(oldImage);
            }
        }
        productRepository.save(product);
    }

    public void editProductKeepOldImage(Product product) {
        Product existingProduct = productRepository.findById(product.getId()).orElseThrow(() -> new RuntimeException("Product not found"));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setManufacturer(product.getManufacturer());
        existingProduct.setBrand(product.getBrand());
        // Không thay đổi mainImage
        productRepository.save(existingProduct);
    }

    private String saveImage(MultipartFile imageProduct) {
        try {
            Path dirImages = Paths.get("static/imgproduct");
            if (!Files.exists(dirImages)) {
                Files.createDirectories(dirImages);
            }
            String newFileName = UUID.randomUUID() + "_" + imageProduct.getOriginalFilename();
            Path pathFileUpload = dirImages.resolve(newFileName);
            Files.copy(imageProduct.getInputStream(), pathFileUpload, StandardCopyOption.REPLACE_EXISTING);
            return newFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }
    }

    private void deleteProductImage(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            File imageFile = Paths.get("static/imgproduct", imagePath).toFile();
            if (imageFile.exists()) {
                boolean deleted = imageFile.delete();
                if (!deleted) {
                    throw new RuntimeException("Failed to delete product image");
                }
            }
        }
    }
}
