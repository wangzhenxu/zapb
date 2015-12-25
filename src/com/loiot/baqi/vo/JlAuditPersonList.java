package com.loiot.baqi.vo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.loiot.baqi.pojo.AccountExpandInfo;
@Data
@EqualsAndHashCode(callSuper = false)
public class JlAuditPersonList {
	
	private String auditPersonName;
	private Long auditPerson;
	private Date lastAuditTime;
	private int auditCount;
	private int waitAuditCount;
	
	public int getAllowAuditCount(){
		return 5-this.getWaitAuditCount();
	}
}
