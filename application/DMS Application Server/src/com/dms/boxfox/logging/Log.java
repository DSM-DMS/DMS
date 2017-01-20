package com.dms.boxfox.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import com.dms.boxfox.database.DataBase;

public class Log {
	private static File logFile;
	private static FileWriter fw;
	private static String path = "/";
	public static final String LOG_INFO = "INFO";
	public static final String LOG_ERROR = "ERROR";
	private static final String LOG_DEBUG = "DEBUG";

	public static File getLogFile() {
		Calendar c = Calendar.getInstance();

		String currentFileName = DataBase.queryBuilder(c.get(Calendar.YEAR), ".", c.get(Calendar.MONTH), ".", c.get(Calendar.DAY_OF_MONTH), ".txt");

		String currentFileName = DataBase.getInstance().queryBuilder(c.get(Calendar.YEAR), ".", c.get(Calendar.MONTH), ".", c.get(Calendar.DAY_OF_MONTH), ".txt");
		
		if (logFile == null || fw == null || logFile.getName().equals(currentFileName)) {
			logFile = new File(path + currentFileName);
			try {
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
		return DataBase.getInstance().queryBuilder(c.get(Calendar.YEAR), "/", (c.get(Calendar.MONTH) + 1), "/",
				c.get(Calendar.DAY_OF_MONTH), "/", c.get(Calendar.HOUR_OF_DAY), ":", c.get(Calendar.MINUTE), ":",
				c.get(Calendar.SECOND), " [", type, "] ", str, "\r\n");
	}

	/// <summary>
	/// �Ϲ� �α� ���
	/// </summary>
	/// <author>BoxFox (rlatjdfo112@naver.com)</author>
	/// <date>2016-9-24</date>
	/// <remarks>
	/// �Ϲ� �α׸� ǥ�� ��Ʈ���� �α� ���Ͽ� ����Ѵ�
	/// </remarks>
	public static void l(String str) {
		getLogFile();
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
	public static void e(String str) {
		getLogFile();
		str = setFormat(str, LOG_ERROR);
		System.err.println(str);
		try {
			fw.write(str);
			fw.flush();
		} catch (IOException | NullPointerException e) {
		}
	}

	/// <summary>
	/// �԰� ���� ���
	/// </summary>
	/// <author>BoxFox (rlatjdfo112@naver.com)</author>
	/// <date>2016-9-24</date>
	/// <remarks>
	/// �԰� ���� �α׸� ����Ѵ�.
	/// </remarks>
	public static void printClear(String str) {
		getLogFile();
		System.out.print(str);
		try {
			fw.write(str);
			fw.flush();
		} catch (IOException | NullPointerException e) {
		}
	}

}
