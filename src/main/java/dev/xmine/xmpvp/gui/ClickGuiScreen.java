package dev.xmine.xmpvp.gui;

import dev.xmine.xmpvp.XMPvpClient;
import dev.xmine.xmpvp.module.ClientModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;

public final class ClickGuiScreen extends Screen {
    private static final int PANEL = 0xF20D0D12;
    private static final int PANEL_ALT = 0xFF171720;
    private static final int PURPLE = 0xFF9B72FF;
    private static final int TEXT = 0xFFF4F0FF;
    private static final int MUTED = 0xFFAAA4B8;
    private final List<Row> rows = new ArrayList<>();
    private int panelX, panelY, panelW, panelH;

    public ClickGuiScreen() {
        super(Text.literal("XM PvP"));
    }

    @Override
    protected void init() {
        panelW = Math.min(540, width - 32);
        panelH = Math.min(360, height - 32);
        panelX = (width - panelW) / 2;
        panelY = (height - panelH) / 2;
        rows.clear();
        int y = panelY + 100;
        for (ClientModule module : XMPvpClient.MODULES.all()) {
            rows.add(new Row(module, panelX + 190, y, panelW - 214, 34));
            y += 42;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        context.fill(0, 0, width, height, 0x99000000);
        context.fill(panelX, panelY, panelX + panelW, panelY + panelH, PANEL);
        context.fill(panelX, panelY, panelX + panelW, panelY + 3, PURPLE);
        context.fill(panelX + 14, panelY + 14, panelX + 164, panelY + panelH - 14, PANEL_ALT);

        context.drawText(textRenderer, Text.literal("XM").styled(s -> s.withColor(PURPLE)), panelX + 28, panelY + 25, PURPLE, false);
        context.drawText(textRenderer, Text.literal("P V P").styled(s -> s.withBold(true)), panelX + 62, panelY + 25, TEXT, false);
        context.drawText(textRenderer, Text.literal("PREMIUM CLIENT"), panelX + 28, panelY + 44, MUTED, false);

        context.drawText(textRenderer, Text.literal("MODULES"), panelX + 28, panelY + 76, MUTED, false);
        context.drawText(textRenderer, Text.literal("CLIENT OVERVIEW"), panelX + 190, panelY + 27, TEXT, false);
        context.drawText(textRenderer, Text.literal("Right Shift  /  Close menu"), panelX + 190, panelY + 47, MUTED, false);
        context.fill(panelX + 190, panelY + 72, panelX + panelW - 18, panelY + 73, 0xFF34303F);

        for (Row row : rows) {
            boolean hover = mouseX >= row.x && mouseX <= row.x + row.w && mouseY >= row.y && mouseY <= row.y + row.h;
            int bg = hover ? 0xFF282333 : PANEL_ALT;
            context.fill(row.x, row.y, row.x + row.w, row.y + row.h, bg);
            context.drawText(textRenderer, Text.literal(row.module.name()), row.x + 10, row.y + 7, TEXT, false);
            context.drawText(textRenderer, Text.literal(row.module.isEnabled() ? "ON" : "OFF"),
                    row.x + row.w - 34, row.y + 7, row.module.isEnabled() ? PURPLE : MUTED, false);
        }

        context.drawText(textRenderer, Text.literal("XM PvP  •  BUILD 0.1.0"), panelX + 190,
                panelY + panelH - 25, MUTED, false);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            for (Row row : rows) {
                if (mouseX >= row.x && mouseX <= row.x + row.w &&
                        mouseY >= row.y && mouseY <= row.y + row.h) {
                    row.module.toggle(client);
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    private record Row(ClientModule module, int x, int y, int w, int h) {}
}
