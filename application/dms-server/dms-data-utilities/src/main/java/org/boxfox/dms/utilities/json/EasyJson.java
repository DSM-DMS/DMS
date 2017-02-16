package org.boxfox.dms.utilities.json;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Created by user on 2017-01-25.
 */
public abstract class EasyJson {
    protected JSONObject obj;
    protected JSONArray arr;

    protected EasyJson(String str){
        Object unknownObject = JSONValue.parse(str);
        if(unknownObject instanceof JSONObject){
            this.obj = (JSONObject)unknownObject;
        }else if(unknownObject instanceof JSONArray){
            this.arr = (JSONArray)unknownObject;
        }
    }
    
    public String toString(){
    	if(obj != null){
    		return obj.toJSONString();
    	}else if(arr !=null){
    		return arr.toJSONString();
    	}
    	return null;
    }

    protected EasyJson(JSONObject obj){
        this.obj = obj;
    }

    protected EasyJson(JSONArray arr){
        this.arr = arr;
    }

    protected String toString(Object object)throws JsonException{
        if(object==null)
            throw new JsonException("대상이 없습니다.");
        return object.toString();
    }

    protected int toInt(Object object)throws JsonException{
        if(object==null)
            throw new JsonException("대상이 없습니다.");
        return Integer.valueOf(toString(object));
    }

    protected boolean toBoolean(Object object)throws JsonException{
        if(object==null)
            throw new JsonException("대상이 없습니다.");
        return Boolean.valueOf(toString(object));
    }

    protected EasyJsonObject toJsonObject(Object object)throws JsonException{
        if(!(object instanceof JSONObject)){
            throw new JsonException("JSONObject 형식이 아닙니다.");
        }
        return new EasyJsonObject((JSONObject)object);
    }

    protected EasyJsonArray toJsonArray(Object object)throws JsonException{
        if(!(object instanceof JSONArray)){
            throw new JsonException("JSONArray 형식이 아닙니다.");
        }
        return new EasyJsonArray((JSONArray)object);
    }

}
