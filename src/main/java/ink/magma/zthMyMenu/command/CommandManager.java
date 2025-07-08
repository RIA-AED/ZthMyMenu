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
                                                    MenuManager.openMenu(player, menuId);
                                                } else {
                                                    context.getSource().getSender()
                                                            .sendMessage("Only players can open menus.");
                                                }
                                                return 1;
                                            })))
                            .build());

            commands.register(
                    Commands.literal("menu")
                            .requires(source -> source.getSender() instanceof Player)
                            .executes(context -> {
                                if (context.getSource().getSender() instanceof Player player) {
                                    MenuManager.openMenu(player, "quick_menu");
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
}