package br.com.cursoudemy.productapi.modules.product.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursoudemy.productapi.modules.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByNameIgnoreCaseContaining(String name);

    List<Product> findByCategoryId(UUID id);
    
    List<Product> findBySupplierId(UUID id);

    Boolean existsByCategoryId(UUID id);

    Boolean existsBySupplierId(UUID id);
}
