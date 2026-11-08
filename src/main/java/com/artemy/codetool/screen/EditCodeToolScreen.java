package com.artemy.codetool.screen;

import com.artemy.codetool.CodeTool;
import com.artemy.codetool.component.CodeToolComponent;
import com.artemy.codetool.component.ModDataComponents;
import com.artemy.codetool.network.EditCodeToolPayload;
import com.lowdragmc.lowdraglib2.gui.ui.ModularUI;
import com.lowdragmc.lowdraglib2.gui.ui.UI;
import com.lowdragmc.lowdraglib2.gui.ui.UIElement;
import com.lowdragmc.lowdraglib2.gui.ui.elements.Button;
import com.lowdragmc.lowdraglib2.gui.ui.elements.Label;
import com.lowdragmc.lowdraglib2.gui.ui.elements.codeeditor.CodeEditor;
import com.lowdragmc.lowdraglib2.gui.ui.styletemplate.Sprites;
import dev.vfyjxf.taffy.style.FlexDirection;
import dev.vfyjxf.taffy.style.TaffyDimension;
import dev.vfyjxf.taffy.style.TaffyDisplay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Arrays;

@OnlyIn(Dist.CLIENT)
public class EditCodeToolScreen extends Screen {
    private final ItemStack itemStack;

    public EditCodeToolScreen(ItemStack stack) {
        super(Component.empty());
        itemStack = stack;
    }

    private ModularUI createModularUI() {
        var root = new UIElement();
        final String[] code = {""};

        CodeEditor codeEditor = new CodeEditor();
        codeEditor.setLanguage(CodeTool.JUMPER_LANG);
        codeEditor.setLinesResponder(lines-> code[0] = String.join("\n", lines));
        codeEditor.textAreaStyle(style->style.fontSize(8));
        codeEditor.layout(layout->layout.setWidth(TaffyDimension.length(350)).setHeight(TaffyDimension.length(300)));
        codeEditor.setLines(Arrays.asList(itemStack.get(ModDataComponents.CODE_TOOL.get()).code().split("\n")));
        root.addChildren(
                new Label().setText("Edit Code Tool"),
                codeEditor,
                new UIElement()
                        .layout(layout -> layout.display(TaffyDisplay.FLEX).flexDirection(FlexDirection.ROW).flexGrow(1))
                        .addChildren(
                                new Button().setText("Save").setOnClick(event->{
                                    itemStack.set(ModDataComponents.CODE_TOOL.get(),new CodeToolComponent(code[0]));
                                    PacketDistributor.sendToServer(new EditCodeToolPayload(code[0]));
                                    Minecraft.getInstance().setScreen(null);
                                }).layout(layout->layout.flex(1)),
                                new Button().setText("Cancel").setOnClick(event-> Minecraft.getInstance().setScreen(null)).layout(layout->layout.flex(1))
                        )
        ).layout(layout->layout.paddingAll(7).gapAll(5)).style(style -> style.background(Sprites.BORDER));
        var ui = UI.of(root);
        return ModularUI.of(ui);
    }


    @Override
    protected void init() {
        super.init();
        var modularUI = createModularUI();
        modularUI.setScreenAndInit(this);
        this.addRenderableWidget(modularUI.getWidget());
    }
}
