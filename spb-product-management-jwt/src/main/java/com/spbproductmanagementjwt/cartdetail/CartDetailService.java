//package com.spbproductmanagementjwt.cartdetail;
//
//import com.spbproductmanagementjwt.cart.Cart;
//import com.spbproductmanagementjwt.product.Product;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//
//@Service
//@Transactional
//public class CartDetailService implements ICartDetailService {
//
//    @Autowired
//    private ICartDetailRepository cartDetailRepository;
//
//    @Override
//    public void deactivate(Long id) {
//
//    }
//
//    @Override
//    public void reactivate(Long id) {
//
//    }
//
//    @Override
//    public Optional<CartDetail> findById(Long id) {
//        return cartDetailRepository.findById(id);
//    }
//
//    @Override
//    public List<CartDetail> findAll() {
//        return cartDetailRepository.findAll();
//    }
//
//    @Override
//    public Optional<CartDetail> findByCartAndProduct(Cart cart, Product product) {
//        return cartDetailRepository.findByCartAndProduct(cart, product);
//    }
//
//    @Override
//    public List<CartDetail> findAllByCart(Cart cart) {
//        return cartDetailRepository.findAllByCart(cart);
//    }
//
//    @Override
//    public void save(CartDetail cartDetail) {
//        cartDetailRepository.save(cartDetail);
//    }
//
//    @Override
//    public Optional<CartDetail> findByProduct(Product product) {
//        return cartDetailRepository.findByProduct(product);
//    }
//}
