package ink.magma.zthMyMenu.menu;

import org.bukkit.entity.Player;

import java.util.*;

public class MenuManager {
    private static final Map<String, Menu> menus = new HashMap<>();

    /**
     * 注册一个新菜单
     *
     * @param menu 要注册的菜单实例
     */
    public static void registerMenu(Menu menu) {
        menus.put(menu.getId(), menu);
    }

    /**
     * 获取所有已注册菜单的ID列表
     *
     * @return 菜单ID的Set集合
     */
    public static java.util.Set<String> getMenuIds() {
        return menus.keySet();
    }

    /**
     * 为玩家打开一个指定ID的菜单
     *
     * @param player   目标玩家
     * @param menuId   目标菜单的ID
     * @param fromMenuId 打开此菜单的上一个菜单的ID, 如果是直接打开则为 null
     */
    public static void openMenu(Player player, String menuId, String fromMenuId) {
        Menu menu = menus.get(menuId);
        if (menu == null) {
            player.sendMessage("Menu not found: " + menuId);
            return;
        }

        Menu fromMenu = null;
        if (fromMenuId != null) {
            fromMenu = menus.get(fromMenuId);
        }

        player.openBook(menu.getBook(player, fromMenu));
    }
}