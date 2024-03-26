package br.com.cursoudemy.productapi.modules.product.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursoudemy.productapi.modules.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
