package ink.magma.zthMyMenu.menu.impl;

import ink.magma.zthMyMenu.ZthMyMenu;
import ink.magma.zthMyMenu.menu.Menu;
import ink.magma.zthMyMenu.menu.MenuHelper;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class QuickMenu implements Menu {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

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
            String balanceStr = ZthMyMenu.econ.format(ZthMyMenu.econ.getBalance(player));
            balanceComponent = miniMessage.deserialize("<black>你的余额: <dark_green>" + balanceStr + "\n\n");
        } else {
            balanceComponent = Component.empty();
        }

        // --- Page 1 ---
        Component title = miniMessage.deserialize("<dark_red><b>玩家快捷指令</b></dark_red>\n");
        if (fromMenu != null) {
            title = Component.text()
                    .append(MenuHelper.createBackButton(fromMenu))
                    .append(Component.text(" "))
                    .append(title)
                    .build();
        }

        Component page1 = Component.text()
                .append(title)
                .appendNewline()
                .append(balanceComponent)
                .append(createButton(
                        "/spawn",
                        "<dark_aqua>[一盏明灯]</dark_aqua>",
                        "<gray>指引你返回偏好的主城.\n\n在零洲使用，需要每隔五分钟才能重新点亮\n据说是因为零洲太大.</gray>"
                ))
                .append(Component.text(" "))
                .append(createButton(
                        "/back",
                        "<dark_aqua>[返回上一地点]</dark_aqua>",
                        "<gray>传送到上一次的传送点.</gray>"
                ))
                .append(Component.text(" "))
                .append(createButton(
                        "/home",
                        "<dark_aqua>[回家]</dark_aqua>",
                        "<gray>Home Sweet Home.\n\n将返回默认家.</gray>"
                ))
                .appendNewline()
                .appendNewline()
                .append(createButton(
                        "/co near",
                        "<dark_aqua>[查询周围历史]</dark_aqua>",
                        "<gray>使用椰能快速查询你周围\n的变化痕迹.</gray>"
                ))
                .append(Component.text(" "))
                .append(createButton(
                        "/co i",
                        "<dark_aqua>[切换查询模式]</dark_aqua>",
                        "<gray>发动椰术\n发动后将能看到所触摸过的方块\n曾经被人操作过的痕迹.</gray>"
                ))
                .appendNewline()
                .appendNewline()
                .append(createLink(
                        "https://wiki.ria.red",
                        "<dark_green>[RIA | Zth Wiki]</dark_green>",
                        "<gray>莉亚的在线百科全书</gray>"
                ))
                .append(Component.text(" "))
                .append(createLink(
                        "https://satellite.ria.red/",
                        "<dark_green>[莉亚红一号卫星]</dark_green>",
                        "<gray>换个视角看莉亚.\n\n<white>点击打开网页卫星地图.</white></gray>"
                ))
                .appendNewline()
                .appendNewline()
                .append(createButton(
                        "/zth-poststations confirm",
                        "<dark_aqua>[菜鱼驿站]</dark_aqua>",
                        "<gray>您的跨世界存储驿站.\n每次打开消耗 〇1.</gray>"
                ))
                .append(Component.text(" "))
                .append(createButton(
                        "/heal",
                        "<dark_aqua>[好味堡]</dark_aqua>",
                        "<gray>云吃堡.</gray>"
                ))
                .build();

        // --- Page 2 ---
        Component page2 = Component.text()
                .append(miniMessage.deserialize("<dark_red><b>功能开关</b></dark_red>\n\n"))
                .append(createButton(
                        "/fastlogin",
                        "<dark_aqua>[切换到正版验证模式]</dark_aqua>",
                        "<gray>启用后, 下次登录\n将会验证您的正版身份.\n\n您可以享受登录免密码\n和原生的 Tab 头像显示特性.</gray>"
                ))
                .append(Component.text(" "))
                .append(createButton(
                        "/cracked",
                        "<dark_aqua>[切换到离线模式]</dark_aqua>",
                        "<gray>切换后, 下次登录时\n将以离线模式进入.\n\n将失去正版模式的特性.</gray>"
                ))
                .appendNewline()
                .appendNewline()
                .append(createButton(
                        "/togglelastserver",
                        "<dark_aqua>[自动返回上次退出时的服务器]</dark_aqua>",
                        "<gray>默认情况下, 登录后将自动返回\n上次退出的世界.</gray>"
                ))
                .appendNewline()
                .appendNewline()
                .append(createButton(
                        "/sit",
                        "<dark_aqua>[当场坐下]</dark_aqua>",
                        "<gray>直接坐在你现在的位置.</gray>"
                ))
                .appendNewline()
                .appendNewline()
                .append(createButton(
                        "/litesignin gui",
                        "<dark_aqua>[每日共鸣]</dark_aqua>",
                        "<gray>祝您拥有美好的一天.</gray>"
                ))
                .build();

        // --- Page 3 ---
        Component page3 = Component.text()
                .append(miniMessage.deserialize("<dark_red><b>其他功能</b></dark_red>\n\n"))
                .append(createButton(
                        "/viaversion list",
                        "<dark_aqua>[你升级了嘛?]</dark_aqua>",
                        "<gray>看看大家都在使用什么版本进行游戏!</gray>"
                ))
                .appendNewline()
                .appendNewline()
                .append(createButton(
                        "/zthmymenu:zmenu colors",
                        "<dark_aqua>[调色板]</dark_aqua>",
                        "<gray>点击将调色板显示到聊天栏</gray>"
                ))
                .appendNewline()
                .appendNewline()
                .append(createButton(
                        "/openspecificstorybook 1",
                        "<dark_aqua>[故事箱 · 漂流瓶]</dark_aqua>",
                        "<gray>点击查看使用教程</gray>"
                ))
                .append(Component.text(" "))
                .append(createButton(
                        "/randomstorybook",
                        "<dark_aqua>[故事箱 · 漂流瓶]</dark_aqua>",
                        "<gray>点击随机看一本书</gray>"
                ))
                .appendNewline()
                .appendNewline()
                .append(createMenuButton(
                        "accessibility",
                        "<dark_green>[辅助功能]</dark_green>",
                        "<gray>一些让 Zth 更好用的选项.</gray>"
                ))
                .append(Component.text(" "))
                .append(createMenuButton(
                        "my_spawn",
                        "<dark_green>[我的主城]</dark_green>",
                        "<gray>设置您的主城传送点.</gray>"
                ))
                .build();


        return Book.book(
                Component.text("快捷菜单"),
                Component.text("服务器"),
                page1,
                page2,
                page3
        );
    }

    private Component createButton(String command, String text, String hoverText) {
        return miniMessage.deserialize(text)
                .clickEvent(ClickEvent.runCommand(command))
                .hoverEvent(miniMessage.deserialize(hoverText).asHoverEvent());
    }

    private Component createLink(String url, String text, String hoverText) {
        return miniMessage.deserialize(text)
                .clickEvent(ClickEvent.openUrl(url))
                .hoverEvent(miniMessage.deserialize(hoverText).asHoverEvent());
    }

    private Component createMenuButton(String menuId, String text, String hoverText) {
        String command = "/zmenu open " + menuId + " " + this.getId();
        return miniMessage.deserialize(text)
                .clickEvent(ClickEvent.runCommand(command))
                .hoverEvent(miniMessage.deserialize(hoverText).asHoverEvent());
    }

}