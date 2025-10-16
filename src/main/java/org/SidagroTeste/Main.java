package org.SidagroTeste;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.SidagroTeste.paginaLogin.*;
import static org.SidagroTeste.divisaoMunicipal.*;


public class Main {
    public static void main(String[] args) { // exception usado para dar uma "pausa" no codigo
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // tempo maximo para uma acao, mantido alto por possiveis instabilidades com a rede
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // mesma coisa do de cima, mas para os driver

        driver.get("https://sidagro-homolog.dac.ufla.br/");
        paginaLogin(wait);
        divisaoMunicipal(driver, wait);
        driver.quit();
    }

}