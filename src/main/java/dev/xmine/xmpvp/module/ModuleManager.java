package dev.xmine.xmpvp.module;

import net.minecraft.client.MinecraftClient;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class ModuleManager {
    private final Map<String, ClientModule> modules = new LinkedHashMap<>();

    public void register(ClientModule module) {
        modules.put(module.name().toLowerCase(), module);
    }

    public Optional<ClientModule> get(String name) {
        return Optional.ofNullable(modules.get(name.toLowerCase()));
    }

    public Collection<ClientModule> all() {
        return modules.values();
    }

    public void tick(MinecraftClient client) {
        for (ClientModule module : modules.values()) {
            if (module.isEnabled()) module.tick(client);
        }
    }
}
