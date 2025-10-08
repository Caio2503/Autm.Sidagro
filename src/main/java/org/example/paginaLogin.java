package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class paginaLogin {
    public static void paginaLogin(WebDriverWait wait) {

        WebElement campoCpf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cpf-input")));
        campoCpf.sendKeys("104.874.126-56"); //pontuacao opcional

        WebElement campoSenha = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("senha-input")));
        campoSenha.sendKeys("admin");

        WebElement botaoEntrar = wait.until(ExpectedConditions.elementToBeClickable(By.id("entrar-button")));
        botaoEntrar.click();

    }
}