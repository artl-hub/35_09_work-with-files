package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkWithCsvPdfXlsxZip {

    @Test
    void xlsxCheckCoffeeInRow5() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(getClass().getClassLoader().getResourceAsStream("zip/files.zip"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {
                    XLS xls = new XLS(zis);

                    var sheet = xls.excel.getSheetAt(0);

                    String value = sheet.getRow(4).getCell(0).getStringCellValue();
                    assertThat(value).isEqualTo("Coffee");

                    break;
                }
            }
        }
    }


    @Test
        void pdfFromZipTest() throws Exception {
            try (ZipInputStream zis = new ZipInputStream(getClass().getClassLoader().getResourceAsStream("zip/files.zip"))) {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().endsWith(".pdf")) {
                        PDF pdf = new PDF(zis);
                        assertThat(pdf.text).contains("Congratulations");
                    }
                }
            }
        }

        @Test
        void xlsxFromZipTest() throws Exception {
            try (ZipInputStream zis = new ZipInputStream(getClass().getClassLoader().getResourceAsStream("zip/files.zip"))) {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().endsWith(".xlsx")) {
                        XLS xls = new XLS(zis);
                        String firstCell = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
                        assertThat(firstCell).isNotEmpty();
                    }
                }
            }
        }
    }


