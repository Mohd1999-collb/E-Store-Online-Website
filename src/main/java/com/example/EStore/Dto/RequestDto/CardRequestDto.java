package com.example.EStore.Dto.RequestDto;

import com.example.EStore.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {

    String customerEmailId;

    String cardNo;

    CardType cardType;

    int cvv;

    Date validTill;
}
