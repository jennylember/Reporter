package model.settings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.jennylember.model.settings.Column;
import ru.jennylember.model.settings.Page;
import ru.jennylember.model.settings.Settings;
import ru.jennylember.utils.JaxbUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SettingsTest {

    private static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "\n" +
            "<model.settings>\n" +
            "\t<page>\n" +
            "\t\t<width>32</width>\n" +
            "\t\t<height>12</height>\n" +
            "\t</page>\n" +
            "\t<columns>\n" +
            "\t\t<column>\n" +
            "\t\t\t<title>Номер</title>\n" +
            "\t\t\t<width>8</width>\n" +
            "\t\t</column>\n" +
            "\t\t<column>\n" +
            "\t\t\t<title>Дата</title>\n" +
            "\t\t\t<width>7</width>\n" +
            "\t\t</column>\n" +
            "\t\t<column>\n" +
            "\t\t\t<title>ФИО</title>\n" +
            "\t\t\t<width>7</width>\n" +
            "\t\t</column>\n" +
            "\t</columns>\n" +
            "</model.settings>";

    @Test
    public void settingsTest() {

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

        Assertions.assertEquals(settings, JaxbUtils.xmlToObject(Settings.class, XML));
    }





}
