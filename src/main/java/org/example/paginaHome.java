package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class paginaHome {
    public static void paginaHome(WebDriverWait wait) {
        WebElement divisaoMunicipal = wait.until(ExpectedConditions.elementToBeClickable(By.id("router-link-divisao-municipal")));
        divisaoMunicipal.click();
    }
}
