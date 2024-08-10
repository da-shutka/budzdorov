package properties;

public class SystemProperties {

    //public static String wdHost = System.getProperty("wdHost", "selenoid.autotests.cloud");
    public static String browser = System.getProperty("browser", "chrome");
    public static String browserSize = System.getProperty("browserSize", "1600x1200");
    public static String browserVersion = System.getProperty("browserVersion", "127");
    public static String email = System.getProperty("email", "");
    public static String password = System.getProperty("password", "");

}
