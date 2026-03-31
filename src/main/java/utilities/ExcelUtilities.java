package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities{

    private Workbook workbook;
    private Sheet sheet;
    private String filePath;

    //Constructor for Excel Operations
    public ExcelUtilities(String filePath, String sheetName) throws IOException{
          this.filePath=filePath;
          FileInputStream fis = new FileInputStream(filePath);
          workbook = new XSSFWorkbook(fis);
          sheet = workbook.getSheet(sheetName);
    }

    //get RowCount
    public int getRowCount(){
       return sheet.getLastRowNum();
    }

    //get ColCount
    public int getColumnCount(){
        return sheet.getRow(0).getLastCellNum();
    }

    //get cell Data
    public String getCellData(int rowNum, int colNum){
         Row row = sheet.getRow(rowNum);
         Cell cell = row.getCell(colNum);

         if(cell==null) return "";

         switch(cell.getCellType()){
            case STRING :
                return cell.getStringCellValue();
            case NUMERIC :
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN :
                return String.valueOf(cell.getBooleanCellValue());
            default :
                return "";
         }
    }

    //set cell data
    public void setCellData(int rowNum, int colNum, String value) throws IOException{
        Row row = sheet.getRow(rowNum);
        if(row==null){
            row=sheet.createRow(rowNum);
        }
        Cell cell = row.getCell(colNum);
        if(cell==null){
            cell = row.createCell(colNum);
        }
        cell.setCellValue(value);
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
    }

    //close WorkBook
    public void close() throws IOException{
        workbook.close();
    }
}
