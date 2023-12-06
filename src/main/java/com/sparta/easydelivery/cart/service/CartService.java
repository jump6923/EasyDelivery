package com.sparta.easydelivery.cart.service;

import com.sparta.easydelivery.cart.dto.CartListResponseDto;
import com.sparta.easydelivery.cart.dto.CartRequestDto;
import com.sparta.easydelivery.cart.dto.CartResponseDto;
import com.sparta.easydelivery.cart.entity.Cart;
import com.sparta.easydelivery.cart.repository.CartRepository;
import com.sparta.easydelivery.cart.temp.Product;
import com.sparta.easydelivery.cart.temp.ProductRepository;
import com.sparta.easydelivery.cart.temp.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    public CartResponseDto addCart(CartRequestDto requestDto, User user) {
        Product product = productRepository.findById(requestDto.getProductId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        cartExist(user, product);
        Cart cart = Cart.create(user, product, requestDto.getQuantity());
        cartRepository.save(cart);
        return new CartResponseDto(cart);
    }

    public CartListResponseDto getCarts(User user) {
        List<Cart> carts = cartRepository.findAllByUser(user);
        return new CartListResponseDto(carts);
    }

    public void deleteCart(Long cartId, User user) {
        cartRepository.delete(getUserCart(cartId, user));
    }

    private void cartExist(User user, Product product) {
        if (cartRepository.existsByUserAndProduct(user, product)) {
            throw new IllegalArgumentException("이미 장바구니에 존재하는 상품입니다.");
        }
    }

    private Cart getUserCart(Long cartId, User user) {
        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new IllegalArgumentException("장바구니에 존재하지 않는 상품입니다."));
        if (!cart.getUser().getLoginId().equals(user.getLoginId())) {
            throw new IllegalArgumentException("회원의 장바구니에 담긴 상품이 아닙니다.");
        }
        return cart;
    }
}
