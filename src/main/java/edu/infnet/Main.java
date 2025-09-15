package edu.infnet;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        // Iniciar o servidor Javalin
        var app = Javalin.create().start(8080);

        // Instanciar o controlador
        ProductController productController = new ProductController();

        // GET /
        app.get("/", productController::getConnection);

        // GET /products
        app.get("/products", productController::getProductsList);
    }
}