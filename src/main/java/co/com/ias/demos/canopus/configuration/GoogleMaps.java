package co.com.ias.demos.canopus.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "google.maps.api")
public class GoogleMaps {
    private String key;

    public GoogleMaps(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
