package ru.jennylember.model.sourcedata;

import lombok.Data;

@Data
public class SourceRecord {
    private String number;
    private String date;
    private String name;

    public SourceRecord(String number, String date, String name) {
        this.number = number;
        this.date = date;
        this.name = name;
    }


}
