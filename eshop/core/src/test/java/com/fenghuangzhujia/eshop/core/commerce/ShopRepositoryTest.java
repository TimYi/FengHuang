package com.fenghuangzhujia.eshop.core.commerce;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.commerce.eshop.Shop;
import com.fenghuangzhujia.eshop.core.commerce.eshop.ShopRepository;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ShopRepositoryTest {
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void addShop() {
		Shop shop=new Shop();
		shop.setVerified(true);
		User user=userRepository.getByUsername("root");
		System.out.println(user.getId());
		shop.setOwner(user);
		shop.setName("fenghuangzhujia");
		shopRepository.save(shop);
		System.out.println(shop.getId());
	}
}
