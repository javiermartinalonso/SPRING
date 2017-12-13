package com.jcg.spring.mvc.file.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class Util {

	private static Logger logger = Logger.getLogger(Util.class);

	/**** Method #1 - This Method Is Used To Retrieve The File Path From The Server ****/
	public static String getFilePath(HttpServletRequest req) throws FileNotFoundException {
		String appPath = "", fullPath = "", downloadPath = "downloads";

		/**** Retrieve The Absolute Path Of The Application ****/
		appPath = req.getSession().getServletContext().getRealPath("");	
		fullPath = appPath + File.separator + downloadPath;
		logger.info("Destination Location For The File Is?= " + fullPath);
		return fullPath;
	}

	/**** Method #2 - This Method Is Used To Get The No. Of Columns In The ResultSet ****/
	public static int getColumnCount(ResultSet res) throws SQLException {
		int totalColumns = res.getMetaData().getColumnCount();		
		return totalColumns;
	}

	/**** Method #3 - This Method Is Used To Set The Download File Properties ****/
	public static void downloadFileProperties(HttpServletRequest req,HttpServletResponse resp, String toBeDownloadedFile, File downloadFile) {
		try {

			/**** Get The Mime Type Of The File & Setting The Binary Type If The Mime Mapping Is Not Found ****/
			String mimeType = req.getSession().getServletContext().getMimeType(toBeDownloadedFile);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}

			/**** Setting The Content Attributes For The Response Object ****/
			resp.setContentType(mimeType);
			resp.setContentLength((int) downloadFile.length());

			/**** Setting The Headers For The Response Object ****/
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			resp.setHeader(headerKey, headerValue);

			/**** Get The Output Stream Of The Response ****/
			OutputStream outStream = resp.getOutputStream();
			FileInputStream inputStream = new FileInputStream(downloadFile);
			byte[] buffer = new byte[IConstants.BUFFER_SIZE];
			int bytesRead = -1;

			/**** Write Each Byte Of Data Read From The Input Stream Write Each Byte Of Data  Read From The Input Stream Into The Output Stream ****/
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outStream.close();
		} catch(IOException ioExObj) {
			logger.error("Exception While Performing The I/O Operation?= " + ioExObj);
		}
	}
}