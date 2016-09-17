package eu.reborn_minecraft.zhorse.enums;

public enum LocaleEnum {
	/* headers */
	adminCommandListHeader("Headers.adminCommandListHeader"),
	availableOptionFormat("Headers.availableOptionFormat"),
	commandListHeader("Headers.commandListHeader"),
	commandUsageFormat("Headers.commandUsageFormat"),
	commandUsageHeader("Headers.commandUsageHeader"),
	headerFormat("Headers.headerFormat"),
	horseInfoHeader("Headers.horseInfoHeader"),
	horseListFormat("Headers.horseListFormat"),
	horseListFormatFavorite("Headers.horseListFormatFavorite"),
	horseListHeader("Headers.horseListHeader"),
	horseListOtherHeader("Headers.horseListOtherHeader"),
	horseOptionFormat("Headers.horseOptionFormat"),
	pluginHeader("Headers.pluginHeader"),
	pluginPrefix("Headers.pluginPrefix"),
	remainingClaimsFormat("Headers.remainingClaimsFormat"),
	settingsCommandListHeader("Headers.settingsCommandListHeader"),
	
	/* messages */
	claimsLimitReached("Messages.claimsLimitReached"),
	claimsLimitReachedOther("Messages.claimsLimitReachedOther"),
	databaseImportFailure("Messages.databaseImportFailure"),
	databaseImportStarted("Messages.databaseImportStarted"),
	databaseImportSuccess("Messages.databaseImportSuccess"),
	favoriteAlreadySet("Messages.favoriteAlreadySet"),
	favoriteAlreadySetOther("Messages.favoriteAlreadySetOther"),
	favoriteEdited("Messages.favoriteEdited"),
	favoriteEditedOther("Messages.favoriteEditedOther"),
	horseAlreadyClaimed("Messages.horseAlreadyClaimed"),
	horseBelongsTo("Messages.horseBelongsTo"),
	horseClaimed("Messages.horseClaimed"),
	horseCleared("Messages.horseCleared"),
	horseClearedOther("Messages.horseClearedOther"),
	horseDied("Messages.horseDied"),
	horseFreed("Messages.horseFreed"),
	horseGiven("Messages.horseGiven"),
	horseHealed("Messages.horseHealed"),
	horseIsProtected("Messages.horseIsProtected"),
	horseLeashed("Messages.horseLeashed"),
	horseLeashedBy("Messages.horseLeashedBy"),
	horseLocked("Messages.horseLocked"),
	horseManuallyTamed("Messages.horseManuallyTamed"),
	horseMounted("Messages.horseMounted"),
	horseMountedBy("Messages.horseMountedBy"),
	horseNameBanned("Messages.horseNameBanned"),
	horseNameForbidden("Messages.horseNameForbidden"),
	horseNameMandatory("Messages.horseNameMandatory"),
	horseNameTooLong("Messages.horseNameTooLong"),
	horseNameTooShort("Messages.horseNameTooShort"),
	horseNotClaimed("Messages.horseNotClaimed"),
	horseNotFound("Messages.horseNotFound"),
	horseNotTamed("Messages.horseNotTamed"),
	horseProtected("Messages.horseProtected"),
	horseReceived("Messages.horseReceived"),
	horseRenamed("Messages.horseRenamed"),
	horseShared("Messages.horseShared"),
	horseSpawned("Messages.horseSpawned"),
	horseTamed("Messages.horseTamed"),
	horseTeleported("Messages.horseTeleported"),
	horseUnLocked("Messages.horseUnLocked"),
	horseUnProtected("Messages.horseUnProtected"),
	horseUnShared("Messages.horseUnShared"),
	horseUnTamed("Messages.horseUnTamed"),
	invalidHealthArgument("Messages.invalidHealthArgument"),
	invalidJumpArgument("Messages.invalidJumpArgument"),
	invalidSpeedArgument("Messages.invalidSpeedArgument"),
	languageAlreadyUsed("Messages.languageAlreadyUsed"),
	languageAlreadyUsedOther("Messages.languageAlreadyUsedOther"),
	languageEdited("Messages.languageEdited"),
	languageEditedOther("Messages.languageEditedOther"),
	listHorseColor("Messages.listHorseColor"),
	listHorseStyle("Messages.listHorseStyle"),
	listHorseVariant("Messages.listHorseVariant"),
	missingArguments("Messages.missingArguments"),
	missingDatabase("Messages.missingDatabase"),
	missingHorseId("Messages.missingHorseId"),
	missingLanguage("Messages.missingLanguage"),
	missingPermission("Messages.missingPermission"),
	missingPermissionOther("Messages.missingPermissionOther"),
	missingTarget("Messages.missingTarget"),
	noHorseOwned("Messages.noHorseOwned"),
	noHorseOwnedOther("Messages.noHorseOwnedOther"),
	notOnHorse("Messages.notOnHorse"),
	playerCleared("Messages.playerCleared"),
	playerClearedOther("Messages.playerClearedOther"),
	playerCommand("Messages.playerCommand"),
	playerOffline("Messages.playerOffline"),
	pluginReloaded("Messages.pluginReloaded"),
	pluginReloadedWithErrors("Messages.pluginReloadedWithErrors"),
	samePlayer("Messages.samePlayer"),
	teleportedToHorse("Messages.teleportedToHorse"),
	unknownAdminCommand("Messages.unknownAdminCommand"),
	unknownCommand("Messages.unknownCommand"),
	unknownDatabase("Messages.unknownDatabase"),
	unknownHorseId("Messages.unknownHorseId"),
	unknownHorseIdOther("Messages.unknownHorseIdOther"),
	unknownHorseName("Messages.unknownHorseName"),
	unknownHorseNameOther("Messages.unknownHorseNameOther"),
	unknownLanguage("Messages.unknownLanguage"),
	unknownPlayer("Messages.unknownPlayer"),
	unknownSettingsCommand("Messages.unknownSettingsCommand"),
	unknownSpawnArgument("Messages.unknownSpawnArgument"),
	worldDisabled("Messages.worldDisabled"),
	worldUnreachable("Messages.worldUnreachable"),
	
	/* economy messages */
	commandCost("Economy.commandCost"),
	commandPaid("Economy.commandPaid"),
	currencySymbol("Economy.currencySymbol"),
	notEnoughMoney("Economy.notEnoughMoney"),
	
	/* horse informations */
	health("Horse informations.health"),
	id("Horse informations.id"),
	jump("Horse informations.jump"),
	location("Horse informations.location"),
	modeLocked("Horse informations.modeLocked"),
	modeProtected("Horse informations.modeProtected"),
	modeShared("Horse informations.modeShared"),
	name("Horse informations.name"),
	owner("Horse informations.owner"),
	speed("Horse informations.speed"),
	status("Horse informations.status"),
	
	/* command descriptions */
	adminDescription("Command descriptions.admin"),
	claimDescription("Command descriptions.claim"),
	freeDescription("Command descriptions.free"),
	giveDescription("Command descriptions.give"),
	healDescription("Command descriptions.heal"),
	helpDescription("Command descriptions.help"),
	hereDescription("Command descriptions.here"),
	infoDescription("Command descriptions.info"),
	killDescription("Command descriptions.kill"),
	listDescription("Command descriptions.list"),
	lockDescription("Command descriptions.lock"),
	renameDescription("Command descriptions.rename"),
	protectDescription("Command descriptions.protect"),
	reloadDescription("Command descriptions.reload"),
	settingsDescription("Command descriptions.settings"),
	shareDescription("Command descriptions.share"),
	spawnDescription("Command descriptions.spawn"),
	tameDescription("Command descriptions.tame"),
	tpDescription("Command descriptions.tp"),
	
	/* admin command descriptions */
	clearDescription("Admin command descriptions.clear"),
	importDescription("Admin command descriptions.import"),
	
	/* settings command descriptions */
	favoriteDescription("Settings command descriptions.favorite"),
	languageDescription("Settings command descriptions.language"),
	
	/* command usages */
	adminUsage("Command usages.admin"),
	claimUsage("Command usages.claim"),
	freeUsage("Command usages.free"),
	giveUsage("Command usages.give"),
	healUsage("Command usages.heal"),
	helpUsage("Command usages.help"),
	hereUsage("Command usages.here"),
	infoUsage("Command usages.info"),
	killUsage("Command usages.kill"),
	listUsage("Command usages.list"),
	lockUsage("Command usages.lock"),
	renameUsage("Command usages.rename"),
	protectUsage("Command usages.protect"),
	reloadUsage("Command usages.reload"),
	settingsUsage("Command usages.settings"),
	shareUsage("Command usages.share"),
	spawnUsage("Command usages.spawn"),
	tameUsage("Command usages.tame"),
	tpUsage("Command usages.tp"),
	
	/* admin command usages */
	clearUsage("Admin command usages.clear"),
	importUsage("Admin command usages.import"),
	
	/* settings command usages */
	favoriteUsage("Settings command usages.favorite"),
	languageUsage("Settings command usages.language");
	
	private String index;
	
	LocaleEnum(String index) {
		this.index = index;
	}
	
	public String getName() {
		return index.substring(index.indexOf(".")+1);
	}
	
	public String getIndex() {
		return index;
	}
	  
}