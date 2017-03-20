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

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


/**
 * The type MainApplicationTest verifies the operation of {@link MainApplication}
 *
 * @author Peter Pilgrim (peter)
 */

public class MainApplicationTest {


    private WeldContainer container;
    private Weld weld;


    @Before
    public void setup() {
        weld = new Weld();
        container = weld.initialize();
    }

    @After
    public void tearDown() {
        if (weld != null) {
            weld.shutdown();        /*1*/
            weld = null;            /*2*/
        }
    }

    @Test
    public void load_main_application_and_import_sample_data() {
        container.instance().select(JobImporterApplication.class).get().importJobs();
        final ElasticSearchExporter exporter = container.instance().select(ElasticSearchExporter.class).get();
        assertThat("dependency injection is broken", exporter, is(notNullValue()));
        System.out.printf("exporter=%s\n", exporter);
        assertThat(((ElasticSearchExporterProxy) exporter).getExportedJobs().size(), is(1));
    }

}
