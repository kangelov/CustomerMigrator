package com.qualicom.cm.dao;

import com.qualicom.cm.model.Report;
import com.qualicom.cm.model.ReportColumns;
import com.qualicom.cm.model.ReportRow;
import com.qualicom.cm.model.ReportParameters;

import java.util.List;

/**
 * Created by x110277 on 11/09/2016.
 */
public interface ReportDao {

    Report createReport();

    void setReportParameters(ReportParameters parameters);
}
