package uk.co.xenonique.client.myjobmatcher;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 * The type MainApplication
 *
 * @author Peter Pilgrim (peter)
 */
public class MainApplication {
    public static void main( String args[]) {
        Weld weld = new Weld();

        WeldContainer container = weld.initialize();

        container.select(JobImporterApplication.class).get().importJobs();
    }
}
