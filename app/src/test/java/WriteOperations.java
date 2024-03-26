// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.util.Iterator;
// import org.apache.poi.ss.usermodel.Cell;
// import org.apache.poi.ss.usermodel.CellType;
// import org.apache.poi.ss.usermodel.Row;
// import org.apache.poi.xssf.usermodel.XSSFCell;
// import org.apache.poi.xssf.usermodel.XSSFRow;
// import org.apache.poi.xssf.usermodel.XSSFSheet;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// public class WriteOperations {

//     public void writeInToExcel(String filePath, String sheetName, Object[][] dataToWrite) {
//         System.out.println("*Adding new row to: " + sheetName + "*");

//         File fileName = new File(filePath);

//         try {
//             FileInputStream file = new FileInputStream(fileName);
//             XSSFWorkbook workbook = new XSSFWorkbook(file);
//             XSSFSheet sheet = workbook.getSheet(sheetName);

//             int rowCount = sheet.getLastRowNum();

//             for (Object[] data : dataToWrite) {
//                 XSSFRow row = sheet.createRow(++rowCount);
//                 int columnCount = 0;

//                 for (Object info : data) {
//                     XSSFCell cell = row.createCell(columnCount++);
//                     if (info instanceof String) {
//                         cell.setCellValue((String) info);
//                     } else if (info instanceof Integer) {
//                         cell.setCellValue((Integer) info);
//                     } else if (info instanceof Double) {
//                         cell.setCellValue((Double) info);
//                     }
//                 }
//             }

//             file.close();
//             FileOutputStream os = new FileOutputStream(fileName);
//             workbook.write(os);
//             workbook.close();
//             os.close();

//             System.out.println("Excel file has been updated successfully.\n");

//         } catch (IOException e) {
//             System.err.println("Exception while updating an existing excel file.\n");
//             e.printStackTrace();
//         }
//     }

//     public void updateCellValue(String filePath, String sheetName, String data, int rowIndex,
//             int colIndex) {
//         System.out.println("*Updating data in cell of row no. " + rowIndex + " col no. " + colIndex
//                 + " of: " + sheetName + "*");

//         File fileName = new File(filePath);

//         try {
//             FileInputStream file = new FileInputStream(fileName);
//             XSSFWorkbook workbook = new XSSFWorkbook(file);
//             XSSFSheet sheet = workbook.getSheet(sheetName);

//             XSSFCell cellToUpdate = sheet.getRow(rowIndex).getCell(colIndex);
//             cellToUpdate.setCellValue(data);

//             file.close();
//             FileOutputStream os = new FileOutputStream(fileName);
//             workbook.write(os);
//             workbook.close();
//             os.close();

//             System.out.println("Cell value has been updated successfully.\n");

//         } catch (IOException e) {
//             System.err.println("Exception while updating an existing excel file.\n");
//             e.printStackTrace();
//         }
//     }

//     // Add other methods for additional operations if needed
// }