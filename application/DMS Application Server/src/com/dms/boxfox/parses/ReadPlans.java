package com.dms.boxfox.parses;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;

import com.dms.boxfox.logging.Log;

public class ReadPlans {
	public String lists="";
	private int year,month;
	
	public void run(int year, int month){
		this.year = year;
		this.month = month;
		Date dates= new Date(year, month, 15);
		Date datess = new Date(1970,1,1);
		long time = dates.getTime();
		long times = datess.getTime();
		Timestamp ts = new Timestamp(time);
		Timestamp ts2 = new Timestamp(times);
		long ResultData = (ts.getTime() - ts2.getTime())/1000;
		try {
			go("http://dsm.hs.kr/segio/plan_v2/month_cont.php?lid=main&sdate_ms="+ResultData);
		} catch (Exception e) {
			Log.e(e.toString());
		}
		
	}
	
	public void go(String targetURL)throws Exception{
		HttpURLConnection httpConn = null;  
		URL url;
		url = new URL(targetURL);
		httpConn = (HttpURLConnection)url.openConnection();
	    
	    httpConn.setDoInput(true);
	    InputStream is = httpConn.getInputStream();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	    
	    String line;
	    StringBuffer sb = new StringBuffer();
	    String HTML_CONTENT;
	    String VAR_POST;
	    
	    while((line = rd.readLine()) != null) {
	            sb.append(line);
	    }

	    HTML_CONTENT = sb.toString();
	    int F = HTML_CONTENT.indexOf("<table class=\"plist\" summary=\"월간\">") + ("<table class=\"plist\" summary=\"월간\">").length();
	    int L = HTML_CONTENT.indexOf("</table>");
	    VAR_POST = sb.toString().substring(F, L);
	    F = VAR_POST.indexOf("<tbody>") + ("<tbody>").length();
	    VAR_POST = VAR_POST.toString().substring(F, VAR_POST.length());
	    getData(VAR_POST);
	}
	
	public void getData(String POST){
		boolean pass = false;
		boolean check = false;
    	ArrayList<String> arr = new ArrayList<String>();
		final String FLAGE = "<span class=\"left\">";
		final String FLAGE2 = "</span>				</div>				<div class=\"mday_wrap\">";
		final String FLAGE3 = "<p class=\"mday_pl\"></p>";
		final String FLAGE4 = "title=\"";
		final String FLAGE5 = "\" >";

		String Day,Data;
		while(POST.contains(FLAGE)){
			int F = POST.indexOf(FLAGE) + FLAGE.length();
		    int L = POST.indexOf(FLAGE2);
		    Day = POST.substring(F, L);
		    POST = POST.substring(L,POST.length());
		    F = POST.indexOf(FLAGE2) + FLAGE2.length();
		    L = POST.indexOf(FLAGE3);
		    Data = POST.toString().substring(F, L);
		    POST = POST.substring(L,POST.length());
		    if(1==Integer.valueOf(Day)) pass = !pass;
		    if(!pass)continue;
		    if(Data.equalsIgnoreCase("				")){
		    	arr.add(Day +"일");
		    }
		    else {
		    	check = true;
		    	String tmp;
		    	tmp = Day+"일 "+Data.substring(Data.indexOf(FLAGE4)+FLAGE4.length(), Data.indexOf(FLAGE5));
		    	Data = Data.substring(Data.indexOf(FLAGE5)+FLAGE5.length(),Data.length());
		    	while(Data.contains(FLAGE4)){
			    	tmp += "\n          "+Data.substring(Data.indexOf(FLAGE4)+FLAGE4.length(), Data.indexOf(FLAGE5));
			    	Data = Data.substring(Data.indexOf(FLAGE5)+FLAGE5.length(),Data.length());
		    	}
		    	arr.add(tmp);
		    }
		}
		JSONArray arrs = new JSONArray();
		for(int i=0;i<arr.size();i++){
			arrs.put(arr.get(i));
		}
		if(check){
		/*if(in.run3(year, month,arrs))
			lists+=month+" ";*/
		}
		}
}
