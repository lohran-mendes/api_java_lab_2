import edu.infnet.Product;
import edu.infnet.ProductController;
import io.javalin.http.Context;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.net.ssl.HttpsURLConnection;
import java.util.HashMap;

public class ProductControllerTest {
    Context ctxMock = Mockito.mock(Context.class);


    @Test
    public void getConnectionMethodShouldReturnStatusCode200() {
        ProductController.getConnection(ctxMock);

        Mockito.verify(ctxMock).status(HttpsURLConnection.HTTP_OK);
        Mockito.verify(ctxMock).result("Conexão ok!");
    }

    @Test
    public void getProductsMethodShouldReturnStatusCode200() {
        ProductController.getProducts(ctxMock);

        Mockito.verify(ctxMock).status(HttpsURLConnection.HTTP_OK);
        Mockito.verify(ctxMock).json(new HashMap<>());
    }


    @Test
    public void getProductsByIdMethodShouldReturnStatusCode200() {
        Product product = new Product("1","chinelo", 29.90, 10);
        ProductController.productsList.put(product.getId(), product);

        Mockito.when(ctxMock.pathParam("id")).thenReturn("1");
        ProductController.getProductById(ctxMock);

        Mockito.verify(ctxMock).status(HttpsURLConnection.HTTP_OK);
        Mockito.verify(ctxMock).json(product);
        ProductController.productsList.clear();
    }

    @Test
    public void getProductsByIdMethodShouldReturnStatusCode404WhenInvalidProduct() {
        Product product = new Product("1","chinelo", 29.90, 10);
        ProductController.productsList.put(product.getId(), product);

        Mockito.when(ctxMock.pathParam("id")).thenReturn("2");
        ProductController.getProductById(ctxMock);

        Mockito.verify(ctxMock).status(HttpsURLConnection.HTTP_NOT_FOUND);
        Mockito.verify(ctxMock).result("Produto não encontrado.");
        ProductController.productsList.clear();

    }
}
