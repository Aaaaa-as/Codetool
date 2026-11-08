package com.artemy.codetool.item;

import com.artemy.codetool.CodeTool;
import com.artemy.codetool.component.CodeToolComponent;
import com.artemy.codetool.component.ModDataComponents;
import com.artemy.codetool.item.custom.CodeToolItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CodeTool.MODID);

    public static final DeferredItem<CodeToolItem> CODE_TOOL = ITEMS.registerItem("code_tool",
            properties -> new CodeToolItem(properties.stacksTo(1).component(ModDataComponents.CODE_TOOL,new CodeToolComponent(""))));

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
