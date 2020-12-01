package com.finadv.entities;

/**
 * @author atanu
 *
 */
public class AssetInstrument {

	private long id;

	private AssetType assetTypeId;

	private String instrumentName;

	private float defaultReturns;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AssetType getAssetTypeId() {
		return assetTypeId;
	}

	public void setAssetTypeId(AssetType assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

	public String getInstrumentName() {
		return instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public float getDefaultReturns() {
		return defaultReturns;
	}

	public void setDefaultReturns(float defaultReturns) {
		this.defaultReturns = defaultReturns;
	}
}
