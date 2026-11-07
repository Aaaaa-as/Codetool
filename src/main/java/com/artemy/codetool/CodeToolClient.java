package com.artemy.codetool;

import com.artemy.codetool.item.ModItems;
import com.artemy.codetool.screen.EditCodeToolScreen;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = CodeTool.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = CodeTool.MODID, value = Dist.CLIENT)
public class CodeToolClient {
    public CodeToolClient(ModContainer container) {
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {

    }

    @SubscribeEvent // on the game event bus only on the physical client
    public static void onClientTick(ClientTickEvent.Post event) {
        while (ModKeyMappings.EDIT_CODE_TOOL.get().consumeClick()) {
            assert Minecraft.getInstance().player != null;
            if(Minecraft.getInstance().player.getMainHandItem().is(ModItems.CODE_TOOL))
                Minecraft.getInstance().setScreen(new EditCodeToolScreen());
        }
    }
}
