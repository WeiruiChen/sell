package com.yuhao.sell.controller;

import com.yuhao.sell.VO.ProductInfoVO;
import com.yuhao.sell.VO.ProductVO;
import com.yuhao.sell.VO.ResultVO;
import com.yuhao.sell.enums.ResultEnum;
import com.yuhao.sell.exception.SellException;
import com.yuhao.sell.model.ProductCategory;
import com.yuhao.sell.model.ProductInfo;
import com.yuhao.sell.model.SellerInfo;
import com.yuhao.sell.service.SellerService;
import com.yuhao.sell.service.impl.ProductCategoryServiceImpl;
import com.yuhao.sell.service.impl.ProductInfoServiceImpl;
import com.yuhao.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BuyerProductController
 *
 * @author CYH
 * @date 2018/3/26
 */
@RestController
@RequestMapping("/buyer/product")
@Slf4j
public class BuyerProductController {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Autowired
    private SellerService sellerService;


    @GetMapping("/list")
    public ResultVO list(@RequestParam("key") String key){

        System.out.println("商户密钥:"+key);

        SellerInfo sellerInfo=sellerService.findSellerInfoByKey(key);

        if (sellerInfo==null){
            log.error("商户不存在");
            throw new SellException(ResultEnum.NULL_MERCHANT);
        }

       //1.查询所有上架商品
        List<ProductInfo> productInfoList=productInfoService.findUpAll(sellerInfo.getSellerId());

        //2.查询类目
        List<Integer> categoryTypeList=productInfoList.stream()
                .map(e->e.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> productCategoryList=productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //3数据封装
        List<ProductVO> productVOs=new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList){
            ProductVO productVO=new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOs=new ArrayList<>();
            for (ProductInfo productInfo:productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO=new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOs.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOs);

            productVOs.add(productVO);
        }


        try {
            sellerInfo.setVisitCount(sellerInfo.getVisitCount()+1);
            sellerService.save(sellerInfo);
        }catch (Exception e){
            e.printStackTrace();
        }


        return ResultVOUtil.success(productVOs);
    }

}
