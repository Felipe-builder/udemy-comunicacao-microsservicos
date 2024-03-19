package br.com.cursoudemy.productapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoudemy.productapi.model.dto.SupplierDTO;
import br.com.cursoudemy.productapi.service.SupplierService;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

  @Autowired
  private SupplierService supplierService;

  @PostMapping
  public ResponseEntity<SupplierDTO> create(@RequestBody SupplierDTO dto) {
    SupplierDTO response = supplierService.create(dto);
    return ResponseEntity.created(null).body(response);
  }

  @GetMapping
  public ResponseEntity<List<SupplierDTO>> findAll() {
    List<SupplierDTO> response = supplierService.findAll();
    return ResponseEntity.ok(response);
  }
}
