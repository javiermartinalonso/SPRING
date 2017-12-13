package com.jcg.spring.mvc.file.download;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileDownloadController {

	static ModelAndView modelViewObj;

	private static Logger logger = Logger.getLogger(FileDownloadController.class);

	@RequestMapping(value = {"/", "fileDownload"}, method = RequestMethod.GET)
	public ModelAndView showUploadFileForm(ModelMap model) {
		modelViewObj = new ModelAndView("fileDownload");
		return  modelViewObj;
	}

	@RequestMapping(value = "downloadFile/pdf", method = RequestMethod.GET)
	public void downloadPdf(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String pdfFilePath = "", pdfFileName = "irregular-verbs.pdf";
		logger.info("Downloading A .PDF File From The Server ....!");

		/**** Get The Absolute Path Of The File ****/
		pdfFilePath = Util.getFilePath(req) + File.separator + pdfFileName;      
		logger.info("Absolute Path Of The .PDF File Is?= " + pdfFilePath);

		File downloadFile = new File(pdfFilePath);
		if(downloadFile.exists()) {
			Util.downloadFileProperties(req, resp, pdfFilePath, downloadFile);				
		} else {
			logger.info("Requested .PDF File Not Found At The Server ....!");
		}
	}

	@RequestMapping(value = "downloadFile/csv", method = RequestMethod.GET)
	public void downloadCsv(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String csvFilePath = "";
		logger.info("Downloading A .CSV File From The Server ....!");

		/**** Get The Absolute Path Of The File ****/	
		csvFilePath = GenerateCsvData.writeDbDataToCsvFile(Util.getFilePath(req));		
		logger.info("Absolute Path Of The .CSV File Is?= " + csvFilePath);

		File downloadFile = new File(csvFilePath);
		if(downloadFile.exists()) {
			Util.downloadFileProperties(req, resp, csvFilePath, downloadFile);
		} else {
			logger.info("Requested .CSV File Not Found At The Server ....!");
		}
	}
}