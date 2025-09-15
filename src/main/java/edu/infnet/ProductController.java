package edu.infnet;

import io.javalin.http.Context;

public class ProductController {
    public void tratarRequisicao(Context ctx, Product product) {

    }
    public void getConnection(Context ctx) {
        ctx.status(200).result("Conexão ok!");
    }

    public void getProductsList(Context ctx) {
    ctx.status(200).result("Lista de produtos consultada.");
    }

}
