package com.jcg.spring.mvc.file.download;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class GenerateCsvData {

	static ResultSet res; 
	static Statement stmt;
	static Connection conn;

	private static Logger logger = Logger.getLogger(GenerateCsvData.class);

	/**** This Method Is Used To Create A Database Connection Object ****/
	public static Connection getDatabaseConnection() {
		try {
			Class.forName(IConstants.DB_DRIVER);
			conn = DriverManager.getConnection(IConstants.DB_URL, IConstants.DB_USERNAME, IConstants.DB_PASSWORD);
		} catch(Exception exObj) {
			logger.error("Exception While Creating The Db Connection Object?= " + exObj);
		}
		return conn;
	}

	/**** This Method Is Used To Prepare The .CSV File Containing The Db Data ****/
	public static String writeDbDataToCsvFile(String filePath) {		
		FileWriter fwObj = null;
		String generatedCsvFilePath = "";
		List <String> tableNameList = new ArrayList<String>();	
		try {
			if (getDatabaseConnection() != null) {
				stmt = conn.createStatement();

				/**** This Query Gets All The Tables In Your Database (i.e. Put The Db Name In The SQL Query ****/
				res = stmt.executeQuery(IConstants.GET_TABLES_LIST_QUERY);

				/**** Preparing List Of Table Names ****/	
				while(res!= null && res.next()) {
					tableNameList.add(res.getString(1));
				}

				logger.info("Total No. Of Tables Available In The '" + IConstants.DBNAME + "' Db Are?= " + tableNameList.size());
				if(tableNameList.size() > 0) {

					/**** Start Iterating On Each Table To Fetch It's Data & Save In A .CSV File ****/
					for(String tableName : tableNameList) {
						logger.info("Table Name Is?= " + tableName);

						/**** Select All Data From The Db Table ****/
						String selectQuery = "SELECT * FROM " + IConstants.DBNAME + "." + tableName + ";";
						res = stmt.executeQuery(selectQuery);

						/**** Column Count Is Necessary As The Tables Are Dynamic And We Need To Figure Out The Numbers Of Columns ****/
						int colunmCount = Util.getColumnCount(res);
						logger.info("Total No. Of Columns Present In The '" + tableName + "' Are?= " + colunmCount);
						try {
							generatedCsvFilePath = filePath + File.separator + tableName +".csv";
							logger.info("The .CSV File Will Be Generated At The Following Path?= " + generatedCsvFilePath);

							fwObj = new FileWriter(generatedCsvFilePath);
							/**** This Loop Is Used To Add The Column Names At The Top Of File. If Developers Do Not Need It Just Comment This Loop ****/
							for(int i=1 ; i<= colunmCount ; i++) {
								fwObj.append(res.getMetaData().getColumnName(i));
								fwObj.append(IConstants.PIPE_SYMBOL);
							}

							fwObj.append(IConstants.NEW_LINE);	                     
							while(res!= null && res.next()) {
								String dbData = "";
								for(int i=1; i<=colunmCount; i++) {	                             	                          
									if(res.getObject(i) != null) {
										dbData = res.getObject(i).toString();
										fwObj.append(dbData).append(IConstants.PIPE_SYMBOL);
									} else {
										dbData = "null";
										fwObj.append(dbData).append(IConstants.PIPE_SYMBOL);
									}
								}

								/**** New Line Entered After Each Row ****/
								fwObj.append(IConstants.NEW_LINE);
							}
							fwObj.flush();
							fwObj.close();
						} catch (IOException ioExObj) {						
							logger.error("Exception While Performing The I/O Operation?= " + ioExObj);
						}
					}
				} else {
					logger.info("No Tables Are Available In The '" + IConstants.DBNAME + "' Database. Please Check The Database ......!");
				}
			}
		} catch (Exception exObj) {
			logger.error("Exception In The 'writeDbDataToCsvFile()' Method?= " + exObj);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlExObj) {
				logger.error("Exception While Closing The Db Connection Object?= " + sqlExObj);
			}
		}
		return generatedCsvFilePath;
	}
}