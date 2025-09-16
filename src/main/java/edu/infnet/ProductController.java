package edu.infnet;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class ProductController {
    private static final String resource = "products";

    public static Map<String, Product> productsList = new HashMap<>();

    public static void config(Javalin app) {

        // GET /
        app.get("/", ProductController::getConnection);

        // GET /products/
        app.get(resource, ProductController::getProducts);

        // GET /products/id
        app.get(resource + "/{id}", ProductController::getProductById);

        // POST /products
        app.post(resource, ProductController::insertProduct);

        // PUT /products/{id}
        app.put(resource + "/{id}", ProductController::updateProduct);

        // DELETE /products/{id
        app.delete(resource + "/{id}", ProductController::deleteProduct);

        // PATCH /products/{id
        app.patch(resource + "/{id}", ProductController::patchProduct);
    }

    public static void getConnection(Context ctx) {
        ctx.status(200);
        ctx.result("Conexão ok!");
    }

    public static void getProducts(Context ctx) {
        ctx.status(200);
        ctx.json(productsList);
    }

    public static void getProductById(Context ctx) {
        String id = ctx.pathParam("id");

        Product product = productsList.get(id);
        if (product != null) {
        ctx.status(200);
        ctx.json(product);
        } else {
            ctx.status(404);
            ctx.result("Produto não encontrado.");
        }
    }

    public static void insertProduct(Context ctx) {
        ctx
                .status(200)
                .result("Produto inserido.");
    }

    public static void updateProduct(Context ctx) {
        String id = ctx
                .pathParam("id");

        ctx
                .status(200)
                .result("produto atualizado.");
    }

    public static void deleteProduct(Context ctx) {
        String id = ctx
                .pathParam("id");

        ctx
                .status(200)
                .result("Produto deletado.");
    }

    public static void patchProduct(Context ctx) {
        String id = ctx
                .pathParam("id");

        ctx
                .status(200)
                .result("Produto atualizado parcialmente.");
    }
}