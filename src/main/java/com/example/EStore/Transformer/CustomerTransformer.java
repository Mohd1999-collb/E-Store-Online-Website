package com.example.EStore.Transformer;

import com.example.EStore.Dto.RequestDto.CustomerRequestDto;
import com.example.EStore.Dto.ResponseDto.CustomerResponseDto;
import com.example.EStore.Model.Customer;

public class CustomerTransformer {
    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .mobNo(customerRequestDto.getMobNo())
                .emailId(customerRequestDto.getEmailId())
                .gender(customerRequestDto.getGender())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .mobNo(customer.getMobNo())
                .emailId(customer.getEmailId())
                .build();
    }
}
