package ink.magma.zthMyMenu.menu;

import net.kyori.adventure.inventory.Book;
import org.bukkit.entity.Player;

public interface Menu {
    /**
     * 获取此菜单的唯一ID
     * @return 菜单ID
     */
    String getId();

    /**
     * 为特定玩家构建并返回此菜单的书本对象
     * @param player 目标玩家
     * @return 构建好的书本
     */
    Book getBook(Player player);
}