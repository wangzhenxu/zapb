package com.loiot.baqi.controller.request;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.timeloit.pojo.Product;
import com.timeloit.pojo.ProductActionAlert;
import com.timeloit.pojo.ProductCondition;
import com.timeloit.pojo.Tag;
/**
 * @author zhangbo
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductRequesthandler {
    private Product product;
    private Tag tag;
    private long[] delProductAttributeId;
    private long[] delProductCommandGroupId;
    private long[] delProductCommandId;
    private String actionType;
    private ProductActionAlert actionAlert;
    private List<ProductCondition> conditionList;
    private long[] delProductconditionId;
}
