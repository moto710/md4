package com.spbproductmanagementjwt.service.product;

import com.spbproductmanagementjwt.model.Product;
import com.spbproductmanagementjwt.model.dto.ProductCreateDTO;
import com.spbproductmanagementjwt.model.dto.ProductResponseDTO;
import com.spbproductmanagementjwt.service.IGeneralService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {

    List<ProductResponseDTO> findAllProductResponseDTOByDeleteIsFalse();

    Product createWithMedia(ProductCreateDTO productCreateDTO, MultipartFile file);

    Product createWithOutMedia(ProductCreateDTO productCreateDTO);

    List<ProductResponseDTO> findAllProductResponseDTO();
}
