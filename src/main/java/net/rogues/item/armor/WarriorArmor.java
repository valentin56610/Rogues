package net.rogues.item.armor;

import mod.azure.azurelibarmor.animatable.GeoItem;
import mod.azure.azurelibarmor.animatable.client.RenderProvider;
import mod.azure.azurelibarmor.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelibarmor.core.animation.AnimatableManager;
import mod.azure.azurelibarmor.renderer.GeoArmorRenderer;
import mod.azure.azurelibarmor.util.AzureLibUtil;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.rogues.RoguesMod;
import net.rogues.client.armor.WarriorArmorRenderer;
import net.spell_engine.api.item.armor.Armor;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class WarriorArmor extends CustomArmor implements GeoItem {
    public static final Identifier equipSoundId = new Identifier(RoguesMod.NAMESPACE, "warrior_armor");
    public static final SoundEvent equipSound = SoundEvent.of(equipSoundId);

    public WarriorArmor(Armor.CustomMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    // MARK: GeoItem

    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoArmorRenderer<?> renderer;
            @Override
            public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> original) {
                if (this.renderer == null) {
                    this.renderer = new WarriorArmorRenderer();
                }
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
