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


@Mod(CodeTool.MODID)
public class CodeTool {

    public static final String MODID = "codetool";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final LanguageDefinition JUMPER_LANG = new LanguageDefinition("Jumper",
            List.of(
                    TokenTypes.KEYWORD.createTokenType(List.of("dyn", "break", "case", "catch", "class", "const", "continue", "default", "do", "else", "enum", "false", "finally", "for", "if", "import", "var", "new", "null", "return", "super", "switch", "this", "throw", "true", "try", "void", "while", "with", "yield", "int", "float", "long", "double","short","char","boolean")),
                    TokenTypes.IDENTIFIER,
                    TokenTypes.STRING,
                    TokenTypes.COMMENT,
                    TokenTypes.NUMBER,
                    TokenTypes.OPERATOR,
                    TokenTypes.WHITESPACE,
                    TokenTypes.OTHER
            ), Set.of("{"));



    public CodeTool(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);


        ModDataComponents.register(modEventBus);
        ModItems.register(modEventBus);


        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey()==CreativeModeTabs.OP_BLOCKS){
            event.accept(ModItems.CODE_TOOL);
        }
    }

}
