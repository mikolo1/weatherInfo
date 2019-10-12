package pl.mikolo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

    //konfiguracja klas dla warstwy logiki biznesowej
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    // wskazanie klas konfiguracyjnych dla warstwy WEB
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    //wskazanie mapowania requestów
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
