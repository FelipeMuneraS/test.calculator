import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSElement;

public class Calculadora extends Thread {
    private AppiumDriver<IOSElement> driver;

    public Calculadora(AppiumDriver<IOSElement> driver) {
        this.driver = driver;
    }

    @Override
    public void run() {
        try {
            IOSElement user;
            user = driver.findElementByName("1");
            user.click();
            Thread.sleep(500);
            user = driver.findElementByName("sumar");
            user.click();
            Thread.sleep(500);
            user = driver.findElementByName("8");
            user.click();
            Thread.sleep(500);
            user = driver.findElementByName("es igual a");
            user.click();
            Thread.sleep(500);

        } catch (Exception e) {
            System.out.print("\n\t Se presento Excepción " + e);
        }
    }
}
