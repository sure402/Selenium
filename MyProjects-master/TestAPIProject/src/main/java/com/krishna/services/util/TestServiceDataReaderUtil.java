package com.krishna.services.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.TypeFactory;

/**
 * Util class to get Data objects.
 *  
 * @author kabothu 
 */

public class TestServiceDataReaderUtil {
	
public static final ObjectMapper JSONMAPPER = new ObjectMapper();
	
	public static  <T> T getDataObject(Class<T> obj,String fileName) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		InputStream is = TestServiceDataReaderUtil.class.getResourceAsStream(fileName);
		T value = mapper.readValue(is, obj);
		return value;
	}
	
	public static  <T> List<T> getDataObjects(Class<T> type,String fileName,String rootPath) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		TypeFactory typeFactory = mapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(
                                            List.class, type);
		InputStream is = TestServiceDataReaderUtil.class.getResourceAsStream("/data"+rootPath+"/"+fileName+".json");
		List<T> value = mapper.readValue(is,collectionType);
		is.close();
		return value;
	} 

}
