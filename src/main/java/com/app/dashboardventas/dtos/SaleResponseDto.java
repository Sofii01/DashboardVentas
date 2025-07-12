package com.app.dashboardventas.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class  SaleResponseDto {
        Long id;
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
        LocalDateTime date;
        List<SaleItemResponseDto> items;

        Double totalAmount;

}
