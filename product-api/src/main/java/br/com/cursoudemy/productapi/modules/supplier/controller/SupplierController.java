package br.com.cursoudemy.productapi.modules.supplier.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoudemy.productapi.modules.supplier.model.dto.SupplierRequest;
import br.com.cursoudemy.productapi.modules.supplier.model.dto.SupplierResponse;
import br.com.cursoudemy.productapi.modules.supplier.service.SupplierService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

  @Autowired
  private SupplierService supplierService;

  @PostMapping
  public ResponseEntity<SupplierResponse> create(@Valid @RequestBody SupplierRequest dto) {
    SupplierResponse response = supplierService.create(dto);
    return ResponseEntity.created(null).body(response);
  }

  @GetMapping
  public ResponseEntity<List<SupplierResponse>> findAll() {
    List<SupplierResponse> response = supplierService.findAll();
    return ResponseEntity.ok(response);
  }

  
  @GetMapping("{id}")
  public ResponseEntity<SupplierResponse> findById(@PathVariable UUID id) {
    SupplierResponse response = supplierService.findByIdResponse(id);
    return ResponseEntity.ok(response);
  }

  
  @GetMapping("name/{name}")
  public ResponseEntity<List<SupplierResponse>> findByDescription(@PathVariable String name) {
    List<SupplierResponse> response = supplierService.findByName(name);
    return ResponseEntity.ok(response);
  }
}
