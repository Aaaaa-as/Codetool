package com.artemy.codetool.network;

import com.artemy.codetool.CodeTool;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record EditCodeToolPayload(String code) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<EditCodeToolPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(CodeTool.MODID, "edit_code_tool"));


    public static final StreamCodec<ByteBuf, EditCodeToolPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            EditCodeToolPayload::code,
            EditCodeToolPayload::new);


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
