package restassured.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class Helper {
    private String pageToTest = "https://trello.com/";
    WebDriver driver = new ChromeDriver();

    public WebDriver initializeWebDriver() {
        driver.get(pageToTest);
        return driver;
    }

    public void logIn(String login, String password) {
        driver.findElement(By.cssSelector("body > div.global-header.u-clearfix > div.global-header-section.mod-right > a:nth-child(1)")).click();
        driver.findElement(By.id("user")).sendKeys(login);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login")).click();
    }

    public void createNewBoard(String boardName){
        driver.findElement(By.cssSelector("#header > div.header-user > a.header-btn.js-open-add-menu > span")).click();
        driver.findElement(By.cssSelector("#classic > div.pop-over.is-shown > div > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > a > span")).click();
        driver.findElement(By.id("boardNewTitle")).sendKeys(boardName);
        driver.findElement(By.cssSelector("#classic > div.pop-over.is-shown > div > div:nth-child(2) > div > div > div > form > input.primary.wide.js-submit")).click();

        goBackToHomePage();
        }
    public void deleteBoards(String name) {
        for (int j = 0; j < driver.findElements(By.className("board-tile-details-name")).size(); j++) {
            List<WebElement> listaFor = driver.findElements(By.className("board-tile-details-name"));
            if (listaFor.get(j).getAttribute("title").equals(name)) {
                listaFor.get(j).click();
                waitForPageLoaded();
                driver.findElement(By.cssSelector("#content > div > div.board-menu.js-fill-board-menu > div > div > div.board-menu-content.u-fancy-scrollbar.js-board-menu-content-wrapper > div > ul > li:nth-child(5) > a")).click();
                waitForPageLoaded();
                driver.findElement(By.cssSelector("#content > div > div.board-menu.js-fill-board-menu > div > div > div.board-menu-content.u-fancy-scrollbar.js-board-menu-content-wrapper > div > ul:nth-child(5) > li > a")).click();
                waitForPageLoaded();

                driver.findElement(By.xpath("//*[@id=\"classic\"]/div[5]/div/div[2]/div/div/div/input")).click();
                waitForPageLoaded();
                driver.findElement(By.cssSelector("#content > div > div > p:nth-child(3) > a")).click();
                waitForPageLoaded();
                driver.findElement(By.xpath("//*[@id=\"classic\"]/div[5]/div/div[2]/div/div/div/input")).click();
                goBackToHomePage();
                waitForPageLoaded();
                listaFor.remove(j);
                j--;
            }
        }

    }

    public void goBackToHomePage() {
        waitForPageLoaded();
        driver.findElement(By.cssSelector("#header > a > span.header-logo-default")).click();
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
}
