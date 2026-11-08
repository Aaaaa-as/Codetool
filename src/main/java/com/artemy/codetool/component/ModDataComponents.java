package com.artemy.codetool.component;

import com.artemy.codetool.CodeTool;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, CodeTool.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CodeToolComponent>> CODE_TOOL = DATA_COMPONENTS.registerComponentType(
            "code_tool",
            builder -> builder.persistent(CodeToolComponent.CODEC).networkSynchronized(CodeToolComponent.STREAM_CODEC)
    );


    public static void register(IEventBus modEventBus) {
        DATA_COMPONENTS.register(modEventBus);
    }
}
