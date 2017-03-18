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

import java.util.List;

/**
 * The type ElasticExporter
 *
 * @author Peter Pilgrim (peter)
 */
public class ElasticExporter {

    // Connection URL properties to go here

    public void exportBundle(List<JobRecord> jobRecordList) {

        // Open the session to ElasticSearch

        for (JobRecord jobRecord : jobRecordList) {

            System.out.printf("export to Elastic API jobRecord=%s\n", jobRecord );
        }

        // Close the session to ElasticSearch
    }
}
