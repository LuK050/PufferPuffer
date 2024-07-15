package ru.luk.pufferpuffer.item.custom

import net.minecraft.component.DataComponentTypes
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.UseAction
import net.minecraft.world.World

class PufferSoupItem(settings: Settings) : Item(settings) {
    override fun finishUsing(stack: ItemStack?, world: World?, user: LivingEntity?): ItemStack {
        if (world?.isClient() == false && user?.isPlayer == true) {
            val player = user as PlayerEntity
            val basedOn = (stack?.components?.get(DataComponentTypes.CUSTOM_DATA)?.nbt?.get("pufferpuffer:based_on")
                ?: "water").toString().replace("\"", "")

            if (basedOn != "milk" && (0..3).random() == 0) {
                player.hungerManager.add(4, 0.3f)
                player.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 20 * 15, 0))
                player.addStatusEffect(StatusEffectInstance(StatusEffects.HUNGER, 20 * 10, 1))
                player.addStatusEffect(StatusEffectInstance(StatusEffects.NAUSEA, 20 * 10, 0))
            } else {
                player.hungerManager.add(10, 0.6f)
            }

            if (!player.isCreative) {
                return ItemStack(Items.BOWL)
            }
        }
        return super.finishUsing(stack, world, user)
    }

    override fun getUseAction(stack: ItemStack?): UseAction {
        return UseAction.EAT
    }
}