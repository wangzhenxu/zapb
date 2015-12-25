package com.loiot.baqi.controller.request;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.timeloit.pojo.Tag;
import com.timeloit.pojo.app.App;
/**
 * @author zhangbo
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppRequesthandler {
    private App app;
    private Tag tag;
}