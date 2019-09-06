package com.example.drools.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.drools.domain.ProductPrice;

@Service
public class PriceCalculatorService {
	@Autowired
	private KieContainer kieContainer;
	
	@Autowired
	private PushSubService pushSubService;

	public void executeRules(ProductPrice productPrice) {
    	KieSession kieSession = kieContainer.newKieSession();
    	
    	kieSession.setGlobal("publishTool", pushSubService);//adding globals
    	
        kieSession.insert(productPrice);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
