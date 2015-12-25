package com.loiot.baqi.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典信息 实体类
 * 
 * @author  wangzx 
 * @creation 2015-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ZpDictionaryInfo  implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
		
	    private java.lang.Long dictionaryId;  //dictionaryId db_column: dictionary_id 
	    private Integer type;  //type db_column: type 
	    private java.lang.String name;  //名称 db_column: name 
	    private Integer sort;  //sort db_column: sort 
	    private java.lang.String value;  //正则表达式(简历) db_column: value 
	    private java.lang.String showName;  //名称 db_column: show_name 

	public ZpDictionaryInfo(){
	}

	public ZpDictionaryInfo(
		java.lang.Long dictionaryId
	){
		this.dictionaryId = dictionaryId;
	}

	
	
}

