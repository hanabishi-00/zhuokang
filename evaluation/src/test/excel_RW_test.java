package test;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class excel_RW_test {

    /*public static void main(String[] args){
        try {
            readExcel("./src/test/测试表格.xlsx");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 读取excel
     */

    public static void readExcel(String past){
        String extString = past.substring(past.lastIndexOf("."));
        if (".xls".equals(extString)){
            readExcel_xls(past);
        }
        else if (".xlsx".equals(extString)){
            readExcel_xlsx(past);
        }
        else {
            System.out.print("Wrong file format!");
        }
    }

    public static void readExcel_xls(String past) {
        InputStream inputStream = null;
        HSSFWorkbook xssfWorkbook = null;
        try {
            inputStream = new FileInputStream(past);
            //POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            xssfWorkbook = new HSSFWorkbook(inputStream);
            //获取sheet的个数
            int numberOfSheets = xssfWorkbook.getNumberOfSheets();
            //获取指定的sheet
            System.out.print("numberOfSheets: "+numberOfSheets+"\n");

            /*//通过指定名称获取
            XSSFSheet sheet = xssfWorkbook.getSheet("笔记本");*/
            //通过下标获取
            HSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);
            if (sheetAt != null) {
                //最后一行有数据的
                int lastRowNum = sheetAt.getLastRowNum();
                HSSFRow row;
                short lastCellNum;
                HSSFCell cell;

                for (int i = 0; i <= lastRowNum; i++) {
                    //获取指定行
                    row = sheetAt.getRow(i);
                    if (row == null) {
                        continue;
                    }
                    //最后一列有数据的
                    lastCellNum = row.getLastCellNum();
                    for (int j = 0; j <= lastCellNum; j++) {
                        cell = row.getCell(j);
                        if (cell == null) {
                            continue;
                        }
                        //数据类型
                        CellType cellType = cell.getCellType();
                        //字符串
                        if (CellType.STRING == cellType) {
                            System.out.println(cell.toString());
                        }
                        //数字
                        else if (CellType.NUMERIC == cellType) {
                            try {
                                System.out.println(cell.getDateCellValue());
                            } catch (Exception e) {
                                System.out.println(cell.toString());
                            }
                        }
                        //……
                        else {
                            System.out.println(cell.toString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readExcel_xlsx(String past) {
        InputStream inputStream = null;
        XSSFWorkbook xssfWorkbook = null;
        try {
            inputStream = new FileInputStream(past);
            //POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            xssfWorkbook = new XSSFWorkbook(inputStream);
            //获取sheet的个数
            int numberOfSheets = xssfWorkbook.getNumberOfSheets();
            //获取指定的sheet
            System.out.print("numberOfSheets: "+numberOfSheets+"\n");

            /*//通过指定名称获取
            XSSFSheet sheet = xssfWorkbook.getSheet("笔记本");*/
            //通过下标获取
            XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);
            if (sheetAt != null) {
                //最后一行有数据的
                int lastRowNum = sheetAt.getLastRowNum();
                XSSFRow row;
                short lastCellNum;
                XSSFCell cell;

                for (int i = 0; i <= lastRowNum; i++) {
                    //获取指定行
                    row = sheetAt.getRow(i);
                    if (row == null) {
                        continue;
                    }
                    //最后一列有数据的
                    lastCellNum = row.getLastCellNum();
                    for (int j = 0; j <= lastCellNum; j++) {
                        cell = row.getCell(j);
                        if (cell == null) {
                            continue;
                        }
                        //数据类型
                        CellType cellType = cell.getCellType();
                        //字符串
                        if (CellType.STRING == cellType) {
                            System.out.println(cell.toString());
                        }
                        //数字
                        else if (CellType.NUMERIC == cellType) {
                            try {
                                System.out.println(cell.getDateCellValue());
                            } catch (Exception e) {
                                System.out.println(cell.toString());
                            }
                        }
                        //……
                        else {
                            System.out.println(cell.toString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
