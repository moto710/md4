package com.spbproductmanagementjwt.service.product;

import com.spbproductmanagementjwt.model.Product;
import com.spbproductmanagementjwt.model.dto.ProductCreateDTO;
import com.spbproductmanagementjwt.model.dto.ProductResponseDTO;
import com.spbproductmanagementjwt.service.IGeneralService;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {

    List<ProductResponseDTO> findAllProductResponseDTOByDeleteIsFalse();

    Product create(ProductCreateDTO productCreateDTO);
}
