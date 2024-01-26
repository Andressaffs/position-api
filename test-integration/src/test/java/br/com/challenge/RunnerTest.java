package br.com.challenge;

import br.com.challenge.steps.LocalizarPetSteps;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

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
