package ink.magma.zthMyMenu.menu.impl;

import ink.magma.zthMyMenu.menu.Menu;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class MySpawnMenu implements Menu {
    @Override
    public String getId() {
        return "my_spawn";
    }

    @Override
    public String getName() {
        return "我的主城";
    }

    @Override
    public Book getBook(Player player, @Nullable Menu fromMenu) {
        // --- Names ---
        Component setSpawnName = Component.text("设置我的主城", NamedTextColor.DARK_AQUA);

        // --- Lores ---
        Component setSpawnLore = Component.text("在这里设置你的主城.", NamedTextColor.GRAY);

        // --- Page 1 ---
        Component title = Component.text("我的主城\n\n", NamedTextColor.DARK_RED, TextDecoration.BOLD);
        if (fromMenu != null) {
            title = Component.text()
                    .append(createBackButton(fromMenu))
                    .append(Component.text(" "))
                    .append(title)
                    .build();
        }

        Component page1 = Component.text()
                .append(title)
                .append(createButton("/setspawn", setSpawnName, setSpawnLore))
                .build();

        return Book.book(
                Component.text("我的主城"),
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