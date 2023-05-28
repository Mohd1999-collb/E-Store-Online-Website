package com.example.EStore.Dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderRequestDto {

    String customerEmailId;
//    int id;
    int productId;

    int cvv;

    String cardNo;

    int requiredQuantity;
}
