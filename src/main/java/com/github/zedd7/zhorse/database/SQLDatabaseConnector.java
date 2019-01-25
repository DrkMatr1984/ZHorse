package com.github.zedd7.zhorse.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.zedd7.zhorse.ZHorse;
import com.github.zedd7.zhorse.utils.CallbackListener;
import com.github.zedd7.zhorse.utils.CallbackResponse;

public abstract class SQLDatabaseConnector {

	protected static final String PREFIX_CODE = "prefix_";

	protected ZHorse zh;
	protected Connection connection;
	protected boolean connected;
	protected String tablePrefix = "";

	public SQLDatabaseConnector(ZHorse zh) {
		this.zh = zh;
	}

	protected abstract void openConnection() throws SQLException;

	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			zh.getLogger().severe("Failed to close connection with database !");
			e.printStackTrace();
		}
	}

	public boolean isConnected() {
		return connected;
	}

	protected void checkConnection() throws SQLException {
		if (connection.isClosed()) {
			openConnection();
		}
	}

	public String applyTablePrefix(String formatableQuery) {
		if (!tablePrefix.isEmpty()) {
			return formatableQuery.replaceAll(PREFIX_CODE, tablePrefix + "_");
		}
		else {
			return formatableQuery.replaceAll(PREFIX_CODE, "");
		}
	}

	public PreparedStatement getPreparedStatement(String formatableQuery) throws SQLException {
		checkConnection();
		String query = applyTablePrefix(formatableQuery);
		return connection.prepareStatement(query);
	}

	public boolean executeUpdate(String update, boolean sync, CallbackListener<Boolean> listener) {
		return executeUpdate(update, false, sync, listener);
	}

	public boolean executeUpdate(final String update, final boolean hideExceptions, boolean sync, final CallbackListener<Boolean> listener) {
		CallbackResponse<Boolean> response = new CallbackResponse<>();
		BukkitRunnable task = new BukkitRunnable() {

			@Override
			public void run() {
				boolean success = false;
				try (PreparedStatement statement = getPreparedStatement(update)) {
					statement.executeUpdate();
					success = true;
				} catch (SQLException e) {
					if (!hideExceptions) {
						e.printStackTrace();
					}
				}
				response.setResult(success);
				if (listener != null) {
					new BukkitRunnable() { // Go back to main (sync) loop

						@Override
						public void run() {
							listener.callback(response);
						}

					}.runTask(zh);
				}
			}

		};
		if (sync) {
			task.run(); // Use run() instead of runTask() tu run on the same tick
			return response.getResult();
		}
		else {
			task.runTaskAsynchronously(zh);
			return true;
		}
	}

	public boolean hasResult(String query) {
		Boolean result = getResult(query, resultSet -> true);
		return result != null && result;
	}

	public Boolean getBooleanResult(String query) {
		return getResult(query, resultSet -> getBooleanResult(resultSet));
	}

	public Integer getIntegerResult(String query) {
		return getResult(query, resultSet -> getIntegerResult(resultSet));
	}

	public Location getLocationResult(String query) {
		return getResult(query, resultSet -> getLocationResult(resultSet));
	}

	public String getStringResult(String query) {
		return getResult(query, resultSet -> getStringResult(resultSet));
	}

	public List<String> getStringResultList(String query) {
		return getResultList(query, resultSet -> getStringResult(resultSet));
	}

	public UUID getUUIDResult(String query) {
		return getResult(query, resultSet -> getUUIDResult(resultSet));
	}

	public List<UUID> getUUIDResultList(String query, boolean sync, CallbackListener<List<UUID>> listener) {
		return getResultList(query, resultSet -> getUUIDResult(resultSet), sync, listener);
	}

	public FriendRecord getFriendRecord(String query) {
		return getResult(query, resultSet -> getFriendRecord(resultSet));
	}

	public List<FriendRecord> getFriendRecordList(String query) {
		return getResultList(query, resultSet -> getFriendRecord(resultSet));
	}

	public HorseRecord getHorseRecord(String query) {
		return getResult(query, resultSet -> getHorseRecord(resultSet));
	}

	public List<HorseRecord> getHorseRecordList(String query) {
		return getResultList(query, resultSet -> getHorseRecord(resultSet));
	}

	public HorseDeathRecord getHorseDeathRecord(String query) {
		return getResult(query, resultSet -> getHorseDeathRecord(resultSet));
	}

	public List<HorseDeathRecord> getHorseDeathRecordList(String query) {
		return getResultList(query, resultSet -> getHorseDeathRecord(resultSet));
	}

	public HorseInventoryRecord getHorseInventoryRecord(String query) {
		return getResult(query, resultSet -> getHorseInventoryRecord(resultSet));
	}

	public List<HorseInventoryRecord> getHorseInventoryRecordList(String query) {
		return getResultList(query, resultSet -> getHorseInventoryRecord(resultSet));
	}

	public HorseStableRecord getHorseStableRecord(String query) {
		return getResult(query, resultSet -> getHorseStableRecord(resultSet));
	}

	public List<HorseStableRecord> getHorseStableRecordList(String query) {
		return getResultList(query, resultSet -> getHorseStableRecord(resultSet));
	}

	public HorseStatsRecord getHorseStatsRecord(String query) {
		return getResult(query, resultSet -> getHorseStatsRecord(resultSet));
	}

	public List<HorseStatsRecord> getHorseStatsRecordList(String query) {
		return getResultList(query, resultSet -> getHorseStatsRecord(resultSet));
	}

	public PendingMessageRecord getPendingMessageRecord(String query) {
		return getResult(query, resultSet -> getPendingMessageRecord(resultSet));
	}

	public List<PendingMessageRecord> getPendingMessageRecordList(String query) {
		return getResultList(query, resultSet -> getPendingMessageRecord(resultSet));
	}

	public PlayerRecord getPlayerRecord(String query) {
		return getResult(query, resultSet -> getPlayerRecord(resultSet));
	}

	public List<PlayerRecord> getPlayerRecordList(String query) {
		return getResultList(query, resultSet -> getPlayerRecord(resultSet));
	}

	public SaleRecord getSaleRecord(String query) {
		return getResult(query, resultSet -> getSaleRecord(resultSet));
	}

	public List<SaleRecord> getSaleRecordList(String query) {
		return getResultList(query, resultSet -> getSaleRecord(resultSet));
	}

	private Boolean getBooleanResult(ResultSet resultSet) throws SQLException {
		return resultSet.getInt(1) == 1;
	}

	private Integer getIntegerResult(ResultSet resultSet) throws SQLException {
		return resultSet.getInt(1);
	}

	private String getStringResult(ResultSet resultSet) throws SQLException {
		return resultSet.getString(1);
	}

	private UUID getUUIDResult(ResultSet resultSet) throws SQLException {
		return UUID.fromString(resultSet.getString(1));
	}

	private Location getLocationResult(ResultSet resultSet) throws SQLException {
		return new Location(
			zh.getServer().getWorld(resultSet.getString("locationWorld")),
			resultSet.getInt("locationX"),
			resultSet.getInt("locationY"),
			resultSet.getInt("locationZ")
		);
	}

	private FriendRecord getFriendRecord(ResultSet resultSet) throws SQLException {
		return new FriendRecord(
			resultSet.getString("requester"),
			resultSet.getString("recipient")
		);
	}

	private HorseRecord getHorseRecord(ResultSet resultSet) throws SQLException {
		return new HorseRecord(
			resultSet.getString("uuid"),
			resultSet.getString("owner"),
			resultSet.getInt("id"),
			resultSet.getString("name"),
			resultSet.getInt("locked") == 1,
			resultSet.getInt("protected") == 1,
			resultSet.getInt("shared") == 1,
			resultSet.getString("locationWorld"),
			resultSet.getInt("locationX"),
			resultSet.getInt("locationY"),
			resultSet.getInt("locationZ")
		);
	}

	private HorseDeathRecord getHorseDeathRecord(ResultSet resultSet) throws SQLException {
		return new HorseDeathRecord(
			resultSet.getString("uuid"),
			new Date(resultSet.getTimestamp("date").getTime())
		);
	}

	private HorseInventoryRecord getHorseInventoryRecord(ResultSet resultSet) throws SQLException {
		return new HorseInventoryRecord(
			resultSet.getString("uuid"),
			resultSet.getString("serial")
		);
	}

	private HorseStableRecord getHorseStableRecord(ResultSet resultSet) throws SQLException {
		return new HorseStableRecord(
			resultSet.getString("uuid"),
			resultSet.getString("locationWorld"),
			resultSet.getInt("locationX"),
			resultSet.getInt("locationY"),
			resultSet.getInt("locationZ")
		);
	}

	private HorseStatsRecord getHorseStatsRecord(ResultSet resultSet) throws SQLException {
		return new HorseStatsRecord(
			resultSet.getString("uuid"),
			resultSet.getInt("age"),
			resultSet.getInt("canBreed") == 1,
			resultSet.getInt("canPickupItems") == 1,
			resultSet.getString("color"),
			resultSet.getString("customName"),
			resultSet.getInt("domestication"),
			resultSet.getInt("fireTicks"),
			resultSet.getDouble("health"),
			resultSet.getInt("isCarryingChest") == 1,
			resultSet.getInt("isCustomNameVisible") == 1,
			resultSet.getInt("isGlowing") == 1,
			resultSet.getInt("isTamed") == 1,
			resultSet.getDouble("jumpStrength"),
			resultSet.getDouble("maxHealth"),
			resultSet.getInt("noDamageTicks"),
			resultSet.getInt("remainingAir"),
			resultSet.getDouble("SPEED"),
			resultSet.getInt("strength"),
			resultSet.getString("style"),
			resultSet.getInt("ticksLived"),
			resultSet.getString("type")
		);
	}

	private PendingMessageRecord getPendingMessageRecord(ResultSet resultSet) throws SQLException {
		return new PendingMessageRecord(
			resultSet.getString("uuid"),
			new Date(resultSet.getTimestamp("date").getTime()),
			resultSet.getString("message")
		);
	}

	private PlayerRecord getPlayerRecord(ResultSet resultSet) throws SQLException {
		return new PlayerRecord(
			resultSet.getString("uuid"),
			resultSet.getString("name"),
			resultSet.getString("language"),
			resultSet.getInt("favorite"),
			resultSet.getInt("display_exact_stats") == 1
		);
	}

	private SaleRecord getSaleRecord(ResultSet resultSet) throws SQLException {
		return new SaleRecord(
			resultSet.getString("uuid"),
			resultSet.getInt("price")
		);
	}

	private <T> T getResult(String query, CheckedFunction<ResultSet, T> mapper) {
		T result = null;
		try (PreparedStatement statement = getPreparedStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				result = mapper.apply(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private <T> List<T> getResultList(String query, CheckedFunction<ResultSet, T> mapper) {
		List<T> resultList = new ArrayList<>();
		try (PreparedStatement statement = getPreparedStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				T result = mapper.apply(resultSet);
				resultList.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	private <T> List<T> getResultList(String query, CheckedFunction<ResultSet, T> mapper, boolean sync, CallbackListener<List<T>> listener) {
		CallbackResponse<List<T>> response = new CallbackResponse<>();
		BukkitRunnable task = new BukkitRunnable() {

			@Override
			public void run() {
				List<T> resultList = new ArrayList<>();
				try (PreparedStatement statement = getPreparedStatement(query)) {
					ResultSet resultSet = statement.executeQuery();
					while (resultSet.next()) {
						T result = mapper.apply(resultSet);
						resultList.add(result);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				response.setResult(resultList);
				if (listener != null) {
					new BukkitRunnable() {

						@Override
						public void run() {
							listener.callback(response);
						}

					}.runTask(zh);
				}
			}

		};
		if (sync) {
			task.run(); // Use run() instead of runTask() tu run on the same tick
			return response.getResult();
		}
		else {
			task.runTaskAsynchronously(zh);
			return null;
		}

	}

	@FunctionalInterface
	private interface CheckedFunction<T, R> {
	   R apply(T t) throws SQLException;
	}

}