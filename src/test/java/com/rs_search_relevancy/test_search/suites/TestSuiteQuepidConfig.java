/**
 * 
 */
package com.rs_search_relevancy.test_search.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.rs_search_relevancy.test_search.quepid.TestQuepidInvalidConfig;
import com.rs_search_relevancy.test_search.quepid.TestQuepidInvalidVariables;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestQuepidInvalidConfig.class,
    TestQuepidInvalidVariables.class
})
public class TestSuiteQuepidConfig {
}