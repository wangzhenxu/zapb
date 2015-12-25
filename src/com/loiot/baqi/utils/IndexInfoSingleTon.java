package com.loiot.baqi.utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IndexInfoSingleTon {
	private static IndexInfoSingleTon indexInfo = new IndexInfoSingleTon();

	private Map<String, List> indexInfoMap = new HashMap();;

	public Map<String, List> getIndexInfoMap() {
		return indexInfoMap;
	}

	public void setIndexInfoMap(Map<String, List> indexInfoMap) {
		this.indexInfoMap = indexInfoMap;
	}

	private IndexInfoSingleTon() {

	}

	public static IndexInfoSingleTon getInstance() {
		return indexInfo;
	}

}
