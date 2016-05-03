package com.bong.common.dao;

import java.util.List;
import java.util.Map;

public interface bongDAO {
	public int insertInformation(String id, Object value) throws Exception;
	
	public int updateInformation(String id, Object value) throws Exception;
	public int updateInformation(String id, Map<String, Object> map) throws Exception;
	
	public int deleteInformation(String id, Map<String, Object> map) throws Exception;
	public int deleteInformation(String id, Object value) throws Exception;
	public int deleteAll(String id) throws Exception;
	
	public int getIntValue(String id, Map<String, Object> map) throws Exception;
	public int getIntValue(String id, Object value) throws Exception;
	public int getIntValue(String id) throws Exception;
	
	public <T> List<T> getListInformation(String id, Map<String, Object> map) throws Exception;
	public <T> List<T> getListInformation(String id, Object value) throws Exception;
	public <T> List<T> getListInformation(String id) throws Exception;
	
	public <T> T getReadInformation(String id) throws Exception;
	public <T> T getReadInformation(String id, Object value) throws Exception;
	public <T> T getReadInformation(String id, Map<String, Object> map) throws Exception;
}
