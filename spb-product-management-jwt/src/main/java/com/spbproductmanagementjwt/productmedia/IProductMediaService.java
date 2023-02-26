package com.spbproductmanagementjwt.productmedia;

import com.spbproductmanagementjwt.product.Product;
import com.spbproductmanagementjwt.productmedia.ProductMedia;
import com.spbproductmanagementjwt.service.IGeneralService;

public interface IProductMediaService extends IGeneralService<ProductMedia> {

    ProductMedia findByProduct(Product product);
}
