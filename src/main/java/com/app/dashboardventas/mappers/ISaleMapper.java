package com.app.dashboardventas.mappers;

import com.app.dashboardventas.dtos.SaleRequestDto;
import com.app.dashboardventas.dtos.SaleResponseDto;
import com.app.dashboardventas.models.entities.Sale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISaleMapper {
    SaleResponseDto toDto(Sale sale);
    Sale toEntity(SaleRequestDto saleRequestDto);


}
