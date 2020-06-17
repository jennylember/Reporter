package ru.jennylember.utils;

import org.jetbrains.annotations.NotNull;
import ru.jennylember.model.sourcedata.SourceRecord;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TsvUtils {

    private static final int NUMBER_INDEX = 0;
    private static final int DATE_INDEX = 1;
    private static final int NAME_INDEX = 2;
    private static final int NUMBER_OF_FIELDS = 3;


    @NotNull
    public static SourceRecord tsvToSourceRecord(@NotNull String tsvRow) {
        String[] splitted = tsvRow.split("\t");
        if (splitted.length != NUMBER_OF_FIELDS) {
            throw new RuntimeException();
        }
        return new SourceRecord(splitted[NUMBER_INDEX], splitted[DATE_INDEX], splitted[NAME_INDEX]);
    }

    public static List<SourceRecord> parseTsv(@NotNull String tsv) {

       return Arrays.stream(tsv.split("\r\n")).map(TsvUtils::tsvToSourceRecord).collect(Collectors.toList());

    }
}
