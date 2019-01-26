package com.github.zedd7.zhorse.commands;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;

import com.github.zedd7.zhorse.ZHorse;
import com.github.zedd7.zhorse.database.HorseInventoryRecord;
import com.github.zedd7.zhorse.database.HorseStatsRecord;
import com.github.zedd7.zhorse.enums.LocaleEnum;
import com.github.zedd7.zhorse.utils.CallbackListener;
import com.github.zedd7.zhorse.utils.CallbackResponse;
import com.github.zedd7.zhorse.utils.MessageConfig;

public class CommandRez extends AbstractCommand {

	public CommandRez(ZHorse zh, CommandSender s, String[] a) {
		super(zh, s, a);
		if (isPlayer() && zh.getEM().canAffordCommand(p, command) && parseArguments() && hasPermission() && isCooldownElapsed() && isWorldEnabled()) {
			if (!idMode) {
				if (!targetMode || isRegistered(targetUUID)) {
					execute();
				}
			}
			else {
				sendCommandUsage();
			}
		}
	}

	private void execute() {
		if (ownsDeadHorse(targetUUID) && !hasReachedClaimsLimit(true)) {
			UUID deadHorseUUID = zh.getDM().getLatestHorseDeathUUID(targetUUID);
			HorseInventoryRecord inventoryRecord = zh.getDM().getHorseInventoryRecord(deadHorseUUID);
			HorseStatsRecord statsRecord = zh.getDM().getHorseStatsRecord(deadHorseUUID);
			if (craftHorseName(true, deadHorseUUID)) {
				zh.getDM().removeHorseDeath(deadHorseUUID, false, new CallbackListener<Boolean>() {

					@Override
					public void callback(CallbackResponse<Boolean> response) {
						if (response.getResult()) {
							Location destination = p.getLocation();
							if (p.isFlying()) {
								Block block = destination.getWorld().getHighestBlockAt(destination);
								destination = new Location(destination.getWorld(), block.getX(), block.getY(), block.getZ());
							}
							int horseID = zh.getDM().getNextHorseID(targetUUID);
							horse = zh.getHM().spawnHorse(destination, inventoryRecord, statsRecord, true, deadHorseUUID, horseID, horseName);
							if (horse != null) {
								applyHorseName(targetUUID);
								horse.setHealth(horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
								zh.getMM().sendMessage(s, new MessageConfig(LocaleEnum.HORSE_RESURRECTED) {{ setHorseName(horseName); }});
								zh.getCmdM().updateCommandHistory(s, command);
								zh.getEM().payCommand(p, command);
							}
						}
					}

				});
			}
		}
	}

}