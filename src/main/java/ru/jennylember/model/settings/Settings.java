package ru.jennylember.model.settings;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "settings")
@XmlAccessorType(XmlAccessType.FIELD)
public class Settings {

    private Page page;

    @XmlElementWrapper(name = "columns")
    @XmlElement(name = "column")
    private List<Column> columns;

}
