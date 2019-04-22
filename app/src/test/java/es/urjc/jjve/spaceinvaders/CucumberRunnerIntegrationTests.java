package es.urjc.jjve.spaceinvaders;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/es/urjc/jjve/spaceinvaders/featuresIntegrationTests", glue="es.urjc.jjve.spaceinvaders.stepDefinitionIntegrationTests")
public class CucumberRunnerIntegrationTests {
}