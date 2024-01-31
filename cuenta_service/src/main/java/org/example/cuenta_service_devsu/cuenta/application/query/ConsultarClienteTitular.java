package org.example.cuenta_service_devsu.cuenta.application.query;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import org.example.cuenta_service_devsu.shared.application.query.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ConsultarClienteTitular implements Query<Mono> {

    WebClient client;
    private String urlBackCliente = "";
    private int clienteid;

    private ConsultarClienteTitular(
            @Value("${url.back.cliente}") String urlBackCliente) {
        this.urlBackCliente = urlBackCliente + "clientes";
        WebClientOptions options = new WebClientOptions()
                .setUserAgent("My-App/1.2.3");
        options.setKeepAlive(false);
        this.client = WebClient.create(Vertx.vertx(), options);
    }

    @Override
    public Mono execute() {

        return Mono.create(subscriber -> {

            // System.out.println(this.urlBackCliente);
            this.client
                    .getAbs(this.urlBackCliente)
                    .addQueryParam("clienteid", String.valueOf(this.clienteid))
                    .send()
                    .onSuccess(bufferHttpResponse1 -> {
                        if (bufferHttpResponse1.statusCode() == 200) {
                            subscriber.success(bufferHttpResponse1.bodyAsJsonObject().toString());
                        } else {
                            JsonObject rta = new JsonObject().put("error", bufferHttpResponse1.statusMessage());
                            subscriber.success(rta.toString());
                        }
                    })
                    .onFailure(throwable -> {
                        System.out.println("ERROR -> " + throwable.getMessage());
                        JsonObject rta = new JsonObject().put("error", throwable.getMessage());
                        subscriber.success(rta.toString());

                    });

        });

    }

    public int getClienteid() {
        return clienteid;
    }

    public void setClienteid(int clienteid) {
        this.clienteid = clienteid;
    }
}
