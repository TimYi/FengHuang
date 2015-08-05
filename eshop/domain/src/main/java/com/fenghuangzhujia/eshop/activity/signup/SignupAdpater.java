package com.fenghuangzhujia.eshop.activity.signup;

import org.springframework.stereotype.Component;

import com.fenghuangzhujia.foundation.core.dto.adapter.SimpleDtoAdapter;

@Component
public class SignupAdpater extends SimpleDtoAdapter<Signup, Signup, Signup> {

	@Override
	public Signup convertToDo(Signup t) {
		return t;
	}

	@Override
	public Signup update(Signup t, Signup d) {
		d.setName(t.getName());
		d.setTelephone(t.getTelephone());
		d.setName2(t.getName2());
		d.setTelephone2(t.getTelephone2());
		d.setRelation(t.getRelation());
		return d;
	}

	@Override
	public Signup convert(Signup source) {
		return source;
	}

}
