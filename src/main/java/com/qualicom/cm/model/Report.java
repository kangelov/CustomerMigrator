package com.qualicom.cm.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * A POJO representation of a report for JAX-B to convert to XML. This is an example what the XML looks like:
 *  <pre>

 <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
 <report>
    <data>
        <row>
            <value>11/10/2016</value>
            <value>CreditCard</value>
            <value>PURCHASE</value>
            <value>6</value>
        </row>
    </data>
    <headers>
        <header>Date</header>
        <header>Tender</header>
        <header>Type</header>
        <header>Count</header>
    </headers>
    <title>Successful Payments Report</title>
 </report>

 *  </pre>
 * Created by x110277 on 11/10/2016.
 */
@XmlRootElement
@XmlSeeAlso({ReportRow.class})
public class Report {

    private String name;

    private String title;

    private ReportColumns header;

    private List<ReportRow> data;

    public Report() {

    }

    public Report(String name, String title, ReportColumns header, List<ReportRow> data) {
        setName(name);
        setTitle(title);
        setHeader(header);
        setData(data);
    }

    @XmlElementWrapper(name = "data")
    @XmlElement(name = "row")
    public List<ReportRow> getData() {
        return data;
    }

    public void setData(List<ReportRow> data) {
        this.data = data;
    }

    @XmlElementWrapper(name = "headers")
    @XmlElement(name="header")
    public ReportColumns getHeader() {
        return header;
    }

    public void setHeader(ReportColumns header) {
        this.header = header;
    }

    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Report)) return false;
        Report other = (Report)obj;
        return name.equals(other);
    }
}
