package com.artemy.codetool.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;


public record CodeToolComponent(String code) {
    public static final Codec<CodeToolComponent> CODEC = RecordCodecBuilder.create(instance->
            instance.group(
                    Codec.STRING.fieldOf("code").forGetter(CodeToolComponent::code)
            ).apply(instance, CodeToolComponent::new)
    );
    public static final StreamCodec<ByteBuf,CodeToolComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            CodeToolComponent::code,
            CodeToolComponent::new);

}
