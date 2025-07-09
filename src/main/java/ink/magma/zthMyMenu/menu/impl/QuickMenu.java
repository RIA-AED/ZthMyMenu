package ink.magma.zthMyMenu.menu.impl;

import ink.magma.zthMyMenu.ZthMyMenu;
import ink.magma.zthMyMenu.menu.Menu;
import ink.magma.zthMyMenu.menu.MenuHelper;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class QuickMenu implements Menu {
    @Override
    public String getId() {
        return "quick_menu";
    }

    @Override
    public String getName() {
        return "快捷菜单";
    }

    @Override
    public Book getBook(Player player, @Nullable Menu fromMenu) {
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
        Component viaVerName = Component.text("你升级了嘛?", NamedTextColor.DARK_AQUA);
        Component colorName = Component.text("调色板", NamedTextColor.DARK_AQUA);
        Component story1Name = Component.text("故事箱 · 漂流瓶", NamedTextColor.DARK_AQUA);
        Component story2Name = Component.text("故事箱 · 漂流瓶", NamedTextColor.DARK_AQUA);
        Component accessibilityName = Component.text("辅助功能", NamedTextColor.DARK_AQUA);
        Component mySpawnName = Component.text("我的主城", NamedTextColor.DARK_AQUA);
        Component enderChestName = Component.text("菜鱼驿站", NamedTextColor.DARK_AQUA);
        Component hamburgerName = Component.text("好味堡", NamedTextColor.DARK_AQUA);

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
        Component viaVerLore = Component.text("看看大家都在使用什么版本进行游戏!", NamedTextColor.GRAY);
        Component colorLore = Component.text("点击将调色板显示到聊天栏", NamedTextColor.GRAY);
        Component story1Lore = Component.text("点击查看使用教程", NamedTextColor.GRAY);
        Component story2Lore = Component.text("点击随机看一本书", NamedTextColor.GRAY);
        Component accessibilityLore = Component.text("一些让 Zth 更好用的选项.", NamedTextColor.GRAY);
        Component mySpawnLore = Component.text("设置您的主城传送点.", NamedTextColor.GRAY);
        Component enderChestLore = Component.text("您的跨世界存储驿站.\n每次打开消耗 〇1.", NamedTextColor.GRAY);
        Component hamburgerLore = Component.text("云吃堡.", NamedTextColor.GRAY);

        // --- Page 1 ---
        Component title = Component.text("玩家快捷指令\n", NamedTextColor.DARK_RED, TextDecoration.BOLD);
        if (fromMenu != null) {
            title = Component.text()
                    .append(MenuHelper.createBackButton(fromMenu))
                    .append(Component.text(" "))
                    .append(title)
                    .build();
        }

        Component page1 = Component.text()
                .append(title)
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
                .append(Component.text("\n\n"))
                .append(createButton("/opbp", enderChestName, enderChestLore))
                .append(Component.text(" "))
                .append(createButton("/heal", hamburgerName, hamburgerLore))
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

        // --- Page 3 ---
        Component page3 = Component.text()
                .append(Component.text("其他功能\n\n", NamedTextColor.DARK_RED, TextDecoration.BOLD))
                .append(createButton("/viaversion list", viaVerName, viaVerLore))
                .append(Component.text("\n\n"))
                .append(createButton("/zthmenu colors", colorName, colorLore))
                .append(Component.text("\n\n"))
                .append(createButton("/openspecificstorybook 1", story1Name, story1Lore))
                .append(Component.text(" "))
                .append(createButton("/randomstorybook", story2Name, story2Lore))
                .append(Component.text("\n\n"))
                .append(createMenuButton("accessibility", accessibilityName, accessibilityLore))
                .append(Component.text(" "))
                .append(createMenuButton("my_spawn", mySpawnName, mySpawnLore))
                .build();


        return Book.book(
                Component.text("快捷菜单"),
                Component.text("服务器"),
                page1,
                page2,
                page3
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

    private Component createLink(String url, Component text, Component hoverText) {
        return Component.text()
                .append(Component.text("[", NamedTextColor.DARK_GREEN))
                .append(text)
                .append(Component.text("]", NamedTextColor.DARK_GREEN))
                .clickEvent(ClickEvent.openUrl(url))
                .hoverEvent(hoverText.asHoverEvent())
                .build();
    }

    private Component createMenuButton(String menuId, Component text, Component hoverText) {
        String command = "/zmenu open " + menuId + " " + this.getId();

        return Component.text()
                .append(Component.text("[", NamedTextColor.DARK_GREEN))
                .append(text)
                .append(Component.text("]", NamedTextColor.DARK_GREEN))
                .clickEvent(ClickEvent.runCommand(command))
                .hoverEvent(hoverText.asHoverEvent())
                .build();
    }

}