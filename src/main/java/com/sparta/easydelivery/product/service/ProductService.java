package com.sparta.easydelivery.product.service;

import com.sparta.easydelivery.product.dto.ProductRequestDto;
import com.sparta.easydelivery.product.dto.ProductResponseDto;
import com.sparta.easydelivery.product.dto.ProductUpdateRequestDto;
import com.sparta.easydelivery.product.entity.Product;
import com.sparta.easydelivery.product.exception.NotFoundProductException;
import com.sparta.easydelivery.product.repository.ProductRepository;
import com.sparta.easydelivery.user.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto requestDto) {
        Product product = new Product(requestDto);
        Product saveProduct = productRepository.save(product);
        return new ProductResponseDto(saveProduct);
    }

    @Transactional(readOnly = true)
    public ProductResponseDto getProduct(Long productId) {
        Product product = getProductById(productId);
        return new ProductResponseDto(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProducts() {
        return productRepository.findAllByOrderByCategory().stream()
            .map(ProductResponseDto::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponseDto updateProduct(Long productId, ProductUpdateRequestDto requestDto) {
        Product product = getProductById(productId);
        product.update(requestDto);
        return new ProductResponseDto(product);
    }

    public void deleteProduct(Long productId) {
        Product product = getProductById(productId);
        productRepository.delete(product);
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(NotFoundProductException::new);
    }

}
