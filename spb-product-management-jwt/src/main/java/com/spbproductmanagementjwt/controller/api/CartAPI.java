package com.spbproductmanagementjwt.controller.api;

import com.spbproductmanagementjwt.exception.DataInputException;
import com.spbproductmanagementjwt.model.Cart;
import com.spbproductmanagementjwt.model.CartDetail;
import com.spbproductmanagementjwt.model.Product;
import com.spbproductmanagementjwt.model.dto.CartRequestDTO;
import com.spbproductmanagementjwt.service.cart.ICartService;
import com.spbproductmanagementjwt.service.cartdetail.ICartDetailService;
import com.spbproductmanagementjwt.service.product.IProductService;
import com.spbproductmanagementjwt.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartAPI {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private ICartDetailService cartDetailService;

    @Autowired
    private AppUtils appUtils;

//    @PostMapping("/add-to-cart")
//    public ResponseEntity<?> addCart(@RequestBody CartRequestDTO cartRequestDTO) {
//
//        String username = appUtils.getUserName();
//
//        Long productId = cartRequestDTO.getProductId();
//
//        Optional<Product> productOptional = productService.findById(productId);
//
//        if (!productOptional.isPresent()) {
//            throw new DataInputException("Invalid product!");
//        }
//
//        Product product = productOptional.get();
//        Long quantity = cartRequestDTO.getQuantity();
//
//        Optional<Cart> cartOptional = cartService.findByCreatedBy(username);
//
//        if (!cartOptional.isPresent()) {
//            cartService.addNewCart(product, quantity, username);
//        }
//        else {
//            Optional<CartDetail> cartDetailOptional = cartDetailService.findByProduct(product);
//
//            if (!cartDetailOptional.isPresent()) {
//                cartService.addCartDetail(cartOptional.get(), product, quantity);
//            }
//            else {
//                cartService.updateCartDetail(cartDetailOptional.get(), quantity);
//            }
//        }
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
