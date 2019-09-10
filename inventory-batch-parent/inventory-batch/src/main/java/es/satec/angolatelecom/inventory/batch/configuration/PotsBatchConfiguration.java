package es.satec.angolatelecom.inventory.batch.configuration;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import es.satec.angolatelecom.inventory.batch.processor.PotsItemProcessor;
import es.satec.angolatelecom.inventory.dto.pots.PotsSheetDto;

@Configuration
public class PotsBatchConfiguration {

	@Bean
	ItemReader<PotsSheetDto> excelPotsReader() {
		PoiItemReader<PotsSheetDto> reader = new PoiItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource("data/POTS/Inventario.xlsx"));
		reader.setRowMapper(excelRowMapper());
		return reader;
	}

	private RowMapper<PotsSheetDto> excelRowMapper() {
		BeanWrapperRowMapper<PotsSheetDto> rowMapper = new BeanWrapperRowMapper<>();
		rowMapper.setTargetType(PotsSheetDto.class);
		return rowMapper;
	}

	@Bean
	public PotsItemProcessor processor() {
		return new PotsItemProcessor();
	}
}
