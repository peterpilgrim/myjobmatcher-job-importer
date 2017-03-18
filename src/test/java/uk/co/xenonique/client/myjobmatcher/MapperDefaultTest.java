package uk.co.xenonique.client.myjobmatcher;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * A unit test MapperDefaultTest to verify the operation of MapperDefaultTest
 *
 * @author Peter Pilgrim
 */
@RunWith(MockitoJUnitRunner.class)
public class MapperDefaultTest {

    private Mapper mapper;

    private XMLStreamReader streamReader;
    private JobConfigurationPropertiesLoader configuration;

    @Before
    public void setup() throws IOException {
        streamReader = mock(XMLStreamReader.class);
        final InputStream is = this.getClass().getResourceAsStream(CommonTestFixtures.TEST_JOB_BUNDLE_CONFIGURATION_FILE);
        configuration = new JobConfigurationPropertiesLoader();
        configuration.load(is);
        mapper = new MapperDefault();
        mapper.setNormalizer(new NormalizerDefault());
    }

    @Test
    public void map_to_job_title_from_job_XML_element() throws IOException, XMLStreamException {
        assertMappingXMLElement("jobTitle", "Java Architect",
                (x) -> assertThat(x.getTitle(), is("Java Architect")));
    }

    @Test
    public void map_to_job_description_from_XML_element() throws IOException, XMLStreamException {
        assertMappingXMLElement("jobDescription", "Design the trading system",
                (x) -> assertThat(x.getDescription(), is("Design the trading system")));
    }

    @Test
    public void map_to_job_location_from_XML_element() throws IOException, XMLStreamException {
        assertMappingXMLElement("location", "CITY", (x) -> assertThat(x.getLocation(), is("CITY")));
    }

    @Test
    public void map_to_job_reference_from_XML_element() throws IOException, XMLStreamException {
        assertMappingXMLElement("jobRefId", "Red1234", (x) -> assertThat(x.getReference(), is("Red1234")));
    }

    @Test
    public void map_to_job_url_from_XML_element() throws IOException, XMLStreamException {
        assertMappingXMLElement("applyURL", "http://acme.com/", (x) -> assertThat(x.getUrl(), is("http://acme.com/")));
    }


    private void assertMappingXMLElement(final String localName, final String elementText, final Consumer<JobRecord> assertFunction) throws IOException, XMLStreamException {
        when(streamReader.getLocalName()).thenReturn(localName);
        when(streamReader.getElementText()).thenReturn(elementText);

        final JobRecord jobRecord = new JobRecord();
        mapper.mapXmlPropertiesToJobRecord(configuration, streamReader, jobRecord);
        assertFunction.accept(jobRecord);

        verify(streamReader).getLocalName();
        verify(streamReader).getElementText();

    }
}
