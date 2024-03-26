package br.com.cursoudemy.productapi.modules.supplier.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.exceptions.NotFoundException;
import br.com.cursoudemy.productapi.modules.supplier.mapper.SupplierMapper;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import br.com.cursoudemy.productapi.modules.supplier.model.dto.SupplierRequest;
import br.com.cursoudemy.productapi.modules.supplier.model.dto.SupplierResponse;
import br.com.cursoudemy.productapi.modules.supplier.repository.SupplierRepository;

@Service
public class SupplierService {

  @Autowired
  private SupplierMapper supplierMapper;

  @Autowired
  private SupplierRepository supplierRepository;

  public SupplierResponse create(SupplierRequest dto) {
    Supplier created = supplierRepository.save(supplierMapper.toEntity(dto));
    return supplierMapper.toDto(created);
  }

  public List<SupplierResponse> findAll() {
    return supplierMapper.toDto(supplierRepository.findAll());
  }

public Supplier findById(UUID supplierId) {
    return supplierRepository
            .findById(supplierId)
            .orElseThrow(() -> new NotFoundException("There's no supplier for the given ID."));

}

}
