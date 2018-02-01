package com.qualicom.cm.report;

import com.qualicom.cm.dao.ReportDao;
import com.qualicom.cm.model.Report;
import com.qualicom.cm.model.ReportParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.text.Format;
import java.util.*;

/**
 * Generates all reports configured in the system by instantiating them specially every time they are needed.
 *
 * Created by x110277 on 11/10/2016.
 */
@Component("reportFactory")
public class ReportFactory implements ApplicationContextAware  {

    @Autowired
    private ApplicationContext appCtx;

    private List<String> reportBeanList;

    public void setApplicationContext(ApplicationContext appCtx) {
        this.appCtx = appCtx;
    }

    public List<Report> generateReports(List<String> customerIds) {
        ArrayList<Report> reportList = new ArrayList<Report>();
        for(String reportBean : reportBeanList) {
            while(customerIds.size() > 0) {
                reportList.add(createCustomerMigratorReport(reportBean, customerIds.subList(0, Math.min(999,customerIds.size()))));
                customerIds.removeAll(customerIds.subList(0, Math.min(999,customerIds.size())));
            }
        }
        return reportList;
    }

    private Report createCustomerMigratorReport(String beanName, List<String> customerIds) {
        //Reports are recreated from scratch for every request, so they cannot be autowired into long-lived objects.
        ReportDao report = (ReportDao)appCtx.getBean(beanName);
        report.setReportParameters(new ReportParameters(Collections.singletonMap("customerIdList",(Object)Collections.unmodifiableList(customerIds))));
        return report.createReport();
    }

    public List<String> getReportBeanList() {
        return reportBeanList;
    }

    public void setReportBeanList(List<String> reportBeanList) {
        this.reportBeanList = reportBeanList;
    }
}
