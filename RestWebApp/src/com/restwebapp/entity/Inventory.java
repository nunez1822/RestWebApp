package com.restwebapp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the inventory database table.
 * 
 */
@Entity
@NamedQuery(name="Inventory.findAll", query="SELECT i FROM Inventory i")
@NamedStoredProcedureQuery(
		name = "film_in_stock", 
		procedureName = "film_in_stock", 
		parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "p_film_id"), 
			@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "p_store_id"), 
			@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "p_film_count")
		}
	)
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="inventory_id")
	private int inventoryId;

	@Column(name="film_id")
	private int filmId;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	@Column(name="store_id")
	private byte storeId;

	public Inventory() {
	}

	public int getInventoryId() {
		return this.inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public int getFilmId() {
		return this.filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public byte getStoreId() {
		return this.storeId;
	}

	public void setStoreId(byte storeId) {
		this.storeId = storeId;
	}

}