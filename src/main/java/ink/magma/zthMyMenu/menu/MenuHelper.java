package ink.magma.zthMyMenu.menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;

public class MenuHelper {
    public static Component createBackButton(Menu fromMenu) {
        String command = "/zmenu open " + fromMenu.getId();

        return Component.text()
                .append(Component.text("[", NamedTextColor.RED))
                .append(Component.text("返回", NamedTextColor.DARK_RED))
                .append(Component.text("]", NamedTextColor.RED))
                .clickEvent(ClickEvent.runCommand(command))
                .hoverEvent(Component.text("返回 " + fromMenu.getName()).asHoverEvent())
                .build();
    }
}