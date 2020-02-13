package util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class excelUtil {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /*public static void main(String[] args){
        readExcel("D:/Jerry/H&IE/CSLee/华东院项目/08-12月开发/评价模型测点信息/水泵水轮机.xlsx");
    }*/

    public static List readExcel(String past){
        String extString = past.substring(past.lastIndexOf("."));
        if (".xls".equals(extString)){
            return readExcel_xls(past);
        }
        else if (".xlsx".equals(extString)){
            return readExcel_xlsx(past);
        }
        else {
            System.out.print("Wrong file format!");
            return null;
        }
    }

    public static List readExcel(String past, int sheet_id){
        String extString = past.substring(past.lastIndexOf("."));
        if (".xls".equals(extString)){
            return readExcel_xls(past, sheet_id);
        }
        else if (".xlsx".equals(extString)){
            return readExcel_xlsx(past, sheet_id);
        }
        else {
            System.out.print("Wrong file format!");
            return null;
        }
    }

    public static List readExcel_xls(String past) {
        List<List> outerList=new ArrayList<>();
        InputStream inputStream = null;
        HSSFWorkbook xssfWorkbook = null;
        try {
            inputStream = new FileInputStream(past);
            //POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            xssfWorkbook = new HSSFWorkbook(inputStream);
            /*//获取sheet的个数
            int numberOfSheets = xssfWorkbook.getNumberOfSheets();
            //获取指定的sheet
            System.out.print("numberOfSheets: "+numberOfSheets+"\n");*/
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
                    List innerList=new ArrayList();
                    //最后一列有数据的
                    lastCellNum = row.getLastCellNum();
                    for (int j = 0; j <= lastCellNum; j++) {
                        cell = row.getCell(j);
                        if (cell == null) {
                            continue;
                        }
                        String cellcontent= cell.toString();
                        if(cellcontent.isEmpty()){
                            continue;
                        }
                        innerList.add(cellcontent);
                        //System.out.println(cell.toString());

                        /*//数据类型
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
                        }*/
                    }
                    outerList.add(i, innerList);
                }
            }
            return outerList;
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
        return null;
    }

    public static List readExcel_xls(String past, int sheet_id) {
        List<List> outerList=new ArrayList<>();
        InputStream inputStream = null;
        HSSFWorkbook xssfWorkbook = null;
        try {
            inputStream = new FileInputStream(past);
            //POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            xssfWorkbook = new HSSFWorkbook(inputStream);
            /*//获取sheet的个数*/
            int numberOfSheets = xssfWorkbook.getNumberOfSheets();
            /*//获取指定的sheet
            System.out.print("numberOfSheets: "+numberOfSheets+"\n");*/
            /*//通过指定名称获取
            XSSFSheet sheet = xssfWorkbook.getSheet("笔记本");*/
            //通过下标获取
            if (sheet_id<numberOfSheets) {
                HSSFSheet sheetAt = xssfWorkbook.getSheetAt(sheet_id);

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
                        List innerList = new ArrayList();
                        //最后一列有数据的
                        lastCellNum = row.getLastCellNum();
                        for (int j = 0; j <= lastCellNum; j++) {
                            cell = row.getCell(j);
                            if (cell == null) {
                                continue;
                            }
                            String cellcontent = cell.toString();
                            if (cellcontent.isEmpty()) {
                                continue;
                            }
                            innerList.add(cellcontent);
                            //System.out.println(cell.toString());

                        /*//数据类型
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
                        }*/
                        }
                        outerList.add(i, innerList);
                    }
                }
                return outerList;
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
        return null;
    }

    public static List readExcel_xlsx(String past) {
        List<List> outerList=new ArrayList<>();
        InputStream inputStream = null;
        XSSFWorkbook xssfWorkbook = null;
        try {
            inputStream = new FileInputStream(past);
            //POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            xssfWorkbook = new XSSFWorkbook(inputStream);
            /*//获取sheet的个数
            int numberOfSheets = xssfWorkbook.getNumberOfSheets();
            //获取指定的sheet
            System.out.print("numberOfSheets: "+numberOfSheets+"\n");*/
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
                    List innerList=new ArrayList();
                    //最后一列有数据的
                    lastCellNum = row.getLastCellNum();
                    for (int j = 0; j <= lastCellNum; j++) {
                        cell = row.getCell(j);
                        if (cell == null) {
                            continue;
                        }
                        String cellcontent= cell.toString();
                        if(cellcontent.isEmpty()){
                            continue;
                        }
                        innerList.add(cellcontent);
                        //System.out.println(cell.toString());

                        /*//数据类型
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
                        }*/
                    }
                    outerList.add(i, innerList);
                }
            }
            return outerList;
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
        return null;
    }

    public static List readExcel_xlsx(String past, int sheet_id) {
        List<List> outerList=new ArrayList<>();
        InputStream inputStream = null;
        XSSFWorkbook xssfWorkbook = null;
        try {
            inputStream = new FileInputStream(past);
            //POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            xssfWorkbook = new XSSFWorkbook(inputStream);
            //获取sheet的个数
            int numberOfSheets = xssfWorkbook.getNumberOfSheets();
            /*//获取指定的sheet
            System.out.print("numberOfSheets: "+numberOfSheets+"\n");*/
            /*//通过指定名称获取
            XSSFSheet sheet = xssfWorkbook.getSheet("笔记本");*/
            //通过下标获取
            if (sheet_id<numberOfSheets) {
                XSSFSheet sheetAt = xssfWorkbook.getSheetAt(sheet_id);
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
                        List innerList = new ArrayList();
                        //最后一列有数据的
                        lastCellNum = row.getLastCellNum();
                        for (int j = 0; j <= lastCellNum; j++) {
                            cell = row.getCell(j);
                            if (cell == null) {
                                continue;
                            }
                            String cellcontent = cell.toString();
                            if (cellcontent.isEmpty()) {
                                continue;
                            }
                            innerList.add(cellcontent);
                            //System.out.println(cell.toString());

                        /*//数据类型
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
                        }*/
                        }
                        outerList.add(i, innerList);
                    }
                }
                return outerList;
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
        return null;
    }

    public static void writeExcel(List<String[]> dataList, int columnCount, String finalXlsxPath){
        OutputStream out = null;
        try {
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet;
            sheet = workBook.getSheetAt(0);

            /**
             * 删除原有数据，除了属性列
            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);*/

            /**
             * 往Excel中写新数据
             */
            for (int j = 0; j < dataList.size(); j++) {

                Row row = sheet.createRow(j );
                // 得到要插入的每一条记录
                for (int k = 0; k < columnCount; k++) {
                    // 在一行内循环
                    String content=dataList.get(j)[k];
                    Cell place = row.createCell(k);
                    place.setCellValue(content);
                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

    /**
     * 判断Excel的版本,获取Workbook
     * @param file
     * @return workbook
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}