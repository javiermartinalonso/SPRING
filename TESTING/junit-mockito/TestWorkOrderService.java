package es.satec.angolatelecom.itsm.mediator.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ao.angolatelecom.sigo.types.oss.itsm.ServiceRequestStatusCode;
import ao.angolatelecom.sigo.types.oss.itsm.ServiceRequestUpdatedRequest;
import es.satec.angolatelecom.itsm.mediator.domain.WorkOrder;
import es.satec.angolatelecom.itsm.mediator.repository.WorkOrderRepository;
import es.satec.angolatelecom.provision.bonita.service.BonitaProvisionService;
import es.satec.angolatelecom.provision.bonita.service.BonitaServiceException;

public class TestWorkOrderService {

	@Mock
	private WorkOrderRepository mockWorkOrderRepository;
	@Mock
	private BonitaProvisionService mockBonitaProvisionService;

	@InjectMocks
	private WorkOrderService mockWorkOrderService;
	
	@Mock
	private WorkOrder mockWorkOrder;
	@Mock
	private ServiceRequestUpdatedRequest mockServiceRequestUpdatedRequest;
	@Mock
	private ProcessInstance mockProcessInstance;


	@Before
	public void initMocks() throws BonitaServiceException {
		MockitoAnnotations.initMocks(this);

		// Premisa que la workOrder exista
		// da igual el status code de la workOrder, pero lo tiene que tener
		// informado o dará null pointer.
		when(mockWorkOrder.getServiceRequestStatusCode()).thenReturn(ServiceRequestStatusCode.INPROG);
		// tiene que existir la orden en el repositorio
		when(mockWorkOrderRepository.findOneByServiceRequestId(anyString())).thenReturn(mockWorkOrder);
		// Cuando se mande iniciar el proceso, este cree una instancia y nos
		// devuelve el id de dicha instancia.
		when(mockWorkOrderService.getBonitaProvisionService().startProcess(eq("Notificaction ITSM"), any())).thenReturn(mockProcessInstance);
	}

	
	@Test
	public void updateServiceRequestFinalState() throws BonitaServiceException {
		/*
		 * Estado FINAL cualquier Estado de los siguientes CLOSED IRRESOLVABLE
		 * NO_FURTHER_ACTION
		 */
		when(mockServiceRequestUpdatedRequest.getServiceRequestStatusCode()).thenReturn(ServiceRequestStatusCode.IRRESOLVABLE);

		mockWorkOrderService.updateServiceRequest(mockServiceRequestUpdatedRequest);

		verify(mockBonitaProvisionService, times(1)).startProcess(eq("Notificaction ITSM"), any());
		verify(mockBonitaProvisionService, times(1)).startProcess(eq("Notificaction ITSM"), eq("1.0"), any());
	}

	@Test
	public void updateServiceRequestNoFinalState() throws BonitaServiceException {
		/*
		 * Estado NO FINAL cualquier Estado distinto de CLOSED IRRESOLVABLE
		 * NO_FURTHER_ACTION
		 */
		when(mockServiceRequestUpdatedRequest.getServiceRequestStatusCode()).thenReturn(ServiceRequestStatusCode.NEW);

		mockWorkOrderService.updateServiceRequest(mockServiceRequestUpdatedRequest);

		// estado NO final
		verify(mockBonitaProvisionService, times(1)).startProcess(eq("Notificaction ITSM"), any());
		verify(mockBonitaProvisionService, times(0)).startProcess(eq("Notificaction ITSM"), eq("1.0"), any());
	}

}
