package com.dms.planb.template_routers.xlsx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.boxfox.dms.algorithm.AES256;
import org.boxfox.dms.util.AdminManager;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/report/download", method={HttpMethod.GET})
public class ReportDownloadRouter implements Handler<RoutingContext> {
	private final String FORMAT_XLSX_FILE = "시설고장신고포맷.xlsx";
    private final String FILE_DIR = "files/";
    private XSSFWorkbook wb;
	
	@Override
	public void handle(RoutingContext ctx) {
		if(AdminManager.isAdmin(ctx)) {
			DataBase database = DataBase.getInstance();
			SafeResultSet reportFacilityResultSet;
			
			File file = getFile();
			
			try {
				wb = new XSSFWorkbook(new FileInputStream(file));
				
				XSSFSheet sheet = wb.getSheetAt(0);
				int rowCount = 0;
				XSSFRow titleRow = sheet.createRow(rowCount++);
				titleRow.createCell(0).setCellValue("호실");
				titleRow.createCell(1).setCellValue("작성자");
				titleRow.createCell(2).setCellValue("제목");
				titleRow.createCell(3).setCellValue("신고 내용");
				titleRow.createCell(4).setCellValue("신고 날짜");
				
				reportFacilityResultSet = database.executeQuery("SELECT * FROM facility_report");
				while(reportFacilityResultSet.next()) {
					XSSFRow row = sheet.createRow(rowCount++);
					row.createCell(0).setCellValue(reportFacilityResultSet.getString("room") + "호");
					row.createCell(1).setCellValue(reportFacilityResultSet.getString("writer"));
					row.createCell(2).setCellValue(reportFacilityResultSet.getString("title"));
					row.createCell(3).setCellValue(reportFacilityResultSet.getString("content"));
					row.createCell(4).setCellValue(reportFacilityResultSet.getString("write_date").split(" ")[0]);
				}
				
				FileOutputStream xlsToSave = new FileOutputStream(FILE_DIR + "시설고장신고.xlsx");
				wb.write(xlsToSave);
				xlsToSave.close();
				
				ctx.response().setStatusCode(200);
				ctx.response().sendFile(FILE_DIR + "시설고장신고.xlsx");
				ctx.response().close();
			} catch (IOException | SQLException e) {
				ctx.response().setStatusCode(500).end();
				ctx.response().close();
			}
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
