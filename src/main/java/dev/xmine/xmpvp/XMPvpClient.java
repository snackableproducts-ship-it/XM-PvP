package dev.xmine.xmpvp;

import dev.xmine.xmpvp.gui.ClickGuiScreen;
import dev.xmine.xmpvp.module.ModuleManager;
import dev.xmine.xmpvp.module.modules.ChunkInfoModule;
import dev.xmine.xmpvp.module.modules.PracticeStatsModule;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public final class XMPvpClient implements ClientModInitializer {
    public static final String MOD_ID = "xmpvp";
    public static final ModuleManager MODULES = new ModuleManager();
    private static KeyBinding clickGuiKey;

    @Override
    public void onInitializeClient() {
        MODULES.register(new ChunkInfoModule());
        MODULES.register(new PracticeStatsModule());

        clickGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.xmpvp.click_gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                KeyBinding.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (clickGuiKey.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new ClickGuiScreen());
                }
            }
            MODULES.tick(client);
        });
    }
}
