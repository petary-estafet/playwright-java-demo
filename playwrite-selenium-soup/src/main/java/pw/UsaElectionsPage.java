package pw;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import common.Locators;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class UsaElectionsPage extends BasePagePw{

    public UsaElectionsPage(Page page) {
        super(page);
    }

    public UsaElectionsPage clickElectionsYear(String year){
        page.onConsoleMessage(msg -> System.out.println(msg.text()));
        click( Locators.Elections.BUTTON_YEAR + "'" + year + "']");
        return this;
    }

    public UsaElectionsPage assertWinningCandidate(String expectedStats){
        page.onConsoleMessage(msg -> System.out.println(msg.text()));
        hover(Locators.Elections.DATA_CHART);
        assertThat(page.locator(Locators.Elections.RATIO_STATS_PER_CADIDATE).first()).hasText(expectedStats);
        return this;
    }

    public UsaElectionsPage assertCandidateStats(String expectedStats){
        page.onConsoleMessage(msg -> System.out.println(msg.text()));
        hover(Locators.Elections.DATA_CHART);
        assertThat(page.locator(Locators.Elections.RATIO_STATS_PER_CADIDATE).last()).hasText(expectedStats);
        return this;
    }

    public String spyNetworkHeaders(String url){
        Response response = page.waitForResponse(r -> r.url().contains(url), () -> {
        });
        return  response.allHeaders().toString();
    }

    public String spyNetworkHResponseBody(String url){
        Response response = page.waitForResponse(r -> r.url().contains(url), () -> {
        });
        return  response.body().toString();
    }


}
