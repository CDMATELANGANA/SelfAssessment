package com.mars.cdma.gov.Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;

import com.mars.cdma.gov.bean.DtcpApplication;

public class ReportDAO {
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ptassessmenttax", "root", "root");//10.166.7.188//localhost
		} catch (ClassNotFoundException e) {
		}
		return conn;

	}

	public void jasperHtml(JasperReport jasperReport,
			Map<String, Object> hmParams, HttpServletResponse response) {
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, hmParams, getConnection());
			HtmlExporter exporter = new HtmlExporter();
			List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
			jasperPrintList.add(jasperPrint);
			exporter.setExporterInput(SimpleExporterInput
					.getInstance(jasperPrintList));
			exporter.setExporterOutput(new SimpleHtmlExporterOutput(response
					.getWriter()));
			SimpleHtmlReportConfiguration configuration = new SimpleHtmlReportConfiguration();
			exporter.setConfiguration(configuration);
			exporter.exportReport();
		} catch (Exception e) {
		}
	}

	public void jasperPDF(JasperReport jasperReport,
			Map<String, Object> hmParams, HttpServletResponse response,String filename) {
		try {
			byte[] bytes = JasperRunManager.runReportToPdf(jasperReport,
					hmParams, new ReportDAO().getConnection());
			response.reset();
			response.resetBuffer();
			
			response.setHeader("Content-Disposition", "attachment;filename="+filename+".pdf");
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
		}
	}

}
