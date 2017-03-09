/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2017 by Peter Pilgrim, Milton Keynes, P.E.A.T UK LTD
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Creative Commons 3.0
 * Non Commercial Non Derivation Share-alike License
 * https://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 * Developers:
 * Peter Pilgrim -- design, development and implementation
 *               -- Blog: http://www.xenonique.co.uk/blog/
 *               -- Twitter: @peter_pilgrim
 *
 * Contributors:
 *
 *******************************************************************************/
package uk.co.xenonique.client.myjobmatcher;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * A unit test JobConfigurationPropertiesLoaderTest to verify the operation of JobConfigurationPropertiesLoaderTest
 *
 * @author Peter Pilgrim
 */
public class JobConfigurationPropertiesLoaderTest {

    @Test
    public void load_configuration_from_properties_file() throws IOException {
        final InputStream is = this.getClass().getResourceAsStream(CommonTestFixtures.TEST_JOB_BUNDLE_CONFIGURATION_FILE);

        final JobConfigurationPropertiesLoader configuration = new JobConfigurationPropertiesLoader();
        configuration.load(is);
        assertThat(configuration.getSentinelNodeElementName(), is("job"));
        assertThat(configuration.getTitleElementName(), is("jobTitle"));
        assertThat(configuration.getDescriptionElementName(), is("jobDescription"));
        assertThat(configuration.getLocationElementName(), is("location"));
        assertThat(configuration.getReferenceElementName(), is("jobRefId"));
        assertThat(configuration.getUrlElementName(), is("applyURL"));
    }

}

