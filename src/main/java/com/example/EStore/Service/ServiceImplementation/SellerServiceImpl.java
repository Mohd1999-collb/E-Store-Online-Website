package com.example.EStore.Service.ServiceImplementation;

import com.example.EStore.Dto.RequestDto.SellerRequestDto;
import com.example.EStore.Dto.ResponseDto.SellerResponseDto;
import com.example.EStore.Exception.SellerNotFoundException;
import com.example.EStore.Model.Product;
import com.example.EStore.Model.Seller;
import com.example.EStore.Repository.SellerRepository;
import com.example.EStore.Service.SellerService;
import com.example.EStore.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {
        /*Dto --> Seller(entity)*/
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);

        /*Saved in Database*/

        Seller savedSeller = sellerRepository.save(seller);

        /*Seller(entity) --> Dto*/
        return SellerTransformer.SellerToSellerResponseDto(savedSeller);
    }

    @Override
    public void updateSellerInfo(String emailId, String name, String mobNo) throws SellerNotFoundException {
        Seller seller = sellerRepository.findByEmailId(emailId);

        /*Check seller exist or not*/
        if (seller == null){
            throw new SellerNotFoundException("Seller doesn't exist.");
        }
        seller.setName(name);
        seller.setMobNo(mobNo);
        sellerRepository.save(seller); // Saved into database
    }

    @Override
    public List<String> sellersSellProductsOfAParticularCategory(String category) {
        Iterable<Seller> sellerIterable = sellerRepository.findAll();
        List<String> list = new ArrayList<>();

        /*Iterate whole seller table*/
        for (Seller seller: sellerIterable) {
            List<Product> productList = seller.getProducts();
            /*Iterate whole seller table*/
            for (Product product: productList) {
                if (product.getCategory().toString().equals(category)){
                    list.add(seller.getName());
                }
            }
        }
        return list;
    }

    @Override
    public List<String> productSoldBySellerInAParticularCategory(String category) {
        Iterable<Seller> sellerIterable = sellerRepository.findAll();
        List<String> list = new ArrayList<>();

        /*Iterate whole seller table*/
        for (Seller seller: sellerIterable) {
            List<Product> productList = seller.getProducts();
            /*Iterate whole seller table*/
            for (Product product: productList) {
                if (product.getCategory().toString().equals(category)){
                    list.add(product.getName());
                }
            }
        }
        return list;
    }

    @Override
    public List<String> sellerMaxProduct() {
        Iterable<Seller> sellerIterable = sellerRepository.findAll();

        /*Call iterate seller table method*/
        HashMap<String, Integer> hm = iterateSellerTable(sellerIterable);

        /* Get the maximum value*/
        int max = Integer.MIN_VALUE;
        for (String key : hm.keySet()){
            max = Math.max(max, hm.get(key));
        }

        /* Now get the all Seller with maximum number of products*/
        List<String> sellerName = new ArrayList<>();
        for (String key : hm.keySet()){
            if (hm.get(key) == max){
                sellerName.add(key);
            }
        }
        return sellerName;
    }

    @Override
    public List<String> sellerMinProduct() {
        Iterable<Seller> sellerIterable = sellerRepository.findAll();

        /*Call iterate seller table method*/
        HashMap<String, Integer> hm = iterateSellerTable(sellerIterable);

        /* Get the minimum value*/
        int min = Integer.MAX_VALUE;
        for (String key : hm.keySet()){
            min = Math.min(min, hm.get(key));
        }

        /* Now get the all Seller with maximum number of products*/
        List<String> sellerName = new ArrayList<>();
        for (String key : hm.keySet()){
            if (hm.get(key) == min){
                sellerName.add(key);
            }
        }
        return sellerName;
    }

    @Override
    public Set<String> sellerCostliestProduct() {
        Iterable<Seller> sellerIterable = sellerRepository.findAll();
        Set<String> sellerName = new HashSet<>();

        /*Iterate whole seller table*/
        for (Seller seller: sellerIterable) {
            List<Product> productList = seller.getProducts();
            /*Iterate whole seller table*/
            for (Product product: productList) {
                /* If product price is greater than 500 then that product is costliest*/
                if (product.getPrice() >= 500){
                    sellerName.add(product.getSeller().getName());
                }
            }
        }
        return sellerName;
    }

    @Override
    public Set<String> sellerCheapestProduct() {
        Iterable<Seller> sellerIterable = sellerRepository.findAll();
        Set<String> sellerName = new HashSet<>();

        /*Iterate whole seller table*/
        for (Seller seller: sellerIterable) {
            List<Product> productList = seller.getProducts();
            /*Iterate whole seller table*/
            for (Product product: productList) {
                /* If product price is less than 500 then that product is cheapest*/
                if (product.getPrice() < 500){
                    sellerName.add(product.getSeller().getName());
                }
            }
        }
        return sellerName;
    }

    public HashMap<String, Integer> iterateSellerTable(Iterable<Seller> sellerIterable){
        HashMap<String, Integer> hm = new HashMap<>();
        /*Iterate whole seller table*/
        for (Seller seller: sellerIterable) {
            List<Product> productList = seller.getProducts();
            /*Iterate whole seller table*/
            for (Product product: productList) {
                String name = product.getSeller().getName();
                hm.put(name, hm.getOrDefault(name, 0) + 1);
            }
        }
        return hm;
    }
}
