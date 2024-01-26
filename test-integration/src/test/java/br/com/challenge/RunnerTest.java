package br.com.challenge;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {"pretty", "json:target/cucumber-reports/cucumber.json"},
        features = "src/test/resources",
        extraGlue = "src/test/java/br/com/challenge"
)
@CucumberContextConfiguration
@ContextConfiguration(classes = {Application.class})
public class RunnerTest {

}
