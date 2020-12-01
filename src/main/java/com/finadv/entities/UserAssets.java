package com.finadv.entities;

import java.time.LocalDateTime;


/**
 * @author atanu
 *
 */
public class UserAssets {

	private int id;

	private Institution assetProvider;

	private AssetType assetType;

	private AssetInstrument assetInstrument;

	private long userId;

	private String nickName;

	private String holderName;

	private double amount;

	private float expectedReturn;

	private LocalDateTime maturityDate;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private String equityDebtName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Institution getAssetProvider() {
		return assetProvider;
	}

	public void setAssetProvider(Institution assetProvider) {
		this.assetProvider = assetProvider;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public AssetInstrument getAssetInstrument() {
		return assetInstrument;
	}

	public void setAssetInstrument(AssetInstrument assetInstrument) {
		this.assetInstrument = assetInstrument;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public float getExpectedReturn() {
		return expectedReturn;
	}

	public void setExpectedReturn(float expectedReturn) {
		this.expectedReturn = expectedReturn;
	}

	public LocalDateTime getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDateTime maturityDate) {
		this.maturityDate = maturityDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getEquityDebtName() {
		return equityDebtName;
	}

	public void setEquityDebtName(String equityDebtName) {
		this.equityDebtName = equityDebtName;
	}

}
