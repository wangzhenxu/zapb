package com.loiot.baqi.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 客户信息 实体类
 * 
 * @author  wangzx 
 * @creation 2015-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ZpCompanyInfo  implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
		
	    private java.lang.Long companyId;  //主建 db_column: company_id 
	    private java.lang.String name;  //公司名称 db_column: name 
	    private java.lang.String address;  //公司详细地址 db_column: address 
	    private java.lang.String desc;  //desc db_column: desc 
	    private java.lang.String moreDesc;  //更多描述 db_column: more_desc 
	    private java.lang.Long scaleId;  //公司规模 db_column: scale_id 
	    private java.util.Date regtime;  //注册时间 db_column: regtime 
	    private java.lang.Long financingLevelId;  //融资规模 db_column: financing_level_id 
	    private java.lang.Long industryId;  //所属行业 db_column: industry_id 
	    private java.lang.Long companyNature;  //公司性质 db_column: company_nature 
	    private java.lang.Long inPerson;  //录入人 db_column: in_person 
	    private java.util.Date lastUpdateTime;  //更新时间 db_column: last_update_time 
	    private java.lang.Integer isDelete;  //1 未删除  2 以删除 db_column: is_delete 
	    private java.lang.String coordX;  //坐标x db_column: coord_x 
	    private java.lang.String coordY;  //坐标y db_column: coord_y 
	    private java.lang.Long areaId;  //公司所在城区 db_column: area_id 
	    private java.lang.String inPersonName;   

	public ZpCompanyInfo(){
	}

	public ZpCompanyInfo(
		java.lang.Long companyId
	){
		this.companyId = companyId;
	}

	
	
}

