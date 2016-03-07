package com.manusoftar.mortero.crafting;

import com.manusoftar.mortero.items.Mortar;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes {
	   public static void initRecipes(){
		   GameRegistry.addRecipe(new ItemStack(Mortar.instance,1), " S ", "B  ", 'S', Items.stick, 'B', Items.bowl );
		   GameRegistry.addRecipe(new ItemStack(Mortar.instance,1), "S  ", " B ", 'S', Items.stick, 'B', Items.bowl );	
		   ItemStack out = new ItemStack(Items.diamond);
		   out.setItem(Items.diamond);
		   out.stackSize=1;
		   
		   ItemStack in = new ItemStack(Blocks.coal_block);
		   in.setItem(Item.getItemFromBlock(Blocks.coal_block));
		   in.stackSize=4;
		   GameRegistry.addSmelting(in, out, 1.5f);
		   
	   }
}
