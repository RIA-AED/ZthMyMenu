package ink.magma.zthMyMenu.menu;

import net.kyori.adventure.inventory.Book;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public interface Menu {
    /**
     * 获取此菜单的唯一ID
     * @return 菜单ID
     */
    String getId();

    /**
     * 获取此菜单的显示名称
     * @return 菜单名称
     */
    String getName();

    /**
     * 为特定玩家构建并返回此菜单的书本对象
     * @param player 目标玩家
     * @param fromMenu 打开此菜单的上一个菜单, 如果是直接打开则为 null
     * @return 构建好的书本
     */
    Book getBook(Player player, @Nullable Menu fromMenu);
}