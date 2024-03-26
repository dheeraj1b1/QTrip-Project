package qtriptest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;


public class DP {
    // TODO: use correct annotation to connect the Data Provider with your Test Cases
    @DataProvider(name = "testData")
    public Object[][] dpMethod(Method method) throws IOException {
        List<List<Object>> outputList = new ArrayList<>();
        int rowIndex = 0;
        int cellIndex = 0;
    
        // Load the Excel file from the resources folder
       // String excelFilePath = "src/test/resources/DatasetsforQTrip.xlsx";
       String currentDir = System.getProperty("user.dir");
       String testDataExcelPath = currentDir + "/src/test/resources/";
       String fileName = testDataExcelPath + "DatasetsforQTrip.xlsx";
        FileInputStream excelFile = new FileInputStream(new File(fileName));
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
            // Use the method name to get the corresponding sheet
            Sheet selectedSheet = workbook.getSheet("TestCase01");
    
            Iterator<Row> iterator = selectedSheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                List<Object> innerList = new ArrayList<>();
    
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
    
                    if (rowIndex > 0 && cellIndex > 0) {
                        if (cell.getCellType() == CellType.STRING) {
                            innerList.add(cell.getStringCellValue());
                        } else if (cell.getCellType() == CellType.NUMERIC) {
                            innerList.add(cell.getNumericCellValue());
                        }
                    }
                    cellIndex = cellIndex + 1;
                }
    
                rowIndex = rowIndex + 1;
                cellIndex = 0;
    
                if (innerList.size() > 0) {
                    outputList.add(innerList);
                }
            }
        }
    
        excelFile.close();
    
        Object[][] objectArray = outputList.stream()
                .map(u -> u.toArray(new Object[0]))
                .toArray(Object[][]::new);
        return objectArray;
    }

    public Object[][] dpMethod(String methodName) {
        return null;
    }

    @DataProvider(name = "testData2")
    public Object[][] dpMethod2(Method method) throws IOException {
        List<List<Object>> outputList = new ArrayList<>();
        int rowIndex = 0;
        int cellIndex = 0;
    
        // Load the Excel file from the resources folder
       // String excelFilePath = "src/test/resources/DatasetsforQTrip.xlsx";
       String currentDir = System.getProperty("user.dir");
       String testDataExcelPath = currentDir + "/src/test/resources/";
       String fileName = testDataExcelPath + "DatasetsforQTrip.xlsx";
        FileInputStream excelFile = new FileInputStream(new File(fileName));
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
            // Use the method name to get the corresponding sheet
            Sheet selectedSheet = workbook.getSheet("TestCase02");
    
            Iterator<Row> iterator = selectedSheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                List<Object> innerList = new ArrayList<>();
    
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
    
                    if (rowIndex > 0 && cellIndex > 0) {
                        if (cell.getCellType() == CellType.STRING) {
                            innerList.add(cell.getStringCellValue());
                        } else if (cell.getCellType() == CellType.NUMERIC) {
                            innerList.add(cell.getNumericCellValue());
                        }
                    }
                    cellIndex = cellIndex + 1;
                }
    
                rowIndex = rowIndex + 1;
                cellIndex = 0;
    
                if (innerList.size() > 0) {
                    outputList.add(innerList);
                }
            }
        }
    
        excelFile.close();
    
        Object[][] objectArray = outputList.stream()
                .map(u -> u.toArray(new Object[0]))
                .toArray(Object[][]::new);
        return objectArray;
    }

    public Object[][] dpMethod2(String methodName) {
        return null;
    }
    


    @DataProvider(name = "testData3")
    public Object[][] dpMethod3(Method method) throws IOException {
        List<List<Object>> outputList = new ArrayList<>();
        int rowIndex = 0;
        int cellIndex = 0;
    
        // Load the Excel file from the resources folder
       // String excelFilePath = "src/test/resources/DatasetsforQTrip.xlsx";
       String currentDir = System.getProperty("user.dir");
       String testDataExcelPath = currentDir + "/src/test/resources/";
       String fileName = testDataExcelPath + "DatasetsforQTrip.xlsx";
        FileInputStream excelFile = new FileInputStream(new File(fileName));
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
            // Use the method name to get the corresponding sheet
            Sheet selectedSheet = workbook.getSheet("TestCase03");
    
            Iterator<Row> iterator = selectedSheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                List<Object> innerList = new ArrayList<>();
    
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
    
                    if (rowIndex > 0 && cellIndex > 0) {
                        if (cell.getCellType() == CellType.STRING) {
                            innerList.add(cell.getStringCellValue());
                        } else if (cell.getCellType() == CellType.NUMERIC) {
                            innerList.add(cell.getNumericCellValue());
                        }
                    }
                    cellIndex = cellIndex + 1;
                }
    
                rowIndex = rowIndex + 1;
                cellIndex = 0;
    
                if (innerList.size() > 0) {
                    outputList.add(innerList);
                }
            }
        }
    
        excelFile.close();
    
        Object[][] objectArray = outputList.stream()
                .map(u -> u.toArray(new Object[0]))
                .toArray(Object[][]::new);
        return objectArray;
    }

    public Object[][] dpMethod3(String methodName) {
        return null;
    }


    @DataProvider(name = "testData4")
public Object[][] dpMethod4(Method method) throws IOException {
    List<List<Object>> outputList = new ArrayList<>();
    int rowIndex = 0;
    int cellIndex = 0;

    // Load the Excel file from the resources folder
    String currentDir = System.getProperty("user.dir");
    String testDataExcelPath = currentDir + "/src/test/resources/";
    String fileName = testDataExcelPath + "DatasetsforQTrip.xlsx";
    FileInputStream excelFile = new FileInputStream(new File(fileName));
    try (Workbook workbook = new XSSFWorkbook(excelFile)) {
        // Use the method name to get the corresponding sheet
        Sheet selectedSheet = workbook.getSheet("TestCase04");

        Iterator<Row> iterator = selectedSheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            List<Object> innerList = new ArrayList<>();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (rowIndex > 0 && cellIndex > 0) {
                    if (cell.getCellType() == CellType.STRING) {
                        innerList.add(cell.getStringCellValue());
                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        innerList.add(cell.getNumericCellValue());
                    }
                }
                cellIndex = cellIndex + 1;
            }

            rowIndex = rowIndex + 1;
            cellIndex = 0;

            if (innerList.size() > 0) {
                outputList.add(innerList);
            }
        }
    }

    excelFile.close();

    Object[][] objectArray = outputList.stream()
            .map(u -> u.toArray(new Object[0]))
            .toArray(Object[][]::new);
    return objectArray;
}

}
