import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

record Product(int id, String name, String description) {}


class Main {

    static class ProductHandler implements HttpHandler{

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            if (!method.equals("GET")) {
                exchange.sendResponseHeaders(405, -1);
                exchange.close();
                return;
            }

            String response = getProducts();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
        }

        private static String getProducts() {
            Product tissue = new Product(32, "Rose petel", "White rose patel large size");
            Product honey = new Product(321, "Pure petel", "White honey");
            
            ArrayList<Product> products = new ArrayList<Product>();
            products.add(tissue);
            products.add(honey);
    
            Gson gson = new Gson();
            return gson.toJson(products);

    
        }

    }

    public static void main(String[] args) throws Exception {
        //testJson();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080),5);
        server.createContext("/products", new ProductHandler());
        server.start();
    }

    private static void testJson() {
        Product tissue = new Product(32, "Rose petel", "White rose patel large size");
        Product honey = new Product(321, "Pure petel", "White honey");
        
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(tissue);
        products.add(honey);

        Gson gson = new Gson();
        String json = gson.toJson(products);
        System.out.println(json);

        try {
        XmlMapper mapper = new XmlMapper();
        String xml = mapper.writeValueAsString(products);
        System.out.println(xml);

        YAMLMapper ymapper = new YAMLMapper();
        String yaml = ymapper.writeValueAsString(products);
        System.out.println(yaml);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}