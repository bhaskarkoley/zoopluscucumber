import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
strict = true,
features = {"/home/osboxes/IdeaProjects/bdd/serenity-demos-master/cucumber-webtests/src/test/resources/features/category/facet_filter.feature:20"},
plugin = {"json:/home/osboxes/IdeaProjects/bdd/serenity-demos-master/cucumber-webtests/target/cucumber-parallel/6.json"},
monochrome = false,
glue = {"net.thucydides.showcase.cucumber.steps"})
public class Parallel06IT {
}