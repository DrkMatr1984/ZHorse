package com.github.zedd7.zhorse.commands;

import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.AbstractHorse;

import com.github.zedd7.zhorse.ZHorse;
import com.github.zedd7.zhorse.enums.LocaleEnum;
import com.github.zedd7.zhorse.utils.CallbackListener;
import com.github.zedd7.zhorse.utils.CallbackResponse;
import com.github.zedd7.zhorse.utils.MessageConfig;

public class CommandRename extends AbstractCommand {

	public CommandRename(ZHorse zh, CommandSender s, String[] a) {
		super(zh, s, a);
		if (isPlayer() && zh.getEM().canAffordCommand(p, command) && parseArguments() && hasPermission() && isCooldownElapsed() && isWorldEnabled()) {
			if (!idMode) {
				if (!targetMode) {
					boolean ownsHorse = ownsHorse(targetUUID, true);
					if (isOnHorse(ownsHorse)) {
						horse = (AbstractHorse) p.getVehicle();
						if (isRegistered(horse)) {
							execute();
						}
					}
					else if (ownsHorse) {
						horseID = zh.getDM().getPlayerFavoriteHorseID(p.getUniqueId(), true, null).toString();
						execute(p.getUniqueId(), horseID);
					}
				}
				else {
					sendCommandUsage();
				}
			}
			else {
				execute(targetUUID, horseID);
			}
		}
	}

	private void execute(UUID ownerUUID, String horseID) {
		if (isRegistered(ownerUUID, horseID)) {
			horse = zh.getHM().getHorse(ownerUUID, Integer.parseInt(horseID));
			if (isHorseLoaded(true)) {
				execute();
			}
		}
	}

	private void execute() {
		if (isOwner(false) && craftHorseName(false)) {
			UUID ownerUUID = zh.getDM().getOwnerUUID(horse.getUniqueId(), true, null);
			applyHorseName(ownerUUID);
			if (zh.getDM().isHorseForSale(horse.getUniqueId(), true, null)) {
				int price = zh.getDM().getSalePrice(horse.getUniqueId(), true, null);
				applyHorsePrice(price);
			}
			horse.setCustomNameVisible(true);
			zh.getDM().updateHorseName(horse.getUniqueId(), horseName, false, new CallbackListener<Boolean>() {

				@Override
				public void callback(CallbackResponse<Boolean> response) {
					if (response.getResult()) {
						zh.getMM().sendMessage(s, new MessageConfig(LocaleEnum.HORSE_RENAMED) {{ setHorseName(horseName); }});
						zh.getCmdM().updateCommandHistory(s, command);
						zh.getEM().payCommand(p, command);
					}
				}

			});
		}
	}
}