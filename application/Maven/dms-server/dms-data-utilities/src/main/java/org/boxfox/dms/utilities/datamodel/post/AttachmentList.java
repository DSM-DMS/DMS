package org.boxfox.dms.utilities.datamodel.post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.boxfox.dms.utilities.database.DataSaveAble;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AttachmentList<E> extends DataSaveAble{
	private ArrayList<E> list;
	private Class<E> clazz;
	
	public AttachmentList(Class<E> clazz){
		this();
		this.clazz = clazz;
	}
	
	public AttachmentList(){
		list = new ArrayList<E>();
	}
	
	public void add(E file){
		list.add(file);
	}
	
	public E get(int index){
		return list.get(index);
	}
	
	public int size(){
		return list.size();
	}

	@Override
	public String toQuery() {
		StringBuilder builder = new StringBuilder();
		for(E file : list){
			builder.append(((DataSaveAble)file).toQuery());
		}
		return builder.toString();
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		for(E file : list){
			arr.add(((DataSaveAble)file).toQuery());
		}
		obj.put("List", arr);
		obj.put("Size", list.size());
		return obj;
	}

	@Override
	public DataSaveAble fromResultSet(ResultSet rs) throws SQLException {
		while(rs.next()){
			try {
				list.add((E) ((DataSaveAble)clazz.newInstance()).fromResultSet(rs));
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return this;
	}

}
