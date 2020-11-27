package com.finadv.dto;

import java.util.List;

import com.finadv.entities.UserInvestment;

/**
 * @author atanu
 *
 */
public class UserInvestmentList {
	
	private List<UserInvestment> investmentList;

	public List<UserInvestment> getInvestmentList() {
		return investmentList;
	}

	public void setInvestmentList(List<UserInvestment> investmentList) {
		this.investmentList = investmentList;
	}
	
	

}
