package model.sourcedata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.jennylember.model.settings.Settings;
import ru.jennylember.model.sourcedata.SourceRecord;
import ru.jennylember.utils.JaxbUtils;
import ru.jennylember.utils.TsvUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SourceRecordTest {

    private static final String TSV = "1\t25/11\tПавлов Дмитрий\n" +
            "2\t26/11\tПавлов Константин\n" +
            "3\t27/11\tН/Д\n" +
            "4\t28/11\tКим Чен Ир\n" +
            "5\t29/11/2009\tЮлианна-Оксана Сухово-Кобылина";


    @Test
    public void sourceRecordTest() {

        SourceRecord sourceRecord1 = new SourceRecord("1", "25/11", "Павлов Дмитрий");
        SourceRecord sourceRecord2 = new SourceRecord("2", "26/11", "Павлов Константин");
        SourceRecord sourceRecord3 = new SourceRecord("3", "27/11", "Н/Д");
        SourceRecord sourceRecord4 = new SourceRecord("4", "28/11", "Ким Чен Ир");
        SourceRecord sourceRecord5 = new SourceRecord("5", "29/11/2009", "Юлианна-Оксана Сухово-Кобылина");

        List<SourceRecord> sourceRecordList = Stream.of(sourceRecord1, sourceRecord2, sourceRecord3, sourceRecord4, sourceRecord5).collect(Collectors.toList());

        Assertions.assertEquals(sourceRecordList, TsvUtils.parseTsv(TSV));

    }
}
