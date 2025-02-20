import java.util.ArrayList;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.gson.Gson;

record Product(int id, String name, String description) {}

class Main {
    public static void main(String[] args) {

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