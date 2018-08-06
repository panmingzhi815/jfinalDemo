package org.pan;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.jfinal.core.Controller;
import com.jfinal.core.ControllerFactory;

public class MyControllerFactory extends ControllerFactory {

    @Inject
    private Injector injector;

    @Override
    public Controller getController(Class<? extends Controller> controllerClass) {
        return injector.getInstance(controllerClass);
    }
}
