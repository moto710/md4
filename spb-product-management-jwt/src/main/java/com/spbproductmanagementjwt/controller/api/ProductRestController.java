package com.spbproductmanagementjwt.controller.api;

import com.spbproductmanagementjwt.model.Product;
import com.spbproductmanagementjwt.model.dto.ProductDTO;
import com.spbproductmanagementjwt.service.product.IProductService;
import com.spbproductmanagementjwt.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IProductService productService;

    private Product product;

    @PostMapping("/create")
    private ResponseEntity<?> createProduct(@Validated @RequestBody ProductDTO productDTO, BindingResult br) {
        new ProductDTO().validate(productDTO, br);

        if (br.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(br);
        }

        product = productDTO.toProduct();

        productService.save(product);

        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }
}
