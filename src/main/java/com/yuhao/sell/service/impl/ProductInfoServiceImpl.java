package com.yuhao.sell.service.impl;

import com.yuhao.sell.dao.ProductInfoDAO;
import com.yuhao.sell.dto.CartDTO;
import com.yuhao.sell.enums.ProductStatusEnum;
import com.yuhao.sell.enums.ResultEnum;
import com.yuhao.sell.exception.SellException;
import com.yuhao.sell.model.ProductInfo;
import com.yuhao.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * ProductInfoServiceImpl
 *
 * @author CYH
 * @date 2018/3/26
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService{

    @Autowired
    private ProductInfoDAO productInfoDAO;

    @Override
    public Optional<ProductInfo> findOne(String productId) {
        return productInfoDAO.findById(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDAO.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findUpAll(String sellerId) {
        return productInfoDAO.findByProductStatusAndSellerId(ProductStatusEnum.UP.getCode(),sellerId);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDAO.findAll(pageable);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable, String sellerId) {
        return productInfoDAO.findBySellerId(sellerId,pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDAO.save(productInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            Optional<ProductInfo> productInfo = productInfoDAO.findById(cartDTO.getProductId());
            if (!productInfo.isPresent()) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.get().getProductStock() + cartDTO.getProductQuantity();
            productInfo.get().setProductStock(result);
            productInfoDAO.save(productInfo.get());
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            Optional<ProductInfo> productInfo = productInfoDAO.findById(cartDTO.getProductId());
            if (!productInfo.isPresent()) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.get().getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.get().setProductStock(result);

            productInfoDAO.save(productInfo.get());
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        Optional<ProductInfo> productInfo = productInfoDAO.findById(productId);
        if (!productInfo.isPresent()) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.get().getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.get().setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoDAO.save(productInfo.get());
    }

    @Override
    public ProductInfo offSale(String productId) {
        Optional<ProductInfo> productInfo = productInfoDAO.findById(productId);
        if (!productInfo.isPresent()) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.get().getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.get().setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoDAO.save(productInfo.get());
    }
}
