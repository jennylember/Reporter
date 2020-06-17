package ru.jennylember;

import org.jetbrains.annotations.NotNull;
import ru.jennylember.model.settings.Column;
import ru.jennylember.model.settings.Page;
import ru.jennylember.model.settings.Settings;
import ru.jennylember.model.sourcedata.SourceRecord;
import ru.jennylember.utils.JaxbUtils;
import ru.jennylember.utils.TsvUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class Generator {

    private static final int SETTINGS_INDEX = 0;
    private static final int SOURCE_DATA_INDEX = 1;
    private static final int OUTPUT_REPORT_INDEX = 2;
    private static final String WORK_DIRECTORY = System.getProperty("user.dir");

    public static void main(String[] args) {

        String settingsPath = args[SETTINGS_INDEX];
        String sourceDataPath = args[SOURCE_DATA_INDEX];
        String outputReportPath = args[OUTPUT_REPORT_INDEX];

        Path pathSettings = Paths.get(WORK_DIRECTORY + settingsPath);
        Path pathSourceData = Paths.get(WORK_DIRECTORY + sourceDataPath);
        Path pathOutputReport = Paths.get(WORK_DIRECTORY + outputReportPath);

        try {

            String xml = new String(Files.readAllBytes(pathSettings));
            Settings settings = JaxbUtils.xmlToObject(Settings.class, xml);

            String tsv = new String(Files.readAllBytes(pathSourceData), StandardCharsets.UTF_16);
            List<SourceRecord> sourceRecordList = TsvUtils.parseTsv(tsv);

            String result = generateReport(settings, sourceRecordList);

            if (!Files.exists(pathOutputReport)) {
                Files.createFile(pathOutputReport);
            }

            Files.write(pathOutputReport, result.getBytes(StandardCharsets.UTF_16));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateReport(@NotNull Settings settings, @NotNull List<SourceRecord> sourceRecordList) {

        StringBuilder report = new StringBuilder();

        String header = generateHeader(settings);
        String rowSeparator = generateRowSeparator(settings.getPage());
        String pageSeparator = "~";

        int currentHeight = 0;

        report.append(header);
        report.append(rowSeparator);

        for (SourceRecord record : sourceRecordList) {
            report.append(generateRow(record, settings.getColumns()));
            report.append(rowSeparator);
            if (currentHeight >= settings.getPage().getHeight()) {
                report.append(pageSeparator);
                report.append(header);
                report.append(rowSeparator);
            }
        }

        return report.toString();
    }

    private static String generateHeader(Settings settings) {
        StringBuilder header = new StringBuilder();

        List<Column> columns = settings.getColumns();
        int numberOfColumns = columns.size();

        for (int i = 0; i < numberOfColumns; i++) {
            header.append("| ");
            int width = columns.get(i).getWidth();
            String title = columns.get(i).getTitle();
            int titleLength = title.length();

            if (titleLength > width) {
                throw new RuntimeException();
            }
            header.append(title);
            if (titleLength < width) {
                for (int j = 0; j < width - titleLength; j++) {
                    header.append(" ");
                }
            }
            header.append(" ");
            if (i == numberOfColumns - 1) {
                header.append("|");
            }
        }
        header.append("\n");
        return header.toString();
    }

    private static String generateRowSeparator(Page page) {
        StringBuilder rowSeparator = new StringBuilder();
        for (int i = 0; i < page.getWidth(); i++) {
            rowSeparator.append("-");
        }
        rowSeparator.append("\n");
        return rowSeparator.toString();

    }

    private static String generateRow(SourceRecord record, List<Column> columns) {

        StringBuilder row = new StringBuilder();

        String number = record.getNumber();
        String date = record.getDate();
        String name = record.getName();

        int numberLength = number.length();
        int dateLength = date.length();
        int nameLength = name.length();

        int widthNumber = columns.get(0).getWidth();
        int widthDate = columns.get(1).getWidth();
        int widthName = columns.get(2).getWidth();

        row.append(generateCell(number, widthNumber, numberLength));
        row.append(generateCell(date, widthDate, dateLength));
        row.append(generateCell(name, widthName, nameLength));

        row.append("|\n");

        return row.toString();
    }

    private static String generateCell(String value, int widthValue, int valueLength) {
        StringBuilder cell = new StringBuilder();

        cell.append("| ");
        cell.append(value);
        if (valueLength < widthValue) {
            for (int j = 0; j < widthValue - valueLength; j++) {
                cell.append(" ");
            }
        }
        cell.append(" ");
        return cell.toString();
    }



}
