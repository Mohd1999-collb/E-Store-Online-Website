package com.example.EStore.Service;

import com.example.EStore.Dto.RequestDto.CustomerRequestDto;
import com.example.EStore.Dto.ResponseDto.CustomerResponseDto;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);
}
