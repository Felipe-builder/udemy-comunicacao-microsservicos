package br.com.cursoudemy.productapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursoudemy.productapi.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

}
