package com.dms.api.xlsx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dms.account_manager.AdminManager;
import com.dms.crypto.AES256;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.routing.Route;
import com.google.common.net.HttpHeaders;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/extension/download/12", method={HttpMethod.GET})
public class ExtensionDownloadRouter12 implements Handler<RoutingContext> {
	private final String FORMAT_XLSX_FILE = "잔류조사포맷.xlsx";
    private final String FILE_DIR = "files/";
    private final String[] CLASS_NAMES = {"", "가온실", "나온실", "다온실", "라온실", "3층 독서실", "4층 독서실", "5층 열린교실"};
	private XSSFWorkbook wb;

	@Override
	public void handle(RoutingContext context) {
		if(AdminManager.isAdmin(context)) {
			DataBase database = DataBase.getInstance();
			SafeResultSet resultSet;
			SafeResultSet extensionStateResultSet;

			File file = getFile();

			try {
				wb = new XSSFWorkbook(new FileInputStream(file));

				XSSFSheet sheet = wb.getSheetAt(0);

				for (Row row : sheet) {
					for (Cell cell : row) {
						switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								StringBuilder sb = new StringBuilder(Double.toString(cell.getNumericCellValue()));

								String studentNumber = AES256.encrypt(sb.toString().substring(0, 4));
								int classId = 0;

								resultSet = database.executeQuery("SELECT * FROM student_data WHERE number='", studentNumber, "'");

								if (resultSet.next()) {
									String uid = resultSet.getString("uid");
									extensionStateResultSet = database.executeQuery("SELECT * FROM extension_apply_12 WHERE uid='", uid, "'");
									if (extensionStateResultSet.next()) {
										classId = extensionStateResultSet.getInt("class");
									}
								}

								XSSFRow studentRow = sheet.getRow(cell.getRowIndex());
								XSSFCell extensionStateCell = studentRow.getCell(cell.getColumnIndex() + 2);
								extensionStateCell.setCellValue(CLASS_NAMES[classId]);

								break;
						}
					}
				}

				FileOutputStream xlsToSave = new FileOutputStream(FILE_DIR + "연장신청.xlsx");
				wb.write(xlsToSave);
				xlsToSave.close();

				String fileName = new String("연장신청.xlsx".getBytes("UTF-8"), "ISO-8859-1");
				context.response()
					.putHeader(HttpHeaders.CONTENT_DISPOSITION, "filename=" + fileName)
					.sendFile(FILE_DIR + "연장신청.xlsx");
				context.response().close();
			} catch (IOException | SQLException e) {
				context.response().setStatusCode(500).end();
				context.response().close();
			}
		}else{
			context.response().setStatusCode(400).end();
			context.response().end("You are not admin!");
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
}
