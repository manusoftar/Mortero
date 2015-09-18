package com.manusoftar.mortero.gui;

import com.manusoftar.mortero.Mortero;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Contenedor extends GuiContainer {
   

	private static final ResourceLocation grinderGuiTextures = new ResourceLocation(Mortero.MODID +":textures/gui/mortero_gui.png");
    private final InventoryPlayer inventoryPlayer = null;
    private final IInventory tileGrinder = null;
    
   
    
    public Contenedor(Container inventorySlotsIn) {
		super(inventorySlotsIn);
		
		this.ySize = 166;
		this.xSize = 176;
		
		
		
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		
		
	}

   
    

    
 }
