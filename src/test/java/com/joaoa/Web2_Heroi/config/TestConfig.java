package com.joaoa.Web2_Heroi.config;

import com.joaoa.Web2_Heroi.service.IHeroiService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public IHeroiService heroiService() {
        return Mockito.mock(IHeroiService.class);
    }

}