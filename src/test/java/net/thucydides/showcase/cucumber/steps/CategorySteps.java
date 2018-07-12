package net.thucydides.showcase.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import net.thucydides.showcase.cucumber.steps.serenity.BuyerSteps;

public class CategorySteps {

    String testcaseFacetValue;
    String testcaseCategory;


    @Steps
    BuyerSteps buyer;


    @Given("^I have a facet filter \"([^\"]*)\" enabled for category \"([^\"]*)\" using link \"([^\"]*)\"$")
    public void buyerWantsToBuy(String facet, String category, String urlpath) {
        testcaseFacetValue= facet;
        testcaseCategory= category;
        buyer.navigateTo(urlpath);
    }

    @Then("^facet filter should be enabled$")
    public void facet_filter_should_be_enabled() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        buyer.verifyFacetEnabled(testcaseFacetValue);
    }

    @Then("^the top header highlighting is correct$")
    public void the_top_header_highlighting_is_correct() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        buyer.verifyHighlighting(testcaseCategory);
    }




}
