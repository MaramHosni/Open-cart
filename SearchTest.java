package com.opencart.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SearchTest extends BaseTest {

    @Test
    public void testSearchFunctionality() {
        System.out.println("بدء اختبار وظيفة البحث...");

        driver.get("https://demo.opencart.com");
        System.out.println("تم فتح الصفحة الرئيسية: " + driver.getCurrentUrl());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated (By.xpath("//input[@name='filter_search']")));

        String searchTerm = "phone";
        searchBox.clear();
        searchBox.sendKeys(searchTerm);
        System.out.println("تم إدخال كلمة البحث: " + searchTerm);

        WebElement searchButton = driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg"));
        searchButton.click();
        System.out.println("تم النقر على زر البحث");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#content h1")));

        WebElement searchHeading = driver.findElement(By.cssSelector("#content h1"));
        String actualHeading = searchHeading.getText();
        String expectedHeading = "Search - " + searchTerm;

        System.out.println("عنوان صفحة نتائج البحث: " + actualHeading);
        Assertions.assertEquals(expectedHeading, actualHeading, "فشل التحقق من عنوان صفحة نتائج البحث");

        List<WebElement> products = driver.findElements(By.cssSelector(".product-layout"));
        System.out.println("عدد المنتجات في نتائج البحث: " + products.size());

        Assertions.assertTrue(products.size() > 0, "لم يتم العثور على أي منتجات في نتائج البحث");
        System.out.println("✅ نجح اختبار وظيفة البحث!");
    }
}