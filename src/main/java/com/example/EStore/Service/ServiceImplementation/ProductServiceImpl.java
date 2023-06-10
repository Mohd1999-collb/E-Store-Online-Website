package com.example.EStore.Service.ServiceImplementation;

import com.example.EStore.Dto.RequestDto.ProductRequestDto;
import com.example.EStore.Dto.ResponseDto.ProductResponseDto;
import com.example.EStore.Enum.Category;
import com.example.EStore.Enum.ProductStatus;
import com.example.EStore.Exception.SellerNotFoundException;
import com.example.EStore.Model.Product;
import com.example.EStore.Model.Seller;
import com.example.EStore.Repository.ProductRepository;
import com.example.EStore.Repository.SellerRepository;
import com.example.EStore.Service.ProductService;
import com.example.EStore.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmailId());

        if(seller == null){
            throw new SellerNotFoundException("EmailId is not registered.");
        }

        /*Dto --> Product(entity)*/
        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);

        /*Update the product list*/
        seller.getProducts().add(product);

        product.setSeller(seller);

        /*Saved into database*/
        Seller savedSeller = sellerRepository.save(seller); /*Saved product and seller both*/

        /*Product(entity) --> Dto*/
        return ProductTransformer.ProductToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getProductByCategoryAndPrice(Category category, Integer price) {
        List<Product> products = productRepository.findByCategoryAndPrice(category, price);

        /*Product(entity) --> Dto*/
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for (Product product: products) {
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));

        }
        return productResponseDtos;
    }

    @Override
    public Set<String> productPriceGreaterThan500() {
        Iterable<Product> productIterable = productRepository.findAll();
        Set<String> productSet = new HashSet<>();

        /*Iterate whole product table*/
        for (Product product: productIterable) {
            if (product.getPrice() > 500){
                productSet.add(product.getName());
            }

        }
        return productSet;
    }

    @Override
    public List<String> top5CostliestProduct() {
        return productRepository.top5CostliestProduct();
    }

    @Override
    public List<String> top5CheapestProduct() {
        return productRepository.top5CheapestProduct();
    }

    @Override
    public Set<String> productBasedOnSellerEmailId(String emailId) {
        Iterable<Product> productIterable = productRepository.findAll();
        Set<String> productSet = new HashSet<>();

        /*Iterate whole product table*/
        for (Product product: productIterable) {
            if (product.getSeller().getEmailId().equals(emailId)){
                productSet.add(product.getName());
            }
        }
        return productSet;
    }

    @Override
    public Set<String> outOfStockProductForAParticularCategory(Category category) {
        Iterable<Product> productIterable = productRepository.findAll();
        Set<String> productSet = new HashSet<>();

        /*Iterate whole product table*/
        for (Product product: productIterable) {
            if (product.getCategory().equals(category)
                && product.getProductStatus().equals(ProductStatus.OUT_OF_STOCK)){
                productSet.add(product.getName());
            }
        }
        return productSet;
    }

    @Override
    public void sendEmailToSellerProductOutOfStock() {
        Iterable<Product> productIterable = productRepository.findAll();
        Set<String> productSet = new HashSet<>();

        /*Iterate whole product table*/
        for (Product product: productIterable) {
            Seller seller = product.getSeller();
            if (product.getProductStatus().equals(ProductStatus.OUT_OF_STOCK)){
                /*Call to the sendMailToSeller method*/
                sendMailToSeller(seller.getName(), seller.getEmailId(), product.getName());
            }
        }
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    private void sendMailToSeller(String sellerName, String emailId, String productName) {
        String text = sellerName + " your product " + productName +
                " is out of stock from E-Store e-commerce website. \n" +
                "Thank you!!!" + "\n" + "no-reply this is automated generated mail.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ecommerce7232@gmail.com");
        message.setTo(emailId);
        message.setSubject("Product Out Of Stock!!!");
        message.setText(text);
        emailSender.send(message);
    }
}
