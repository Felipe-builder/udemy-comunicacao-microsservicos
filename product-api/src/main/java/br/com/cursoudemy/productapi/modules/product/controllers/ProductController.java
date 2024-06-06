package br.com.cursoudemy.productapi.modules.product.controllers;

import static br.com.cursoudemy.productapi.config.RequestUtil.getCurrenRequest;

import java.util.List;
import java.util.logging.Logger;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cursoudemy.productapi.config.handlers.SuccessResponse;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductCheckStockRequest;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductRequest;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductResponse;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductSalesResponse;
import br.com.cursoudemy.productapi.modules.product.rabbitmq.ProductStockListener;
import br.com.cursoudemy.productapi.modules.product.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

  private static final Logger logger = Logger.getLogger(ProductController.class.getName());


  @Autowired
  private ProductService productService;

  @PostMapping()
  public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
    ProductResponse response = productService.create(request);
    return ResponseEntity.created(null).body(response);
  }

  @GetMapping()
  public ResponseEntity<List<ProductResponse>> findAll() {
    List<ProductResponse> response = productService.findAll();
    return ResponseEntity.ok(response);
  }

  @GetMapping("{id}")
  public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
    ProductResponse response = productService.findByIdResponse(id);
    return ResponseEntity.ok(response);
  }

  
  @GetMapping("name/{name}")
  public ResponseEntity<List<ProductResponse>> findByName(@PathVariable String name) {
    List<ProductResponse> response = productService.findByName(name);
    return ResponseEntity.ok(response);
  }

  @GetMapping("category/{categoryId}")
  public ResponseEntity<List<ProductResponse>> findByCategoryId(@PathVariable Long categoryId) {
    List<ProductResponse> response = productService.findByCategoryId(categoryId);
    return ResponseEntity.ok(response);
  }
  
  @GetMapping("supplier/{supplierId}")
  public ResponseEntity<List<ProductResponse>> findBySupplierId(@PathVariable Long supplierId) {
    List<ProductResponse> response = productService.findBySupplierId(supplierId);
    return ResponseEntity.ok(response);
  }

  @PutMapping("{id}")
  public ResponseEntity<ProductResponse> update(@Valid @RequestBody ProductRequest request, @PathVariable Long id) {
    return ResponseEntity.ok(productService.update(request, id));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<SuccessResponse> delete(@PathVariable Long id) {
    return ResponseEntity.ok(productService.delete(id));
  }

  @PostMapping("check-stock")
  public ResponseEntity<SuccessResponse> checkProductStock(@RequestBody ProductCheckStockRequest request) {
    return ResponseEntity.ok(productService.checkProductStock(request));
  }

  @GetMapping("{id}/sales")
  public ResponseEntity<ProductSalesResponse> findProductSales(@PathVariable Long id) {
    ProductSalesResponse response = productService.findProductsSales(id);
    return ResponseEntity.ok(response);
  }
}
