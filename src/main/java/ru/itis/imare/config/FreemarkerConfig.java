package ru.itis.imare.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import java.util.Collections;

@RequiredArgsConstructor
@Configuration
public class FreemarkerConfig {

    private final FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void init() {
        freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(
                Collections.singletonList("/META-INF/security.tld"));
        freeMarkerConfigurer.getTaglibFactory().setObjectWrapper(freemarker.template.Configuration
                .getDefaultObjectWrapper(freemarker.template.Configuration.getVersion()));
    }
}
