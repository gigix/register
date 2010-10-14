package org.openmrs.module.register.web.dwr;

import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.register.RegisterService;
import org.openmrs.web.dwr.EncounterListItem;

public class DWRRegisterService {
	protected final Log log = LogFactory.getLog(getClass());
	
	public List<EncounterListItem> getRegisterEntriesByLocation(int registerId, int locationId){
		RegisterService registerService = Context.getService(RegisterService.class);
		List<Encounter> encounters = registerService.getEncountersForRegisterByLocation(registerId, locationId);
		List<EncounterListItem> encounterListItems = new Vector<EncounterListItem>();
		for (Encounter encounter : encounters) {
			encounterListItems.add(new EncounterListItem(encounter));
		}
		return encounterListItems;		
	}
}
