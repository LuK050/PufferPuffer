package ru.luk.pufferpuffer.item.custom

import net.minecraft.component.type.FoodComponents
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.UseAction
import net.minecraft.world.World

class CookedPufferfishItem(settings: Settings) : Item(settings) {
    override fun finishUsing(stack: ItemStack?, world: World?, user: LivingEntity?): ItemStack {
        if (world?.isClient() == false && user?.isPlayer == true) {
            val player = user as PlayerEntity

            if ((0..2).random() == 0) {
                player.hungerManager.add(2, 0.1f)
                player.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 20 * 15, 1))
                player.addStatusEffect(StatusEffectInstance(StatusEffects.HUNGER, 20 * 10, 2))
                player.addStatusEffect(StatusEffectInstance(StatusEffects.NAUSEA, 20 * 10, 0))
            } else {
                player.hungerManager.add(6, 0.6f)
            }
        }
        return super.finishUsing(stack, world, user)
    }

    override fun getUseAction(stack: ItemStack?): UseAction {
        return UseAction.EAT
    }
}