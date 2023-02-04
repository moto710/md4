package com.spbproductmanagementjwt.controller.api;

import com.spbproductmanagementjwt.model.Product;
import com.spbproductmanagementjwt.model.ProductMedia;
import com.spbproductmanagementjwt.model.dto.ProductCreateDTO;
import com.spbproductmanagementjwt.model.dto.ProductResponseDTO;
import com.spbproductmanagementjwt.service.product.IProductService;
import com.spbproductmanagementjwt.service.productmedia.IProductMediaService;
import com.spbproductmanagementjwt.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductMediaService productMediaService;

    private Product product;

    private ProductResponseDTO productResponseDTO;

    @GetMapping
    private ResponseEntity<?> getAllProducts() {
        List<ProductResponseDTO> productList = productService.findAllProductResponseDTOByDeleteIsFalse();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> createProduct(ProductCreateDTO productCreateDTO, MultipartFile file) {
        if (file == null) {
            product = productService.createWithOutMedia(productCreateDTO);
            productResponseDTO = product.toProductResponseDTO();
        } else {
            product = productService.createWithMedia(productCreateDTO, file);

            ProductMedia productMedia = productMediaService.findByProduct(product);

            productResponseDTO = product.toProductResponseDTO(productMedia);
        }

        return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
    }
}
