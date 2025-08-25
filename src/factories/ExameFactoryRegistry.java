package factories;
import java.util.HashMap;
import java.util.Map;

public class ExameFactoryRegistry {
    private static final Map<String, FabricaExame> registry = new HashMap<>();

    public static void registerFactory(String key, FabricaExame factory) {
        registry.put(key.toLowerCase(), factory);
    }
    
    public static FabricaExame getFactory(String key) {
        return registry.get(key.toLowerCase());
    }
}

