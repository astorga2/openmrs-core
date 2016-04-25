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
import java.util.ArrayList;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
//import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.openmrs.api.EncounterService;
import org.openmrs.api.ProviderService;
import org.openmrs.test.BaseContextMockTest;
import org.openmrs.test.Verifies;

/**
 * This class tests the all of the {@link Encounter} non-trivial object methods.
 * 
 * @see Encounter
 */

/*
This test is the fixed version from documentation
*/
public class EncounterMockTest extends BaseContextMockTest {
	
	@Mock
	EncounterService encounterService;
	
	@Mock
	ProviderService providerService;


	/**
	 * @see Encounter#setProvider(Person)
	 * @verifies set existing provider for unknown role
	 */
	@Test
	public void setProvider_shouldSetExistingProviderForUnknownRole() throws Exception {
	    //given
	    Encounter encounter = new Encounter();
	    EncounterRole unknownRole = new EncounterRole();
	    Person person = new Person();
	    Provider provider = new Provider();
	    provider.setPerson(person);
	    List<Provider> providers = new ArrayList<Provider>();
	    providers.add(provider);
	 
	    when(encounterService.getEncounterRoleByUuid(EncounterRole.UNKNOWN_ENCOUNTER_ROLE_UUID)).thenReturn(unknownRole);
	 
	    when(providerService.getProvidersByPerson(person)).thenReturn(providers);
	 
	    //when
	    encounter.setProvider(unknownRole, provider);
	 
	    //then
	    assertEquals(1, encounter.getProvidersByRoles().size());
	    assertEquals(1, encounter.getProvidersByRole(unknownRole).size());
	    assertEquals(provider, encounter.getProvidersByRole(unknownRole).iterator().next());
	}

}
