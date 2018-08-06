package org.pan.controller;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.jfinal.core.Controller;
import org.pan.model.Product;

public class HelloController extends Controller {

    @Inject
    @Named("url")
    private String url;

    public void text() {
        renderText(url);
    }

    public void json() {
        Product product = new Product();
        product.setUrl("www.baidu.com");
        product.setName("pmz");
        renderJson(product);
    }

    public void temp() {
        Product product = new Product();
        product.setUrl("www.baidu.com");
        product.setName("pmz");
        setAttr("user", "panmingzhi");
        setAttr("latestProduct", product);

        renderFreeMarker("/index.html");
    }
}
