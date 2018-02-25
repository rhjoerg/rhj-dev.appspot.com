package ch.rhj.dev.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.junit.jupiter.api.Test;

public class DeployTest {

	@Test
	public void deploy() throws Exception {
		
		File buildDirectory = new File("build/deploytest");
		
		if (buildDirectory.exists())
			FileUtils.deleteDirectory(buildDirectory);
		
		assertFalse(buildDirectory.exists());
		buildDirectory.mkdirs();
		
		URL buildFileUrl = DeployTest.class.getResource("deploytest.build.gradle");
		URL settingsFileUrl = DeployTest.class.getResource("deploytest.settings.gradle");
		
		File buildFile = new File(buildDirectory, "build.gradle");
		File settingsFile = new File(buildDirectory, "settings.gradle");
		
		FileUtils.copyURLToFile(buildFileUrl, buildFile);
		FileUtils.copyURLToFile(settingsFileUrl, settingsFile);
		
		BuildResult result = GradleRunner
				.create()
				.withProjectDir(buildDirectory)
				.forwardOutput()
				.withArguments("publish")
				.build();
		
		assertTrue(result.task(":publish").getOutcome() == TaskOutcome.SUCCESS);
	}
}
