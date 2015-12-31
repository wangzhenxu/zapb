package com.loiot.baqi.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 建议 实体类
 * 
 * @author  wangzx 
 * @creation 2015-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TgSuggestBugInfo  implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
		
	    private java.lang.Long suggestId;  //主键 db_column: suggest_id 
	    private Integer accountType;  //用户类型 db_column: account_type 
	    private Integer projectType;  //项目类型 db_column: project_type 
	    private java.lang.String currentUrl;  //当前访问的url db_column: current_url 
	    private java.lang.String title;  //标题 db_column: title 
	    private java.lang.String content;  //内容 db_column: content 
	    private java.lang.String address;  //用户所在地 db_column: address 
	    private java.lang.String agent;  //浏览器类型和版本 db_column: agent 
	    private java.lang.String userOs;  //用户使用的操作系统 db_column: user_os 
	    private Integer operationType;  //操作类型 db_column: operation_type 
	    private java.lang.Long inPerson;  //录入人 db_column: in_person 
	    private java.util.Date inTime;  //录入时间 db_column: in_time 
	    private Integer sugType;  //1 bug 2建议 db_column: sug_type 
	    private java.util.Date updateTime;  //更新时间 db_column: update_time 
	    private java.lang.Long updatePerson;  //更新人 db_column: update_person 

	  private java.lang.String inPersonName;

	public TgSuggestBugInfo(){
	}

	public TgSuggestBugInfo(
		java.lang.Long suggestId
	){
		this.suggestId = suggestId;
	}

	
	
}

