package com.boxfox.dsm.xlsx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.boxfox.dms.mapper.UserMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boxfox.dms.users.dto.UserDataDTO;

//2016.03.12
//need refactoring

@Repository
public class ResidualDownLoad {
	private static final String FORMAT_XLSX_FILE = "ÀÜ·ùÁ¶»çÆ÷¸Ë.xlsx";
	private static final String [] RESIDUAL_TYPE = new String[]{"±Ý¿ä±Í°¡", "Åä¿ä±Í°¡", "Åä¿ä±Í»ç", "ÀÜ·ù"};

	@Autowired
	private SqlSession sqlSession;

	private HashMap<String, String> map = new HashMap();

	public String readExcel(String path) {
		initResidualMaps();
		File xlsxFile = new File(path + FORMAT_XLSX_FILE);
		try {
			XSSFWorkbook workbook = processXlsx(xlsxFile);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String fileName = "Residual" + sdf.format(now) + ".xlsx";
			FileOutputStream fileoutputstream = new FileOutputStream(path + fileName);
			workbook.write(fileoutputstream);
			fileoutputstream.close();
			return fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private XSSFWorkbook processXlsx(File input) throws IOException{
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(input));
		int rowindex = 0;
		int columnindex = 0;
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		for (rowindex = 1; rowindex < rows; rowindex++) {
			XSSFRow row = sheet.getRow(rowindex);
			if (row != null) {
				int cells = row.getPhysicalNumberOfCells();
				for (columnindex = 0; columnindex <= cells; columnindex++) {
					XSSFCell cell = row.getCell(columnindex);
						if (cell != null && cell.getCellType() == 0) {
							String sNum = new StringBuilder(String.valueOf(cell.getNumericCellValue())).toString();
							if (sNum != null) {
								cell = row.getCell(++columnindex);
								if (cell.getCellType() == 1) {
									String type = map.get(sNum);
									if (type != null) {
										cell = row.getCell(++columnindex);
										cell.setCellValue(type);
									}
								}
							}
					}
				}
			}
		}
		return workbook;
	}

	private void initResidualMaps() {
			UserMapper userMapper = (UserMapper) sqlSession.getMapper(UserMapper.class);
			List<UserDataDTO> list = userMapper.residual();

			for (int i = 0; i < list.size(); i++) {
				String typeStr = null;
				Integer type = userMapper.residualAtWeek(list.get(i).getId());
				if (type == null) {
					type = list.get(i).getResidualDefault();
				}
				typeStr = RESIDUAL_TYPE[type-1];
				map.put(list.get(i).getNumber() + "", typeStr);
			}
	}

}