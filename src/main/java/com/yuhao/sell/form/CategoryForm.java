package com.yuhao.sell.form;

import lombok.Data;

/**
 * CategoryForm
 *
 * @author CYH
 * @date 2018/3/30
 */
@Data
public class CategoryForm {
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
