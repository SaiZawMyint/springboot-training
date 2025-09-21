package com.example.demo.dtos;

import com.example.demo.common.CommonConstants;
import com.example.demo.dtos.user.UserDTO;
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
	private UserDTO createdBy;
	private String updatedAt;
	
	protected void setCommonField(BaseEntity b) {
		this.id = b.getId();
		if(b.getCreatedBy() != null) {
			this.createdBy = new UserDTO(b.getCreatedBy());
		}
		if(b.getCreatedAt() != null) {
			this.createdAt = CommonUtils.dateToString(b.getCreatedAt(), CommonConstants.STD_DATE_FORMAT);
		}
		if(b.getUpdatedAt() != null) {
			this.updatedAt = CommonUtils.dateToString(b.getUpdatedAt(), CommonConstants.STD_DATE_FORMAT);
		}
	}
}
