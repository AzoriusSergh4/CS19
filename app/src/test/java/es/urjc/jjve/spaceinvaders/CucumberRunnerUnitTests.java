package es.urjc.jjve.spaceinvaders;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/es/urjc/jjve/spaceinvaders/featuresUnitTests", glue="es.urjc.jjve.spaceinvaders.stepDefinitionUnitTests")
public class CucumberRunnerUnitTests {
}
