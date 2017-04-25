package com.example.vertx;

import com.datastax.driver.core.utils.UUIDs;
import com.example.vertx.domain.User;
import com.example.vertx.service.UserService;
import com.example.vertx.service.impl.UserServiceImpl;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pushkarmurkute on 25/04/17.
 */
public class DemoHandler implements Handler<RoutingContext> {


    HttpClient httpClient;

    public DemoHandler() {}

    @Override
    public void handle(RoutingContext routingContext) {
        String username = routingContext.request().getParam("username");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);

        try {
            User user = new User();
            user.setUsername("pushkar");
            user.setPassword("12345678");
            user.setEmail("abcd");
            user.setId(UUIDs.timeBased());

            UserService userService = new UserServiceImpl();
            userService.addUser(user);

        } catch (Exception e) {
            System.out.print("Exception"  + e.getMessage());
            e.printStackTrace();
        }


        // Return the created whisky as JSON
        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(map));

    }
}