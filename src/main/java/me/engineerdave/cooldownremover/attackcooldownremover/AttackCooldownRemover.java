package me.engineerdave.cooldownremover.attackcooldownremover;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AttackCooldownRemover extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("Launching Cooldown Remover...");
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getDamager();
        AttributeInstance attackSpeed = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        assert attackSpeed != null;
        attackSpeed.setBaseValue(Double.POSITIVE_INFINITY);
    }

    @Override
    public void onDisable() {
        System.out.println("Disabling Cooldown Remover...");
    }
}