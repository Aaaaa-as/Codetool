package com.artemy.codetool.item.custom;

import me.padej.jumper.interp.Interpreter;
import me.padej.jumper.interp.Script;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;


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
        if(level.isClientSide){
            System.out.println("TODO: use client");
        }else{
            System.out.println("TODO: use server");
            Interpreter  jumper = new Interpreter();

            Script script = jumper.script(
                    "import net.minecraft.world.level.Level;import net.minecraft.core.BlockPos;import net.minecraft.world.level.block.Blocks;import net.minecraft.world.level.block.state.BlockState;\n" +
                            "void test(Level level){level.setBlock(new BlockPos(0,100,0), Blocks.COBBLESTONE.defaultBlockState(),3);}");
            script.run();
            script.invoke("test",level);
        }

        return super.use(level, player, usedHand);
    }
}
