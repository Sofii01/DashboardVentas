package com.app.dashboardventas.mappers;


import com.app.dashboardventas.dtos.SaleItemRequestDto;
import com.app.dashboardventas.dtos.SaleItemResponseDto;
import com.app.dashboardventas.models.entities.SaleItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISaleItemMapper {
    SaleItemResponseDto toDto(SaleItem saleItem);
    SaleItem toEntity(SaleItemRequestDto saleItemRequestDto);
}
