import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 13.06.2017.
 */
public class ExcelImpl implements Excel {

    @Override
    public List<List<Object>> read(final String path) throws IOException, InvalidFormatException {
        final List<List<Object>> table = new ArrayList<>();
        final Workbook workbook = getWorkbook(path);
        final Sheet sheet = workbook.getSheetAt(0);
        final Iterator rows = sheet.rowIterator();
        while (rows.hasNext()) {
            final List<Object> raw = new ArrayList<>();
            final Row row = (Row) rows.next();
            final short lastCellNum = row.getLastCellNum();
            int index = 0;
            while (index < lastCellNum) {
                final Cell cell = row.getCell(index, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                raw.add(getFormattedCell(cell));
                index++;
            }
            table.add(raw);
        }
        return table;
    }

    @Override
    public void write(final List<List<Object>> table, final String path) throws IOException {
        final Workbook workbook = new XSSFWorkbook();
        final Sheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("New sheet"));
        int rowNum = 0;
        for (List<Object> rows : table) {
            final Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object obj : rows) {
                final Cell cell = row.createCell(colNum++);
                cell.setCellValue(String.valueOf(obj));
            }
        }
        try (final FileOutputStream outputStream = new FileOutputStream(new File(path))) {
            workbook.write(outputStream);
        }
    }

    private String getFormattedCell(final Cell cell) {
        final DataFormatter df = new DataFormatter();
        return df.formatCellValue(cell);
    }

    private Workbook getWorkbook(final String path) throws IOException, InvalidFormatException {
        try (final FileInputStream is = new FileInputStream(new File(path))) {
            return WorkbookFactory.create(is);
        }
    }
}
