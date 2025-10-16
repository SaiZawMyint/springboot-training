package com.example.demo.dtos.item;

import java.math.BigDecimal;

import com.example.demo.dtos.BaseDTO;
import com.example.demo.persistence.model.item.Item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO extends BaseDTO{

	//private Long id;
	@NotBlank(message = "Name cannot be empty!")
	private String name;
	@NotNull(message = "Sell Price cannot be empty!")
	private BigDecimal sellPrice;

	@NotNull(message = "Original Price cannot be empty!")
	private BigDecimal originalPrice;

	@NotNull(message = "Quantity cannot be empty!")
	private Integer quantity; // e.g., 1, 2

	private Integer status; // 1 = Active, 2 = Inactive
	private String statusDesc;

	@NotNull(message = "pdt cannot be empty!")
	private Long product;

	@NotBlank(message = "Product Name cannot be empty!")
	private String productName;

	public ItemDTO(Item item) {
		if (item != null) {
		//	this.id = item.getId();
			this.name = item.getName();
			this.sellPrice = item.getSellPrice();
			this.originalPrice = item.getOriginalPrice();
			this.quantity = item.getQuantity();
			this.status = item.getStatus();
			this.product = (item.getProduct() != null ? item.getProduct().getId() : null);
			this.productName=(item.getProduct() != null ? item.getProduct().getName() : null);
			this.statusDesc = status != null && status.equals(1) ? "Active" : "Inactive";
			//call common method
			setCommonField(item);
		}
	}

}
