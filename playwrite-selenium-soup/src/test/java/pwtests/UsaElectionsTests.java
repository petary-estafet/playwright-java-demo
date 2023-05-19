package pwtests;

import com.microsoft.playwright.Page;
import org.testng.Assert;
import org.testng.annotations.Test;
import pw.UsaElectionsPage;

public class UsaElectionsTests extends BaseTestPw{


    Page page;
    UsaElectionsPage usaElectionsPage;


    @Test
    public void electionsTest(){
        usaElectionsPage = new UsaElectionsPage(page);
        usaElectionsPage
                .clickElectionsYear("2012")
                .assertWinningCandidate("Barack Obama (Democrat)")
                .assertCandidateStats("Electoral Votes: 341 (63%)");
    }

    @Test
    public void electionsWinTest(){
        usaElectionsPage = new UsaElectionsPage(page);
        usaElectionsPage
                .clickElectionsYear("2016")
                .assertWinningCandidate("Donald Trump (Republican)")
                .assertCandidateStats("Electoral Votes: 304 (56.5%)");
    }

    @Test
    public void spyNetworkHeadersTest(){
        usaElectionsPage = new UsaElectionsPage(page);
        String networkHeaders = usaElectionsPage.spyNetworkHeaders("usa-elections/data/elections-recent.json");
        System.out.println(networkHeaders);
        Assert.assertTrue(networkHeaders.contains("content-security-policy=frame-ancestors"));
    }

}
