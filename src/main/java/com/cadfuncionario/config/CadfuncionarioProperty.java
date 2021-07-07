package com.cadfuncionario.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("cadfuncionario")
public class CadfuncionarioProperty {

    private String origemPermitida = "http://localhost:4200";

    private final Seguranca seguranca = new Seguranca();
    
    

    public Seguranca getSeguranca() {
        return this.seguranca;
    }

    public String getOrigemPermitida() {
        return this.origemPermitida;
    }

    public void setOrigemPermitida(String origemPermitida) {
        this.origemPermitida = origemPermitida;
    }

    public static class Seguranca {

        private boolean enableHttps;

        public boolean isEnableHttps() {
            return this.enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }
    }

}