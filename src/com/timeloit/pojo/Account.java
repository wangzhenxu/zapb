package com.timeloit.pojo;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.loiot.commons.cache.BaseLoiotPojo;
import com.timeloit.pojo.Permission;
import com.timeloit.pojo.Role;

/**
 * 账号
 * 
 * @author zhengrj
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Account implements java.io.Serializable, BaseLoiotPojo {

    private static final long serialVersionUID = -7359793891878472807L;

    /**
     * 账号ID
     */
    private Long accountId;
    /**
     * 账号名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    
    /**
     * 账号类型
     */
    private Integer type;
    
    /**
     * 是否删除
     */
    private Integer isDelete; 
    
    
    private java.lang.Long expandId;  //id db_column: expand_id 
    private java.lang.Long auditPositionId;  //评审职位id db_column: audit_position_id 
    private java.lang.String nickName;  //昵称 db_column: nick_name 
    private java.lang.String iphone;  //手机 db_column: iphone 
    private java.lang.String email;  //邮箱 db_column: email 
    private java.lang.Long sexId;  //性别id db_column: sex_id 
    private java.lang.Long paymentTypeId;  //支付方式 db_column: payment_type_id 
    private java.lang.String paymentCode;  //支付内容 db_column: payment_code 
    private java.util.Date inTime;  //录入时间 db_column: in_time 
    private java.lang.Long inPerson;  //录入人 db_column: in_person 
    private Date lastLoginTime; //最后登陆时间
    private Long isAcceptAudit; //是否接受评审

    /**
     * 账号角色
     */
    private Role role;

    /**
     * 获得这个账号的所有权限字符
     * 
     * @return 返回一个权限字符列表
     */
    public List<String> getPermissionStringList() {

        List<String> permissionStringList = new ArrayList<String>();

        if (role == null) {
            return permissionStringList;
        }

        // 获得所有角色的所有权限字符
        for (Permission permission : role.getPermissionList()) {
            permissionStringList.add(permission.getPermissionStr());
        }

        return permissionStringList;
    }

    @JsonIgnore
    public String getMemkey() {
        return "com.timeloit.pojo.Account." + accountId;
    }
}
