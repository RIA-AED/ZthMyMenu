package ink.magma.zthMyMenu.menu;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MenuManager {
    private static final Map<String, Menu> menus = new HashMap<>();

    /**
     * 注册一个新菜单
     * @param menu 要注册的菜单实例
     */
    public static void registerMenu(Menu menu) {
        menus.put(menu.getId(), menu);
    }

    /**
     * 为玩家打开一个指定ID的菜单
     * @param player 目标玩家
     * @param menuId 目标菜单的ID
     */
    public static void openMenu(Player player, String menuId) {
        Menu menu = menus.get(menuId);
        if (menu != null) {
            player.openBook(menu.getBook(player));
        } else {
            player.sendMessage("Menu not found: " + menuId);
        }
    }
}