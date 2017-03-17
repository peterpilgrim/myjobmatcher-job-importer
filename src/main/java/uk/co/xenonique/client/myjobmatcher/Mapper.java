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
