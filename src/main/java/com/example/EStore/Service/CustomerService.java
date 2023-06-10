package com.example.EStore.Service;

import com.example.EStore.Dto.RequestDto.CustomerRequestDto;
import com.example.EStore.Dto.ResponseDto.CustomerResponseDto;
import com.example.EStore.Model.Customer;

import java.util.List;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);

    List<String> allFemaleCustomer();

    List<String> allMaleCustomer();

    List<String> customerAtLeastKOrders(Integer k);
}
