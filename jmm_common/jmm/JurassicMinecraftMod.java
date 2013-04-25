package jmm;


import jmm.block.BlockTestingBlock;
import jmm.common.CommonProxy;
import jmm.gui.GuiHandler;
import jmm.lib.Reference;
import jmm.network.PacketHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


@Mod (
        modid = Reference.MOD_ID, 
        name = Reference.MOD_NAME, 
        version = Reference.VERSION
        )
@NetworkMod (
        channels = { Reference.CHANNEL_NAME },
        clientSideRequired = true,
        serverSideRequired = false,
        packetHandler = PacketHandler.class
        )
public class JurassicMinecraftMod {
    
    @Instance
    public static JurassicMinecraftMod instance;
    
    @SidedProxy(clientSide="jmm.client.ClientProxy", serverSide="jmm.common.CommonProxy")
    public static CommonProxy proxy;
    
    public static Block testingBlock;
    public static CreativeTabs tabJMM_Custom = new CreativeTabs("JMM") {
        public ItemStack getIconItemStack() {
                return new ItemStack(Item.eyeOfEnder, 1, 0);
        }
    };

    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        instance = this;
        
        testingBlock = new BlockTestingBlock(500).setUnlocalizedName("Testing Block");
        
        GameRegistry.registerBlock(testingBlock, Reference.MOD_ID + testingBlock.getUnlocalizedName2());
        LanguageRegistry.addName(testingBlock, "Testing Block");
        
        testingBlock.setCreativeTab(tabJMM_Custom);
        
        LanguageRegistry.instance().addStringLocalization("itemGroup.JMM", "en_US", "JMM");
    }
    
    @Init
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
        
    }
}