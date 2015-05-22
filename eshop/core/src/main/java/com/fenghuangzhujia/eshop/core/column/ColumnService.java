package com.fenghuangzhujia.eshop.core.column;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.column.dto.ColumnDto;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;

@Service
@Transactional
public class ColumnService extends DtoPagingService<Column, ColumnDto, String> {
	public List<ColumnDto> findByTypeName(String type) {
		List<Column> columns=getRepository().findByTypeName(type);
		if(columns==null)return null;
		List<ColumnDto> result=new ArrayList<>();
		result.addAll(adapter.convertDoList(columns));
		return result;
	}
	
	@Override
	public ColumnRepository getRepository() {
		return (ColumnRepository)super.getRepository();
	}
}
