package org.maxgamer.quickshop.economy;

import gg.jps.jpscore.JpsCore;
import gg.jps.jpscore.api.JpsApiResponse;
import gg.jps.jpscore.api.i.IJpsBankApi;
import gg.jps.jpscore.core.JpsUtils;
import gg.jps.jpscore.db.def.strChar.PlayerUuidStr;
import gg.jps.jpscore.db.def.varchar.PassBookNote;
import gg.jps.jpscore.db.def.varchar.PluginName;
import gg.jps.jpscore.define.JpsConst;
import gg.jps.jpscore.define.exception.JpsApiException;
import gg.jps.jpscore.economy.def.MoneyJp;
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

    private final IJpsBankApi api = JpsCore.API().JpsBankApi();

    private String lastError = null;
    @Override
    public @Nullable String getLastError() {
        return this.lastError;
    }

    @Override
    public boolean deposit(@NotNull UUID toPlayer, double amount, @NotNull World world, @Nullable String currency) {
        try {
            JpsApiResponse<Boolean> res = api.giveBySystem(
                    new PlayerUuidStr(toPlayer), PluginName.instance(plugin.getName()), new MoneyJp(amount),
                    PassBookNote.instance("ショップ収入"), IJpsBankApi.AddableToDailyEarnAmount.YES
            );
            return res.get();
        }catch(JpsApiException e){
            plugin.getLogger().warning(e.getMessage());
            JpsUtils.Logging.fail(e.getCause());
            lastError = e.getCause().getMessage();
            return false;
        }
    }

    @Override
    public boolean deposit(@NotNull OfflinePlayer trader, double amount, @NotNull World world, @Nullable String currency) {
        try {
            JpsApiResponse<Boolean> res = api.giveBySystem(
                PlayerUuidStr.instance(trader), PluginName.instance(plugin.getName()), new MoneyJp(amount),
                PassBookNote.instance("ショップ収入"), IJpsBankApi.AddableToDailyEarnAmount.YES
            );
            return res.get();
        }catch(JpsApiException e){
            plugin.getLogger().warning(e.getMessage());
            JpsUtils.Logging.fail(e.getCause());
            lastError = e.getCause().getMessage();
            return false;
        }
    }

    @Override
    public String format(double balance, @NotNull World world, @Nullable String currency) {
        return new MoneyJp(balance).toUnitString();
    }

    @Override
    public double getBalance(@NotNull UUID name, @NotNull World world, @Nullable String currency) {
        try{
            JpsApiResponse<MoneyJp> res = api.currentCharge(api.getAccountId(new PlayerUuidStr(name)).get());
            return res.get().doubleValue();
        }catch(JpsApiException e){
            plugin.getLogger().warning(e.getMessage());
            JpsUtils.Logging.fail(e.getCause());
            return 0;
        }
    }

    @Override
    public double getBalance(@NotNull OfflinePlayer player, @NotNull World world, @Nullable String currency) {
        try{
            JpsApiResponse<MoneyJp> res = api.currentCharge(api.getAccountId(PlayerUuidStr.instance(player)).get());
            return res.get().doubleValue();
        }catch(JpsApiException e){
            plugin.getLogger().warning(e.getMessage());
            JpsUtils.Logging.fail(e.getCause());
            return 0;
        }
    }

    @Override
    public boolean withdraw(@NotNull UUID fromPlayer, double amount, @NotNull World world, @Nullable String currency) {
        try {
            JpsApiResponse<Boolean> res = api.tollBySystem(
                    new PlayerUuidStr(fromPlayer), PluginName.instance(plugin.getName()),
                    new MoneyJp(amount), PassBookNote.instance("ショップ費用")
            );
            return res.get();
        }catch(JpsApiException e){
            plugin.getLogger().warning(e.getMessage());
            JpsUtils.Logging.fail(e.getCause());
            lastError = e.getCause().getMessage();
            return false;
        }
    }

    @Override
    public boolean withdraw(@NotNull OfflinePlayer trader, double amount, @NotNull World world, @Nullable String currency) {
        try {
            JpsApiResponse<Boolean> res = api.tollBySystem(
                    PlayerUuidStr.instance(trader), PluginName.instance(plugin.getName()),
                    new MoneyJp(amount), PassBookNote.instance("ショップ費用")
            );
            return res.get();
        }catch(JpsApiException e){
            plugin.getLogger().warning(e.getMessage());
            JpsUtils.Logging.fail(e.getCause());
            lastError = e.getCause().getMessage();
            return false;
        }
    }

    @Override
    public boolean hasCurrency(@NotNull World world, @NotNull String currency) {
        return false;
    }

    @Override
    public boolean supportCurrency() {
        return false;
    }

    @Override
    public boolean isValid() {
        return api != null;
    }

    @Override
    public @NotNull String getName() {
        return JpsConst.pluginName.get();
    }

    @Override
    public @NotNull Plugin getPlugin() {
        return plugin;
    }

    @Override
    public String toString() {
        return getClass().getName() +":{isValid:"+ isValid() +", lastError:"+ lastError +"}";
    }
}
