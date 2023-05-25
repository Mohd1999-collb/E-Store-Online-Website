package com.example.EStore.Dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemRequestDto {
    int requiredQuantity;

    String customerEmailId;

    int productId;
}
