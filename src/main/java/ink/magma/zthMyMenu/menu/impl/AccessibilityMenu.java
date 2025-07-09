package ink.magma.zthMyMenu.menu.impl;

import ink.magma.zthMyMenu.menu.Menu;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class AccessibilityMenu implements Menu {
    @Override
    public String getId() {
        return "accessibility";
    }

    @Override
    public String getName() {
        return "辅助功能";
    }

    @Override
    public Book getBook(Player player, @Nullable Menu fromMenu) {
        // --- Names ---
        Component localChatName = Component.text("切换到本地聊天", NamedTextColor.DARK_AQUA);
        Component globalChatName = Component.text("切换到世界聊天", NamedTextColor.DARK_AQUA);

        // --- Lores ---
        Component localChatLore = Component.text("开启本项后，您的消息将不会发送到其他大洲\n将会在下次登录时失效.", NamedTextColor.GRAY);
        Component globalChatLore = Component.text("开启本项后，您的消息将会发送到全服\n本项为每次进入服务器的默认状态.", NamedTextColor.GRAY);

        // --- Page 1 ---
        Component title = Component.text("辅助功能\n\n", NamedTextColor.DARK_RED, TextDecoration.BOLD);
        if (fromMenu != null) {
            title = Component.text()
                    .append(createBackButton(fromMenu))
                    .append(Component.text(" "))
                    .append(title)
                    .build();
        }

        Component page1 = Component.text()
                .append(title)
                .append(createButton("/local", localChatName, localChatLore))
                .append(Component.text("\n\n"))
                .append(createButton("/global", globalChatName, globalChatLore))
                .build();

        return Book.book(
                Component.text("辅助功能"),
                Component.text("服务器"),
                page1
        );
    }

    private Component createButton(String command, Component text, Component hoverText) {
        return Component.text()
                .append(Component.text("[", NamedTextColor.DARK_AQUA))
                .append(text)
                .append(Component.text("]", NamedTextColor.DARK_AQUA))
                .clickEvent(ClickEvent.runCommand(command))
                .hoverEvent(hoverText.asHoverEvent())
                .build();
    }

    private Component createBackButton(Menu fromMenu) {
        String command = "/zmenu open " + fromMenu.getId();

        return Component.text()
                .append(Component.text("[", NamedTextColor.RED))
                .append(Component.text("返回", NamedTextColor.WHITE))
                .append(Component.text("]", NamedTextColor.RED))
                .clickEvent(ClickEvent.runCommand(command))
                .hoverEvent(Component.text("返回 " + fromMenu.getName()).asHoverEvent())
                .build();
    }
}