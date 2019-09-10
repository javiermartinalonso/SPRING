package es.satec.angolatelecom.inventory.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import es.satec.angolatelecom.inventory.batch.listener.JobCompletionNotificationListener;
import es.satec.angolatelecom.inventory.batch.processor.WimaxItemProcessor;
import es.satec.angolatelecom.inventory.batch.writer.WimaxItemWriter;
import es.satec.angolatelecom.inventory.dto.wimax.WimaxResultDto;
import es.satec.angolatelecom.inventory.dto.wimax.WimaxSheetDto;

@Configuration
@EnableBatchProcessing
public class WimaxBatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	ItemReader<WimaxSheetDto> reader() {
		PoiItemReader<WimaxSheetDto> reader = new PoiItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource("data/WIMAX/Wimax.xlsx"));
		reader.setRowMapper(excelRowMapper());
		return reader;
	}

	private RowMapper<WimaxSheetDto> excelRowMapper() {
		BeanWrapperRowMapper<WimaxSheetDto> rowMapper = new BeanWrapperRowMapper<>();
		rowMapper.setTargetType(WimaxSheetDto.class);
		return rowMapper;
	}

	@Bean
	public WimaxItemProcessor processor() {
		return new WimaxItemProcessor();
	}
	
	@Bean
	public ItemStreamWriter<WimaxResultDto> writer (){
		return new WimaxItemWriter();
	}
		
	@Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
            .<WimaxSheetDto, WimaxResultDto> chunk(10)
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .build();
    }
}
