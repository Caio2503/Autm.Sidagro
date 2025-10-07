package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.example.paginaLogin.*;
import static org.example.paginaHome.*;
import static org.example.divisaoMunicipal.*;


public class Main {
    public static void main(String[] args) throws InterruptedException { // exception usado para dar uma "pausa" no codigo
        WebDriver driver = new ChromeDriver();

        driver.get("https://sidagro-homolog.dac.ufla.br/");

        paginaLogin(driver);
        Thread.sleep(2000); // pausa por 2 segundos
        paginaHome(driver);
        Thread.sleep(2000);
        divisaoMunicipal(driver);
        //driver.quit();
    }

}