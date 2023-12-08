package com.sparta.easydelivery.cart.service;

import com.sparta.easydelivery.cart.dto.CartListResponseDto;
import com.sparta.easydelivery.cart.dto.CartRequestDto;
import com.sparta.easydelivery.cart.dto.CartResponseDto;
import com.sparta.easydelivery.cart.entity.Cart;
import com.sparta.easydelivery.cart.exception.DuplicatedProductInCartException;
import com.sparta.easydelivery.cart.exception.NotFoundCartException;
import com.sparta.easydelivery.cart.repository.CartRepository;
import com.sparta.easydelivery.common.exception.UnauthorizedUserException;
import com.sparta.easydelivery.product.entity.Product;
import com.sparta.easydelivery.product.service.ProductService;
import com.sparta.easydelivery.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;

    private final ProductService productService;

    public CartResponseDto addCart(CartRequestDto requestDto, User user) {
        Product product = productService.getProductById(requestDto.getProductId());
        cartExist(user, product);
        Cart cart = Cart.create(user, product, requestDto.getQuantity());
        cartRepository.save(cart);
        return new CartResponseDto(cart);
    }

    public CartListResponseDto getCarts(User user) {
        List<Cart> carts = getCartList(user);
        return new CartListResponseDto(carts);
    }

    public void deleteCart(Long cartId, User user) {
        cartRepository.delete(getUserCart(cartId, user));
    }

    public void clearCart(User user) {
        cartRepository.clearCartByUser(user);
    }

    public CartResponseDto updateQuantity(Long cartId, int quantity, User user) {
        Cart cart = getUserCart(cartId, user);
        cart.quantityUpdate(quantity);
        return new CartResponseDto(cart);
    }

    private void cartExist(User user, Product product) {
        if (cartRepository.existsByUserAndProduct(user, product)) {
            throw new DuplicatedProductInCartException();
        }
    }

    private Cart getUserCart(Long cartId, User user) {
        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(NotFoundCartException::new);
        if (!cart.getUser().getUsername().equals(user.getUsername())) {
            throw new UnauthorizedUserException();
        }
        return cart;
    }

    public List<Cart> getCartList(User user){  //List<Cart>가 주문 도메인에서 필요해서 추가
        return cartRepository.findAllFetchByUser(user);
    }
}
