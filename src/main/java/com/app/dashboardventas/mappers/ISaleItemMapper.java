package com.app.dashboardventas.mappers;


import com.app.dashboardventas.dtos.SaleItemRequestDto;
import com.app.dashboardventas.dtos.SaleItemResponseDto;
import com.app.dashboardventas.models.entities.SaleItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ISaleItemMapper {
    @Mapping(source = "product.id", target = "product_id")
    @Mapping(source = "product.name", target = "product_name")
    SaleItemResponseDto toDto(SaleItem saleItem);
    SaleItem toEntity(SaleItemRequestDto saleItemRequestDto);
}
