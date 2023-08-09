package ir.saadati.customeraccountrestapi2.service;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class Log4j2ConfigInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        // Load Log4j2 configuration
        System.setProperty("log4j.configurationFile", "C:\\anoosheh\\CP\\work\\Internship\\Kish Novin\\Before the Interview\\CustomerAccountRestApi2\\src\\main\\resources\\log4j2.xml");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Cleanup or perform actions when the context is destroyed
    }
}