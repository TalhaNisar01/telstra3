package au.com.telstra.simcardactivator;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.junit.platform.engine.Cucumber;

@SpringBootTest
@CucumberContextConfiguration
@Cucumber
@ActiveProfiles("test")
public class SimCardActivatorApplicationTests {
}
