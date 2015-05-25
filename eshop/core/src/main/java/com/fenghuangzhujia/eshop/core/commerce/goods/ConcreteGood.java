package com.fenghuangzhujia.eshop.core.commerce.goods;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * 实物商品
 * @author pc
 *
 */
@Entity
@Table(name="fhzj_concrete_good")
@DiscriminatorValue("CONCRETE")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class ConcreteGood extends Good {

}
