package com.opencart.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductTest extends BaseTest {

    @Test
    public void testProductDetailsAndAddToCart() {
        System.out.println("بدء اختبار تفاصيل المنتج وإضافة إلى السلة...");

        driver.get("https://demo.opencart.com");
        System.out.println("تم فتح الصفحة الرئيسية: " + driver.getCurrentUrl());


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement firstProductLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-layout h4 a")));
        String productName = firstProductLink.getText();
        System.out.println("النقر على المنتج: " + productName);
        firstProductLink.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-cart")));

        WebElement productTitleElement = driver.findElement(By.cssSelector("div.col-sm-4 h1"));
        String actualProductTitle = productTitleElement.getText();
        System.out.println("عنوان صفحة المنتج: " + actualProductTitle);
        Assertions.assertEquals(productName, actualProductTitle, "فشل التحقق من عنوان صفحة المنتج");

        WebElement productPriceElement = driver.findElement(By.cssSelector(".list-styled h2"));
        String productPrice = productPriceElement.getText();
        System.out.println("سعر المنتج: " + productPrice);
        Assertions.assertFalse(productPrice.isEmpty(), "سعر المنتج غير معروض");

        WebElement addToCartButton = driver.findElement(By.id("button-cart"));
        addToCartButton.click();
        System.out.println("تم النقر على زر إضافة إلى سلة التسوق");

        WebElement successAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
        String successMessage = successAlert.getText();
        System.out.println("رسالة التأكيد: " + successMessage);

        Assertions.assertTrue(successMessage.contains("Success: You have added " + productName + " to your shopping cart!"),
                "فشل التحقق من رسالة إضافة المنتج إلى السلة");
        System.out.println("✅ نجح اختبار تفاصيل المنتج وإضافة إلى السلة!");
    }
}