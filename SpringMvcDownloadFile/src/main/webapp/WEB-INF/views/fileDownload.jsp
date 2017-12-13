<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	    <title>Spring Mvc File Download Example</title>
	    <style type="text/css">
	    	.linkCSS {	    		
    			cursor: pointer;
    			text-decoration: none;
	    	}	    	
	    	.padding {
	    		padding: 13px 0px 20px 145px;
	    	}
	    </style>
	</head>
	<body>
	    <center><h2>Spring Mvc File Download Example</h2></center>
	    <div id="pdfFile" class="padding">
	    	<a id="downloadPdfFileLink" target="_self" class="linkCSS" href="${pageContext.request.contextPath}/downloadFile/pdf">Download Pdf File</a>
	    </div>	
	    <div id="csvField" class="padding">
	    	<a id="downloadCsvFileLink" target="_self" class="linkCSS" href="${pageContext.request.contextPath}/downloadFile/csv">Download Csv File</a>
	    </div>   
	</body>
</html>