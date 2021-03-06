package com.manusoftar.mortero;

import com.manusoftar.mortero.crafting.Recipes;
import com.manusoftar.mortero.handlers.GuiHandler;
import com.manusoftar.mortero.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Mortero.MODID, version = Mortero.VERSION, name = Mortero.NAME)
public class Mortero {
	
	
	
    public static final String MODID = "mortero";
    public static final String VERSION = "1.0";
    public static final String NAME = "mortero";
    
    
    @SidedProxy(clientSide="com.manusoftar.mortero.proxy.ClientProxy", serverSide="com.manusoftar.mortero.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    @Instance("mortero")
    public static Mortero instance;
	
    public Mortero(){
    	
    }
    
    
    
    
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        //System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    	
    	
    	proxy.registerRenderers();
    	proxy.registerWorldGen();
    	Recipes.initRecipes();
    	//MinecraftForge.EVENT_BUS.register(new GuiHandler());
    	//FMLCommonHandler.instance().bus().register(new GuiHandler());
    	NetworkRegistry.INSTANCE.registerGuiHandler(Mortero.instance, new GuiHandler());
    }



	
}
