package com.javacraft;
import com.javacraft.commands.CommandRegistry;
import com.javacraft.commands.BasicJavaCommand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

@Mod(JavaCraft.MOD_ID)

public class JavaCraft{
    public static final String MOD_ID = "javacraft";

    public JavaCraft(){
        System.out.println(">>>> Mod Carregado com sucesso!!");
    }
    @Mod.EventBusSubscriber(modid = MOD_ID)
    public static class Events {
        @SubscribeEvent
        public static void registerCommands(RegisterCommandsEvent event){
            BasicJavaCommand.register(event.getDispatcher());
        }
    }


    @Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onPlayerLogin(ClientPlayerNetworkEvent.LoggingIn event){
            Minecraft.getInstance().player.sendSystemMessage(
                    Component.literal(">>>>>>>>Mod carregado com sucesso!!!!!!!\n\n>>>>>>>>Use /ajuda_java para obter mais informações.")
            );
        }
    }
}
