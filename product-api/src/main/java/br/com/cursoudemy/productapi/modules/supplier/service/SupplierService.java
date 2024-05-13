package br.com.cursoudemy.productapi.modules.supplier.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.config.handlers.SuccessResponse;
import br.com.cursoudemy.productapi.exceptions.NotFoundException;
import br.com.cursoudemy.productapi.exceptions.ValidationException;
import br.com.cursoudemy.productapi.modules.product.service.ProductService;
import br.com.cursoudemy.productapi.modules.supplier.mapper.SupplierMapper;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import br.com.cursoudemy.productapi.modules.supplier.model.dto.SupplierRequest;
import br.com.cursoudemy.productapi.modules.supplier.model.dto.SupplierResponse;
import br.com.cursoudemy.productapi.modules.supplier.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class SupplierService {

  @Autowired
  private SupplierMapper supplierMapper;

  @Autowired
  private SupplierRepository supplierRepository;

  @Autowired
  private ProductService productService;

  @Cacheable(value = "suppliers", key = "#id")
  public boolean isSupplierExists(Long id) {
    return supplierRepository.existsById(id);
  }

  public SupplierResponse create(SupplierRequest request) {
    Supplier created = supplierRepository.save(supplierMapper.toEntity(request));
    return supplierMapper.toDto(created);
  }

  @Transactional
  public SupplierResponse update(SupplierRequest request, Long id) {
    if (!isSupplierExists(id)) {
      throw new EntityNotFoundException("Fornecedor não encontrado");
    }

    Supplier toUpdate = supplierMapper.toEntity(request);
    toUpdate.setId(id);
    Supplier updatedSupplier = supplierRepository.save(toUpdate);

    return supplierMapper.toDto(updatedSupplier);
  }

  public List<SupplierResponse> findAll() {
    return supplierMapper.toDto(supplierRepository.findAll());
  }

  public Supplier findById(Long supplierId) {
    return supplierRepository
        .findById(supplierId)
        .orElseThrow(() -> new NotFoundException("There's no supplier for the given ID."));

  }

  public SupplierResponse findByIdResponse(Long supplierId) {
    Supplier entity = supplierRepository
        .findById(supplierId)
        .orElseThrow(() -> new NotFoundException("There's no supplier for the given ID."));
    return supplierMapper.toDto(entity);
  }

  public List<SupplierResponse> findByName(String name) {
    List<Supplier> listEntites = supplierRepository
        .findByNameIgnoreCaseContaining(name);
    return supplierMapper.toDto(listEntites);
  }

  public SuccessResponse delete(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("ID is required");
    }
    if (productService.existsBySupplierId(id)) {
      throw new ValidationException("You cannot delete this supplier because it's already defined byh a product");
    }

    supplierRepository.deleteById(id);
    return SuccessResponse.create("The supplier was deleted.");
  }

}
