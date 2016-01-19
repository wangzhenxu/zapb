package com.loiot.baqi.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 建议 实体类
 * 
 * @author  wangzx 
 * @creation 2016-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TgProduct  implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
		
	    private java.lang.Long id;  //主建 db_column: id 
	    private java.lang.Long category;  //分类 db_column: category 
	    private java.lang.String name;  //名称 db_column: name 
	    private java.lang.Double price;  //价格 db_column: price 
	    private java.lang.String unit;  //单位 db_column: unit 
	    private java.lang.Long origin;  //来源 db_column: origin 
	    private java.lang.String originUrl;  //来源网址 db_column: origin_url 
	    private java.lang.String remark;  //备注 db_column: remark 
	    private Integer isDelete;  //上架或下架状态 db_column: is_delete 
	    private java.lang.Long inPerson;  //录入人 db_column: in_person 
	    private java.util.Date inTime;  //录入时间 db_column: in_time 
	    private java.lang.Long updatePerson;  //更新人 db_column: update_person 
	    private java.util.Date udpateTime;  //更新时间 db_column: udpate_time 

	    private java.lang.String inPersonName;
	    private java.lang.String updatePersonName;

	public TgProduct(){
	}

	public TgProduct(
		java.lang.Long id
	){
		this.id = id;
	}

	
	
}

