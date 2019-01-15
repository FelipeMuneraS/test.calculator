import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class EriBank {
    public static AppiumDriver<IOSElement> driver;
    ArrayList<String> version = new ArrayList<String>();
    ArrayList<String> udid = new ArrayList<String>();
    ArrayList<String> url = new ArrayList<String>();
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> port = new ArrayList<String>();
    static ArrayList<AppiumDriver<IOSElement>> drivers = new ArrayList<AppiumDriver<IOSElement>>();

    @Test
    public void f() {
        //ArrayList<Hilo> hilos = new ArrayList<Hilo>();
        //Hilo hilo;
        ArrayList<Calculadora> hilos = new ArrayList<Calculadora>();
        Calculadora hilo;

        for (int i = 0; i < drivers.size(); i++) {
            hilo = new Calculadora(drivers.get(i));
            //hilo = new Hilo(drivers.get(i));
            hilos.add(hilo);
        }
        for (int i = 0; i < hilos.size(); i++) {
            hilos.get(i).start();
        }
        int i = hilos.size();
        while (hilos.size() != 0) {
            if (hilos.get(i - 1).getState() == Thread.State.TERMINATED) {
                hilos.remove(i - 1);
            }
            if (i == 1) {
                i = hilos.size();
            } else {
                i--;
            }
        }
    }

    @BeforeTest
    public void beforeTest() throws MalformedURLException {
        

        udid.add("");
        version.add("");
        url.add("");
        name.add("");
        port.add("");


        for (int i = 0; i < udid.size(); i++) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", name.get(i));
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("automationName", "XCUITest");
            capabilities.setCapability("bundleId", "com.apple.calculator");
            capabilities.setCapability("udid", udid.get(i));
            capabilities.setCapability("platformVersion",version.get(i));
            capabilities.setCapability("wdaLocalPort", port.get(i));
            driver = new IOSDriver<IOSElement>(new URL(url.get(i)), capabilities);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            drivers.add(driver);
        }
    }

    @AfterTest
    public void afterTest() throws InterruptedException {
        Thread.sleep(2000);
        for (int i = 0; i < drivers.size(); i++) {
            drivers.get(i).quit();
        }
    }

}
