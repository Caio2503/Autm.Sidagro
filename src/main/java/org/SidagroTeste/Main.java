package org.SidagroTeste;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // tempo maximo para uma acao, mantido alto por possiveis instabilidades com a rede
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // mesma coisa do de cima, mas para os driver
        driver.get("https://sidagro-homolog.dac.ufla.br/");
        String cpf = "104.874.126-56";
        String senha = "admin";
        String nomeDivisao = "Teste55";
        String nomeEditado = "Teste55 Editado";
        String municipio = "Belo Horizonte";
        String estado = "Minas Gerais";
        String latitude = String.valueOf(-19.97851);
        String longitude = String.valueOf(-43.96912);
        boolean distrito = false;//variavel para definir se será selecionado distrito ou localidade no campo "tipo"


        paginaLogin loginPage = new paginaLogin(wait);
        loginPage.executarLogin(cpf, senha);
        divisaoMunicipal divisaoPage = new divisaoMunicipal(driver, wait);
        divisaoPage.preencherDivisaoMunicipal(nomeDivisao, nomeEditado, distrito, municipio, estado, latitude, longitude);
        driver.quit();
    }
}