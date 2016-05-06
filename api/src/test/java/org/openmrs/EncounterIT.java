/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.openmrs.api.EncounterService;
import org.openmrs.api.ProviderService;
import org.openmrs.test.BaseContextMockTest;
import org.openmrs.test.Verifies;
import org.mockito.MockitoAnnotations;
import org.junit.Before;


/**
 * This class tests the all of the {@link Encounter} non-trivial object methods.
 * 
 * @see Encounter
 */
public class EncounterIT extends BaseContextMockTest {
	
	@Mock
	EncounterService encounterService;
	
	@Mock
	ProviderService providerService;
	

	@Before 
	public void initMocks() {
           MockitoAnnotations.initMocks(this);
    }

	/**
	 * @see Encounter#copyAndAssignToAnotherPatient(org.openmrs.Patient)
	 */
	@Test
	@Verifies(value = "should copy all Encounter data except visit and assign copied Encounter to given Patient", method = "copyAndAssignToAnotherPatient()")
	public void copy_shouldCopyAllEncounterDataExceptVisitAndAssignCopiedEncounterToGivenPatient() throws Exception {
		Encounter encounter = new Encounter();
		
		encounter.setCreator(new User());
		encounter.setDateCreated(new Date());
		encounter.setChangedBy(new User());
		encounter.setDateChanged(new Date());
		encounter.setVoided(true);
		encounter.setVoidReason("void");
		encounter.setDateVoided(new Date());
		
		encounter.setEncounterDatetime(new Date());
		encounter.setEncounterType(new EncounterType());
		encounter.setForm(new Form());
		encounter.setLocation(new Location());
		encounter.setPatient(new Patient());
		
		encounter.addObs(new Obs());
		encounter.addOrder(new Order());
		
		EncounterRole encounterRole = new EncounterRole();
		encounter.addProvider(encounterRole, new Provider());
		
		encounter.setVisit(new Visit());
		
		Patient patient = new Patient(7);
		
		Encounter encounterMock = new Encounter();
		/*Mocking encounterService*/	
		when(encounterService.saveEncounter(encounterMock)).thenReturn(encounterMock);
		
		Encounter encounterCopy = encounter.copyAndAssignToAnotherPatient(patient);
		
		Assert.assertNotEquals(encounter, encounterCopy);
		
		Assert.assertEquals(encounter.getCreator(), encounterCopy.getCreator());
		Assert.assertEquals(encounter.getDateCreated(), encounterCopy.getDateCreated());
		Assert.assertEquals(encounter.getChangedBy(), encounterCopy.getChangedBy());
		Assert.assertEquals(encounter.getDateChanged(), encounterCopy.getDateChanged());
		Assert.assertEquals(encounter.getVoided(), encounterCopy.getVoided());
		Assert.assertEquals(encounter.getVoidReason(), encounterCopy.getVoidReason());
		Assert.assertEquals(encounter.getDateVoided(), encounterCopy.getDateVoided());
		
		Assert.assertEquals(encounter.getEncounterDatetime(), encounterCopy.getEncounterDatetime());
		Assert.assertEquals(encounter.getEncounterType(), encounterCopy.getEncounterType());
		Assert.assertEquals(encounter.getForm(), encounterCopy.getForm());
		Assert.assertEquals(encounter.getLocation(), encounterCopy.getLocation());
		
		Assert.assertEquals(1, encounter.getObs().size());
		Assert.assertEquals(1, encounterCopy.getObs().size());
		Assert.assertEquals(1, encounter.getOrders().size());
		Assert.assertEquals(0, encounterCopy.getOrders().size());
		
		Assert.assertEquals(1, encounter.getProvidersByRole(encounterRole).size());
		Assert.assertEquals(1, encounterCopy.getProvidersByRole(encounterRole).size());
		Assert.assertEquals(true, encounter.getProvidersByRole(encounterRole).containsAll(
		    encounterCopy.getProvidersByRole(encounterRole)));
		
		Assert.assertNotNull(encounter.getVisit());
		Assert.assertNull(encounterCopy.getVisit());
		
		Assert.assertEquals(patient, encounterCopy.getPatient());
	}
}
