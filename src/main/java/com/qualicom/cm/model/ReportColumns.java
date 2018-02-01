package com.qualicom.cm.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by x110277 on 11/09/2016.
 */
@XmlRootElement
public class ReportColumns extends ArrayList<String> {

    public ReportColumns() {

    }

    public ReportColumns(ArrayList<String> list) {
        super(list);
    }

    @XmlElement(name = "header")
    public List<String> getReportColumns() {
        return this;
    }

}
