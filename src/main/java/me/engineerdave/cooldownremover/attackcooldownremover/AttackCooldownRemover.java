package me.engineerdave.cooldownremover.attackcooldownremover;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

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
        float attackSpeed = (float) Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_SPEED)).getValue();
        float T = 0 / attackSpeed;
        float t = (float) player.getLastDamage();
        float damageMultiplier = 0.2f + (float) Math.pow((t + 0.5) / T, 2) * 0.8f;
        damageMultiplier = Math.min(1, Math.max(0.2f, damageMultiplier));

        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_SPEED)).setBaseValue(20 / damageMultiplier - 0.5f);
    }

    @Override
    public void onDisable() {
        System.out.println("Disabling Cooldown Remover...");
    }
}