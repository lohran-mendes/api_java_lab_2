package edu.infnet;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class ProductController {
    private static final String resource = "products";

    public static void config(Javalin app) {

        // GET /
        app.get("/", ProductController::getConnection);

        // GET /products/
        app.get(resource, ProductController::getProducts);

        // GET /products/id
        app.get(resource + "/id", ProductController::getProductById);

        // POST /products
        app.post(resource, ProductController::insertProduct);

        // PUT /products/{id}
        app.put(resource, ProductController::updateProduct);

        // DELETE /products/{id
        app.delete(resource, ProductController::deleteProduct);

        // PATCH /products/{id
        app.patch(resource + "/id", ProductController::patchProduct);
    }

    public static void getConnection(Context ctx) {
        ctx.status(200).result("Conexão ok!");
    }

    public static void getProducts(Context ctx) {
        ctx.status(200).result("Lista de produtos consultada.");
    }

    public static void getProductById(Context ctx) {
        ctx.status(200).result("produto consultado por ID.");
    }

    public static void insertProduct(Context ctx) {
        ctx.status(200).result("Produto inserido.");
    }

    public static void updateProduct(Context ctx) {
        ctx.status(200).result("produto atualizado.");
    }

    public static void deleteProduct(Context ctx) {
        ctx.status(200).result("Produto deletado.");
    }

    public static void patchProduct(Context ctx) {
        ctx.status(200).result("Produto atualizado parcialmente.");
    }


}
