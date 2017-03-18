package com.dms.planb.action.extension;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;

import com.dms.planb.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/extension/download", method={HttpMethod.POST})
public class ExtensionDownloadRouter implements Handler<RoutingContext> {
	private static final String FORMAT_XLSX_FILE = "잔류조사포맷.xlsx";
    private static final String FILE_DIR = "files/";
    
	@Override
	public void handle(RoutingContext context) {
		DataBase database = DataBase.getInstance();
		
		context = PrecedingWork.putHeaders(context);
		
		File file = getFile();

		try {
			System.out.println(file.getPath());
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));

			XSSFSheet sheet = wb.getSheetAt(0);
			
			int cnt = 0;
			
			for (Row row : sheet) {
				for (Cell cell : row) {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						StringBuilder sb = new StringBuilder(Double.toString(cell.getNumericCellValue()));
						sb.insert(1, "0");
						
						int number = Integer.valueOf(sb.toString());
						String uid = database.executeQuery("SELECT * FROM student_data WHERE number=", number).getString("uid");
						int classId = database.executeQuery("SELECT * FROM extension_apply WHERE uid='", uid, "'").getInt("class");
						
						// 연장신청 안된 경우 수정
						
						String className = null;
						
						switch(classId) {
						case 1:
							className = "가온실";
							break;
						case 2:
							className = "나온실";
							break;
						case 3:
							className = "다온실";
							break;
						case 4:
							className = "라온실";
							break;
						case 5:
							className = "3층 독서실";
							break;
						case 6:
							className = "4층 독서실";
							break;
						case 7:
							className = "5층 열린교실";
							break;
						default:
							className = "미신청";
							break;
						}
						
						XSSFRow extensionStateRow = sheet.getRow(cell.getRowIndex());
						Cell extensionStateCell = extensionStateRow.getCell(cell.getColumnIndex() + 2);
						extensionStateCell.setCellValue(className);
						
						break;
					}
				}
			}
			
			FileOutputStream xlsToSave = new FileOutputStream(FILE_DIR + "잔류신청.xlsx");
			wb.write(xlsToSave);
			xlsToSave.close();
			
			context.response().setStatusCode(201);
			context.response().sendFile(FILE_DIR + "잔류신청.xlsx");
			context.response().close();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private File getFile() {
		File file = new File(FILE_DIR + FORMAT_XLSX_FILE);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }
}
