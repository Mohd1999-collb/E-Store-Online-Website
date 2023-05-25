package com.example.EStore.Service.ServiceImplementation;

import com.example.EStore.Dto.RequestDto.CardRequestDto;
import com.example.EStore.Dto.ResponseDto.CardResponseDto;
import com.example.EStore.Exception.CustomerNotFoundException;
import com.example.EStore.Model.Card;
import com.example.EStore.Model.Customer;
import com.example.EStore.Repository.CustomerRepository;
import com.example.EStore.Service.CardService;
import com.example.EStore.Transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {
        Customer customer = customerRepository.findByEmailId(cardRequestDto.getCustomerEmailId());
        if (customer == null){
            throw new CustomerNotFoundException("Invalid customer emailId.");
        }

        /*Dto --> Card(Entity)*/
        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card); // Update the card list

        /*saved into database*/
        Customer savedCustomer = customerRepository.save(customer);  // Saved both customer and card

        /*Card(Entity) --> Dto*/
        return CardTransformer.CardToCardResponseDto(card);
    }
}
