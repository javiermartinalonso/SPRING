package es.satec.angolatelecom.inventory.batch.writer;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;

import es.satec.angolatelecom.inventory.dto.pots.PotsResultDto;

public class PotsItemWriter implements ItemStreamWriter<PotsResultDto> {
    private HSSFWorkbook wb;
    private WritableResource resource;
    private int row;

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        wb = new HSSFWorkbook();
        HSSFPalette palette = wb.getCustomPalette();
        HSSFSheet s = wb.createSheet();
     
        resource = new FileSystemResource("output.xlsx");
        row = 0;
        createTitleRow(s, palette);
        createHeaderRow(s);
    }
    
    
    @Override
    public void write(List<? extends PotsResultDto> items) throws Exception {
        HSSFSheet s = wb.getSheetAt(0);
     
        for (PotsResultDto prDto : items) {
            Row r = s.createRow(row++);
            Cell c = r.createCell(0);
            c.setCellValue(prDto.getCentral());
     
            c = r.createCell(1);
            c.setCellValue(prDto.getMdf());
     
            c = r.createCell(2);
            c.setCellValue(prDto.getCaixa());
     
            c = r.createCell(3);
//            c.setCellValue(prDto.getPrice().doubleValue());
            c.setCellValue(prDto.getComentarios());
        }
    }    
    

	@Override
	public void close() throws ItemStreamException {
	    if (wb == null) {
	        return;
	    }
//	    createFooterRow();
	    try (BufferedOutputStream bos = new BufferedOutputStream(resource.getOutputStream())) {
	        wb.write(bos);
	        bos.flush();
//	        bos.close();
	    } catch (IOException ex) {
	        throw new ItemStreamException("Error writing to output file", ex);
	    }
	    row = 0;
	}


	@Override
	public void update(ExecutionContext arg0) throws ItemStreamException {
		// TODO Auto-generated method stub
		
	}    
	
    private void createTitleRow(HSSFSheet s, HSSFPalette palette) {
        HSSFColor redish = palette.findSimilarColor((byte) 0xE6, (byte) 0x50, (byte) 0x32);
        palette.setColorAtIndex(redish.getIndex(), (byte) 0xE6, (byte) 0x50, (byte) 0x32);
 
        CellStyle headerStyle = wb.createCellStyle();
        headerStyle.setWrapText(true);
//        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillForegroundColor(redish.getIndex());
//        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        headerStyle.setBorderTop(BorderStyle.THIN);
//        headerStyle.setBorderBottom(BorderStyle.THIN);
//        headerStyle.setBorderLeft(BorderStyle.THIN);
//        headerStyle.setBorderRight(BorderStyle.THIN);
 
        HSSFRow r = s.createRow(row);
 
        Cell c = r.createCell(0);
        c.setCellValue("Internal Use Only");
        r.createCell(1).setCellStyle(headerStyle);
        r.createCell(2).setCellStyle(headerStyle);
        s.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
        c.setCellStyle(headerStyle);
 
//        CellUtil.setAlignment(c, HorizontalAlignment.CENTER);
 
        row++;
    }   
    
    
    private void createHeaderRow(HSSFSheet s) {
        CellStyle cs = wb.createCellStyle();
        cs.setWrapText(true);
//        cs.setAlignment(HorizontalAlignment.LEFT);
    
        HSSFRow r = s.createRow(row);
//        r.setRowStyle(cs);
    
        Cell c = r.createCell(0);
        c.setCellValue("Author");
        s.setColumnWidth(0, poiWidth(18.0));
        c = r.createCell(1);
        c.setCellValue("Book Name");
        s.setColumnWidth(1, poiWidth(24.0));
        c = r.createCell(2);
        c.setCellValue("ISBN");
        s.setColumnWidth(2, poiWidth(18.0));
        c = r.createCell(3);
        c.setCellValue("Price");
        s.setColumnWidth(3, poiWidth(18.0));
    
        row++;
    }   
    
    private int poiWidth(double width) {
        return (int) Math.round(width * 256 + 200);
    }
/*
    private void createFooterRow() {
        HSSFSheet s = wb.getSheetAt(0);
        HSSFRow r = s.createRow(row);
        Cell c = r.createCell(3);
        c.setCellType(CellType.FORMULA);
        c.setCellFormula(String.format("SUM(D3:D%d)", row));
        row++;
     
    }
  */  

	
	
	
}
