package com.spbproductmanagementjwt.service.product;

import com.spbproductmanagementjwt.exception.DataInputException;
import com.spbproductmanagementjwt.model.Product;
import com.spbproductmanagementjwt.model.ProductMedia;
import com.spbproductmanagementjwt.model.dto.ProductCreateDTO;
import com.spbproductmanagementjwt.model.dto.ProductResponseDTO;
import com.spbproductmanagementjwt.repository.IProductMediaRepository;
import com.spbproductmanagementjwt.repository.IProductRepository;
import com.spbproductmanagementjwt.service.upload.IUploadService;
import com.spbproductmanagementjwt.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    private Product product;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductResponseDTO> findAllProductResponseDTOByDeleteIsFalse() {
        return productRepository.findAllProductResponseDTOByDeleteIsFalse();
    }

    @Override
    public List<ProductResponseDTO> findAllProductResponseDTOByDeleteIsTrue() {
        return productRepository.findAllProductResponseDTOByDeleteIsTrue();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByIdAndDeletedIsTrue(Long id) {
        return productRepository.findByIdAndDeletedIsTrue(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
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
    public Product createWithOutMedia(ProductCreateDTO productCreateDTO) {
        product = productCreateDTO.toProduct();
        productRepository.save(product);

        Optional<ProductMedia> productMediaOptional = productMediaRepository.findById("c0e1e113-0c94-4ece-96f1-d889006dea04");

        if (!productMediaOptional.isPresent()) {
            throw new RuntimeException("No Image!");
        }

        ProductMedia productMedia = productMediaOptional.get();
        productMedia.setProduct(product);

        return product;
    }
    @Override
    public Product createWithMedia(ProductCreateDTO productCreateDTO, MultipartFile file) {
        product = productCreateDTO.toProduct();
        productRepository.save(product);

        ProductMedia productMedia = new ProductMedia();
        productMedia.setProduct(product);
        productMedia.setFileType(file.getContentType());
        productMedia.setFileFolder(uploadUtils.IMAGE_UPLOAD_FOLDER);
        productMediaRepository.save(productMedia);

        uploadAndSaveProductImage(file, productMedia);

        return product;
    }

    private void uploadAndSaveProductImage(MultipartFile file, ProductMedia productMedia) {
        try {
            Map uploadResult = uploadService.uploadImage(file, uploadUtils.buildImageUploadParams(productMedia));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            productMedia.setFileName(productMedia.getId() + "." + fileFormat);
            productMedia.setFileUrl(fileUrl);
            productMedia.setCloudId(productMedia.getFileFolder() + "/" + productMedia.getId());
            productMediaRepository.save(productMedia);

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload image fail!");
        }
    }

    @Override
    public List<ProductResponseDTO> findAllProductResponseDTO() {
        return productRepository.findAllProductResponseDTO();
    }
}
