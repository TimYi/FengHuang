package com.fenghuangzhujia.weixin.service;

import java.util.ArrayList;
import java.util.Collections;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenValidateService {

	@Autowired
	private WxMpConfigStorage storage;
	
	public boolean validateToken(String signature, String timestamp, String nonce) {
		ArrayList<String> list=new ArrayList<>();
		list.add(storage.getToken());
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);
		StringBuilder sb=new StringBuilder();
		for (String str : list) {
			sb.append(str);
		}
		try {
			String calculatedSignature=DigestUtils.shaHex(sb.toString());
			if(calculatedSignature.equals(signature)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			//TODO 这里需要再想一下
			return false;
		}		
	}
}
