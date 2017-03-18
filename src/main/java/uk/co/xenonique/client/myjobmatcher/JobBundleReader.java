package uk.co.xenonique.client.myjobmatcher;

import java.io.InputStream;
import java.util.List;

/**
 * The type JobBundleReader
 *
 * @author Peter Pilgrim (peter)
 */
public interface JobBundleReader {
    List<JobRecord> load(JobConfigurationPropertiesLoader configuration, InputStream is);

    void setMapper(Mapper mapper);
}
