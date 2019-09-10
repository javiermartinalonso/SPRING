package es.satec.angolatelecom.inventory.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import es.satec.angolatelecom.inventory.dto.wimax.WimaxResultDto;
import es.satec.angolatelecom.inventory.dto.wimax.WimaxSheetDto;
import es.satec.angolatelecom.inventory.service.wimax.WimaxInventoryService;

public class WimaxItemProcessor implements ItemProcessor<WimaxSheetDto, WimaxResultDto> {

	private static final Logger log = LoggerFactory.getLogger(WimaxItemProcessor.class);

	private int index;

	@Autowired
	private WimaxInventoryService wimaxInventoryService;

	public WimaxItemProcessor() {
		super();
		this.index = 0;
	}

	@Override
	public WimaxResultDto process(WimaxSheetDto wimaxSheetDto) throws Exception {

		/*
		 * String telefono = wimaxSheetDto.getMainkey(); String eqNbrSerie =
		 * wimaxSheetDto.getEqNbrSerie(); String estado =
		 * wimaxSheetDto.getEstado(); String mac = wimaxSheetDto.getMac();
		 * String modeloTelefono = wimaxSheetDto.getModeloTelefone(); String
		 * passDatos = wimaxSheetDto.getPasswordDados(); String passVoz =
		 * wimaxSheetDto.getPasswordVoz();
		 */

		log.trace("** wimaxSheetDto ** {}", wimaxSheetDto);

		WimaxResultDto wimaxResultDto = new WimaxResultDto("strPerfilDeRedeWiMax" + index, "strServicio" + index,
				"strNumeroDeTelefono" + index, "strComentarios" + index);

		log.trace("** Armario ** {}", wimaxInventoryService.get("9222290003"));

		// comentarios

		index++;
		return wimaxResultDto;
	}

}
