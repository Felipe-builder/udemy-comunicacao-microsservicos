package br.com.cursoudemy.productapi.modules.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoudemy.productapi.modules.supplier.model.dto.SupplierRequest;
import br.com.cursoudemy.productapi.modules.supplier.model.dto.SupplierResponse;
import br.com.cursoudemy.productapi.modules.supplier.service.SupplierService;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

  @Autowired
  private SupplierService supplierService;

  @PostMapping
  public ResponseEntity<SupplierResponse> create(@RequestBody SupplierRequest dto) {
    SupplierResponse response = supplierService.create(dto);
    return ResponseEntity.created(null).body(response);
  }

  @GetMapping
  public ResponseEntity<List<SupplierResponse>> findAll() {
    List<SupplierResponse> response = supplierService.findAll();
    return ResponseEntity.ok(response);
  }
}
