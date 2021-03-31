package pl.org.conceptweb.heydeskb.configuration;

import com.sun.istack.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfiguration {

    @NotNull
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public static final byte MIN_USER_NAME_LENGTH = 6;
    public static final int MAX_USER_NAME_LENGTH = 50;
    public static final byte MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 50;
     public static final int MIN_INPUT_LENGTH = 3;
      public static final int MAX_INPUT_LENGTH = 50;

}
