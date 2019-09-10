package es.satec.angolatelecom.inventory.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import es.satec.angolatelecom.inventory.dto.pots.PotsResultDto;
import es.satec.angolatelecom.inventory.dto.pots.PotsSheetDto;

public class PotsItemProcessor implements ItemProcessor<PotsSheetDto, PotsResultDto>{

	private static final Logger log = LoggerFactory.getLogger(PotsItemProcessor.class);

	@Override
	public PotsResultDto process(PotsSheetDto arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
