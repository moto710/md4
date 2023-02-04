package com.spbproductmanagementjwt.service.cartdetail;

import com.spbproductmanagementjwt.model.Cart;
import com.spbproductmanagementjwt.model.CartDetail;
import com.spbproductmanagementjwt.model.Product;
import com.spbproductmanagementjwt.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ICartDetailService extends IGeneralService<CartDetail> {

    Optional<CartDetail> findByCartAndProduct(Cart cart, Product product);

    List<CartDetail> findAllByCart(Cart cart);

    Optional<CartDetail> findByProduct(Product product);
}
