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

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * A unit test JobBundleReaderTest to verify the operation of JobBundleReader
 *
 * @author Peter Pilgrim
 */
public class JobBundleReaderTest {

    private JobConfigurationPropertiesLoader configuration;

    @Before
    public void setup() throws IOException {
        final InputStream is = this.getClass().getResourceAsStream(CommonTestFixtures.TEST_JOB_BUNDLE_CONFIGURATION_FILE);
        configuration = new JobConfigurationPropertiesLoader();
        configuration.load(is);
    }

    @Test
    public void read_bundle() throws IOException {
        final InputStream is = this.getClass().getResourceAsStream(CommonTestFixtures.TEST_JOB_BUNDLE_DATA_FILE);
        final JobBundleReader reader = new JobBundleReader();
        final List<JobRecord> jobRecordList = reader.load(configuration, is);
        assertThat(jobRecordList, is(notNullValue()));
        assertThat(jobRecordList.size(), is(1));
        assertThat(jobRecordList.get(0).getTitle(), is("Technical/Software Architect"));
        assertThat(jobRecordList.get(0).getDescription(), startsWith("Sopra Steria Recruitment is working in partnership"));
        assertThat(jobRecordList.get(0).getLocation(), is("LONDON ."));
        assertThat(jobRecordList.get(0).getReference(), is("JS_SOFTWARE ARCHITECT_AC_2051"));
        assertThat(jobRecordList.get(0).getUrl(), startsWith("https://www.jobserve.com/W1118294FB0EE26FB.jsap?src=D4C01DF1DAFBC22C9635"));
    }
}
