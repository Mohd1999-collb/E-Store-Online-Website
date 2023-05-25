package com.example.EStore.Service.ServiceImplementation;

import com.example.EStore.Dto.RequestDto.ItemRequestDto;
import com.example.EStore.Dto.ResponseDto.CartResponseDto;
import com.example.EStore.Model.Cart;
import com.example.EStore.Model.Customer;
import com.example.EStore.Model.Item;
import com.example.EStore.Model.Product;
import com.example.EStore.Repository.CartRepository;
import com.example.EStore.Repository.CustomerRepository;
import com.example.EStore.Repository.ProductRepository;
import com.example.EStore.Service.CartService;
import com.example.EStore.Transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();

        /*Set the total price of cart*/
        cart.setCartTotal(cart.getCartTotal() + item.getRequiredQuantity()*product.getPrice());
        cart.getItems().add(item); // Update the item list
        item.setCart(cart);
        item.setProduct(product);

        /*Saved in database*/
        Cart savedCart  = cartRepository.save(cart);
        Item savedItem = cart.getItems().get(cart.getItems().size() - 1);
        product.getItems().add(savedItem); // Update the item list

        /*SavedCart(Entity) --> DTO*/
        return CartTransformer.CartToCartResponseDto(savedCart);
    }
}
