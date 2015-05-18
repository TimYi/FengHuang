package com.fenghuangzhujia.eshop.core.authentication.authority;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="level")
public abstract class AbstractAuthority extends UUIDBaseModel implements Authority {

}
