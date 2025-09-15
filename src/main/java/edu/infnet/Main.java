package edu.infnet;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        // Iniciar o servidor Javalin
        var app = Javalin.create().start(8080);
        ProductController.config(app);
    }
}