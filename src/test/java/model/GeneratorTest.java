package model;

import org.junit.jupiter.api.Test;
import ru.jennylember.Generator;
import ru.jennylember.model.settings.Column;
import ru.jennylember.model.settings.Page;
import ru.jennylember.model.settings.Settings;
import ru.jennylember.model.sourcedata.SourceRecord;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneratorTest {

    @Test
    void generateReportTest() {

        SourceRecord sourceRecord1 = new SourceRecord("1", "25/11", "Павлов Дмитрий");
        SourceRecord sourceRecord2 = new SourceRecord("2", "26/11", "Павлов Константин");
        SourceRecord sourceRecord3 = new SourceRecord("3", "27/11", "Н/Д");
        SourceRecord sourceRecord4 = new SourceRecord("4", "28/11", "Ким Чен Ир");
        SourceRecord sourceRecord5 = new SourceRecord("5", "29/11/2009", "Юлианна-Оксана Сухово-Кобылина");

        List<SourceRecord> sourceRecordList = Stream.of(sourceRecord1, sourceRecord2, sourceRecord3, sourceRecord4, sourceRecord5).collect(Collectors.toList());


        Column column1 = new Column();
        column1.setTitle("Номер");
        column1.setWidth(8);

        Column column2 = new Column();
        column2.setTitle("Дата");
        column2.setWidth(7);

        Column column3 = new Column();
        column3.setTitle("ФИО");
        column3.setWidth(7);

        Page page = new Page();
        page.setHeight(12);
        page.setWidth(32);

        Settings settings = new Settings();
        settings.setPage(page);
        settings.setColumns(Stream.of(column1, column2, column3).collect(Collectors.toList()));

        String report = Generator.generateReport(settings, sourceRecordList);

        System.out.println(report);
        String s = "";
    }
}
