package uk.co.xenonique.client.myjobmatcher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * The type JobImporterApplication
 *
 * @author Peter Pilgrim (peter)
 */
@Singleton
public class JobImporterApplication {

    @Inject
    private Normalizer normalizer;
    @Inject
    private Mapper mapper;
    @Inject
    private JobBundleReader jobBundleReader;
    @Inject
    private JobConfigurationPropertiesLoader propertiesLoader;
    @Inject
    private ElasticExporter exporter;

    public void importJobs() {

        System.out.printf("%s.importJobs()\n", getClass().getSimpleName());
        System.out.printf("normalizer=%s\n", normalizer);
        System.out.printf("mapper=%s\n", normalizer);
        System.out.printf("jobBundleReader=%s\n", jobBundleReader);
        System.out.printf("propertiesLoader=%s\n", propertiesLoader);
        System.out.printf("exporter=%s\n", exporter);


        try {
            final InputStream is1 = this.getClass().getResourceAsStream("/xanadu-configuration.properties");
            propertiesLoader.load(is1);

            final InputStream is2 = this.getClass().getResourceAsStream("/xanadu-board-bundle.xml");
            final List<JobRecord> jobRecordList = jobBundleReader.load(propertiesLoader, is2);

            exporter.exportBundle(jobRecordList);

        } catch (IOException e) {
            e.printStackTrace(System.err);
        }


    }
}
