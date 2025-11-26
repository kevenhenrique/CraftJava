package com.javacraft.commands;
import com.javacraft.JavaCraft;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.commands.Commands;


@Mod.EventBusSubscriber(modid = JavaCraft.MOD_ID)
public class CommandRegistry {

    @SubscribeEvent
    public static void onregisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        BasicJavaCommand.register(dispatcher);

        dispatcher.register(
                Commands.literal("ajuda_java")
                        .executes(context -> {
                            CommandMessages.sendHelpText(context.getSource());
                            return 1;
                        })

        );
        dispatcher.register(
                Commands.literal("java_if")
                        .executes(context -> {
                            CommandMessages.sendIfExplanation(context.getSource());
                            return 1;
                        })

        );
        dispatcher.register(
                Commands.literal("java_loop")
                        .executes(context -> {
                            CommandMessages.sendLoopExplanation(context.getSource());
                            return 1;
                        })
        );
        dispatcher.register(
                Commands.literal("java_variavel")
                        .executes(context -> {
                            CommandMessages.sendVariableExplanation();
                            return 1;
                        })
        );
        dispatcher.register(
                Commands.literal("variavel")
                        .executes(context -> {
                            CommandMessages.summonVariable(context.getSource());
                            return 1;
                        })
        );
    }
}