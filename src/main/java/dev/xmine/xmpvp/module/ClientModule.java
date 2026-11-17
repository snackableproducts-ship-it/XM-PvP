package dev.xmine.xmpvp.module;

import net.minecraft.client.MinecraftClient;

public abstract class ClientModule {
    private final String name;
    private final String description;
    private boolean enabled;

    protected ClientModule(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public final String name() { return name; }
    public final String description() { return description; }
    public final boolean isEnabled() { return enabled; }

    public final void setEnabled(boolean value, MinecraftClient client) {
        if (enabled == value) return;
        enabled = value;
        if (enabled) onEnable(client);
        else onDisable(client);
    }

    public final void toggle(MinecraftClient client) {
        setEnabled(!enabled, client);
    }

    public void tick(MinecraftClient client) {}
    protected void onEnable(MinecraftClient client) {}
    protected void onDisable(MinecraftClient client) {}
}
