package dev.xmine.xmpvp.module.modules;

import dev.xmine.xmpvp.module.ClientModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.ChunkPos;

public final class ChunkInfoModule extends ClientModule {
    private ChunkPos currentChunk;

    public ChunkInfoModule() {
        super("Chunk Info", "Shows your current chunk coordinates.");
    }

    @Override
    public void tick(MinecraftClient client) {
        if (client.player == null || client.world == null) return;
        currentChunk = new ChunkPos(client.player.getBlockPos());
    }

    public ChunkPos currentChunk() {
        return currentChunk;
    }
}
