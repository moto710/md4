package com.spbproductmanagementjwt.service.cart;

import com.spbproductmanagementjwt.model.Cart;
import com.spbproductmanagementjwt.model.CartDetail;
import com.spbproductmanagementjwt.model.Product;
import com.spbproductmanagementjwt.repository.ICartDetailRepository;
import com.spbproductmanagementjwt.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService implements ICartService {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private ICartDetailRepository cartDetailRepository;

    @Override
    public void deactivate(Long id) {

    }

    @Override
    public void reactivate(Long id) {

    }

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return Optional.empty();
    }

//    @Override
//    public Optional<Cart> findByUsername(String username) {
//        return cartRepository.findByUsername(username);
//    }

    @Override
    public void updateCartNotExistProduct(Cart cart, Product product, String username) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setId(null);
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setProductName(product.getName());
        cartDetail.setProductPrice(product.getPrice());
        cartDetail.setProductQuantity(1L);
        cartDetail.setProductAmount(product.getPrice());
        cartDetailRepository.save(cartDetail);

        BigDecimal totalAmount = cartDetailRepository.sumTotalAmountByCartId(cart.getId());
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
    }

    @Override
    public void updateCartExistProduct(Cart cart, CartDetail cartDetail, Product product, String username) {
        Long currentQuantity = cartDetail.getProductQuantity();
        long newQuantity = currentQuantity + 1;
        BigDecimal price = product.getPrice();
        BigDecimal newAmount = price.multiply(BigDecimal.valueOf(newQuantity));

        cartDetail.setProductQuantity(newQuantity);
        cartDetail.setProductAmount(newAmount);
        cartDetailRepository.save(cartDetail);

        BigDecimal totalAmount = cartDetailRepository.sumTotalAmountByCartId(cart.getId());
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
    }

    public BigDecimal sumTotalAmountCartDetail(Cart cart) {
        BigDecimal totalAmount = BigDecimal.valueOf(0L);

//        List<CartDetail> cartDetails = cartDetailRepository.findAllByCart(cart);

        return totalAmount;
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> findByCreatedBy(String createdBy) {
        return cartRepository.findByCreatedBy(createdBy);
    }

    @Override
    public void addNewCart(Product product, Long quantity, String createdBy) {
        BigDecimal totalAmount = product.getPrice();
        Cart cart = new Cart();
        cart.setTotalAmount(totalAmount);
        cart.setCreatedBy(createdBy);
        cartRepository.save(cart);

        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setProductName(product.getName());
        cartDetail.setProductPrice(product.getPrice());
        cartDetail.setProductQuantity(quantity);
        cartDetail.setProductAmount(product.getPrice());
        cartDetailRepository.save(cartDetail);
    }

    @Override
    public void addCartDetail(Cart cart, Product product, Long quantity) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setProductName(product.getName());
        cartDetail.setProductPrice(product.getPrice());
        cartDetail.setProductQuantity(quantity);
        cartDetail.setProductAmount(product.getPrice());
        cartDetailRepository.save(cartDetail);

        BigDecimal totalAmount = cartDetailRepository.getAllProductAmount(cart);
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
    }

    @Override
    public void updateCartDetail(CartDetail cartDetail, Long quantity) {
        Cart cart = cartDetail.getCart();

        BigDecimal productPrice = cartDetail.getProduct().getPrice();
        Long currentQuantity = cartDetail.getProductQuantity();
        long newQuantity = currentQuantity + quantity;

        BigDecimal productAmount = productPrice.multiply(BigDecimal.valueOf(newQuantity));
        cartDetail.setProductPrice(productPrice);
        cartDetail.setProductQuantity(newQuantity);
        cartDetail.setProductAmount(productAmount);
        cartDetailRepository.save(cartDetail);

        BigDecimal totalAmount = cartDetailRepository.getAllProductAmount(cart);
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
    }
}
