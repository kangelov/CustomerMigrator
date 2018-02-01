package com.qualicom.cm.view;

import com.qualicom.cm.model.MarshalledReports;
import com.qualicom.cm.model.Report;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class CSVView extends AbstractView {

    private final MarshalledReports report;

    public CSVView(MarshalledReports report) {
        super();
        this.report = report;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ByteArrayOutputStream baos = this.createTemporaryOutputStream();
        this.setContentType("application/csv");
        httpServletResponse.setContentType("application/csv; name=\"" + report.getName() + "." + report.getFileExtension() + "\"");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + report.getName() + "." + report.getFileExtension() + "\"");
        baos.write(report.getMarshalledReports());
        baos.flush();
        this.writeToResponse(httpServletResponse, baos);
        baos.close();
    }
}
