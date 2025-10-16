package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.example.paginaLogin.*;
import static org.example.paginaHome.*;
import static org.example.divisaoMunicipal.*;


public class Main {
    public static void main(String[] args) throws InterruptedException { // exception usado para dar uma "pausa" no codigo
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // tempo maximo para uma acao, mantido alto por possiveis instabilidades com a rede
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // mesma coisa do de cima, mas para os driver
        driver.get("https://sidagro-homolog.dac.ufla.br/");
        paginaLogin(wait);
        paginaHome(wait);
        divisaoMunicipal(driver, wait);
        Thread.sleep(5000);
        //driver.quit();
    }

}