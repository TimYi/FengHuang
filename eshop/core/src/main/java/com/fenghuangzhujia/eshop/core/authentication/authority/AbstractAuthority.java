package com.fenghuangzhujia.eshop.core.authentication.authority;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;

@Entity
@Table(name="fhzj_authority")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="level")
public abstract class AbstractAuthority extends UUIDBaseModel implements Authority {
}
