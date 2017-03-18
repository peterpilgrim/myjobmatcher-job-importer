package uk.co.xenonique.client.myjobmatcher;

import javax.inject.Singleton;

/**
 * The type JobImporterApplication
 *
 * @author Peter Pilgrim (peter)
 */
@Singleton
public class JobImporterApplication {
    
    public void importJobs() {

        System.out.printf("%s.importJobs()\n", getClass().getSimpleName());
    }
}
