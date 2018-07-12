import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
strict = true,
features = {"/home/osboxes/IdeaProjects/bdd/serenity-demos-master/cucumber-webtests/src/test/resources/features/category/facet_filter.feature:23"},
plugin = {"json:/home/osboxes/IdeaProjects/bdd/serenity-demos-master/cucumber-webtests/target/cucumber-parallel/9.json"},
monochrome = false,
glue = {"net.thucydides.showcase.cucumber.steps"})
public class Parallel09IT {
}