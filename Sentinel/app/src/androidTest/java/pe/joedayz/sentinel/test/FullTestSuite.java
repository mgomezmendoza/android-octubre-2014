package pe.joedayz.sentinel.test;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by josediaz on 11/15/14.
 */
public class FullTestSuite extends TestSuite {
    public static Test suite() {
        return new TestSuiteBuilder(FullTestSuite.class)
                .includeAllPackagesUnderHere().build();
    }

    public FullTestSuite() {
        super();
    }
}
