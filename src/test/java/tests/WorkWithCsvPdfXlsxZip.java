package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class WorkWithCsvPdfXlsxZip {

    @Test
    void CheckXlsxInZip() throws Exception {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("zip/files.zip")) {
            assertNotNull(is, "Архив files.zip не найден!");

            try (ZipInputStream zis = new ZipInputStream(is)) {
                ZipEntry entry;
                boolean foundXlsx = false;

                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().endsWith(".xlsx")) {
                        foundXlsx = true;

                        XLS xls = new XLS(zis);
                        var sheet = xls.excel.getSheetAt(0);

                        String value = sheet.getRow(4).getCell(0).getStringCellValue();

                        assertThat(value).isEqualTo("Coffee");

                        break;
                    }
                }
                assertTrue(foundXlsx, "В архиве нет Excel-файла!");
            }
        }
    }

    @Test
    void checkPdfInZip() throws Exception {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("zip/files.zip")) {
            assertNotNull(is, "Архив files.zip не найден!");

            try (ZipInputStream zis = new ZipInputStream(is)) {
                ZipEntry entry;
                boolean foundPdf = false;

                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().endsWith(".pdf")) {
                        foundPdf = true;

                        PDF pdf = new PDF(zis);

                        assertThat(pdf.text).contains("Congratulations");

                        break;
                    }
                }

                assertTrue(foundPdf, "В архиве нет PDF-файла!");
            }
        }
    }

    @Test
    void checkCsvInZip() throws Exception {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("zip/files.zip")) {
            assertNotNull(is, "Архив files.zip не найден!");

            try (ZipInputStream zis = new ZipInputStream(is)) {
                ZipEntry entry;
                boolean foundCsv = false;

                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().endsWith(".csv")) {
                        foundCsv = true;

                        try (CSVReader reader = new CSVReader(new InputStreamReader(zis))) {
                            List<String[]> rows = reader.readAll();

                            String value = rows.get(1)[1];

                            assertThat(value).isEqualTo("Alex");
                        }
                        break;
                    }
                }

                assertTrue(foundCsv, "В архиве нет CSV-файла!");
            }
        }
    }
}




