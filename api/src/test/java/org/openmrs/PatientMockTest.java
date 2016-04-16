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


import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openmrs.test.BaseContextMockTest;
import org.openmrs.test.Verifies;


import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * This class should test all methods on the patient object. It should not worry about the extended
 * Person object -- that testing is done by {@link org.openmrs.PersonTest} This class does not touch
 * the database, so it does not need to extend the normal openmrs BaseTest TODO: Complete this class
 * by testing all other non getter/setters in the patient object
 */
public class PatientMockTest extends BaseContextMockTest {
	
	@Mock
	PatientIdentifier patientIdentifier1;

	@Mock
	PatientIdentifier patientIdentifier2;

	@Mock
	PatientIdentifier patientIdentifier3;

	@Before 
	public void initMocks() {
       MockitoAnnotations.initMocks(this);
	}

	/**
	 * @see Patient#getIdentifier(PatientIdentifierType)
	 * @verifies get identifier based on PatientIdentifierType
	 */
	@Test
	public void  getIdentifier_shouldGetPatientIdentifierBasedOnPatientIdentifierType() throws Exception {
	    Patient patient = new Patient();
	 
	 	PatientIdentifierType type1 = new PatientIdentifierType(new Integer(1));
	 	PatientIdentifierType type2 = new PatientIdentifierType(new Integer(2));
	 	PatientIdentifierType type3 = new PatientIdentifierType(new Integer(3));
	    when(patientIdentifier1.getIdentifierType()).thenReturn(type1);
	    when(patientIdentifier2.getIdentifierType()).thenReturn(type2);
	    when(patientIdentifier3.getIdentifierType()).thenReturn(type3);

	    when(patientIdentifier1.isVoided()).thenReturn(false);
	    when(patientIdentifier2.isVoided()).thenReturn(false);
	    when(patientIdentifier3.isVoided()).thenReturn(false);

	    when(patientIdentifier1.isPreferred()).thenReturn(true);
	    when(patientIdentifier2.isPreferred()).thenReturn(true);
	    when(patientIdentifier3.isPreferred()).thenReturn(true);


	    patient.addIdentifier(patientIdentifier1);
	    patient.addIdentifier(patientIdentifier2);
	    patient.addIdentifier(patientIdentifier3);

	    Assert.assertEquals(patientIdentifier3, patient.getPatientIdentifier(type3));
	}

	/**
	 * @see Patient#getIdentifier(Integer)
	 * @verifies get identifier based on Integer
	 */
	@Test
	public void  getIdentifier_shouldGetPatientIdentifierBasedOnInteger() throws Exception {
	    Patient patient = new Patient();
	 
	 	PatientIdentifierType type1 = new PatientIdentifierType(new Integer(1));
	 	PatientIdentifierType type2 = new PatientIdentifierType(new Integer(2));
	 	PatientIdentifierType type3 = new PatientIdentifierType(new Integer(3));
	    when(patientIdentifier1.getIdentifierType()).thenReturn(type1);
	    when(patientIdentifier2.getIdentifierType()).thenReturn(type2);
	    when(patientIdentifier3.getIdentifierType()).thenReturn(type3);

	    when(patientIdentifier1.isVoided()).thenReturn(false);
	    when(patientIdentifier2.isVoided()).thenReturn(false);
	    when(patientIdentifier3.isVoided()).thenReturn(false);

	    when(patientIdentifier1.isPreferred()).thenReturn(true);
	    when(patientIdentifier2.isPreferred()).thenReturn(true);
	    when(patientIdentifier3.isPreferred()).thenReturn(true);


	    patient.addIdentifier(patientIdentifier1);
	    patient.addIdentifier(patientIdentifier2);
	    patient.addIdentifier(patientIdentifier3);

	    Assert.assertEquals(patientIdentifier3, patient.getPatientIdentifier(new Integer(3)));
	}

/**
	 * @see Patient#getIdentifier(String)
	 * @verifies get identifier based on String
	 */
	@Test
	public void  getIdentifier_shouldGetPatientIdentifierBasedOnString() throws Exception {
	    Patient patient = new Patient();
	 
	 	PatientIdentifierType type1 = new PatientIdentifierType(new Integer(1));
	 	PatientIdentifierType type2 = new PatientIdentifierType(new Integer(2));
	 	PatientIdentifierType type3 = new PatientIdentifierType(new Integer(3));
	 	type1.setName("1");
	 	type2.setName("2");
	 	type3.setName("3");
	    when(patientIdentifier1.getIdentifierType()).thenReturn(type1);
	    when(patientIdentifier2.getIdentifierType()).thenReturn(type2);
	    when(patientIdentifier3.getIdentifierType()).thenReturn(type3);

	    when(patientIdentifier1.isVoided()).thenReturn(false);
	    when(patientIdentifier2.isVoided()).thenReturn(false);
	    when(patientIdentifier3.isVoided()).thenReturn(false);

	    when(patientIdentifier1.isPreferred()).thenReturn(true);
	    when(patientIdentifier2.isPreferred()).thenReturn(true);
	    when(patientIdentifier3.isPreferred()).thenReturn(true);


	    patient.addIdentifier(patientIdentifier1);
	    patient.addIdentifier(patientIdentifier2);
	    patient.addIdentifier(patientIdentifier3);

	    Assert.assertEquals(patientIdentifier3, patient.getPatientIdentifier("3"));
	}

	/**
	 * @see Patient#getIdentifiers(PatientIdentifierType)
	 * @verifies get list of identifiers based on PatientIdentifierType
	 */
	@Test
	public void  getIdentifier_shouldGetPatientIdentifiersBasedOnPatientIdentifierType() throws Exception {
	    Patient patient = new Patient();
	 
	 	PatientIdentifierType type1 = new PatientIdentifierType(new Integer(1));
	 	PatientIdentifierType type2 = new PatientIdentifierType(new Integer(2));
	 	PatientIdentifierType type3 = new PatientIdentifierType(new Integer(2));
	    when(patientIdentifier1.getIdentifierType()).thenReturn(type1);
	    when(patientIdentifier2.getIdentifierType()).thenReturn(type2);
	    when(patientIdentifier3.getIdentifierType()).thenReturn(type3);

	    when(patientIdentifier1.isVoided()).thenReturn(false);
	    when(patientIdentifier2.isVoided()).thenReturn(false);
	    when(patientIdentifier3.isVoided()).thenReturn(false);

	    List<PatientIdentifier> expectedRet = new ArrayList<PatientIdentifier>();

	    when(patientIdentifier1.isPreferred()).thenReturn(true);
	    when(patientIdentifier2.isPreferred()).thenReturn(true);
	    when(patientIdentifier3.isPreferred()).thenReturn(true);


	    patient.addIdentifier(patientIdentifier1);
	    patient.addIdentifier(patientIdentifier2);
	    patient.addIdentifier(patientIdentifier3);

	    expectedRet.add(patientIdentifier2);


	    Assert.assertEquals(expectedRet, patient.getPatientIdentifiers(type2));
	}


}
