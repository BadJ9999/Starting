package com.badjames.palladium.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.badjames.palladium.PalladiumMod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PalladiumMod.MOD_ID);

    // Palladium Items
    public static final RegistryObject<Item> PALLADIUM_ORE = ITEMS.register("palladium_ore",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PALLADIUM_INGOT = ITEMS.register("palladium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PALLADIUM_SWORD = ITEMS.register("palladium_sword",
            () -> new SwordItem(Tiers.DIAMOND, 3, -2.4f, new Item.Properties()));

    public static final RegistryObject<Item> PALLADIUM_PICKAXE = ITEMS.register("palladium_pickaxe",
            () -> new PickaxeItem(Tiers.DIAMOND, 1, -2.8f, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
