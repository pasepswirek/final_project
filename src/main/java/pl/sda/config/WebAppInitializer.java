package pl.sda.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { FinalProjectApplication.class };
    }
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return null;
//    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebAppInitializer.class};
    }
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
