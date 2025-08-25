package reports.template;
import java.util.HashMap;
import java.util.Map;

public class LaudoFactoryRegistry {

    private static final Map<String, LaudoTemplate> registry = new HashMap<>();

    static {
        registry.put("texto", new LaudoTexto());
        registry.put("txt", new LaudoTexto());
        registry.put("html", new LaudoHtml());
        registry.put("pdf", new LaudoPdf());
    }

    public static void registerTemplate(String key, LaudoTemplate template) {
        registry.put(key.toLowerCase(), template);
    }

    public static LaudoTemplate getTemplate(String key) {
        return registry.getOrDefault(key.toLowerCase(), new LaudoTexto());
    }
}
