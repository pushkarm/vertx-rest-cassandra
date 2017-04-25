package com.example.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;


/**
 * Created by pushkarmurkute on 25/04/17.
 *
 * Reference : http://vertx.io/blog/my-first-vert-x-3-application/
 */
public class MainVerticle extends AbstractVerticle {


    @Override
    public void start(Future<Void> future) throws Exception {

        // Create a router object.
        Router router = Router.router(vertx);

        // Serve static resources from the /assets directory
        router.route("/assets/*").handler(StaticHandler.create("assets"));

        // Bind "/" to our hello message - so we are still compatible.
        router
                .route("/").handler(
                    routingContext -> {
                        HttpServerResponse response = routingContext.response();
                        response
                            .putHeader("content-type", "text/html")
                            .end("<h1>Hello from pushkar</h1>");
                     }
                );

        // api route
        router.route(HttpMethod.GET, "/api/echo").handler(new DemoHandler());



        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx
            .createHttpServer()
            .requestHandler(router::accept)
            .listen(
                    // Retrieve the port from the configuration,
                    // default to 8080.
                    config().getInteger("http.port", 8080),
                    result -> {
                        if (result.succeeded()) {
                            future.complete();
                        } else {
                            future.fail(result.cause());
                        }
                    }
            );

    }
}