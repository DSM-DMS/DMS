package org.boxfox.dms.utilities.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by user on 2017-01-25.
 */
public class EasyJsonObject extends EasyJson{
	
	public EasyJsonObject(){
		super(new JSONObject());
	}

    public EasyJsonObject(JSONObject obj){
        super(obj);
    }

    public EasyJsonObject(String str) throws JsonException{
        super(str);
        if(obj==null){
            throw new JsonException("JSONObject 형식이 아닙니다!");
        }
    }
    
    public JSONObject getContext(){
    	return obj;
    }
    
    public boolean containsKey(String key) {
    	return obj.containsKey(key);
    }
    
    public void put(String key, Object obj){
    	super.obj.put(key, obj);
    }

    public String getString(String key) throws JsonException{
        return toString(obj.get(key));
    }

    public Object get(String key){
        return obj.get(key);
    }

    public void remove(String key){
        obj.remove(key);
    }
    
    public void clear() {
    	obj.clear();
    }

    public int getInt(String key) throws JsonException{
        return toInt(get(key));
    }

    public boolean getBoolean(String key)throws JsonException{
        return toBoolean(get(key));
    }

    public EasyJsonObject getJsonObject(String key)throws JsonException{
        return toJsonObject(get(key));
    }

    public EasyJsonArray getJSONArray(String key)throws JsonException{
        return toJsonArray(get(key));
    }
}
