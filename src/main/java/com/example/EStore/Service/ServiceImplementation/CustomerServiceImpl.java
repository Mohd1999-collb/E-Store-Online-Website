package com.example.EStore.Service.ServiceImplementation;

import com.example.EStore.Dto.RequestDto.CustomerRequestDto;
import com.example.EStore.Dto.ResponseDto.CustomerResponseDto;
import com.example.EStore.Model.Cart;
import com.example.EStore.Model.Customer;
import com.example.EStore.Repository.CustomerRepository;
import com.example.EStore.Service.CustomerService;
import com.example.EStore.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        /*Dto --> Customer(entity)*/
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart =  Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        /*Allocate to cart each customer, when add the customer*/
        customer.setCart(cart);

        /*Saved in database*/
        Customer savedCustomer = customerRepository.save(customer); /*Saved both customer and cart*/

        /*Customer(entity* --> Dto*/
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }
}
