package com.spbproductmanagementjwt.cart;

//import com.spbproductmanagementjwt.cartdetail.CartDetail;
import com.spbproductmanagementjwt.product.Product;
import com.spbproductmanagementjwt.service.IGeneralService;

import java.util.Optional;


public interface ICartService extends IGeneralService<Cart> {

//    Optional<Cart> findByUsername(String username);

    void updateCartNotExistProduct(Cart cart, Product product, String username);

//    void updateCartExistProduct(Cart cart, CartDetail cartDetail, Product product, String username);

    Optional<Cart> findByCreatedBy(String createdBy);

    void addNewCart(Product product, Long quantity, String createdBy);

    void addCartDetail(Cart cart, Product product, Long quantity);

//    void updateCartDetail(CartDetail cartDetail, Long quantity);
}
