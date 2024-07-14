package ru.luk.pufferpuffer.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.luk.pufferpuffer.item.ModItems;

@Mixin(DolphinEntity.class)
public class DolphinEntityMixin {
    @Inject(method = "loot", at = @At("HEAD"))
    private void eatPufferfish(ItemEntity item, CallbackInfo info) {
        DolphinEntity dolphin = (DolphinEntity)(Object) this;

        if (dolphin.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty()) {
            ItemStack itemStack = item.getStack();

            if (itemStack.isOf(Items.PUFFERFISH) || itemStack.isOf(ModItems.INSTANCE.getCOOKED_PUFFERFISH_ITEM())) {
                dolphin.heal(10.0f);
                dolphin.addStatusEffect(
                    new StatusEffectInstance(
                            StatusEffects.SLOWNESS, 30 * 15, 4, false, false
                    )
                );
                dolphin.setFrozenTicks(30 * 15);
            }
        }
    }
}
