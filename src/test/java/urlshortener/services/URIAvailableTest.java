package urlshortener.services;

import org.junit.Test;
import urlshortener.service.URIAvailable;

/**
 *
 */

public class URIAvailableTest {

    private URIAvailable availableURI = new URIAvailable();

    /**
     * Checks if it returns false given a uri that doesn't exist
     */
    @Test
    public void isURIAvailableFalse(){
        assert !availableURI.isURIAvailable("http://www.thisdomainIhopedoesnotexist.es");
    }

    /**
     * Checks if it returns true given a uri that exist
     */
    @Test
    public void isURIAvailableTrue(){
        assert availableURI.isURIAvailable("http://www.google.es");
    }

    /**
     * Check that the check time is longer for a uri that has not been registered.
     */
    @Test
    public void checkTimeURI(){
        String uri = "https://www.google.es";

        long timeBefore = System.currentTimeMillis();
        // Call to the service
        availableURI.isURIAvailable(uri);
        long timeWithoutRegister = System.currentTimeMillis() - timeBefore;

        // Now register the uri
        availableURI.saveURI(uri);
        timeBefore = System.currentTimeMillis();
        // Call to the service
        availableURI.isURIAvailable(uri);
        long timeWithRegister = System.currentTimeMillis() - timeBefore;

        assert timeWithoutRegister > timeWithRegister;
    }
}
