package com.manusoftar.mortero.bloques.render;

import com.manusoftar.mortero.bloques.Salitre;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public final class BlockRenderer {
	public static void registerBlockRenderer(Block block){
		   Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block),0, new ModelResourceLocation("mortero:" + block.getUnlocalizedName().substring(5).toLowerCase(), "Inventory"));
	}
}
