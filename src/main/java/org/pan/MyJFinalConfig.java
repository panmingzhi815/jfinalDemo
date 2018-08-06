package org.pan;

import com.jfinal.config.*;
import com.jfinal.template.Engine;
import org.pan.controller.HelloController;

public class MyJFinalConfig extends JFinalConfig {

    static MyControllerFactory myControllerFactory;

    public void configConstant(Constants me) {
        me.setControllerFactory(myControllerFactory);
    }

    public void configRoute(Routes me) {
        me.setBaseViewPath("/");
        me.add("/test",HelloController.class);
    }

    public void configEngine(Engine me) {
        me.setDevMode(true);
    }

    public void configPlugin(Plugins me) {

    }

    public void configInterceptor(Interceptors me) {

    }

    public void configHandler(Handlers me) {

    }
}
