package com.zrtg.auth.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "auth")
public class AuthConfigurationProperties {

    /**
     * Enable Auth, default true
     */
    private boolean enabled = true;

    /**
     * param config
     */
    private AuthConfigurationParamProperties config = new AuthConfigurationParamProperties();

    /**
     * test
     */
    private String test;


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public AuthConfigurationParamProperties getConfig() {
        return config;
    }

    public void setConfig(AuthConfigurationParamProperties config) {
        this.config = config;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}

