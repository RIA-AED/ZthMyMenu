package ink.magma.zthMyMenu.menu.impl;

import ink.magma.zthMyMenu.menu.Menu;
import ink.magma.zthMyMenu.menu.MenuHelper;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class MySpawnMenu implements Menu {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

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
        Component title = miniMessage.deserialize("<dark_red><b>我的主城</b></dark_red>\n\n");
        if (fromMenu != null) {
            title = Component.text()
                    .append(MenuHelper.createBackButton(fromMenu))
                    .append(Component.text(" "))
                    .append(title)
                    .build();
        }

        String settingText = "<gray>点击设置为我的出生点</gray>";

        Component page1 = Component.text()
                .append(title)
                .append(createButton(
                        "",
                        "<dark_aqua>[?]</dark_aqua> 什么是多主城",
                        """
                        <gray>在零洲、奈落有多个主城,
                        您可以修改偏好主城, 这将影响 /spawn 命令的默认传送目的地.
                        
                        使用 /spawn <主城代号> 仍然可以临时传送到某个指定主城.
                        主城代号可在下方菜单项中查看.</gray>"""
                ))
                .appendNewline()
                .appendNewline()
                .append(createButton(
                        "/setmyspawn default",
                        "<dark_aqua>[还原默认]</dark_aqua>",
                        """
                        <gray>点击将您的主城还原为默认设置,
                        在零洲时将会传送至初生水殿,
                        在奈落时将会传送至奈落洲主城.</gray>
                        """ + "\n" + settingText
                ))
                .appendNewline()
                .appendNewline()
                .append(createButton(
                        "/setmyspawn spawn",
                        "<dark_aqua>[水殿]</dark_aqua>",
                        """
                        <gray>来自海洋更新的默认出生点
                        最令人安心的地方.
                        
                        主城代号 spawn</gray>
                        """ + "\n" + settingText
                ))
                .appendNewline()
                .append(createButton(
                        "/setmyspawn tsg",
                        "<dark_aqua>[图书馆]</dark_aqua>",
                        """
                        <gray>莉亚主岛图书馆
                        建于 2021 年夏天, 休闲的最佳去处.
                        
                        主城代号 tsg</gray>
                        """ + "\n" + settingText
                ))
                .appendNewline()
                .append(createButton(
                        "/setmyspawn zdmt",
                        "<dark_aqua>[主岛码头]</dark_aqua>",
                        """
                        <gray>清脆的共鸣之音响彻你的耳畔.
                        
                        主城代号 zdmt</gray>
                        """ + "\n" + settingText
                ))
                .appendNewline()
                .append(createButton(
                        "/setmyspawn hfw",
                        "<dark_aqua>[海风湾]</dark_aqua>",
                        """
                        <gray>信任与自由之城.
                        
                        主城代号 hfw</gray>
                        """ + "\n" + settingText
                ))
                .appendNewline()
                .append(createButton(
                        "/setmyspawn yzxd",
                        "<dark_aqua>[鱼子小雕]</dark_aqua>",
                        """
                        <gray>海风湾鱼子小雕
                        海风湾共鸣长廊的起点.
                        
                        主城代号 yzxd</gray>
                        """ + "\n" + settingText
                ))
                .appendNewline()
                .appendNewline()
                .append(createButton(
                        "/setmyspawn naraku",
                        "<dark_aqua>[奈落洲主城]</dark_aqua>",
                        """
                        <gray>未来的新大陆.
                        
                        主城代号 naraku</gray>
                        """ + "\n" + settingText
                ))
                .appendNewline()
                .append(createButton(
                        "/setmyspawn xzhj",
                        "<dark_aqua>[新洲海角]</dark_aqua>",
                        """
                        <gray>出航奈落洲时探险者最早驻扎的地方.
                        
                        主城代号 xzhj</gray>
                        """ + "\n" + settingText
                ))
                .build();

        return Book.book(
                Component.text("我的主城"),
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