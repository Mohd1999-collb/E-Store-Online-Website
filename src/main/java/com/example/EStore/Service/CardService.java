package com.example.EStore.Service;

import com.example.EStore.Dto.RequestDto.CardRequestDto;
import com.example.EStore.Dto.ResponseDto.CardResponseDto;
import com.example.EStore.Enum.CardType;
import com.example.EStore.Exception.CustomerNotFoundException;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException;

    String cardTypeMaxNumberOfTimes();

    String cardTypeMinNumberOfTimes();
}
