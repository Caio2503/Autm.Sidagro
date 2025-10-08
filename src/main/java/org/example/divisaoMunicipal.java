package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions; // usado para descer a tela usa move to element

// default.wait
//action.movetoelement
public class divisaoMunicipal {
    public static void divisaoMunicipal(WebDriver driver) throws InterruptedException {
        WebElement adicionarNovo = driver.findElement(By.id("adicionar-novo-button"));
        adicionarNovo.click();
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        WebElement campoLongitude = driver.findElement(By.id("longitude-input")); // usado antes no codigo para fazer o scroll
        actions.scrollToElement(campoLongitude).perform(); // Scroll ate longitude por estetica e para facilitar a visao
        Thread.sleep(1000);

        boolean distrito = false; //variavel para definir se será selecionado distrito ou localidade no campo "tipo"
        WebElement opcaoTipo = driver.findElement(By.id("mat-select-value-0"));
        opcaoTipo.click();
        Thread.sleep(1000); // pausa por um segundo para ter certeza que a opcao de tipos apareceu
        if (distrito) {
            WebElement opcaoDistrito = driver.findElement(By.id("tipo-localidade-distrito"));
            opcaoDistrito.click();
        } else {
            WebElement opcaoLocalidade = driver.findElement(By.id("tipo-localidade-localidade"));
            opcaoLocalidade.click();
            WebElement campoDivisao = driver.findElement(By.id("divisao-municipal-input"));
            campoDivisao.sendKeys("teste555");
            WebElement opcaoEstado = driver.findElement(By.id("mat-select-value-1"));
            opcaoEstado.click();
            Thread.sleep(1000);
            WebElement opcaoMinasGerais = driver.findElement(By.xpath("//mat-option[contains(., 'Minas Gerais')]"));
            Thread.sleep(500);
            opcaoMinasGerais.click();
            WebElement campoMunicipio = driver.findElement(By.id("municipio-input"));
            campoMunicipio.sendKeys("Belo Horizonte");
            WebElement campoLatitude = driver.findElement(By.id("latitude-input"));
            campoLatitude.sendKeys("-19.97851");
            campoLongitude.sendKeys("-43.96912");
            WebElement adicionarButton = driver.findElement(By.id("adicionar-button"));
            adicionarButton.click();
            adicionarButton.click();

            try {
                By.xpath("//*[contains(text(), 'Divisão Municipal adicionada com sucesso!')]");
                Thread.sleep(100);
                System.out.println("Deu tudo certo");

            } catch (TimeoutException e) {
                System.out.println("Falha ao adicionar. A mensagem de sucesso não foi encontrada a tempo.");
            }


        }
    }
}
