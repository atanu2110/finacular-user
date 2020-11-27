package com.finadv.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author atanu
 *
 */
@Entity
@Table(name = "expense_category")
public class ExpenseCategory {

	@Id
	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "category")
	private String category;

	@Column(name = "description")
	private String description;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
