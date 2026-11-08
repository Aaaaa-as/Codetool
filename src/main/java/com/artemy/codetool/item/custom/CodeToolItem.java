package com.artemy.codetool.item.custom;

import com.artemy.codetool.component.ModDataComponents;
import me.padej.jumper.interp.Interpreter;
import me.padej.jumper.interp.Script;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;


public class CodeToolItem extends Item {
    public CodeToolItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {
        return super.useOn(context);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack =  player.getItemInHand(usedHand);
        if(level.isClientSide){
            System.out.println("TODO: use client");
        }else{
            System.out.println("TODO: use server");
            Interpreter  jumper = new Interpreter();



            Script script = jumper.script(
                    stack.get(ModDataComponents.CODE_TOOL).code());
            script.run();
            Object use = script.invoke("useServer", level, player, usedHand);
            if(use instanceof InteractionResultHolder interactionResultHolder){
                return interactionResultHolder;
            }
        }

        return super.use(level, player, usedHand);
    }
}
