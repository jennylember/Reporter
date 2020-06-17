package ru.jennylember.model.settings;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Column {

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "width")
    private Integer width;
}
