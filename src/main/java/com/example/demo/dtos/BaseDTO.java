package com.example.demo.dtos;


import com.example.demo.common.CommonConstants;
import com.example.demo.dtos.user.UserDto;
import com.example.demo.persistence.model.BaseEntity;
import com.example.demo.utils.CommonUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseDTO {
	private Long id;
	private String createdAt;
	private UserDto createdBy;
	private String updatedAt;

	protected void setCommonField(BaseEntity b) { //to ask
		this.id = b.getId();
		if(b.getCreatedBy() != null) {
			this.createdBy = new UserDto(b.getCreatedBy());   //to ask
		}
		if(b.getCreatedAt() != null) {
			this.createdAt = CommonUtils.dateToString(b.getCreatedAt(), CommonConstants.STD_DATE_FORMAT);
		}
		if(b.getUpdatedAt() != null) {
			this.updatedAt = CommonUtils.dateToString(b.getUpdatedAt(), CommonConstants.STD_DATE_FORMAT);
		}
	}
}
