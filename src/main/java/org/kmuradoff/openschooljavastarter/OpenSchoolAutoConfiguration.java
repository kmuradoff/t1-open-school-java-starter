package org.kmuradoff.openschooljavastarter;

import org.kmuradoff.openschooljavastarter.aspect.LoggingAspect;
import org.kmuradoff.openschooljavastarter.config.LoggingProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
@EnableConfigurationProperties(LoggingProperty.class)
@ConditionalOnClass(RequestContextListener.class)
public class OpenSchoolAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "t1.logging", name = "enabled", matchIfMissing = true)
    public LoggingAspect loggingAspect(LoggingProperty properties) {
        return new LoggingAspect(properties);
    }
}