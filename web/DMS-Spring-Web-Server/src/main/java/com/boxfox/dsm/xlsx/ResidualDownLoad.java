package com.boxfox.dsm.xlsx;

import java.io.File;
import java.io.FileInputStream;
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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.boxfox.dms.secure.AES256;

//2016.03.12
//need refactoring

public class ResidualDownLoad
{
  private HashMap<String, String> map = new HashMap();
  
  public String readExcel(String path)
  {
    setResidualMaps();
    File f = new File(path + "????????????.xlsx");
    try
    {
      FileInputStream fin = new FileInputStream(f);
      XSSFWorkbook workbook = new XSSFWorkbook(fin);
      int rowindex = 0;
      int columnindex = 0;
      XSSFSheet sheet = workbook.getSheetAt(0);
      int rows = sheet.getPhysicalNumberOfRows();
      for (rowindex = 1; rowindex < rows; rowindex++)
      {
        XSSFRow row = sheet.getRow(rowindex);
        if (row != null)
        {
          int cells = row.getPhysicalNumberOfCells();
          for (columnindex = 0; columnindex <= cells; columnindex++)
          {
            XSSFCell cell = row.getCell(columnindex);
            String value = "";
            if (cell != null) {
              if (cell.getCellType() == 0)
              {
                String sNum = new StringBuilder(String.valueOf(cell.getNumericCellValue())).toString();
                sNum = checkStudentNumber(sNum);
                if (sNum != null)
                {
                  cell = row.getCell(++columnindex);
                  if (cell.getCellType() == 1)
                  {
                    String name = cell.getStringCellValue();
                    String type = checkStudent(sNum, name);
                    if (type != null)
                    {
                      cell = row.getCell(++columnindex);
                      cell.setCellValue(type);
                    }
                  }
                }
              }
            }
          }
        }
      }
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Date now = new Date();
      String fileName = "Residual" + sdf.format(now) + ".xlsx";
      FileOutputStream fileoutputstream = new FileOutputStream(path + fileName);
      workbook.write(fileoutputstream);
      fileoutputstream.close();
      return fileName;
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  private String checkStudentNumber(String sNum)
  {
    int year = Calendar.getInstance().get(1);
    if (sNum.length() > 0)
    {
      sNum = sNum.substring(0, 1);
      switch (Integer.valueOf(sNum).intValue())
      {
      case 1: 
        sNum = year;
        break;
      case 2: 
        sNum = year - 1;
        break;
      case 3: 
        sNum = year - 2;
        break;
      default: 
        sNum = null;
      }
    }
    return sNum;
  }
  
  private String checkStudent(String sNum, String name)
  {
    String key = sNum + name;
    return (String)this.map.get(key);
  }
  
  private void setResidualMaps()
  {
    db.accept("user");
    try
    {
      ResultSet rs = db.st.executeQuery("select * from user.names");
      ArrayList<User> list = new ArrayList();
      while (rs.next()) {
        list.add(new User(rs.getString("name"), rs.getString("id"), rs.getString("year")));
      }
      for (int i = 0; i < list.size(); i++)
      {
        String type = "????????";
        ResultSet rss = db.st
          .executeQuery("select type from checkin.residual where id='" + ((User)list.get(i)).getId() + "'");
        if (rss.next()) {
          switch (rss.getInt(1))
          {
          case 1: 
            type = "????????";
            break;
          case 2: 
            type = "????????";
            break;
          case 3: 
            type = "????????";
            break;
          case 4: 
            type = "????";
          }
        }
        this.map.put(((User)list.get(i)).getYear() + AES256.decrypt(((User)list.get(i)).getName()), type);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  
  private class User
  {
    private String name;
    private String id;
    private String year;
    
    User(String name, String id, String year)
    {
      this.name = name;
      this.id = id;
      this.year = year;
    }
    
    private String getName()
    {
      return this.name;
    }
    
    private String getId()
    {
      return this.id;
    }
    
    private String getYear()
    {
      return this.year;
    }
  }
}