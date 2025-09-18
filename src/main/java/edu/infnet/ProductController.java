package edu.infnet;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class ProductController {
    private static final String resource = "products";

    public static Map<String, Product> productsList = new HashMap<>();
    private static int lastId = 0;

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
        try {

            String id = ctx.pathParam("id");

            Product product = productsList.get(id);
            if (product != null) {
                ctx.status(200);
                ctx.json(product);
            } else {
                ctx.status(404);
                ctx.result("Produto não encontrado.");
            }
        } catch (Exception e) {
            ctx.status(400);
            ctx.result("Erro ao buscar o produto.");
        }
    }

    public static void insertProduct(Context ctx) {
        try {
            Product produto = ctx.bodyAsClass(Product.class);
            produto.setId(Integer.toString(lastId));

            productsList.put(Integer.toString(lastId), produto);
            ctx.status(200);
            ctx.result("Produto inserido.");
            lastId++;
        } catch (Exception ex) {
            ctx.status(400);
            ctx.result("Erro ao inserir produto.");
        }
    }

    public static void updateProduct(Context ctx) {
        try {
            String id = ctx.pathParam("id");
            Product newProduct = ctx.bodyAsClass(Product.class);
            newProduct.setId(id);

            if (!productsList.containsKey(id)) {
                ctx.status(404);
                ctx.result("Produto não encontrado.");
                return;
            }

            productsList.replace(id, newProduct);
            ctx.status(200);
            ctx.result("produto atualizado.");
        } catch (Exception e) {
            ctx.status(400);
            ctx.result("Erro ao atualizar o produto.");
        }
    }

    public static void deleteProduct(Context ctx) {
        try {
            String id = ctx.pathParam("id");

            Product product = productsList.get(id);
            if (product != null) {
                productsList.remove(id);
                ctx.status(HttpURLConnection.HTTP_NO_CONTENT);
            } else {
                ctx.status(HttpURLConnection.HTTP_NOT_FOUND);
                ctx.result("Produto não encontrado.");
            }
        } catch (Exception e) {
            ctx.status(HttpURLConnection.HTTP_BAD_REQUEST);
            ctx.result("Erro ao buscar o produto.");
        }
    }

    public static void patchProduct(Context ctx) {
        try {
            String id = ctx.pathParam("id");

            Product newProduct = ctx.bodyAsClass(Product.class);
            Product existingProduct = productsList.get(id);

            if (existingProduct == null) {
                ctx.status(404);
                ctx.result("Produto não encontrado.");
                return;
            }

            if (newProduct.getName() != null) {
                existingProduct.setName(newProduct.getName());
            }
            if (newProduct.getPrice() != null) {
                existingProduct.setPrice(newProduct.getPrice());
            }
            if (newProduct.getQuantity() != null) {
                existingProduct.setQuantity(newProduct.getQuantity());
            }

            productsList.replace(existingProduct.getId(), existingProduct);
            ctx.status(200);
            ctx.result("Produto atualizado parcialmente.");
        } catch (Exception e) {
            ctx.status(400);
            ctx.result("Erro ao atualizar o produto.");
        }
    }
}
