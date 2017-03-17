package uk.co.xenonique.client.myjobmatcher;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * The type MapperDefault
 *
 * @author Peter Pilgrim (peter)
 */
public class MapperDefault implements Mapper {

    private Normalizer normalizer = new NormalizerDefault();


    @Override
    public void mapXmlPropertiesToJobRecord(JobConfigurationPropertiesLoader configuration, XMLStreamReader streamReader, JobRecord jobRecord) throws XMLStreamException {
        final String localName = streamReader.getLocalName();
        if (configuration.getTitleElementName().equalsIgnoreCase(localName)) {
            jobRecord.setTitle(normalizer.notNullAndNormalise(streamReader.getElementText()));
        }
        if (configuration.getDescriptionElementName().equalsIgnoreCase(localName)) {
            jobRecord.setDescription(normalizer.notNullAndNormalise(streamReader.getElementText()));
        }
        if (configuration.getLocationElementName().equalsIgnoreCase(localName)) {
            jobRecord.setLocation(normalizer.notNullAndNormalise(streamReader.getElementText()));
        }
        if (configuration.getReferenceElementName().equalsIgnoreCase(localName)) {
            jobRecord.setReference(normalizer.notNullAndNormalise(streamReader.getElementText()));
        }
        if (configuration.getUrlElementName().equalsIgnoreCase(localName)) {
            jobRecord.setUrl(normalizer.notNullAndNormalise(streamReader.getElementText()));
        }
    }

    @Override
    public Normalizer getNormalizer() {
        return normalizer;
    }

    @Override
    public void setNormalizer(Normalizer normalizer) {
        this.normalizer = normalizer;
    }

}
