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

import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.List;

/**
 * The type ElasticPathExporterProxy
 *
 * @author Peter Pilgrim (peter)
 */
@Singleton
public class ElasticPathExporterProxy implements ElasticPathExporter {

    private List<JobRecord> exportedJobs = new LinkedList<>();

    // Connection URL properties to go here
    @Override
    public void exportBundle(List<JobRecord> jobRecordList) {

        // Open the session to ElasticSearch

        for (int j = 0; j < jobRecordList.size(); ++j) {
            exportedJobs.add(jobRecordList.get(j));
            System.out.printf("   [%d] export to Elastic API jobRecord=%s\n", j, jobRecordList.get(j));
        }

        // Close the session to ElasticSearch
    }

    public List<JobRecord> getExportedJobs() {
        return exportedJobs;
    }
}
