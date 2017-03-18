package com.dms.planb.action.extension;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;

import com.dms.planb.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/extension/download", method={HttpMethod.POST})
public class ExtensionDownloadRouter implements Handler<RoutingContext> {
	UserManager userManager;
	private static final String FORMAT_XLSX_FILE = "연장신청.xlsx";
    private static final String FILE = "files/";
    
    public ExtensionDownloadRouter() {
    	userManager = new UserManager();
    }
	
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		File file = getFile();
		
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
			
			XSSFSheet sheet = wb.getSheetAt(0);
			
			for(Row row : sheet) {
				for(Cell cell : row) {
					switch(cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						String number = cell.getStringCellValue();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private File getFile() {
        File file = new File(FILE + FORMAT_XLSX_FILE);
        try {
            if (!file.exists()) {
                file.mkdir();
                InputStream inputStream = getClass().getResourceAsStream(FILE + FORMAT_XLSX_FILE);
                OutputStream outStream = new FileOutputStream(file);
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = inputStream.read(buf)) > 0) {
                    outStream.write(buf, 0, len);
                }
                outStream.close();
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
