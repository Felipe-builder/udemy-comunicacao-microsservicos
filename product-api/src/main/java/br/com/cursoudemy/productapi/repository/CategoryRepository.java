package br.com.cursoudemy.productapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursoudemy.productapi.model.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
