package br.com.cursoudemy.productapi.modules.product.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.cursoudemy.productapi.modules.category.mapper.CategoryMapper;
import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.product.model.Product;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductRequest;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductResponse;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductSalesResponse;
import br.com.cursoudemy.productapi.modules.supplier.mapper.SupplierMapper;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;

@Component
public class ProductMapper {

  private final CategoryMapper categoryMapper;

  private final SupplierMapper supplierMapper;

  public ProductMapper(CategoryMapper categoryMapper, SupplierMapper supplierMapper) {
    this.categoryMapper = categoryMapper;
    this.supplierMapper = supplierMapper;
  }

  public ProductResponse toResponse(Product entity) {
    return new ProductResponse(
        entity.getId(),
        entity.getName(),
        entity.getQuantityAvailable(),
        categoryMapper.toDto(entity.getCategory()),
        supplierMapper.toDto(entity.getSupplier()),
        entity.getCreatedAt(),
        entity.getUpdatedAt());
  }

  public List<ProductResponse> toResponse(List<Product> entites) {
    return entites.stream().map(entity -> this.toResponse(entity)).collect(Collectors.toList());
  }

  public Product toEntity(ProductRequest dto, Category category, Supplier supplier) {
    return new Product(
        dto.getName(),
        dto.getQuantityAvailable(),
        category,
        supplier);
  }

  public ProductSalesResponse toProductSalesResponse(
    Product entity,
    List<String> sales
  ) {
    return new ProductSalesResponse(
        entity.getId(),
        entity.getName(),
        entity.getQuantityAvailable(),
        categoryMapper.toDto(entity.getCategory()),
        supplierMapper.toDto(entity.getSupplier()),
        sales,
        entity.getCreatedAt(),
        entity.getUpdatedAt());
  }

}
