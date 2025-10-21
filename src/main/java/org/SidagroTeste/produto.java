package org.SidagroTeste;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class produto {

    private WebDriver driver;
    private WebDriverWait wait;

    public produto(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    public void preencherProduto(String nome,String unidade,Boolean animal,String nomeEditado){
        WebElement entrarProdutoButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("router-link-produto")));
        entrarProdutoButton.click();

        WebElement adicionarNovo = wait.until(ExpectedConditions.elementToBeClickable(By.id("adicionar-novo-button")));
        adicionarNovo.click();

        WebElement campoProduto = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("produto-input")));
        campoProduto.sendKeys(nome);

        WebElement campoUnidade = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("unidade-medida-input")));
        campoUnidade.sendKeys(unidade);

        WebElement opcaoTipo = wait.until(ExpectedConditions.elementToBeClickable(By.id("tipo-produto-select")));
        opcaoTipo.click();

        if (animal) {
            WebElement opcaoAnimal = wait.until(ExpectedConditions.elementToBeClickable(By.id("tipo-produto-animal")));
            opcaoAnimal.click();
        } else {
            WebElement opcaoVegetal = wait.until(ExpectedConditions.elementToBeClickable(By.id("tipo-produto-vegetal")));
            opcaoVegetal.click();
        }

        WebElement adicionarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("adicionar-button")));
        adicionarButton.click();

        if(verificarProdutoAdicionado()){
            WebElement voltarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cancelar-button")));
            voltarButton.click();
            produtoList(nome,nomeEditado);
        }
    }
        public boolean verificarProdutoAdicionado() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(), 'Produto adicionado com sucesso! ')]")
            ));
            System.out.println("Deu tudo certo com a insercao de produto.");
            return true;
        } catch (TimeoutException e) {
            System.out.println("Falha ao adicionar. A mensagem de sucesso não foi encontrada ao adicionar 'produto'.");
            return false;
        }
    }
    public void produtoList(String nome, String nomeEditado) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-input")));
        searchField.sendKeys(nome);

        WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("search-icon")));
        searchIcon.click();
        if (achouBusca(nome)) {
            String xpathList = String.format("//td[contains(., '%s')]", nome);

            WebElement celulaNomeProduto = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(xpathList)
            ));
            celulaNomeProduto.click();
            campoEdicao(nomeEditado);
        }
    }
    public boolean achouBusca(String nomeProduto) {
        try {
            String XnomeDivisao = "//*[contains(., '" + nomeProduto + "')]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(XnomeDivisao)
            ));
            System.out.println("Deu tudo certo com a busca. A divisão '" + nomeProduto + "' foi encontrada na tela de busca.");
            return true;
        } catch (TimeoutException e) {
            System.out.println("Falha na busca. O texto da divisão '" + nomeProduto + "' não foi encontrado.");
            return false;
        }
    }
    public void campoEdicao(String nomeEditado) {
        WebElement editarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("editar-button")));
        editarButton.click();

        WebElement campoNome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("produto-input")));
        Actions actions = new Actions(driver);
        actions.click(campoNome) //clear nao deu certo
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .perform();
        campoNome.sendKeys(nomeEditado);

        WebElement inativarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("inativar-button")));
        inativarButton.click();

        WebElement confirmarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmar-button")));
        confirmarButton.click();

        WebElement salvarButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("salvar-button")));
        salvarButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmar-button"))).click(); // demora para aparecer, normalemente eu espero para fazer a declaração, mas nesse caso, a declaracao ja foi feita
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(), 'Produto atualizado com sucesso!')]")
            ));
            System.out.println("Deu tudo certo com a edicao de dados.");
        } catch (TimeoutException e) {
            System.out.println("Falha ao editar. A mensagem de sucesso não foi encontrada.");
        }
        voltarTelaPrincipal();
    }
    public void voltarTelaPrincipal(){
        wait.until(ExpectedConditions.elementToBeClickable(By.id("header-back-button"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("header-back-button"))).click();
        }
}