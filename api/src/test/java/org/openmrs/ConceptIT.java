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

import java.util.Collections;
import java.util.List;
import java.util.Vector;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.junit.Test;

import org.openmrs.test.BaseContextMockTest;

import java.util.Locale;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
/**
 * Contains integration tests of the Concept class.
 */
public class ConceptIT extends BaseContextMockTest {
	

	@Mock
	ConceptService conceptService;

	@Before 
	public void initMocks() {
           MockitoAnnotations.initMocks(this);
    }
	/**
	 * @verifies return a name in the matching locale if exact is set to false
	 * @see Concept#getName(java.util.Locale, boolean)
	 */
	@Test
	public void getName_shouldReturnANameInTheMatchingLocaleIfExactIsSetToFalse() throws Exception {
		Concept concept = new Concept();
		ConceptName frenchConceptName = new ConceptName("frenchName", Locale.FRENCH);
		ConceptName englishConceptName = new ConceptName("enqlishName", Locale.ENGLISH);
		
		concept.addName(englishConceptName);
		concept.addName(frenchConceptName);
		
		assertEquals(frenchConceptName, concept.getName(Locale.FRENCH));
	}

	/**
	 * @verifies finds a value that ConceptService pulled from a list of concepts
	 * @see Concept#findPossibleValues(String)
	 */
	@Test
	public void findPossibleValues_shouldReturnListOfConceptsFromResults() throws Exception {
		Concept concept = new Concept();
		List<Concept> expectedConcepts = new Vector<Concept>();
		expectedConcepts.add(new Concept(1));
		expectedConcepts.add(new Concept(2));
		List<ConceptSearchResult> conceptSearchResults = new Vector<ConceptSearchResult>();
		conceptSearchResults.add(new ConceptSearchResult("result", expectedConcepts.get(0), new ConceptName("result", Locale.ENGLISH)));
		conceptSearchResults.add(new ConceptSearchResult("result", expectedConcepts.get(1), new ConceptName("result", Locale.ENGLISH)));
		when(conceptService.getConcepts("result", Collections.singletonList(Context.getLocale()), false, null, null, null, null, null, null, null)).thenReturn(conceptSearchResults);
		List<Concept> resultConcepts = concept.findPossibleValues("result");
		assertEquals(expectedConcepts, resultConcepts);

	}
}
