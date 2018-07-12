package net.thucydides.showcase.cucumber.steps.serenity;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.showcase.cucumber.pages.*;

import static org.assertj.core.api.Assertions.assertThat;

public class BuyerSteps extends ScenarioSteps {

    HomePage homePage;
    CategoryPage categoryPage;

    @Step
    public void opens_home_page() {
        homePage.open();
    }

    @Step
    public void visit_link() {
        homePage.open();
    }


    public void navigateTo(String urlpath) {
        homePage.navigateTo(urlpath);
    }

    public void verifyFacetEnabled(String facet) {
        categoryPage.verifyFacetEnabled(facet);
    }

    public void verifyHighlighting(String category) {
        categoryPage.verifyHighlighting(category);
    }
}
