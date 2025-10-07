package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions; // usado para descer a tela usa move to element

// default.wait
//action.movetoelement
public class divisaoMunicipal {
    public static void divisaoMunicipal(WebDriver driver) throws InterruptedException {
        WebElement adicionarNovo = driver.findElement(By.id("adicionar-novo-button"));
        adicionarNovo.click();
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.scrollByAmount(0, 75).perform(); // Rola 500 pixels para baixo, opcional e usado apenas para melhor vizualizacao
        Thread.sleep(1000);

        boolean distrito = false; //variavel para definir se será selecionado distrito ou localidade no campo "tipo"
        WebElement opcaoTipo  = driver.findElement(By.id("mat-select-value-0"));
        opcaoTipo.click();
        Thread.sleep(1000); // pausa por um segundo para ter certeza que a opcao de tipos apareceu
        if (distrito == true){
            WebElement opcaoDistrito  = driver.findElement(By.id("tipo-localidade-distrito"));
            opcaoDistrito.click();
        } else {
            WebElement opcaoLocalidade  = driver.findElement(By.id("tipo-localidade-localidade"));
            opcaoLocalidade.click();
            WebElement campoDivisao = driver.findElement(By.id("divisao-municipal-input"));
            campoDivisao.sendKeys("Região Metropolitana de Belo Horizonte");

            WebElement opcaoEstado = driver.findElement(By.id("mat-select-value-1"));
            opcaoEstado.click();
            Thread.sleep(1000);
            WebElement opcaoMinasGerais = driver.findElement(By.xpath("//mat-option[contains(., 'Minas Gerais')]"));
            Thread.sleep(500);
            opcaoMinasGerais.click();
        }



    }
}
