package ch.rhj.dev.integration;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.UseTechnicalNames;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@UseTechnicalNames
@SelectPackages("ch.rhj.dev.integration")
public class IntegrationSuite {
}
