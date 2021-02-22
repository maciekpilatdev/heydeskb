package pl.org.conceptweb.heydeskb.configuration;

import com.sun.istack.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Component
//@EnableConfigurationProperties
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
    
    
}
