package com.spbproductmanagementjwt.cartdetail;

import com.spbproductmanagementjwt.cart.Cart;
import com.spbproductmanagementjwt.product.Product;
import com.spbproductmanagementjwt.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ICartDetailService extends IGeneralService<CartDetail> {

    Optional<CartDetail> findByCartAndProduct(Cart cart, Product product);

    List<CartDetail> findAllByCart(Cart cart);

    Optional<CartDetail> findByProduct(Product product);
}
