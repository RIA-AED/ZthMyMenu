package ink.magma.zthMyMenu.command;

import ink.magma.zthMyMenu.menu.MenuManager;
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

        if (args.length > 1 && args[0].equalsIgnoreCase("open")) {
            String menuId = args[1];
            MenuManager.openMenu(player, menuId);
        } else {
            // 默认打开快捷菜单
            MenuManager.openMenu(player, "quick_menu");
        }

        return true;
    }
}