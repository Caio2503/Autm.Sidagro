package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions; // usado para descer a tela usa move to element
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class divisaoMunicipal {
    public static void divisaoMunicipal(WebDriver driver, WebDriverWait wait) {
        WebElement adicionarNovo = wait.until(ExpectedConditions.elementToBeClickable(By.id("adicionar-novo-button")));
        adicionarNovo.click();

        WebElement campoLongitude = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("longitude-input")));
        Actions actions = new Actions(driver);
        actions.scrollToElement(campoLongitude).perform();

        boolean distrito = false; //variavel para definir se será selecionado distrito ou localidade no campo "tipo"
        WebElement opcaoTipo = wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-select-value-0")));
        opcaoTipo.click();

        if (distrito) {
            WebElement opcaoDistrito = wait.until(ExpectedConditions.elementToBeClickable(By.id("tipo-localidade-distrito")));
            opcaoDistrito.click();
        } else {
            WebElement opcaoLocalidade = wait.until(ExpectedConditions.elementToBeClickable(By.id("tipo-localidade-localidade")));
            opcaoLocalidade.click();
        }
        WebElement campoDivisao = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("divisao-municipal-input")));
        campoDivisao.sendKeys("teste5555");

        WebElement opcaoEstado = wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-select-value-1")));
        opcaoEstado.click();

        WebElement opcaoMinasGerais = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-option[contains(., 'Minas Gerais')]")));
        opcaoMinasGerais.click();

        WebElement campoMunicipio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("municipio-input")));
        campoMunicipio.sendKeys("Belo Horizonte");

        WebElement campoLatitude = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("latitude-input")));
        campoLatitude.sendKeys("-19.97851");

        campoLongitude.sendKeys("-43.96912"); //defini a longitude la em cima pra dar scroll to element
        WebElement adicionarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("adicionar-button")));
        adicionarButton.click();
        adicionarButton.click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(), 'Divisão Municipal adicionada com sucesso!')]")
            ));
            System.out.println("Deu tudo certo.");

        } catch (TimeoutException e) {
            System.out.println("Falha ao adicionar. A mensagem de sucesso não foi encontrada.");
        }
    }
}
