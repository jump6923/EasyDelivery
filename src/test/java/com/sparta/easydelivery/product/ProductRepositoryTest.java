package com.sparta.easydelivery.product;

import com.sparta.easydelivery.JpaConfig;
import com.sparta.easydelivery.product.data.Salad.Salad;
import com.sparta.easydelivery.product.data.Sandwich.Bread;
import com.sparta.easydelivery.product.data.Sandwich.Cheese;
import com.sparta.easydelivery.product.data.Sandwich.Sandwich15;
import com.sparta.easydelivery.product.data.Sandwich.Sandwich30;
import com.sparta.easydelivery.product.data.Sauce;
import com.sparta.easydelivery.product.data.Side.Beverage;
import com.sparta.easydelivery.product.data.Side.Side;
import com.sparta.easydelivery.product.data.Veggie;
import com.sparta.easydelivery.product.data.Wrap.Wrap;
import com.sparta.easydelivery.product.entity.Product;
import com.sparta.easydelivery.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest
@Import(JpaConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @Disabled
    @Transactional
    @Rollback(value = false)
    void saveBread() {
        List<Product> productList = new ArrayList<>();
        // for문으로 enum 데이터를 list에 저장
        for (Bread data : Bread.values()) {
            Product product = new Product("bread", data.getName(), data.getPrice(),
                data.getProductDetails());
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }

    @Test
    @Disabled
    @Transactional
    @Rollback(value = false)
    void saveCheese() {
        List<Product> productList = new ArrayList<>();
        // for문으로 enum 데이터를 list에 저장
        for (Cheese data : Cheese.values()) {
            Product product = new Product("cheese", data.getName(), data.getPrice(),
                data.getProductDetails());
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }

    @Test
    @Disabled
    @Transactional
    @Rollback(value = false)
    void saveSalad() {
        List<Product> productList = new ArrayList<>();
        // for문으로 enum 데이터를 list에 저장
        for (Salad data : Salad.values()) {
            Product product = new Product("salad", data.getName(), data.getPrice(),
                data.getProductDetails());
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }

    @Test
    @Disabled
    @Transactional
    @Rollback(value = false)
    void saveFood15() {
        List<Product> productList = new ArrayList<>();
        // for문으로 enum 데이터를 list에 저장
        for (Sandwich15 data : Sandwich15.values()) {
            Product product = new Product("sandwich15", data.getName(), data.getPrice(),
                data.getProductDetails());
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }

    @Test
    @Disabled
    @Transactional
    @Rollback(value = false)
    void saveFood30() {
        List<Product> productList = new ArrayList<>();
        // for문으로 enum 데이터를 list에 저장
        for (Sandwich30 data : Sandwich30.values()) {
            Product product = new Product("sandwich30", data.getName(), data.getPrice(),
                data.getProductDetails());
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }

    @Test
    @Disabled
    @Transactional
    @Rollback(value = false)
    void saveBev() {
        List<Product> productList = new ArrayList<>();
        // for문으로 enum 데이터를 list에 저장
        for (Beverage data : Beverage.values()) {
            Product product = new Product("beverage", data.getName(), data.getPrice(),
                data.getProductDetails());
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }

    @Test
    @Disabled
    @Transactional
    @Rollback(value = false)
    void saveSide() {
        List<Product> productList = new ArrayList<>();
        // for문으로 enum 데이터를 list에 저장
        for (Side data : Side.values()) {
            Product product = new Product("side", data.getName(), data.getPrice(),
                data.getProductDetails());
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }

    @Test
    @Disabled
    @Transactional
    @Rollback(value = false)
    void saveWrap() {
        List<Product> productList = new ArrayList<>();
        // for문으로 enum 데이터를 list에 저장
        for (Wrap data : Wrap.values()) {
            Product product = new Product("wrap", data.getName(), data.getPrice(),
                data.getProductDetails());
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }

    @Test
    @Disabled
    @Transactional
    @Rollback(value = false)
    void saveSauce() {
        List<Product> productList = new ArrayList<>();
        // for문으로 enum 데이터를 list에 저장
        for (Sauce data : Sauce.values()) {
            Product product = new Product("sauce", data.getName(), data.getPrice(),
                data.getProductDetails());
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }

    @Test
    @Disabled
    @Transactional
    @Rollback(value = false)
    void saveVeggie() {
        List<Product> productList = new ArrayList<>();
        // for문으로 enum 데이터를 list에 저장
        for (Veggie data : Veggie.values()) {
            Product product = new Product("veggie", data.getName(), data.getPrice(),
                data.getProductDetails());
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }
}
