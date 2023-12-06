package com.sparta.easydelivery.product.productservice;

import com.sparta.easydelivery.product.dto.ProductRequestDto;
import com.sparta.easydelivery.product.dto.ProductResponseDto;
import com.sparta.easydelivery.product.entity.Product;
import com.sparta.easydelivery.product.exception.InvalidModifierException;
import com.sparta.easydelivery.product.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final OrderProductService orderProductService;

    private final CartService cartService;

    public ProductResponseDto addProduct(ProductRequestDto requestDto, Admin admin) throws NullPointerException{
        Product product = new Product(requestDto, admin);
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
        return productRepository.findAllByOrderByProductCategory().stream()
            .map(ProductResponseDto::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto requestDto, Admin admin) throws NullPointerException{
        Product product = getProductById(productId);
        // validateIsAdmin(Admin.getAdmin().getId(), user.getId());
        product.update(requestDto);
        return new ProductResponseDto(product);
    }

    public void deleteProduct(Long productId, Admin admin) {
        Product product = getProductById(productId);
        productRepository.delete(product);
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new NullPointerException ("productId", productId.toString(), "주어진 id이 해당하는 제품이 존재하지 않음"))
    }
  /*
    void validateIsAdmin(Long AdminId, Long loggedInUserId) throws InvalidModifierException {
        if (!AdminId.equals(loggedInUserId)) {
            throw new InvalidModifierException(loggedInUserId.toString(),
                "사용자는 이 게시물을 업데이트/삭제할 권한이 없습니다.");
        }
    }*/
}
