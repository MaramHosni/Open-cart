package com.opencart.tests;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomepageTest extends BaseTest {


    @Test
    public void testHomepageTitle() {
        System.out.println("بدء اختبار عنوان الصفحة الرئيسية...");


        driver.get("https://demo.opencart.com");
        System.out.println("تم فتح الصفحة الرئيسية: " + driver.getCurrentUrl());


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logo")));
        System.out.println("تم تحميل الصفحة الرئيسية بنجاح.");


        String expectedTitle = "Your Store";
        String actualTitle = driver.getTitle();
        System.out.println("العنوان الفعلي للصفحة: " + actualTitle);

        Assertions.assertEquals(expectedTitle, actualTitle, "فشل التحقق من عنوان الصفحة الرئيسية");
        System.out.println("✅ نجح اختبار عنوان الصفحة الرئيسية!");
    }
}
