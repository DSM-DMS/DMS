package org.boxfox.dms.utilities.json;

import org.json.simple.JSONArray;

/**
 * Created by user on 2017-01-25.
 */
public class EasyJsonArray extends EasyJson{

    public EasyJsonArray(JSONArray arr){
        super(arr);
    }
    
    public EasyJsonArray(){
    	super(new JSONArray());
    }
    
    public JSONArray getContext(){
    	return arr;
    }
    
    public void add(Object obj){
    	arr.add(obj);
    }

    public EasyJsonArray(String str) throws JsonException{
        super(str);
        if(arr==null){
            throw new JsonException("JSONArray 형식이 아닙니다!");
        }
    }

    public Object get(int num){
        return arr.get(num);
    }

    public void remove(int num){
        arr.remove(num);
    }

    public int getInt(int num) throws JsonException{
        return toInt(get(num));
    }

    public String getString(int num) throws JsonException{
        return toString(get(num));
    }

    public boolean getBoolean(int num) throws JsonException{
        return toBoolean(get(num));
    }

    public EasyJsonObject getJsonObject(int num) throws JsonException{
        return toJsonObject(get(num));
    }

    public EasyJsonArray getJSONArray(int num) throws JsonException{
        return toJsonArray(get(num));
    }
}
