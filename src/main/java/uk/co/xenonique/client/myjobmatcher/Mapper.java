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

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * The type Mapper
 *
 * @author Peter Pilgrim (peter)
 */
public interface Mapper {
    /**
     * Map the stream reader to the job record according the properties configuration
     *
     * @param configuration the properties configuration
     * @param streamReader  the stream reader
     * @param jobRecord     the job record
     * @throws XMLStreamException
     */
    void mapXmlPropertiesToJobRecord(JobConfigurationPropertiesLoader configuration, XMLStreamReader streamReader, JobRecord jobRecord) throws XMLStreamException;

    Normalizer getNormalizer();

    void setNormalizer(Normalizer normalizer);
}
