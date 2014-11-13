package org.eclipse.stardust.test.model.bpmn2.sdbpmn;

import static org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2.camelCase;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CamelCaseTest {

	@Test
	public void camelCaseTest() {

		assertThat("Basic CamelCase works", camelCase("some loose words"), is("someLooseWords"));
		assertThat("Underlines remain", camelCase("some_loose _ words"), is("some_loose_Words"));
		assertThat("Numbers work", camelCase("number 1 and number 5"), is("number1AndNumber5"));
		assertThat("Null proof (returns null)", camelCase(null), nullValue());
		assertThat("No change without blanks", camelCase("ImASatisfyingString"), is("ImASatisfyingString"));
		assertThat("Single character parts are simply concatenated as Upper-Case letters", camelCase("a b c"), is("aBC"));

	}
}
