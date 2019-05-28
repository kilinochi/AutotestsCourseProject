import config.AppConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriversFactory {

    private WebDriversFactory(){}

    public static WebDriver getDriver() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(AppConfig.baseUrl + "/dk?st.cmd=anonymMain&st.layer.cmd=PopLayerClose");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return webDriver;
    }
}
