package ink.magma.zthMyMenu.menu.impl;

import ink.magma.zthMyMenu.menu.Menu;
import ink.magma.zthMyMenu.menu.MenuHelper;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class AccessibilityMenu implements Menu {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

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
        // --- Page 1 ---
        Component title = miniMessage.deserialize("<dark_red><b>辅助功能</b></dark_red>\n\n");
        if (fromMenu != null) {
            title = Component.text()
                    .append(MenuHelper.createBackButton(fromMenu))
                    .append(Component.text(" "))
                    .append(title)
                    .build();
        }

        Component page1 = Component.text()
                .append(title)
                .append(createButton(
                        "/local",
                        "<dark_aqua>[切换到本地聊天]</dark_aqua>",
                        "<gray>开启本项后，您的消息将不会发送到其他大洲\n将会在下次登录时失效.</gray>"
                ))
                .appendNewline()
                .append(createButton(
                        "/global",
                        "<dark_aqua>[切换到世界聊天]</dark_aqua>",
                        "<gray>开启本项后，您的消息将会发送到全服\n本项为每次进入服务器的默认状态.</gray>"
                ))
                .build();

        return Book.book(
                Component.text("辅助功能"),
                Component.text("服务器"),
                page1
        );
    }

    private Component createButton(String command, String text, String hoverText) {
        return miniMessage.deserialize(text)
                .clickEvent(ClickEvent.runCommand(command))
                .hoverEvent(miniMessage.deserialize(hoverText).asHoverEvent());
    }

}