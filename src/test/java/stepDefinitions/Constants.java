package stepDefinitions;

public interface Constants {
    public static final String CLASS = "class";
    public static final String ID = "id";
    public  static final String CSS = "css";
    public static final String XPATH = "xpath";
    public   static final String NAME = "name";
    public static final String TAG_NAME = "tagname";
    public  static final String LINK = "link";
    public   static final String LINK_TEXT = "Link Text";
    public static final String PARTIAL_LINK = "partial_link";
    public  static final String TEXT_INSIDE = "Text Inside";
    public  static final String TEXT_CONTAINS="Text contains";
    public static final String STAGING_HOST_1 = "http://rl-staging-web-php01";
    public static final String STAGING_HOST_2 = "http://rl-staging-web02";
    public static final String LOCAL_HOST = "http://localhost";
    public static final String STAGING_1 = "STAGING_MACHINE_1";
    public static final String STAGING_2 = "STAGING_MACHINE_2";
    public final String RUNNER = "com.resumelibrary.cucumber.CucumberTestRunner";
    public final String RUNNER2 = "com.resumelibrary.cucumber.CucumberTestRunner2";


    //Property file
    public final String PROPERTY_FILE_PATH = "/src/test/java/com/resumelibrary/configs/config.properties";
    public final String LOG_PROPERTY_FILE_PATH = "/src/test/java/com/resumelibrary/configs/log4j.properties";

    //Other variables
    public final String DISABLE_DATABASE_QUERY = "DISABLE_DB_PROFILER";
    public final String ERROR_502 = "502 Bad Gateway";
    public final String ERROR_403 = "ERROR 403 - Forbidden | Resume-Library.com";

    public String getConstantsURL(String URL);
}