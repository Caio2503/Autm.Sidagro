package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class paginaHome {
    public static void paginaHome(WebDriver driver) {

        WebElement divisaoMunicipal = driver.findElement(By.id("router-link-divisao-municipal"));
        divisaoMunicipal.click();

    }
}
