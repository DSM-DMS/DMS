package org.boxfox.dms.utilities.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.QueryUtils;

public class Log {
	private static File logFile;
	private static FileWriter fw;
	private static String path = "";
	public static final String LOG_INFO = "INFO";
	public static final String LOG_ERROR = "ERROR";
	private static final String LOG_DEBUG = "DEBUG";

	public static File getLogFile() {
		Calendar c = Calendar.getInstance();
		String currentFileName = QueryUtils.queryBuilder(c.get(Calendar.YEAR), "-", c.get(Calendar.MONTH) + 1, "-",
				c.get(Calendar.DAY_OF_MONTH), ".log");
		if (logFile == null || fw == null || !logFile.getName().equals(currentFileName)) {
			logFile = new File(path + currentFileName);
			try {
				System.out.println(logFile.getPath());
				if (logFile.getParentFile() != null)
					logFile.getParentFile().mkdirs();
				if (!logFile.exists())
					logFile.createNewFile();
				fw = new FileWriter(logFile, true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return logFile;
	}

	public static void setPath(String str) {
		if (!str.endsWith("/")) {
			str += "/";
		}
		path = str;
	}

	/// <summary>
	/// �α� �԰�
	/// </summary>
	/// <author>BoxFox (rlatjdfo112@naver.com)</author>
	/// <date>2016-9-24</date>
	/// <remarks>
	/// �α� ���뿡 ����/�Ϲ�/����� ������ ��¥�� �߰��Ͽ� �԰�(����)�� �����.
	/// </remarks>
	private static String setFormat(String str, String type) {
		Calendar c = Calendar.getInstance();
		return QueryUtils.queryBuilder(c.get(Calendar.YEAR), "/", (c.get(Calendar.MONTH) + 1), "/",
				c.get(Calendar.DAY_OF_MONTH), "/", c.get(Calendar.HOUR_OF_DAY), ":", c.get(Calendar.MINUTE), ":",
				c.get(Calendar.SECOND), " [", type, "] ", str, "\r\n");
	}
	
	public static String cleanStringBuilder(Object ... args){
		StringBuilder builder = new StringBuilder();
		for(Object obj : args){
			if(obj==null) obj = "null";
			builder.append(obj.toString());
		}
		return builder.toString();
	}

	/// <summary>
	/// �Ϲ� �α� ���
	/// </summary>
	/// <author>BoxFox (rlatjdfo112@naver.com)</author>
	/// <date>2016-9-24</date>
	/// <remarks>
	/// �Ϲ� �α׸� ǥ�� ��Ʈ���� �α� ���Ͽ� ����Ѵ�
	/// </remarks>
	public static void l(Object ... args) {
		getLogFile();
		String str = cleanStringBuilder(args);
		str = setFormat(str, LOG_INFO);
		System.out.println(str);
		try {
			fw.write(str);
			fw.flush();
		} catch (IOException | NullPointerException e) {
		}
	}

	/// <summary>
	/// ���� �α� ���
	/// </summary>
	/// <author>BoxFox (rlatjdfo112@naver.com)</author>
	/// <date>2016-9-24</date>
	/// <remarks>
	/// ���� �α׸� ǥ�� ��Ʈ���� �α� ���Ͽ� ����Ѵ�
	/// </remarks>
	public static void e(Object ... args) {
		getLogFile();
		String str = cleanStringBuilder(args);
		str = setFormat(str, LOG_ERROR);
		System.err.println(str);
		try {
			fw.write(str);
			fw.flush();
		} catch (IOException | NullPointerException e) {
		}
	}
	
	public static void printClear(Object ... args) {
		getLogFile();
		String str = cleanStringBuilder(args);
		System.out.print(str);
		try {
			fw.write(str);
			fw.flush();
		} catch (IOException | NullPointerException e) {
		}
	}

}
