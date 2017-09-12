package com.dms.api.xlsx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.boxfox.dms.algorithm.AES256;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.QueryUtils;
import com.dms.utilities.database.SafeResultSet;

//2016.03.12
public class ResidualDownload {
    private static final String FORMAT_XLSX_FILE = "잔류조사포맷.xlsx";
    private static final String FILE = "files/";
    private static final String[] RESIDUAL_TYPE = new String[]{"금요귀가", "토요귀가", "토요귀사", "잔류"};

    public String readExcel(String date) {
        HashMap<String, String> map = new HashMap<String, String>();
        initResidualMaps(date, map);
        try {
            File xlsxFile = getFile();
            XSSFWorkbook workbook = processXlsx(xlsxFile, map);
            File file = new File(FILE + date + ".xlsx");
            FileOutputStream fout = new FileOutputStream(file);
            workbook.write(fout);
            return FILE + date + ".xlsx";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    private XSSFWorkbook processXlsx(File input, HashMap<String, String> map) throws IOException {
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
                        String sNum = (String.valueOf((int) cell.getNumericCellValue()));
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

    private void initResidualMaps(String date, HashMap<String, String> map) {
        List<com.dms.api.xlsx.ResidualData> list = new ArrayList<com.dms.api.xlsx.ResidualData>();
        try {
            SafeResultSet rs = DataBase.getInstance().executeQuery("SELECT student_data.uid, number, value FROM student_data right join stay_apply_default on student_data.uid = stay_apply_default.uid");
            while (rs.next()) {
                com.dms.api.xlsx.ResidualData data = new com.dms.api.xlsx.ResidualData();
                data.setId(rs.getString("uid"));
                String uid = AES256.decrypt(rs.getString("number"));
                if (uid == null || uid.length() == 0) continue;
                data.setNumber(Integer.valueOf(uid));
                data.setResidualDefault(rs.getInt("value"));
                list.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (com.dms.api.xlsx.ResidualData user : list) {
            try {
                int type;
                String query = QueryUtils.queryBuilder("SELECT value FROM stay_apply where uid='", user.getId(), "'");
                SafeResultSet rs = DataBase.getInstance().executeQuery(query);
                if (rs.next()) type = rs.getInt(1);
                else type = user.getResidualDefault();
                map.put(user.getNumber() + "", RESIDUAL_TYPE[type - 1]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}