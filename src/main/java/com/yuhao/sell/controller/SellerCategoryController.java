package com.yuhao.sell.controller;

import com.yuhao.sell.exception.SellException;
import com.yuhao.sell.form.CategoryForm;
import com.yuhao.sell.model.ProductCategory;
import com.yuhao.sell.model.SellerInfo;
import com.yuhao.sell.service.ProductCategoryService;
import com.yuhao.sell.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * SellerCategoryController
 *
 * @author CYH
 * @date 2018/3/30
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService categoryService;

    @Autowired
    private SecurityUtils securityUtils;

    /**
     * 类目列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {

        SellerInfo sellerInfo=securityUtils.getCurrentSeller();
        if (sellerInfo==null){
            map.put("msg","请重新登陆");
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        List<ProductCategory> categoryList = categoryService.findAll(sellerInfo.getSellerId());
        map.put("categoryList", categoryList);
        return new ModelAndView("category/families", map);
    }

    /**
     * 展示
     * @param categoryId
     * @return
     */
    @GetMapping("/index")
    @ResponseBody
    public ProductCategory index(@RequestParam(value = "categoryId") Integer categoryId) {

        Optional<ProductCategory> productCategory = categoryService.finOne(categoryId);

        return productCategory.get();

    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {

        SellerInfo sellerInfo=securityUtils.getCurrentSeller();
        if (sellerInfo==null){
            map.put("msg","请重新登陆");
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        Optional<ProductCategory> productCategory = Optional.of(new ProductCategory());
        try {
            if (form.getCategoryId() != null) {
                productCategory = categoryService.finOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form, productCategory.get());
            productCategory.get().setSellerId(sellerInfo.getSellerId());
            categoryService.save(productCategory.get());
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }


}
