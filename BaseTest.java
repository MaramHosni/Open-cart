package com.opencart.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;


    @BeforeEach
    public void setUp() {

       io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();


        ChromeOptions options = new ChromeOptions();
       /*
       options.addArguments("--headless");
        options.addArguments("--no-sandbox");
       options.addArguments("--disable-dev-shm-usage");
      */


        driver = new ChromeDriver(options);


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(30));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}