/*
 * Timeloit.com Inc.
 * Copyright (c) 2012 时代凌宇物联网数据平台. All Rights Reserved
 */
package com.loiot.baqi.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author yulianyu $Id$
 * 
 */
public class StringToCommonsMultipartFileConverter implements
		Converter<String, CommonsMultipartFile> {

	@Override
	public CommonsMultipartFile convert(String arg0) {
		return null;
	}

}