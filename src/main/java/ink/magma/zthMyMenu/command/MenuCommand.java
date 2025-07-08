package ink.magma.zthMyMenu.command;

import ink.magma.zthMyMenu.ZthMyMenu;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        // Page 1
        final Component balanceComponent;
        if (ZthMyMenu.econ != null) {
            balanceComponent = Component.text("你的余额: " + ZthMyMenu.econ.format(ZthMyMenu.econ.getBalance(player)) + "\n\n", NamedTextColor.GOLD);
        } else {
            balanceComponent = Component.text("经济系统未链接\n\n", NamedTextColor.GRAY);
        }

        Component page1 = Component.text()
                .append(Component.text("玩家快捷指令\n", NamedTextColor.RED, TextDecoration.BOLD))
                .append(balanceComponent)
                .append(createButton("/spawn", "主城"))
                .append(Component.text(" "))
                .append(createButton("/back", "返回"))
                .append(Component.text(" "))
                .append(createButton("/home", "回家"))
                .append(Component.text("\n\n"))
                .append(createButton("/co near", "附近查询"))
                .append(Component.text(" "))
                .append(createButton("/co i", "方块查询"))
                .append(Component.text("\n\n"))
                .append(createLink("https://wiki.ria.red", "维基"))
                .append(Component.text(" "))
                .append(createLink("https://satellite.ria.red/", "卫星地图"))
                .build();

        // Page 2
        Component page2 = Component.text()
                .append(Component.text("功能开关\n\n", NamedTextColor.RED, TextDecoration.BOLD))
                .append(createButton("/fastlogin", "正版登录"))
                .append(Component.text(" "))
                .append(createButton("/cracked", "离线登录"))
                .append(Component.text("\n\n"))
                .append(createButton("/togglelastserver", "自动返回子服"))
                .append(Component.text("\n\n"))
                .append(createButton("/sit", "坐下"))
                .append(Component.text("\n\n"))
                .append(createButton("/litesignin gui", "签到日历"))
                .build();


        final Book myBook = Book.book(
                Component.text("快捷菜单"),
                Component.text("服务器"),
                page1,
                page2
        );

        player.openBook(myBook);

        return true;
    }

    private Component createButton(String command, String text) {
        return Component.text("[" + text + "]", NamedTextColor.AQUA, TextDecoration.UNDERLINED)
                .clickEvent(ClickEvent.runCommand(command));
    }

    private Component createLink(String url, String text) {
        return Component.text("[" + text + "]", NamedTextColor.GREEN, TextDecoration.UNDERLINED)
                .clickEvent(ClickEvent.openUrl(url));
    }
}