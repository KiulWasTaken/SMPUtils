package kiul.smpevents.listeners;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class CrateInteract implements Listener {

    public ArrayList<ItemStack> netherCrateGuaranteed = new ArrayList<>() {
        final ItemStack netheriteUpgrade = new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
        final ItemStack eGapple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);

    };

    public final ArrayList<ItemStack> netherCrateLuckyDip = new ArrayList<>() {

        final ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
        final ItemStack snout = new ItemStack(Material.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE);
        final ItemStack ribs = new ItemStack(Material.RIB_ARMOR_TRIM_SMITHING_TEMPLATE);
        final ItemStack scrap = new ItemStack(Material.NETHERITE_SCRAP);
        final ItemStack ingot = new ItemStack(Material.NETHERITE_INGOT);

    };

    public ArrayList<ItemStack> endCrateLuckyDip = new ArrayList<>() {
        final ItemStack spireTrim = new ItemStack(Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE);
        final ItemStack eyeTrim = new ItemStack(Material.EYE_ARMOR_TRIM_SMITHING_TEMPLATE);
        final ItemStack chorusFruit = new ItemStack(Material.CHORUS_FRUIT);

    };

    @EventHandler
    public void OpenCrate (PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof ArmorStand crate) {
            if (!e.getRightClicked().hasMetadata("locked")) {
                Random random = new Random();
                switch (crate.getHelmet().getType()) {
                    case RED_NETHER_BRICKS:
                        for (ItemStack items : netherCrateGuaranteed) {
                            if (items.getType() == Material.ENCHANTED_GOLDEN_APPLE) {
                                items.setAmount(2);
                            }
                            e.getPlayer().getWorld().dropItemNaturally(crate.getEyeLocation(),items);
                        }
                        ItemStack luckyDip = netherCrateLuckyDip.get(random.nextInt(0,netherCrateLuckyDip.size()));
                        e.getPlayer().getWorld().dropItemNaturally(crate.getEyeLocation(),luckyDip);
                        crate.remove();
                        break;
                    case ENDER_CHEST:
                        e.getPlayer().getWorld().dropItemNaturally(crate.getEyeLocation(),new ItemStack(Material.SHULKER_BOX));
                        ItemStack luckyDipE = endCrateLuckyDip.get(random.nextInt(0,endCrateLuckyDip.size()));
                        if (luckyDipE.getType() == Material.CHORUS_FRUIT) {
                            luckyDipE.setAmount(32);
                        }
                        e.getPlayer().getWorld().dropItemNaturally(crate.getEyeLocation(),luckyDipE);
                        crate.remove();
                        break;
                }

            }
        }
    }


}
