package com.manusoftar.mortero.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SalitreItem extends Item {
		
		public static SalitreItem instance;
	
		public SalitreItem(){
			super();
			instance = this;
		}
		
		public SalitreItem(String unlocalizedname){
			super();
			this.setUnlocalizedName(unlocalizedname);
			this.setCreativeTab(CreativeTabs.tabMisc);
			
		}
}
