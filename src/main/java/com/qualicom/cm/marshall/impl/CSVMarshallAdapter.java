package com.qualicom.cm.marshall.impl;

import com.qualicom.cm.marshall.MarshallAdapter;
import com.qualicom.cm.model.Report;
import com.qualicom.cm.model.ReportRow;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("csvMarshallAdapter")
public class CSVMarshallAdapter implements MarshallAdapter {

    @Override
    public byte[] generateReports(Report... reports) throws Exception {
        if (reports == null || reports.length == 0)
            throw new Exception("No report passed to marshall.");
        Report reportHeader = reports[0];
        StringBuffer csvOutput = new StringBuffer();
        List<String> headerLine = new ArrayList<String>();
        for(String header : reportHeader.getHeader()) {
            headerLine.add(header);
        }
        csvOutput.append(writeLine(headerLine));
        for(Report report : reports) {
            for (ReportRow row : report.getData()) {
                csvOutput.append(writeLine(row.getValue()));
            }
        }
        return csvOutput.toString().getBytes();
    }

    private String writeLine(List<String> fields) {
        if (fields == null || fields.size() == 0) return "";
        StringBuffer line = new StringBuffer("");
        for (int i=0; i<fields.size(); i++) {
            line.append(escapeString(fields.get(i)));
            if (i < fields.size() - 1) line.append(",");
        }
        return line.toString() + "\n";
    }
    private String escapeString(String csvValue) {
        if (csvValue == null) return "";
        return StringEscapeUtils.escapeCsv(csvValue);
    }

    @Override
    public boolean canHandleMultipleReports() {
        return true;
    }

    @Override
    public String getMimeType() {
       return "text/csv";
    }

    @Override
    public String getFileExtension() {
        return "csv";
    }
}
