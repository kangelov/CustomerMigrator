package com.qualicom.cm.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Created by x110277 on 11/09/2016.
 */
@XmlRootElement
public class ReportRow extends ArrayList<String> {

    @XmlElement(name="value")
    public List<String> getValue() {
        return this;
    }

}
