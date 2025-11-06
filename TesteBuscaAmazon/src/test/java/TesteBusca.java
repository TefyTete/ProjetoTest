import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TesteBusca {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Validar a busca de produtos na Amazon")
    public void buscarProdutoComSucesso() {
        driver.get("https://www.amazon.com.br");

        WebElement campoBusca = driver.findElement(By.id("twotabsearchtextbox"));

        String produtoParaBusca = "iPhone 17 Pro";
        campoBusca.sendKeys(produtoParaBusca);
        campoBusca.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        
        WebElement spanResultados = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'resultados para')]")
        ));

       
        Assert.assertTrue(spanResultados.isDisplayed(), "A página de resultados não foi carregada corretamente.");

       
        try {
            wait.until(ExpectedConditions.titleContains(produtoParaBusca));
          
            Assert.assertTrue(true, "O título da página foi atualizado corretamente.");
        } catch (Exception e) {
            
            String tituloAtual = driver.getTitle();
            Assert.fail("Falha na validação: o título da página não foi atualizado a tempo. Título encontrado: '" + tituloAtual + "'");
        }
    }

}