package com.opencart.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AdminLoginTest extends BaseTest {


    @Test
    public void testAdminLogin() {
        System.out.println("بدء اختبار تسجيل الدخول إلى لوحة الإدارة...");

        driver.get("https://demo.opencart.com/admin");
        System.out.println("تم فتح صفحة تسجيل الدخول إلى لوحة الإدارة: " + driver.getCurrentUrl());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-username")));

        String username = "demo";
        String password = "demo";

        usernameField.clear();
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("input-password"));
        passwordField.clear();
        passwordField.sendKeys(password);

        System.out.println("تم إدخال بيانات الاعتماد: اسم المستخدم=" + username + ", كلمة المرور=" + password);

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();
        System.out.println("تم النقر على زر تسجيل الدخول");

        try {
            WebElement modalDialog = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".modal-content")));
            WebElement closeButton = modalDialog.findElement(By.cssSelector(".modal-header button.close"));
            closeButton.click();
            System.out.println("تم إغلاق نافذة الإشعارات");
        } catch (Exception e) {
            System.out.println("لم تظهر نافذة إشعارات");
        }

        WebElement dashboardHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content .page-header h1")));
        String dashboardTitle = dashboardHeading.getText();
        System.out.println("عنوان لوحة التحكم: " + dashboardTitle);

        Assertions.assertEquals("Dashboard", dashboardTitle, "فشل التحقق من عنوان لوحة التحكم بعد تسجيل الدخول");
        System.out.println("✅ نجح اختبار تسجيل الدخول إلى لوحة الإدارة!");
    }
}