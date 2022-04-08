package com.parallel.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/Activities",
                     "src/test/resources/features/fleet"
        },
        glue = "com/parallel/step_definitions",
        dryRun = false,
        tags = "",
        plugin = {"html:target/default-cucumber-reports",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt"
        }
)
public class CucumberRunner {
}
