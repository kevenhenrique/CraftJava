package com.javacraft.commands;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class BasicJavaCommand {
    public static void register (CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("javabasic")
                        .executes(context -> {
                            context.getSource().sendSuccess(
                                    () -> net.minecraft.network.chat.Component.literal("Comando java básico funcionando"),
                                    false
                            );
                            return 1;
                        })
        );
        dispatcher.register(
                Commands.literal("basicjava")
                        .then(Commands.argument("topic", StringArgumentType.word())
                                .executes(context -> {
                                    String topic = StringArgumentType.getString(context, "topic");

                                    context.getSource().sendSuccess(
                                            () -> Component.literal("📘 Você escolheu o tópico: " + topic),
                                            false
                                    );

                                    return 1;
                                })
                        )
                        .executes(context -> {
                            context.getSource().sendSuccess(
                                    () -> Component.literal("📘 Use: /basicjava <topic>"),
                                    false
                            );
                            return 1;
                        })
        );
    }
}
