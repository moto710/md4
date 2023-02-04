package com.spbproductmanagementjwt.controller.api;

import com.spbproductmanagementjwt.model.Product;
import com.spbproductmanagementjwt.model.ProductMedia;
import com.spbproductmanagementjwt.model.dto.ProductCreateDTO;
import com.spbproductmanagementjwt.model.dto.ProductDTO;
import com.spbproductmanagementjwt.model.dto.ProductResponseDTO;
import com.spbproductmanagementjwt.service.product.IProductService;
import com.spbproductmanagementjwt.service.productmedia.IProductMediaService;
import com.spbproductmanagementjwt.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    private ResponseEntity<?> getAllProducts() {
        List<ProductResponseDTO> productList = productService.findAllProductResponseDTOByDeleteIsFalse();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> createProduct(ProductCreateDTO productCreateDTO, BindingResult br) {
        System.out.println(productCreateDTO);
//        new ProductCreateDTO().validate(productCreateDTO, br);
//
//        if (br.hasFieldErrors()) {
//            return appUtils.mapErrorToResponse(br);
//        }

        productCreateDTO.setId(null);

        product = productService.create(productCreateDTO);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        Optional<ProductMedia> productMediaOptional = productMediaService.findByProduct(product);

        if (!productMediaOptional.isPresent()) {
            ProductMedia productMedia = new ProductMedia();
            productMedia.setId(null);
            productMedia.setFileName(null);
            productMedia.setFileFolder(null);
            productMedia.setFileUrl(null);

            productResponseDTO = product.toProductResponseDTO(productMedia);
        } else {
            ProductMedia productMedia = productMediaOptional.get();
            productResponseDTO = product.toProductResponseDTO(productMedia);
        }

        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }
}
