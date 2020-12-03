package com.finadv.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.finadv.dto.UserExpenseList;
import com.finadv.dto.UserInvestmentList;
import com.finadv.entities.AssetInstrument;
import com.finadv.entities.AssetType;
import com.finadv.entities.ExpenseCategory;
import com.finadv.entities.Institution;
import com.finadv.entities.UserAsset;
import com.finadv.entities.UserAssets;
import com.finadv.entities.UserExpense;
import com.finadv.entities.UserInvestment;
import com.finadv.repository.ExpenseCategoryRepository;
import com.finadv.repository.ExpenseRepository;
import com.finadv.repository.InvestmentRepository;

/**
 * @author atanu
 *
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {

	private static final Logger LOG = LoggerFactory.getLogger(ExpenseServiceImpl.class);

	private ExpenseCategoryRepository categoryRepository;

	private ExpenseRepository expenseRepository;

	private InvestmentRepository investmentRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	public void setCategoryRepository(ExpenseCategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Autowired
	public void setExpenseRepository(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	@Autowired
	public void setInvestmentRepository(InvestmentRepository investmentRepository) {
		this.investmentRepository = investmentRepository;
	}

	/**
	 *
	 */
	@Override
	public List<ExpenseCategory> getAllExpenseCatrgory() {
		return categoryRepository.findAll();
	}

	/**
	 *
	 */
	@Override
	public UserExpenseList getUserExpenses(long userId) {
		UserExpenseList userExpenseList = new UserExpenseList();
		List<UserExpense> expenseList = expenseRepository.findUserExpensesByUserId(userId);
		userExpenseList.setExpenseList(expenseList);
		return userExpenseList;
	}

	@Override
	public void createUserExpense(UserExpenseList userExpense) {
		expenseRepository.saveAll(userExpense.getExpenseList());
	}

	@Override
	public UserExpense updateUserExpense(UserExpense userExpense) {

		UserExpense userExpenseInDB = expenseRepository.findById(userExpense.getExpenseId()).orElse(null);
		if (userExpenseInDB != null) {
			expenseRepository.save(userExpense);
			return userExpense;
		}

		return null;
	}

	@Override
	public void deleteUserExpense(long expenseId) {
		expenseRepository.deleteById((int) expenseId);

	}

	@Override
	public UserInvestmentList getUserInvestment(long userId) {

		UserInvestmentList userInvestmentList = new UserInvestmentList();
		List<UserInvestment> investList = investmentRepository.findUserInvestmentByUserId(userId);
		userInvestmentList.setInvestmentList(investList);
		return userInvestmentList;
	}

	@Override
	public void createUserInvestment(UserInvestmentList userInvestmentList) {
		investmentRepository.saveAll(userInvestmentList.getInvestmentList());
		
		try {
		//Get all asset type and investment to map the investment type
		URI uri = new URI("http://ec2-13-58-243-251.us-east-2.compute.amazonaws.com:8081/api/v1/assets/instruments");
		ResponseEntity<List<AssetInstrument>> assetInstruments = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<AssetInstrument>>() {
				});
		LOG.info("Response to get asset Instruments : " + assetInstruments.getStatusCode());
		// Add investment to user asset
		// Post call to add asset
		UserAsset userAsset = new UserAsset();
		userAsset.setUserId(userInvestmentList.getInvestmentList().get(0).getUserId());
		List<UserAssets> assets = new ArrayList<UserAssets>();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

	
		//	URI url = new URI("http://ec2-13-58-243-251.us-east-2.compute.amazonaws.com:8081/api/v1/assets/"
		URI url = new URI("http://localhost:8081/api/v1/assets/"
					+ userInvestmentList.getInvestmentList().get(0).getUserId());
			for (UserInvestment userInvest : userInvestmentList.getInvestmentList()) {
				// Find asset Type
				AssetInstrument assetInstrumentType = assetInstruments.getBody().stream()
						.filter(i -> i.getInstrumentName().equals(userInvest.getInvestmentType())).findAny()
						.orElse(null);

				UserAssets userAssets = new UserAssets();
				userAssets.setUserId(userInvest.getUserId());
				Institution institution = new Institution();
				institution.setId(1);
				userAssets.setAssetProvider(institution);

				// Get investment type and instrument
				AssetType assetType = new AssetType();
				assetType.setId(assetInstrumentType.getAssetTypeId().getId());
				userAssets.setAssetType(assetType);

				AssetInstrument assetInstrument = new AssetInstrument();
				assetInstrument.setId(assetInstrumentType.getId());
				userAssets.setAssetInstrument(assetInstrument);

				userAssets.setNickName(userInvest.getInvestmentName());
				userAssets.setHolderName(userInvest.getAccountName());
				userAssets.setAmount(userInvest.getInvestmentAmount());
				userAssets.setExpectedReturn(assetInstrumentType.getDefaultReturns());

				userAssets.setCreatedAt(
						userInvest.getCreatedOn().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
				userAssets.setEquityDebtName(userInvest.getInvestmentOn());

				assets.add(userAssets);
			}
			userAsset.setAssets(assets);

			HttpEntity<UserAsset> requestEntity = new HttpEntity<>(userAsset, headers);
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				LOG.info("Successfully created assets for the investments" + responseEntity.getBody());
			}

		} catch (URISyntaxException e) {
			LOG.error(e.getMessage());
		}

	}

	@Override
	public UserInvestment updateUserInvestment(UserInvestment userInvestment) {
		UserInvestment userInvestmentInDB = investmentRepository.findById(userInvestment.getInvestmentId())
				.orElse(null);
		if (userInvestmentInDB != null) {
			investmentRepository.save(userInvestment);
			return userInvestment;
		}
		return null;
	}

	@Override
	public void deleteUserInvestment(long investmentId) {
		UserInvestment userInvestmentInDB = investmentRepository.findById((int)investmentId)
				.orElse(null);
		investmentRepository.deleteById((int) investmentId);
		// Get user assets and remove the value and record from asset list as well

		URI uri;
		try {
			uri = new URI("http://ec2-13-58-243-251.us-east-2.compute.amazonaws.com:8081/api/v1/assets/"
					+ userInvestmentInDB.getUserId());

			ResponseEntity<UserAsset> assetList = restTemplate.exchange(uri, HttpMethod.GET, null, UserAsset.class);
			LOG.info("Response to get asset Instruments : " + assetList.getStatusCode());

			if ("Mutual Fund".equalsIgnoreCase(userInvestmentInDB.getInvestmentType())) {
				UserAssets userAsset = assetList.getBody().getAssets().stream()
						.filter(x -> x.getEquityDebtName() != null
								&& x.getEquityDebtName().equalsIgnoreCase(userInvestmentInDB.getInvestmentOn()))
						.findFirst().orElse(null);
				userAsset.setAmount(userAsset.getAmount() - userInvestmentInDB.getInvestmentAmount());
				//Update user asset with api call
				updateAsset(userAsset);
			} else {
				UserAssets userAsset = assetList.getBody().getAssets().stream()
						.filter(x -> x.getEquityDebtName() != null
								&& x.getEquityDebtName().equalsIgnoreCase(userInvestmentInDB.getInvestmentOn())
								&& x.getAmount() == userInvestmentInDB.getInvestmentAmount())
						.findFirst().orElse(null);

				// Delete user asset
				deleteAsset(userAsset.getId());
			}

		} catch (URISyntaxException e) {
			LOG.error(e.getMessage());
		}
	}

	private void deleteAsset(int assetId) {
		try {
			URI url = new URI("http://ec2-13-58-243-251.us-east-2.compute.amazonaws.com:8081/api/v1/assets/"
					+ String.valueOf(assetId));
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);

			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				LOG.info("Successfully deleted assets for the investments" + responseEntity.getBody());
			}
		} catch (URISyntaxException e) {
			LOG.error(e.getMessage());
		}

	}

	private void updateAsset(UserAssets userAsset) {
		try {
			URI url = new URI("http://ec2-13-58-243-251.us-east-2.compute.amazonaws.com:8081/api/v1/assets/"
					+ String.valueOf(userAsset.getUserId()));

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<UserAssets> requestEntity = new HttpEntity<>(userAsset, headers);

			ResponseEntity<UserAssets> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity,
					UserAssets.class);

			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				LOG.info("Successfully updated assets for the investments" + responseEntity.getBody());
			}
		} catch (URISyntaxException e) {
			LOG.error(e.getMessage());
		}

	}

}
