package br.com.cursoudemy.productapi.modules.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursoudemy.productapi.modules.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameIgnoreCaseContaining(String name);

    List<Product> findByCategoryId(Long id);
    
    List<Product> findBySupplierId(Long id);

    Boolean existsByCategoryId(Long id);

    Boolean existsBySupplierId(Long id);
}
