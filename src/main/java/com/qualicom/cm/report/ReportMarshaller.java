package com.qualicom.cm.report;

import com.qualicom.cm.marshall.MarshallAdapter;
import com.qualicom.cm.model.MarshalledReports;
import com.qualicom.cm.model.Report;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * A generic converter to convert your report to whatever formats are configured.
 *
 * Created by x110277 on 11/10/2016.
 */
@Component
public class ReportMarshaller {

    private String collectiveReportName;

    public String getCollectiveReportName() {
        return collectiveReportName;
    }

    public void setCollectiveReportName(String collectiveReportName) {
        this.collectiveReportName = collectiveReportName;
    }

    private List<MarshallAdapter> marshallAdapterList;

    public List<MarshallAdapter> getMarshallAdapterList() {
        return marshallAdapterList;
    }

    public void setMarshallAdapterList(List<MarshallAdapter> marshallAdapterList) {
        this.marshallAdapterList = marshallAdapterList;
    }

    public List<MarshalledReports> marshallReports(List<Report> reportList) throws Exception {
        List<MarshalledReports> marshalledReportsList = new ArrayList<MarshalledReports>();
        for(MarshallAdapter marshallAdapter : marshallAdapterList) {
            if (!marshallAdapter.canHandleMultipleReports()) {
                for (Report report : reportList) {
                    MarshalledReports marshalledReport = new MarshalledReports(
                            report.getName(),
                            marshallAdapter.getFileExtension(),
                            marshallAdapter.generateReports(report),
                            marshallAdapter.getMimeType());
                    marshalledReportsList.add(marshalledReport);
                }
            } else {
                MarshalledReports marshalledReport = new MarshalledReports(
                        collectiveReportName,
                        marshallAdapter.getFileExtension(),
                        marshallAdapter.generateReports(reportList.toArray(new Report[] { } )),
                        marshallAdapter.getMimeType());
                marshalledReportsList.add(marshalledReport);
            }
        }
        return marshalledReportsList;
    }

}
