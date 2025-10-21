package org.SidagroTeste;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class paginaLogin {
    private WebDriverWait wait;

    public paginaLogin(WebDriverWait wait) {
        this.wait = wait;
    }

    public void executarLogin(String cpf, String senha) {
        WebElement campoCpf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cpf-input")));
        campoCpf.sendKeys(cpf);

        WebElement campoSenha = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("senha-input")));
        campoSenha.sendKeys(senha);

        WebElement botaoEntrar = wait.until(ExpectedConditions.elementToBeClickable(By.id("entrar-button")));
        botaoEntrar.click();

        paginaHome();
    }
    public void paginaHome() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(), 'Cadastros')]")
            ));
            System.out.println("Login completo");
        } catch (TimeoutException e) {
            System.out.println("Falha ao fazer login. O elemento de texto 'Cadastros' nao foi encontrado.");
        }
    }
}