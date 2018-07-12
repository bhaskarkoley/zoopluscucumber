package net.thucydides.showcase.cucumber.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;


/**
 * Created by Bhaskar on 09/07/18.
 */
public class CategoryPage extends PageObject {

    public void verifyFacetEnabled(String inputfacet) {
        List<WebElementFacade> facets= findAll(By.cssSelector("#product-facet > div._tst_categoryOverviewFacet-flavour.facet.js-facet.active > div.facet-values.js-facet-values.js-facet-form > ul > li"));
        for (WebElement facet:facets)
        {
            if (facet.getText().contains(inputfacet)) {
                assertThat(facet.findElement(By.cssSelector("label > input")).isSelected());
            }
        }
    }

    public void verifyHighlighting(String category) {
        List<WebElementFacade> categorylinks  = findAll(By.cssSelector("body > main > header > div.nav-bottom > div > nav > ul.nav.nav-pills.js-offcanvas-links > li > a"));
        for (WebElement categorylink:categorylinks)
        {
            if (categorylink.getAttribute("title").contains(category)) {
                assertThat(categorylink.getAttribute("class").contains("active"));
            }
        }
    }

}
