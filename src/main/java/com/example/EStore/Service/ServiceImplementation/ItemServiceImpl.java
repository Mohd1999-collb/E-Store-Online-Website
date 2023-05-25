package com.example.EStore.Service.ServiceImplementation;

import com.example.EStore.Dto.RequestDto.ItemRequestDto;
import com.example.EStore.Exception.CustomerNotFoundException;
import com.example.EStore.Exception.InsufficientQuantityException;
import com.example.EStore.Exception.OutOfStockException;
import com.example.EStore.Exception.ProductNotFoundException;
import com.example.EStore.Model.Customer;
import com.example.EStore.Model.Item;
import com.example.EStore.Model.Product;
import com.example.EStore.Repository.CustomerRepository;
import com.example.EStore.Repository.ProductRepository;
import com.example.EStore.Service.ItemService;
import com.example.EStore.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, InsufficientQuantityException, OutOfStockException {
        Optional<Product> productOpt = productRepository.findById(itemRequestDto.getProductId());

        /*Check product exist or not.*/
        if (productOpt.isEmpty()){
            throw  new ProductNotFoundException("Product does not exist.");
        }

        Product product = productOpt.get();

        /*Check customer exist or not.*/
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());

        if (customer == null){
            throw new CustomerNotFoundException("Customer does not exist.");
        }

        /*Check product quantity exist or not.*/
        if (product.getQuantity() == 0) {
            throw new OutOfStockException("Product is out of stock.");
        }

        if (product.getQuantity() < itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! The required quantity is not available.");
        }

        /*Dto --> Item(entity)*/
        return ItemTransformer.ItemRequestDtoToItem(itemRequestDto);
    }
}
