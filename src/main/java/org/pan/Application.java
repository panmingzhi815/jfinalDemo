package org.pan;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.jfinal.core.JFinalFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Application {
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                this.binder().bindConstant().annotatedWith(Names.named("url")).to("www.panmingzhi.com");
            }
        });

        Server server = new Server(9999);
        HandlerList handlers = new HandlerList();

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase("webapp/static");
        handlers.addHandler(resourceHandler);

        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.setResourceBase("webapp/template");
        FilterHolder holder = new FilterHolder(JFinalFilter.class);
        holder.setInitParameter("configClass",MyJFinalConfig.class.getName());
        MyJFinalConfig.myControllerFactory = injector.getInstance(MyControllerFactory.class);
        servletContextHandler.addFilter(holder,"/*",EnumSet.allOf(DispatcherType.class));
        handlers.addHandler(servletContextHandler);

        server.setHandler(handlers);
        server.start();
    }
}
