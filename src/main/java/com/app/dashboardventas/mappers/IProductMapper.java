package com.app.dashboardventas.mappers;

import com.app.dashboardventas.dtos.ProductRequestDto;
import com.app.dashboardventas.dtos.ProductResponseDto;
import com.app.dashboardventas.models.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    ProductResponseDto productToProductResponseDto(Product product);
    Product productRequestDtoToProduct(ProductRequestDto  productRequestDto);

}
