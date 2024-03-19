package br.com.cursoudemy.productapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.mapper.SupplierMapper;
import br.com.cursoudemy.productapi.model.Supplier;
import br.com.cursoudemy.productapi.model.dto.SupplierDTO;
import br.com.cursoudemy.productapi.repository.SupplierRepository;

@Service
public class SupplierService {

  @Autowired
  private SupplierMapper supplierMapper;

  @Autowired
  private SupplierRepository supplierRepository;

  public SupplierDTO create(SupplierDTO dto) {
    Supplier created = supplierRepository.save(supplierMapper.toEntity(dto));
    return supplierMapper.toDto(created);
  }

  public List<SupplierDTO> findAll() {
    return supplierMapper.toDto(supplierRepository.findAll());
  }

}
