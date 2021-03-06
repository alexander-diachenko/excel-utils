package excel.model;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.util.List;

/**
 * @author Alexander Diachenko.
 */
public interface Excel {
    /**
     * Return list representation of excel file.
     *
     * @param path Path to excel file.
     * @return List representation of excel file.
     * @throws IOException            Throws IOException if file read failed.
     * @throws InvalidFormatException Throws InvalidFormatException if it is not excel file(.xls or .xlsx).
     */
    List<List<Object>> read(final String path) throws IOException, InvalidFormatException;

    /**
     * Write List<List<>> to excel file.
     *
     * @param table Data in List<List<>>.
     * @param path  Path to new excel file.
     * @throws IOException Throws IOException if file write failed.
     */
    void write(final List<List<Object>> table, final String path) throws IOException;

    /**
     * Return sheet column count.
     *
     * @param sheet sheet of excel file.
     * @return column count.
     */
    int getColumnCount(final Sheet sheet);

    /**
     * Auto resize excel table. If column is empty - hide it
     *
     * @param sheet of table
     */
    void autoResizeSheet(final Sheet sheet);
}
