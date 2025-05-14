package com.opencart.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ShoppingCartTest extends BaseTest {

    @Test
    public void testAddProductAndVerifyInCart() {
        System.out.println("بدء اختبار سلة التسوق...");
        String productToAdd = "MacBook";

        driver.get("https://demo.opencart.com");
        System.out.println("تم فتح الصفحة الرئيسية: " + driver.getCurrentUrl());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));
        searchBox.clear();
        searchBox.sendKeys(productToAdd);
        WebElement searchButton = driver.findElement(By.cssSelector(".btn-default.btn-lg"));
        searchButton.click();
        System.out.println("تم البحث عن المنتج: " + productToAdd);

        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(productToAdd)));
        productLink.click();
        System.out.println("تم النقر على رابط المنتج");

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-cart")));
        addToCartButton.click();
        System.out.println("تمت إضافة المنتج إلى السلة");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));

        WebElement cartButton = driver.findElement(By.id("cart"));
        cartButton.click();
        System.out.println("تم فتح قائمة السلة المنسدلة");

        WebElement viewCartLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View Cart")));
        viewCartLink.click();
        System.out.println("تم الانتقال إلى صفحة سلة التسوق");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#content h1")));
        WebElement cartProductNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table-responsive .text-left a")));
        String cartProductName = cartProductNameElement.getText();
        System.out.println("المنتج الموجود في السلة: " + cartProductName);

        Assertions.assertEquals(productToAdd, cartProductName, "فشل التحقق من اسم المنتج في سلة التسوق");
        System.out.println("✅ نجح اختبار سلة التسوق!");
    }
}