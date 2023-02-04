package com.spbproductmanagementjwt.service.productmedia;

import com.spbproductmanagementjwt.model.Product;
import com.spbproductmanagementjwt.model.ProductMedia;
import com.spbproductmanagementjwt.service.IGeneralService;

import java.util.Optional;

public interface IProductMediaService extends IGeneralService<ProductMedia> {

    ProductMedia findByProduct(Product product);
}
