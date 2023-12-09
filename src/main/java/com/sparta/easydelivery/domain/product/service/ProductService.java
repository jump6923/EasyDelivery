package com.sparta.easydelivery.domain.product.service;

import com.sparta.easydelivery.domain.product.dto.ProductRequestDto;
import com.sparta.easydelivery.domain.product.dto.ProductResponseDto;
import com.sparta.easydelivery.domain.product.dto.ProductUpdateRequestDto;
import com.sparta.easydelivery.domain.product.entity.Product;
import com.sparta.easydelivery.domain.product.exception.NotFoundProductException;
import com.sparta.easydelivery.domain.product.repository.ProductRepository;
import java.util.List;
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
        if (product.isDeleted()) {
            throw new NotFoundProductException();
        }
        return new ProductResponseDto(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProducts() {
        return productRepository.findAllByOrderByCategory().stream().filter(product ->
            !product.isDeleted()).map(ProductResponseDto::new).toList();
    }

    @Transactional
    public ProductResponseDto updateProduct(Long productId, ProductUpdateRequestDto requestDto) {
        Product product = getProductById(productId);
        product.update(requestDto);
        return new ProductResponseDto(product);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = getProductById(productId);
        product.delete();
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(NotFoundProductException::new);
    }

}
