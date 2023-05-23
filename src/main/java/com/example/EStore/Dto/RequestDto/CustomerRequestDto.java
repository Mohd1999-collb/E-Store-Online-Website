package com.example.EStore.Dto.RequestDto;

import com.example.EStore.Enum.Gender;
import com.example.EStore.Model.Cart;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerRequestDto {
    String name;

    String mobNo;

    String emailId;

    Gender gender;
}
