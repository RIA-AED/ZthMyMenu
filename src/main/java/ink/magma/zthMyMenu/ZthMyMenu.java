package ink.magma.zthMyMenu;

import ink.magma.zthMyMenu.menu.MenuManager;
import ink.magma.zthMyMenu.menu.impl.QuickMenu;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class ZthMyMenu extends JavaPlugin {
    public static Economy econ = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!setupEconomy()) {
            getLogger().warning("Vault not found! Economy features will be disabled.");
        }

        // 注册所有菜单
        registerMenus();

        getCommand("mymenu").setExecutor(new ink.magma.zthMyMenu.command.MenuCommand());
    }

    private void registerMenus() {
        MenuManager.registerMenu(new QuickMenu());
        // 未来可以在这里注册更多菜单
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
