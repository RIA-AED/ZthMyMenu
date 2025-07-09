package ink.magma.zthMyMenu.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.mojang.brigadier.arguments.StringArgumentType;

import ink.magma.zthMyMenu.menu.MenuManager;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

@SuppressWarnings("UnstableApiUsage")
public class CommandManager {

    public static void register(JavaPlugin plugin) {
        plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();

            commands.register(
                    Commands.literal("zmenu")
                            .requires(source -> source.getSender().hasPermission("zth.mymenu.admin"))
                            .then(Commands.literal("list").executes(context -> {
                                handleList(context.getSource().getSender());
                                return 1;
                            }))
                            .then(Commands.literal("open")
                                    .then(Commands.argument("menu_id", StringArgumentType.string())
                                            .suggests((context, builder) -> {
                                                MenuManager.getMenuIds().forEach(builder::suggest);
                                                return builder.buildFuture();
                                            })
                                            .executes(context -> {
                                                if (context.getSource().getSender() instanceof Player player) {
                                                    String menuId = context.getArgument("menu_id", String.class);
                                                    MenuManager.openMenu(player, menuId, null);
                                                } else {
                                                    context.getSource().getSender()
                                                            .sendMessage("Only players can open menus.");
                                                }
                                                return 1;
                                            })
                                            .then(Commands.argument("from_menu_id", StringArgumentType.string())
                                                    .suggests((context, builder) -> {
                                                        MenuManager.getMenuIds().forEach(builder::suggest);
                                                        return builder.buildFuture();
                                                    })
                                                    .executes(context -> {
                                                        if (context.getSource().getSender() instanceof Player player) {
                                                            String menuId = context.getArgument("menu_id", String.class);
                                                            String fromMenuId = context.getArgument("from_menu_id", String.class);
                                                            MenuManager.openMenu(player, menuId, fromMenuId);
                                                        } else {
                                                            context.getSource().getSender()
                                                                    .sendMessage("Only players can open menus.");
                                                        }
                                                        return 1;
                                                    }))))
                            .then(Commands.literal("colors").executes(context -> {
                                if (context.getSource().getSender() instanceof Player player) {
                                    handleColors(player);
                                }
                                return 1;
                            }))
                            .build());

            commands.register(
                    Commands.literal("menu")
                            .requires(source -> source.getSender() instanceof Player)
                            .executes(context -> {
                                if (context.getSource().getSender() instanceof Player player) {
                                    MenuManager.openMenu(player, "quick_menu", null);
                                }
                                return 1;
                            })
                            .build());
        });
    }

    private static void handleList(CommandSender sender) {
        sender.sendMessage(Component.text("Available Menus:", NamedTextColor.GOLD));
        for (String menuId : MenuManager.getMenuIds()) {
            sender.sendMessage(Component.text("- " + menuId, NamedTextColor.GRAY));
        }
    }

    private static void handleColors(Player player) {
        player.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&7-----====== &l调色板 &7======-----"));
        player.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&11 深蓝 &22 深绿 &33 深青 &44 深红 &55 深紫"));
        player.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&66 金色 &77 灰色 &88 深灰 &99 蓝 &00 黑色"));
        player.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&aa 绿色 &bb 青色 &cc 红色 &dd 亮紫 &ee 黄色 &ff 白色"));
        player.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&ll 加粗 &r&f&mm 删除线&r &r&f&nn 下划线&r &r&f&oo 斜体 &r&f&fr 重置"));
        player.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&7-----====== &l调色板 &7======-----"));
    }
}