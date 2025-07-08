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
            balanceComponent = Component.text("你的余额: ", NamedTextColor.BLACK)
                    .append(Component.text(ZthMyMenu.econ.format(ZthMyMenu.econ.getBalance(player)) + "\n\n", NamedTextColor.DARK_GREEN));
        } else {
            balanceComponent = Component.text("经济系统未链接\n\n", NamedTextColor.DARK_GRAY);
        }

        // --- Names ---
        Component spawnName = Component.text("一盏明灯", NamedTextColor.DARK_AQUA);
        Component backName = Component.text("返回上一地点", NamedTextColor.DARK_AQUA);
        Component homeName = Component.text("回家", NamedTextColor.DARK_AQUA);
        Component coNearName = Component.text("查询周围历史", NamedTextColor.DARK_AQUA);
        Component coInspectName = Component.text("切换查询模式", NamedTextColor.DARK_AQUA);
        Component wikiName = Component.text("RIA | Zth Wiki", NamedTextColor.DARK_GREEN);
        Component satelliteName = Component.text("莉亚红一号卫星", NamedTextColor.DARK_GREEN);
        Component fastLoginName = Component.text("切换到正版验证模式", NamedTextColor.DARK_AQUA);
        Component crackedName = Component.text("切换到离线模式", NamedTextColor.DARK_AQUA);
        Component toggleLastServerName = Component.text("自动返回上次退出时的服务器", NamedTextColor.DARK_AQUA);
        Component sitName = Component.text("当场坐下", NamedTextColor.DARK_AQUA);
        Component signInName = Component.text("每日共鸣", NamedTextColor.DARK_AQUA);

        // --- Lores ---
        Component spawnLore = Component.text("指引你返回偏好的主城.\n\n", NamedTextColor.GRAY)
                .append(Component.text("在零洲使用，需要每隔五分钟才能重新点亮\n", NamedTextColor.GRAY))
                .append(Component.text("据说是因为零洲太大.", NamedTextColor.GRAY));
        Component backLore = Component.text("传送到上一次的传送点.", NamedTextColor.GRAY);
        Component homeLore = Component.text("Home Sweet Home.\n\n", NamedTextColor.GRAY).append(Component.text("将返回默认家.", NamedTextColor.GRAY));
        Component coNearLore = Component.text("使用椰能快速查询你周围\n的变化痕迹.", NamedTextColor.GRAY);
        Component coInspectLore = Component.text("发动椰术\n发动后将能看到所触摸过的方块\n曾经被人操作过的痕迹.", NamedTextColor.GRAY);
        Component wikiLore = Component.text("莉亚的在线百科全书", NamedTextColor.GRAY);
        Component satelliteLore = Component.text("换个视角看莉亚.\n\n", NamedTextColor.GRAY).append(Component.text("点击打开网页卫星地图.", NamedTextColor.WHITE));
        Component fastLoginLore = Component.text("启用后, 下次登录\n将会验证您的正版身份.\n\n", NamedTextColor.GRAY)
                .append(Component.text("您可以享受登录免密码\n和原生的 Tab 头像显示特性.", NamedTextColor.GRAY));
        Component crackedLore = Component.text("切换后, 下次登录时\n将以离线模式进入.\n\n", NamedTextColor.GRAY).append(Component.text("将失去正版模式的特性.", NamedTextColor.GRAY));
        Component toggleLastServerLore = Component.text("默认情况下, 登录后将自动返回\n上次退出的世界.", NamedTextColor.GRAY);
        Component sitLore = Component.text("直接坐在你现在的位置.", NamedTextColor.GRAY);
        Component signInLore = Component.text("祝您拥有美好的一天.", NamedTextColor.GRAY);

        // --- Page 1 ---
        Component page1 = Component.text()
                .append(Component.text("玩家快捷指令\n", NamedTextColor.DARK_RED, TextDecoration.BOLD))
                .append(balanceComponent)
                .append(createButton("/spawn", spawnName, spawnLore))
                .append(Component.text(" "))
                .append(createButton("/back", backName, backLore))
                .append(Component.text(" "))
                .append(createButton("/home", homeName, homeLore))
                .append(Component.text("\n\n"))
                .append(createButton("/co near", coNearName, coNearLore))
                .append(Component.text(" "))
                .append(createButton("/co i", coInspectName, coInspectLore))
                .append(Component.text("\n\n"))
                .append(createLink("https://wiki.ria.red", wikiName, wikiLore))
                .append(Component.text(" "))
                .append(createLink("https://satellite.ria.red/", satelliteName, satelliteLore))
                .build();

        // --- Page 2 ---
        Component page2 = Component.text()
                .append(Component.text("功能开关\n\n", NamedTextColor.DARK_RED, TextDecoration.BOLD))
                .append(createButton("/fastlogin", fastLoginName, fastLoginLore))
                .append(Component.text(" "))
                .append(createButton("/cracked", crackedName, crackedLore))
                .append(Component.text("\n\n"))
                .append(createButton("/togglelastserver", toggleLastServerName, toggleLastServerLore))
                .append(Component.text("\n\n"))
                .append(createButton("/sit", sitName, sitLore))
                .append(Component.text("\n\n"))
                .append(createButton("/litesignin gui", signInName, signInLore))
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

    private Component createButton(String command, Component text, Component hoverText) {
        return Component.text()
                .append(Component.text("[", NamedTextColor.DARK_AQUA))
                .append(text)
                .append(Component.text("]", NamedTextColor.DARK_AQUA))
                .clickEvent(ClickEvent.runCommand(command))
                .hoverEvent(hoverText.asHoverEvent())
                .build();
    }

    private Component createLink(String url, Component text, Component hoverText) {
        return Component.text()
                .append(Component.text("[", NamedTextColor.DARK_GREEN))
                .append(text)
                .append(Component.text("]", NamedTextColor.DARK_GREEN))
                .clickEvent(ClickEvent.openUrl(url))
                .hoverEvent(hoverText.asHoverEvent())
                .build();
    }
}