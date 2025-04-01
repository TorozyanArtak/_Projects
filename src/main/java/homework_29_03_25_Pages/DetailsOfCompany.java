package homework_29_03_25_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public class DetailsOfCompany extends CompanyPage{

    private final By nameLoc = By.xpath("//h1[@role='heading']");
    private final By industryLoc = By.xpath("//div[text() ='Industry']/following-sibling::div");
    private final By pageViws = By.xpath("//div[img[@alt=\"company-poster\"]]//following-sibling::div/div[3]/div[1]");
    private final By followers = By.xpath("//div[img[@alt=\"company-poster\"]]//following-sibling::div/div[3]/div[2]");
    private final By activeJob = By.xpath("//div[img[@alt=\"company-poster\"]]//following-sibling::div/div[3]/div[3]");
    private final By jobHistory = By.xpath("//div[img[@alt=\"company-poster\"]]//following-sibling::div/div[3]/div[4]");
    private String name = driver.findElement(nameLoc).getText().toLowerCase() ;
    private String industry;
    public final Integer countPageViews = Integer.parseInt(driver.findElement(pageViws).getText().replaceAll("[^0-9]", ""));
    public final Integer countFollowers =Integer.parseInt(driver.findElement(followers).getText().replaceAll("[^0-9]", ""));
    public final Integer countActiveJob=Integer.parseInt(driver.findElement(activeJob).getText().replaceAll("[^0-9]", ""));
    public final Integer countJobHistory=Integer.parseInt(driver.findElement(jobHistory).getText().replaceAll("[^0-9]", ""));
//    public DetailsOfCompany(String name, String industry, Integer countPageViews, Integer countFollowers, Integer countActiveJob, Integer countJobHistory) {
//        this.name = name;
//        this.industry = industry;
//        this.countPageViews = countPageViews;
//        this.countFollowers = countFollowers;
//        this.countActiveJob = countActiveJob;
//        this.countJobHistory = countJobHistory;
//    }

//    public DetailsOfCompany(String name) {
//        this.name = name;
//    }
    public void getDetails(){
        System.out.println( countPageViews);
        System.out.println(countFollowers);
        System.out.println(countActiveJob);
        System.out.println(countJobHistory);

    }

    public String getIndustry() {
        return driver.findElement(industryLoc).getText().toLowerCase();
    }



//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        DetailsOfCompany that = (DetailsOfCompany) o;
//        return Objects.equals(name, that.name) && Objects.equals(countPageViews, that.countPageViews) && Objects.equals(countFollowers, that.countFollowers) && Objects.equals(countActiveJob, that.countActiveJob) && Objects.equals(countJobHistory, that.countJobHistory);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, countPageViews, countFollowers, countActiveJob, countJobHistory);
//    }
}
