package com.spbproductmanagementjwt.service.product;

import com.spbproductmanagementjwt.exception.DataInputException;
import com.spbproductmanagementjwt.model.Product;
import com.spbproductmanagementjwt.model.ProductMedia;
import com.spbproductmanagementjwt.model.dto.ProductCreateDTO;
import com.spbproductmanagementjwt.model.dto.ProductResponseDTO;
import com.spbproductmanagementjwt.repository.IProductMediaRepository;
import com.spbproductmanagementjwt.repository.IProductRepository;
import com.spbproductmanagementjwt.service.productmedia.IProductMediaService;
import com.spbproductmanagementjwt.service.upload.IUploadService;
import com.spbproductmanagementjwt.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IProductMediaRepository productMediaRepository;

    @Autowired
    private UploadUtils uploadUtils;

    @Autowired
    private IUploadService uploadService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductResponseDTO> findAllProductResponseDTOByDeleteIsFalse() {
        return productRepository.findAllProductResponseDTOByDeleteIsFalse();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
//        productRepository.save(product.getProductMedia());
//        productRepository.save(product);
    }

    @Override
    public void deactivate(Long id) {
        productRepository.deactivate(id);
    }

    @Override
    public void reactivate(Long id) {
        productRepository.reactivate(id);
    }

    @Override
    public Product create(ProductCreateDTO productCreateDTO) {

        Product product = productCreateDTO.toProduct();
        product.setId(null);
        productRepository.save(product);

        String fileType = productCreateDTO.getMultipartFile().getContentType();

        assert fileType != null;

        fileType = fileType.substring(0, 5);

        ProductMedia productMedia = new ProductMedia();
        productMedia.setId(null);
        productMedia.setFileType(fileType);
        productMediaRepository.save(productMedia);

        uploadAndSaveProductImage(productCreateDTO, product, productMedia);

        return product;
    }

    private void uploadAndSaveProductImage(ProductCreateDTO productCreateDTO, Product product, ProductMedia productMedia) {
        try {
            Map uploadResult = uploadService.uploadImage(productCreateDTO.getMultipartFile(), uploadUtils.buildImageUploadParams(productMedia));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            productMedia.setFileName(productMedia.getId() + "." + fileFormat);
            productMedia.setFileUrl(fileUrl);
            productMedia.setFileFolder(uploadUtils.IMAGE_UPLOAD_FOLDER);
            productMedia.setCloudId(productMedia.getFileFolder() + "/" + productMedia.getId());
            productMedia.setProduct(product);
            productMediaRepository.save(productMedia);

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload image fail!");
        }
    }
}
