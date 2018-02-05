package com.qualicom.cm.controller;

import com.qualicom.cm.marshall.MarshallAdapter;
import com.qualicom.cm.model.MarshalledReports;
import com.qualicom.cm.model.Report;
import com.qualicom.cm.report.ReportFactory;
import com.qualicom.cm.report.ReportMarshaller;
import com.qualicom.cm.view.CSVView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by x110277 on 11/09/2016.
 */
@Controller("mainController")
public class MainController {

    @Autowired
    private ReportFactory reportFactory;

    @Autowired
    private ReportMarshaller reportMarshaller;

    @RequestMapping("")
    public ModelAndView beginHere() {
        ModelAndView model = new ModelAndView();
        model.addObject("title","GWP Customer Report");
        model.addObject("message","This page allows you to force a report run. Please note the generated reports will be e-mailed every time you click on the link below.");
        model.setViewName("main");
        return model;
    }

    @RequestMapping({"main", "main/"})
    public ModelAndView main() {
        return beginHere();
    }

    @RequestMapping(value={"customermigrator","customermigrator/"}, method = RequestMethod.POST)
    public ModelAndView customerMigrator(@RequestParam MultipartFile csvFile) throws Exception {

        List<MarshalledReports> reports = runReports(getCustomerIds(csvFile));
        ModelAndView model = new ModelAndView();
        if (reports == null) {
            model.addObject("title", "GWP Customer Report");
            model.addObject("message", "Report was not generated.");
            model.setViewName("main");
        } else if (reports.size() > 1) {
            model.addObject("title", "GWP Customer Report");
            model.addObject("message", "More than one report was not generated.");
            model.setViewName("main");
        } else {
            model.setView(new CSVView(reports.get(0)));
        }
        return model;
    }

    private List<MarshalledReports>  runReports(List<String> customerIds) throws Exception {

        List<Report> reportList = reportFactory.generateReports(customerIds);
        reportMarshaller.setCollectiveReportName("customerMigrator");
        List<MarshalledReports> marshalledReports = reportMarshaller.marshallReports(reportList);
        return marshalledReports;
    }

    private List<String> getCustomerIds(MultipartFile csvFile) throws IOException {
        ArrayList<String> customerIds = new ArrayList<String>();
        if (csvFile != null && csvFile.getBytes() != null && csvFile.getBytes().length > 0) { // && csvFile.getContentType().equals("text/csv")) {

            BufferedReader csvStream = new BufferedReader(new InputStreamReader(csvFile.getInputStream()));
            String csvLine = null;
            while ((csvLine = csvStream.readLine()) != null) {
                String[] csvFields = csvLine.split(",");
                if (csvFields != null && csvFields.length > 0 && csvFields[0].matches("^[0-9]*$")) {
                    customerIds.add(csvFields[0]);
                }
            }
        }
        return customerIds;
    }


}
