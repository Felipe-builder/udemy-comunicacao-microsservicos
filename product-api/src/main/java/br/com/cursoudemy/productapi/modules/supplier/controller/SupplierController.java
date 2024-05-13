package br.com.cursoudemy.productapi.modules.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoudemy.productapi.config.handlers.SuccessResponse;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductRequest;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductResponse;
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
  public ResponseEntity<SupplierResponse> create(@Valid @RequestBody SupplierRequest request) {
    SupplierResponse response = supplierService.create(request);
    return ResponseEntity.created(null).body(response);
  }

  @GetMapping
  public ResponseEntity<List<SupplierResponse>> findAll() {
    List<SupplierResponse> response = supplierService.findAll();
    return ResponseEntity.ok(response);
  }

  @GetMapping("{id}")
  public ResponseEntity<SupplierResponse> findById(@PathVariable Long id) {
    SupplierResponse response = supplierService.findByIdResponse(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping("name/{name}")
  public ResponseEntity<List<SupplierResponse>> findByDescription(@PathVariable String name) {
    List<SupplierResponse> response = supplierService.findByName(name);
    return ResponseEntity.ok(response);
  }

  @PutMapping("{id}")
  public ResponseEntity<SupplierResponse> update(@Valid @RequestBody SupplierRequest request, @PathVariable Long id) {
    return ResponseEntity.ok(supplierService.update(request, id));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<SuccessResponse> delete(@PathVariable Long id) {
    return ResponseEntity.ok(supplierService.delete(id));
  }
}
