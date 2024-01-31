package org.example.cuenta_service_devsu.cuenta.infrastructure.rest.dto.response;

import io.vertx.core.json.JsonObject;

public class RespuestaDto {
    private int error;
    private String mensaje;
    private String data;
    private JsonObject rta;


    public RespuestaDto() {
        this.error = 0;
        this.mensaje = "";
        this.data = "";
    }
    public RespuestaDto(int error, String mensaje, String data) {
        this.error = error;
        this.mensaje = mensaje;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        this.rta = new JsonObject()
                .put("error", error)
                .put("mensaje", mensaje)
                .put("data", data);
        //return "{ \"error\":" + error +", \"mensaje\":\"" + mensaje + "\", \"data\":" + data + "}";
        return this.rta.encodePrettily();
    }
}
