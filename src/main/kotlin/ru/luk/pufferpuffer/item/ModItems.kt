package ru.luk.pufferpuffer.item
import ru.luk.pufferpuffer.item.custom.CookedPufferfishItem
import ru.luk.pufferpuffer.item.custom.PufferSoupItem

import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.LoreComponent
import net.minecraft.component.type.NbtComponent
import net.minecraft.item.ItemStack
import net.minecraft.text.Text

object ModItems {
    val COOKED_PUFFERFISH_ITEM: Item? = register(
        CookedPufferfishItem(Item.Settings().food(ModFoodComponents.COOKED_PUFFERFISH)),
        "cooked_pufferfish"
    )
    val PUFFER_SOUP_ITEM: Item? = register(
        PufferSoupItem(Item.Settings().food(ModFoodComponents.PUFFER_SOUP).maxCount(1)),
        "puffer_soup"
    )

    fun register(instance: Item?, path: String?): Item? {
        return Registry.register(Registries.ITEM, Identifier.of("pufferpuffer", path), instance)
    }

    fun initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register {
            it.addAfter(Items.PUFFERFISH, COOKED_PUFFERFISH_ITEM)

            val pufferSoupBasedOnMilkItemStack = ItemStack(PUFFER_SOUP_ITEM)
            pufferSoupBasedOnMilkItemStack.set(
                DataComponentTypes.CUSTOM_DATA,
                NbtComponent.DEFAULT.apply { nbt ->
                    nbt.putString("pufferpuffer:based_on", "milk")
                }
            )
            pufferSoupBasedOnMilkItemStack.set(
                DataComponentTypes.LORE,
                LoreComponent(listOf(Text.translatable("lore.pufferfish.based_on_milk")))
            )
            it.addAfter(Items.RABBIT_STEW, pufferSoupBasedOnMilkItemStack)

            val pufferSoupBasedOnWaterItemStack = ItemStack(PUFFER_SOUP_ITEM)
            pufferSoupBasedOnWaterItemStack.set(
                DataComponentTypes.CUSTOM_DATA,
                NbtComponent.DEFAULT.apply { nbt ->
                    nbt.putString("pufferpuffer:based_on", "water")
                }
            )
            pufferSoupBasedOnWaterItemStack.set(
                DataComponentTypes.LORE,
                LoreComponent(listOf(Text.translatable("lore.pufferfish.based_on_water")))
            )
            it.addAfter(pufferSoupBasedOnMilkItemStack, pufferSoupBasedOnWaterItemStack)
        }
    }
}