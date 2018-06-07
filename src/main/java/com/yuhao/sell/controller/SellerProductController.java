package com.yuhao.sell.controller;

import com.yuhao.sell.exception.SellException;
import com.yuhao.sell.form.ProductForm;
import com.yuhao.sell.model.ProductCategory;
import com.yuhao.sell.model.ProductInfo;
import com.yuhao.sell.model.SellerInfo;
import com.yuhao.sell.service.ProductCategoryService;
import com.yuhao.sell.service.ProductInfoService;
import com.yuhao.sell.utils.ImageUtils;
import com.yuhao.sell.utils.KeyUtil;
import com.yuhao.sell.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * SellerProductController
 *
 * @author CYH
 * @date 2018/3/30
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductInfoService productService;

    @Autowired
    private ProductCategoryService categoryService;

    @Autowired
    private SecurityUtils securityUtils;

    /**
     * 列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {

        SellerInfo sellerInfo=securityUtils.getCurrentSeller();
        if (sellerInfo==null){
            map.put("msg","请重新登陆");
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request,sellerInfo.getSellerId());
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/dishes", map);
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {

        SellerInfo sellerInfo=securityUtils.getCurrentSeller();
        if (sellerInfo==null){
            map.put("msg","请重新登陆");
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        if (!StringUtils.isEmpty(productId)) {
            Optional<ProductInfo> productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo.get());
        }

        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll(sellerInfo.getSellerId());
        map.put("categoryList", categoryList);

        return new ModelAndView("product/dishesDetail", map);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map,
                             @RequestParam(required = false,value = "multipartFile") MultipartFile multipartFile) throws IOException {

        SellerInfo sellerInfo=securityUtils.getCurrentSeller();
        if (sellerInfo==null){
            map.put("msg","请重新登陆");
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        if (multipartFile!=null&&!"".equals(multipartFile.getOriginalFilename())){
            if (multipartFile.getSize()>1148576){
                map.put("url", "/sell/seller/product/list");
                map.put("msg", "图片大小不能大于1M");
                return new ModelAndView("common/error", map);
            }else {
                try {
                    form.setProductIcon(ImageUtils.uploadToCDNAndGetUrl(multipartFile.getBytes()));
                }catch (Exception e){
                    log.error("上传图片错误",e);
                }
            }
        }


        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        Optional<ProductInfo> productInfo = Optional.of(new ProductInfo());

        try {
            //如果productId为空, 说明是新增
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId());
            } else {
                form.setProductId(KeyUtil.genUniqueKey());
            }
            if (form.getProductIcon()==null){
                form.setProductIcon(productInfo.get().getProductIcon());
            }
            BeanUtils.copyProperties(form, productInfo.get());
            productInfo.get().setSellerId(sellerInfo.getSellerId());
            productService.save(productInfo.get());
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }


}
