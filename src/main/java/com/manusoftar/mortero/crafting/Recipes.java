package com.manusoftar.mortero.crafting;

import com.manusoftar.mortero.items.Mortar;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes {
	   public static void initRecipes(){
		   GameRegistry.addRecipe(new ItemStack(Mortar.instance,1), " S ", "B  ", 'S', Items.stick, 'B', Items.bowl );
		   GameRegistry.addRecipe(new ItemStack(Mortar.instance,1), "S  ", " B ", 'S', Items.stick, 'B', Items.bowl );		   
	   }
}
