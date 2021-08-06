package Listener;

import com.sun.jndi.ldap.Connection;
import model.CategoryData;
import model.DataBase;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextCreationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("connection", DataBase.getConnection());
        servletContextEvent.getServletContext().setAttribute("CategoryData", new CategoryData());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
