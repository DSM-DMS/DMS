package com.dms.planb.action.goingout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.boxfox.dms.algorithm.AES256;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.PrecedingWork;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/goingout/download", method = { HttpMethod.POST })
public class GoingoutDownloadRouter implements Handler<RoutingContext> {
	private final String FORMAT_XLSX_FILE = "잔류조사포맷.xlsx";
	private final String FILE_DIR = "files/";
	private XSSFWorkbook wb;

	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		SafeResultSet stayStateResultSet;
		SafeResultSet goingoutStateResultSet;
		AES256 aes = UserManager.getAES();
		String currentWeek = getCurrentWeek();
		
		
		File file = getFile();
		
		try {
			wb = new XSSFWorkbook(new FileInputStream(file));
			
			XSSFSheet sheet = wb.getSheetAt(0);
			
			for(Row row : sheet) {
				for(Cell cell : row) {
					switch(cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						StringBuilder sb = new StringBuilder(Double.toString(cell.getNumericCellValue()));
						
						String studentNumber = aes.encrypt(sb.toString().substring(0, 4));
						
						resultSet = database.executeQuery("SELECT * FROM student_data WHERE number='", studentNumber, "'");
						
						if(resultSet.next()) {
							String uid = resultSet.getString("uid");
							System.out.println(uid);
							System.out.println(currentWeek);
							stayStateResultSet = database.executeQuery("SELECT * FROM stay_apply WHERE uid='", uid, "' AND week='", currentWeek, "'");

							if (stayStateResultSet.next()) {
								// 잔류신청을 한 경우
								if (stayStateResultSet.getInt("value") == 4) {
									// 잔류일 때
									XSSFRow studentRow = sheet.getRow(cell.getRowIndex());
									XSSFCell goingoutStateCell = studentRow.getCell(cell.getColumnIndex() + 2);

									goingoutStateResultSet = database.executeQuery("SELECT * FROM goingout_apply WHERE uid='", uid, "'");
									if (!goingoutStateResultSet.next()) {
										// 외출신청 여부 없음
										goingoutStateCell.setCellValue("외출신청 여부 없음");
										continue;
									}

									boolean sat = goingoutStateResultSet.getBoolean("sat");
									boolean sun = goingoutStateResultSet.getBoolean("sun");

									if (sat && sun) {
										// 모두 외출
										goingoutStateCell.setCellValue("토요일, 일요일 외출");
									} else if (sat && (!sun)) {
										// 토요일만 외출
										goingoutStateCell.setCellValue("토요일 외출");
									} else if ((!sat) && sun) {
										// 일요일만 외출
										goingoutStateCell.setCellValue("일요일 외출");
									}
								} else {
									// 잔류가 아닐 때
									XSSFRow studentRow = sheet.getRow(cell.getRowIndex());
									studentRow.getCell(cell.getColumnIndex()).setCellValue("");
									studentRow.getCell(cell.getColumnIndex() + 1).setCellValue("");
								}
							} else {
								// 잔류신청 정보 없음
								XSSFRow studentRow = sheet.getRow(cell.getRowIndex());
								XSSFCell goingoutStateCell = studentRow.getCell(cell.getColumnIndex() + 2);
								
								goingoutStateCell.setCellValue("잔류신청 정보 없음");
							}
						}
						
						break;
					}
				}
			}
			
			FileOutputStream xlsToSave = new FileOutputStream(FILE_DIR + "외출신청.xlsx");
			wb.write(xlsToSave);
			xlsToSave.close();
			
			context.response().setStatusCode(201);
			context.response().sendFile(FILE_DIR + "외출신청.xlsx");
			context.response().close();
		} catch(IOException | SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
		}
	}

	private File getFile() {
		File file = new File(FILE_DIR + FORMAT_XLSX_FILE);
		if (!file.exists()) {
			file.mkdir();
		}
		return file;
	}

	private String getCurrentWeek() {
		Calendar calendar = Calendar.getInstance();

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int week = calendar.get(Calendar.WEEK_OF_MONTH);

		StringBuilder currentWeek = new StringBuilder();

		currentWeek.append(Integer.toString(year)).append("-0");
		currentWeek.append(Integer.toString(month)).append("-0");
		currentWeek.append(Integer.toString(week));

		return currentWeek.toString();
	}
}
