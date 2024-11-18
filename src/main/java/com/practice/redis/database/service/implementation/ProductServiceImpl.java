package com.practice.redis.database.service.implementation;

import com.practice.redis.common.exception.ApplicationException;
import com.practice.redis.database.domain.entity.Company;
import com.practice.redis.database.domain.entity.Product;
import com.practice.redis.database.domain.request.ProductRequest;
import com.practice.redis.database.domain.response.CompanyResponse;
import com.practice.redis.database.domain.response.MessageResponse;
import com.practice.redis.database.domain.response.ProductResponse;
import com.practice.redis.database.repository.CompanyRepository;
import com.practice.redis.database.repository.ProductRepository;
import com.practice.redis.database.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductResponse saveProduct(ProductRequest productRequest) {
        Optional<Product> optionalProduct = productRepository.findByName(productRequest.getName());
        if(optionalProduct.isPresent()) {
            throw new ApplicationException("Product already saved", HttpStatus.ALREADY_REPORTED);
        }

        Optional<Company> optionalCompany = companyRepository.findById(productRequest.getCompanyId());
        if(optionalCompany.isEmpty()) {
            throw new ApplicationException("Company not found", HttpStatus.NOT_FOUND);
        }

        Product product = modelMapper.map(productRequest, Product.class);
        product = productRepository.save(product);

        return convertProductToProductResponse(product);
    }

    @Override
    public ProductResponse editProduct(ProductRequest productRequest) {
        Optional<Product> optionalProduct = productRepository.findById(productRequest.getId());
        if(optionalProduct.isEmpty()) {
            throw new ApplicationException("Product not found.", HttpStatus.NOT_FOUND);
        }

        Optional<Company> optionalCompany = companyRepository.findById(productRequest.getCompanyId());
        if(optionalCompany.isEmpty()) {
            throw new ApplicationException("Company not found", HttpStatus.NOT_FOUND);
        }

        Product product = modelMapper.map(productRequest, Product.class);
        product = productRepository.save(product);

        return convertProductToProductResponse(product);
    }

    private ProductResponse convertProductToProductResponse(Product product) {
        ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
        productResponse.setCompany(modelMapper.map(companyRepository.findById(product.getCompanyId()).get(), CompanyResponse.class));

        return productResponse;
    }

    @Override
    public List<ProductResponse> getProducts() {
        Iterable<Product> products = productRepository.findAll();
        if(!products.iterator().hasNext()) {
            throw new ApplicationException("No product found", HttpStatus.NOT_FOUND);
        }

        List<ProductResponse> productResponses = new ArrayList<>();
        products.forEach(product -> productResponses.add(convertProductToProductResponse(product)));

        return productResponses;
    }

    @Override
    public ProductResponse getProductById(UUID productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()) {
            throw new ApplicationException("No product found", HttpStatus.NOT_FOUND);
        }

        return convertProductToProductResponse(optionalProduct.get());
    }

    @Override
    public MessageResponse deleteProductById(UUID productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()) {
            throw new ApplicationException("No product found", HttpStatus.NOT_FOUND);
        }

        productRepository.delete(optionalProduct.get());
        return new MessageResponse("Product deleted successfully.");
    }
}
