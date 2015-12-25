/**
 * 51diancai.com Inc.
 * Copyright (c) 2011-2011 All Rights Reserved.
 */
package com.loiot.baqi.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Id $Id: ResSubject.java 1334 2011-12-26 14:38:10Z yulianyu $
 * @author yulianyu
 */
public class ResSubject extends HashMap<String, Object> implements Serializable{

    /**  */
    private static final long serialVersionUID = -8759447598491570544L;
    public ResSubject(){
        super();
        this.put("ra", new ResAtom());
    }
    
    public void setResult(int status,String desc){
        ((ResAtom)this.get("ra")).setSd(desc);
        ((ResAtom)this.get("ra")).setSt(status);
    }
    
    public void setResult(int status,String desc,String result){
        ((ResAtom)this.get("ra")).setSd(desc);
        ((ResAtom)this.get("ra")).setSt(status);
        ((ResAtom)this.get("ra")).setResult(result);
    }
}
