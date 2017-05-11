package com.dms.parser.datamodel.post;

import java.sql.SQLException;
import java.util.ArrayList;

import org.boxfox.dms.utilities.database.DataSaveable;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AttachmentList extends DataSaveable{
	private ArrayList<Attachment> list;
	
	public AttachmentList(){
		list = new ArrayList<Attachment>();
	}
	
	public void add(Attachment file){
		list.add(file);
	}
	
	public Attachment get(int index){
		return list.get(index);
	}
	
	public int size(){
		return list.size();
	}

	@Override
	public String toQuery() {
		StringBuilder builder = new StringBuilder();
		for(Attachment file : list){
			builder.append(((DataSaveable)file).toQuery());
		}
		return builder.toString();
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		for(Attachment file : list){
			arr.add(((DataSaveable)file).toJSONObject());
		}
		obj.put("List", arr);
		obj.put("Size", list.size());
		return obj;
	}

	@Override
	public DataSaveable fromResultSet(SafeResultSet rs) throws SQLException {
		while(rs.next()){
				list.add((Attachment) new Attachment().fromResultSet(rs));
		}
		return this;
	}

}
