package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions; // usado para descer a tela usa move to element
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class divisaoMunicipal {
    public static void divisaoMunicipal(WebDriver driver, WebDriverWait wait) throws InterruptedException {
        WebElement adicionarNovo = wait.until(ExpectedConditions.elementToBeClickable(By.id("adicionar-novo-button")));
        adicionarNovo.click();

        WebElement campoLongitude = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("longitude-input"))); // longitude usada apenas por se ro elemento utilizado mais abaixo na pagina, poderia ser tbm latitude
        Actions actions = new Actions(driver);
        actions.scrollToElement(campoLongitude).perform();

        boolean distrito = false; //variavel para definir se será selecionado distrito ou localidade no campo "tipo"
        preencherDivisaoMunicipal(wait, distrito, driver);
    }
    public static void preencherDivisaoMunicipal(WebDriverWait wait, boolean distrito, WebDriver driver) {

        String nomeDivisao= "Teste28"; // variavel usada para guardar o nome do "campo divisao", visto que ele sera usado mais de uma vez
        // tem que ser maiusculo pq se nao da problema na busca

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
        campoDivisao.sendKeys(nomeDivisao);

        WebElement opcaoEstado = wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-select-value-1")));
        opcaoEstado.click();

        WebElement opcaoMinasGerais = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-option[contains(., 'Minas Gerais')]")));
        opcaoMinasGerais.click();

        WebElement campoMunicipio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("municipio-input")));
        campoMunicipio.sendKeys("Belo Horizonte");

        WebElement campoLatitude = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("latitude-input")));
        campoLatitude.sendKeys("-19.97851");

        WebElement campoLongitude = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("longitude-input"))); //longitude declarada novamente para manter padrao, mas poderia ser passada como parametro da funcao "divisaoMunicipal",
        campoLongitude.sendKeys("-43.96912"); //defini a longitude la em cima pra dar scroll to element

        WebElement adicionarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("adicionar-button")));
        adicionarButton.click();

        boolean adicionou = false; // variavel usada para definir se a  divisao municipal foi adicionada
        // usada para separar o try do if para continuar o codigo
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(), 'Divisão Municipal adicionada com sucesso!')]")
            ));
            System.out.println("Deu tudo certo com a insercao de dados.");
            adicionou=true;

        } catch (TimeoutException e) {
            System.out.println("Falha ao adicionar. A mensagem de sucesso não foi encontrada.");

        }
        if(adicionou) {
            WebElement voltarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cancelar-button")));
            voltarButton.click();

            divisaoMunicipalList(wait, nomeDivisao, driver);
        }
    }
    public static void divisaoMunicipalList(WebDriverWait wait, String nomeDivisao, WebDriver driver){

        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-input")));
        searchField.sendKeys(nomeDivisao);

        WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("search-icon")));
        searchIcon.click();
        boolean encontrou = false;
        try {
            String xpathnomeDivisao = "//*[contains(., '" + nomeDivisao + "')]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(xpathnomeDivisao)
            ));
            System.out.println("Deu tudo certo com a busca. A divisão '" + nomeDivisao + "' foi encontrada na tela de busca.");
            encontrou = true;
        } catch (TimeoutException e) {
            System.out.println("Falha na busca. O texto da divisão '" + nomeDivisao + "' não foi encontrado.");
        }

        if (encontrou) {
            String xpathList = String.format("//td[contains(., '%s')]", nomeDivisao);

            WebElement celulaNomeDivisao = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(xpathList)
            ));
            celulaNomeDivisao.click();

            WebElement editarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("editar-button")));
            editarButton.click();
            WebElement campoDivisao = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("divisao-municipal-input")));
            campoDivisao.sendKeys("Teste Concluido");

            WebElement inativarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("inativar-button")));
            Actions actions = new Actions(driver);
            actions.scrollToElement(inativarButton).perform();
            inativarButton.click();

            WebElement confirmarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmar-button")));
            confirmarButton.click();

            WebElement salvarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("salvar-button")));
            salvarButton.click();
            confirmarButton.click();
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), 'Divisão Municipal atualizada com sucesso!')]")
                ));
                System.out.println("Deu tudo certo com a atualização de dados.");

            } catch (TimeoutException e) {
                System.out.println("Falha ao editar os dados. A mensagem de sucesso não foi encontrada.");

            }
        } else {
            System.out.println("Não foi encontrado o bloco, encerrando o programa");
        }
        }
    }

