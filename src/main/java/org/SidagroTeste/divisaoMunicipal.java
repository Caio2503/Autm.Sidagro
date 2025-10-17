package org.SidagroTeste;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class divisaoMunicipal {
    private WebDriver driver;
    private WebDriverWait wait;

    public divisaoMunicipal(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void preencherDivisaoMunicipal(String nomeDivisao, String nomeEditado,Boolean distrito, String municipio, String estado, String latitude, String longitude) {
        WebElement adicionarNovo = wait.until(ExpectedConditions.elementToBeClickable(By.id("adicionar-novo-button")));
        adicionarNovo.click();

        WebElement campoLongitude = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("longitude-input"))); // longitude usada apenas por se ro elemento utilizado mais abaixo na pagina, poderia ser tbm latitude
        Actions actions = new Actions(driver);
        actions.scrollToElement(campoLongitude).perform();

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
        
      
        String xpathDinamico = "//mat-option[contains(., '" + estado + "')]";
        WebElement opcaoSelecionada = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathDinamico)));
        opcaoSelecionada.click();

        WebElement campoMunicipio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("municipio-input")));
        campoMunicipio.sendKeys(municipio);

        WebElement campoLatitude = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("latitude-input")));
        campoLatitude.sendKeys(latitude);

        campoLongitude.sendKeys(longitude); //defini o WebElement da longitude na linha 21 pra dar scroll to element

        WebElement adicionarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("adicionar-button")));
        adicionarButton.click();

        if(verificarInsercao()){
            WebElement voltarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cancelar-button")));
            voltarButton.click();
            divisaoMunicipalList(nomeDivisao, nomeEditado);
        }
    }
        public boolean verificarInsercao() {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), 'Divisão Municipal adicionada com sucesso!')]")
                ));
                System.out.println("Deu tudo certo com a insercao de dados.");
                return true;
            } catch (TimeoutException e) {
                System.out.println("Falha ao adicionar. A mensagem de sucesso não foi encontrada.");
                return false;
            }
        }

    public void divisaoMunicipalList(String nomeDivisao,String nomeEditado) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-input")));
        searchField.sendKeys(nomeDivisao);

        WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("search-icon")));
        searchIcon.click();
        if (achouBusca(nomeDivisao)) {
            String xpathList = String.format("//td[contains(., '%s')]", nomeDivisao);

            WebElement celulaNomeDivisao = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(xpathList)
            ));
            celulaNomeDivisao.click();
            campoEdicao(nomeEditado);
        }
    }
    public boolean achouBusca(String nomeDivisao) {
        try {
            String XnomeDivisao = "//*[contains(., '" + nomeDivisao + "')]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(XnomeDivisao)
            ));
            System.out.println("Deu tudo certo com a busca. A divisão '" + nomeDivisao + "' foi encontrada na tela de busca.");
            return true;
        } catch (TimeoutException e) {
            System.out.println("Falha na busca. O texto da divisão '" + nomeDivisao + "' não foi encontrado.");
            return false;
        }
    }

        public void campoEdicao(String nomeEditado) {
            WebElement editarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("editar-button")));
            editarButton.click();

            WebElement campoDivisao = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("divisao-municipal-input")));
            Actions actions = new Actions(driver);
            actions.click(campoDivisao) //clear nao deu certo
                    .keyDown(Keys.CONTROL)
                    .sendKeys("a")
                    .keyUp(Keys.CONTROL)
                    .sendKeys(Keys.BACK_SPACE)
                    .perform();
            campoDivisao.sendKeys(nomeEditado);

            WebElement inativarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("inativar-button")));
            actions = new Actions(driver);
            actions.scrollToElement(inativarButton).perform();
            inativarButton.click();

            WebElement confirmarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmar-button")));
            confirmarButton.click();

            WebElement salvarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("salvar-button")));
            actions.scrollToElement(salvarButton).perform();
            salvarButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmar-button"))).click(); // demora para aparecer, normalemente eu espero para fazer a declaração, mas nesse caso, a declaracao ja foi feita
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), 'Divisão Municipal atualizada com sucesso!')]")
                ));
                System.out.println("Deu tudo certo com a edicao de dados.");

            } catch (TimeoutException e) {
                System.out.println("Falha ao editar. A mensagem de sucesso não foi encontrada.");
            }
        }
}