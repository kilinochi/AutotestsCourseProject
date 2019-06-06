import config.AppConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;


import java.util.concurrent.TimeUnit;

final class WebDriversFactory {

    private WebDriversFactory(){}

    static WebDriver getDriver(final Drivers driver) {
        WebDriver webDriver;
        switch (driver){
            case EdgeDriver:
                webDriver = new EdgeDriver();
                break;
            case OperaDriver:
                webDriver = new OperaDriver();
                break;
            case ChromeDriver:
                webDriver = new ChromeDriver();
                break;
            case InternetExplorerDriver:
                webDriver = new InternetExplorerDriver();
                break;
            case SafariDriver:
                webDriver = new SafariDriver();
                break;
            case FireFoxDriver:
                webDriver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException();
        }
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get(AppConfig.BASE_URL + "/dk?st.cmd=anonymMain&st.layer.cmd=PopLayerClose");
        return webDriver;
    }
}