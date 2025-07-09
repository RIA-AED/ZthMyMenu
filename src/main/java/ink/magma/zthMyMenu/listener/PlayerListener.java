package ink.magma.zthMyMenu.listener;

import ink.magma.zthMyMenu.menu.MenuManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerListener implements Listener {
    // 存储玩家开始潜行的时间戳
    private final Map<UUID, Long> sneakTimestamps = new HashMap<>();
    private final long SNEAK_TIMEOUT = 2000; // 2秒超时

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (event.isSneaking()) {
            // 玩家开始潜行，记录时间
            sneakTimestamps.put(playerUUID, System.currentTimeMillis());
        } else {
            // 玩家停止潜行，移除记录
            sneakTimestamps.remove(playerUUID);
        }
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        // 检查玩家是否在潜行计时列表中
        if (sneakTimestamps.containsKey(playerUUID)) {
            long sneakTime = sneakTimestamps.get(playerUUID);
            long currentTime = System.currentTimeMillis();

            if ((currentTime - sneakTime) <= SNEAK_TIMEOUT) {
                // 时间在2秒内，判定为快捷操作
                event.setCancelled(true); // 取消默认的物品交换行为
                MenuManager.openMenu(player, "quick_menu", null); // 打开快捷菜单
                sneakTimestamps.remove(playerUUID); // 重置状态
            }
            // 如果超过2秒，不做任何事，允许正常的物品交换
            else {
                // 超时了, 移除该次潜行记录
                sneakTimestamps.remove(playerUUID);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // 玩家下线时，清理记录，防止内存泄漏
        sneakTimestamps.remove(event.getPlayer().getUniqueId());
    }
}