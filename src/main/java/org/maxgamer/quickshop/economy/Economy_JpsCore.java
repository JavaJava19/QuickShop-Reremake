package org.maxgamer.quickshop.economy;

import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.QuickShop;
import org.maxgamer.quickshop.api.economy.AbstractEconomy;

import java.util.UUID;

public class Economy_JpsCore extends AbstractEconomy {

    private static final QuickShop plugin = QuickShop.getInstance();

    @Override
    public boolean deposit(@NotNull UUID name, double amount, @NotNull World world, @Nullable String currency) {
        //TODO implement
        return false;
    }

    @Override
    public boolean deposit(@NotNull OfflinePlayer trader, double amount, @NotNull World world, @Nullable String currency) {
        //TODO implement
        return false;
    }

    @Override
    public String format(double balance, @NotNull World world, @Nullable String currency) {
        //TODO implement
        return null;
    }

    @Override
    public double getBalance(@NotNull UUID name, @NotNull World world, @Nullable String currency) {
        //TODO implement
        return 0;
    }

    @Override
    public double getBalance(@NotNull OfflinePlayer player, @NotNull World world, @Nullable String currency) {
        //TODO implement
        return 0;
    }

    @Override
    public boolean withdraw(@NotNull UUID name, double amount, @NotNull World world, @Nullable String currency) {
        //TODO implement
        return false;
    }

    @Override
    public boolean withdraw(@NotNull OfflinePlayer trader, double amount, @NotNull World world, @Nullable String currency) {
        //TODO implement
        return false;
    }

    @Override
    public boolean hasCurrency(@NotNull World world, @NotNull String currency) {
        //TODO implement
        return false;
    }

    @Override
    public boolean supportCurrency() {
        //TODO implement
        return false;
    }

    @Override
    public @Nullable String getLastError() {
        //TODO implement
        return null;
    }

    @Override
    public boolean isValid() {
        //TODO implement
        return false;
    }

    @Override
    public @NotNull Plugin getPlugin() {
        return plugin;
    }

    @Override
    public String toString() {
        //TODO implement
        return null;
    }
}
