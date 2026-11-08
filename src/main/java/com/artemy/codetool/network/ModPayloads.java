package com.artemy.codetool.network;


import com.artemy.codetool.CodeTool;
import com.artemy.codetool.component.CodeToolComponent;
import com.artemy.codetool.component.ModDataComponents;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = CodeTool.MODID)
public class ModPayloads {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        // Sets the current network version
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(
                EditCodeToolPayload.TYPE,
                EditCodeToolPayload.STREAM_CODEC,
                new DirectionalPayloadHandler<>(null, ModPayloads::handleEditCodeToolPayload)

        );
    }


    static void handleEditCodeToolPayload(final EditCodeToolPayload payload, final IPayloadContext context) {
        context.player().getMainHandItem().set(ModDataComponents.CODE_TOOL,new CodeToolComponent(payload.code()));
    }
}
