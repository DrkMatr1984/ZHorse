package com.github.zedd7.zhorse.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.AbstractHorse;

import com.github.zedd7.zhorse.ZHorse;
import com.github.zedd7.zhorse.enums.LocaleEnum;
import com.github.zedd7.zhorse.utils.MessageConfig;

public class CommandBuy extends AbstractCommand {

	public CommandBuy(ZHorse zh, CommandSender s, String[] a) {
		super(zh, s, a);
		if (isPlayer() && zh.getEM().canAffordCommand(p, command) && parseArguments() && hasPermission() && isCooldownElapsed() && isWorldEnabled()) {
			if (!idMode) {
				if (!targetMode) {
					if (isOnHorse(false)) {
						horse = (AbstractHorse) p.getVehicle();
						execute();
					}
				}
				else {
					sendCommandUsage();
				}
			}
			else {
				if (isRegistered(targetUUID, horseID)) {
					horse = zh.getHM().getHorse(targetUUID, Integer.parseInt(horseID));
					if (isHorseLoaded(true)) {
						execute();
					}
				}
			}
		}
	}

	private void execute() {
		String previousHorseName = zh.getDM().getHorseName(horse.getUniqueId());
		if (zh.getDM().isHorseForSale(horse.getUniqueId())) {
			targetUUID = zh.getDM().getOwnerUUID(horse.getUniqueId());
			if (!p.getUniqueId().equals(targetUUID)) {
				if (!hasReachedClaimsLimit(false) && craftHorseName(true)) {
					int price = zh.getDM().getSalePrice(horse.getUniqueId());
					if (zh.getEM().canAffordPayment(p, price)) {
						int horseID = zh.getDM().getNextHorseID(p.getUniqueId());
						boolean lock = zh.getCM().shouldLockOnClaim();
						boolean protect = zh.getCM().shouldProtectOnClaim();
						boolean share = zh.getCM().shouldShareOnClaim();
						boolean success = true; // Always true because of async updates
						success &= zh.getDM().removeSale(horse.getUniqueId(), false, null);
						success &= zh.getDM().updateHorseOwner(horse.getUniqueId(), p.getUniqueId(), false, null);
						success &= zh.getDM().updateHorseID(horse.getUniqueId(), horseID, false, null);
						success &= zh.getDM().updateHorseName(horse.getUniqueId(), horseName, false, null);
						success &= zh.getDM().updateHorseLocked(horse.getUniqueId(), lock, false, null);
						success &= zh.getDM().updateHorseProtected(horse.getUniqueId(), protect, false, null);
						success &= zh.getDM().updateHorseShared(horse.getUniqueId(), share, false, null);
						if (success) {
							applyHorseName(p.getUniqueId());
							horse.setOwner(zh.getServer().getOfflinePlayer(p.getUniqueId()));
							zh.getEM().payPlayer(p, targetUUID, price);

							String buyerLanguage = zh.getDM().getPlayerLanguage(p.getUniqueId());
							String sellerLanguage = zh.getDM().getPlayerLanguage(targetUUID);
							String buyerCurrencySymbol = zh.getLM().getMessage(LocaleEnum.CURRENCY_SYMBOL.getIndex(), buyerLanguage, true);
							String sellerCurrencySymbol = zh.getLM().getMessage(LocaleEnum.CURRENCY_SYMBOL.getIndex(), sellerLanguage, true);
							zh.getMM().sendMessage(s, new MessageConfig(LocaleEnum.HORSE_BOUGHT) {{ setAmount(price); setCurrencySymbol(buyerCurrencySymbol); setHorseName(horseName); }});
							zh.getMM().sendPendingMessage(targetUUID, new MessageConfig(LocaleEnum.HORSE_SOLD) {{
								setAmount(price); setCurrencySymbol(sellerCurrencySymbol); setHorseName(previousHorseName); setPlayerName(p.getName());
							}});
							zh.getCmdM().updateCommandHistory(s, command);
							zh.getEM().payCommand(p, command);
						}
					}

				}
			}
			else {
				zh.getMM().sendMessage(s, new MessageConfig(LocaleEnum.HORSE_ALREADY_CLAIMED));
			}
		}
		else {
			zh.getMM().sendMessage(s, new MessageConfig(LocaleEnum.HORSE_NOT_FOR_SALE) {{ setHorseName(previousHorseName); }});
		}
	}

}