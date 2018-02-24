package ch.rhj.appengine.security;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.platform.suite.api.UseTechnicalNames;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@UseTechnicalNames
@SuiteDisplayName("ch.rhj.appengine.security")
@SelectPackages("ch.rhj.appengine.security")
public class SecurityTestSuite {
}
