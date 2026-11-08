package com.artemy.codetool;

import com.artemy.codetool.item.ModItems;
import com.artemy.codetool.component.ModDataComponents;
import com.lowdragmc.lowdraglib2.gui.ui.elements.codeeditor.language.LanguageDefinition;
import com.lowdragmc.lowdraglib2.gui.ui.elements.codeeditor.language.TokenTypes;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.List;
import java.util.Set;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CodeTool.MODID)
public class CodeTool {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "codetool";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final LanguageDefinition JUMPER_LANG = new LanguageDefinition("Jumper",
            List.of(
                    TokenTypes.KEYWORD.createTokenType(List.of("dyn", "break", "case", "catch", "class", "const", "continue", "default", "do", "else", "enum", "false", "finally", "for", "if", "import", "var", "new", "null", "return", "super", "switch", "this", "throw", "true", "try", "void", "while", "with", "yield", "int", "float", "long", "double","short","char")),
                    TokenTypes.IDENTIFIER,
                    TokenTypes.STRING,
                    TokenTypes.COMMENT,
                    TokenTypes.NUMBER,
                    TokenTypes.OPERATOR,
                    TokenTypes.WHITESPACE,
                    TokenTypes.OTHER
            ), Set.of("{"));

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public CodeTool(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        // Register the Deferred Register to the mod event bus so items get registered
        ModDataComponents.register(modEventBus);
        ModItems.register(modEventBus);


        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey()==CreativeModeTabs.OP_BLOCKS){
            event.accept(ModItems.CODE_TOOL);
        }
    }

}
