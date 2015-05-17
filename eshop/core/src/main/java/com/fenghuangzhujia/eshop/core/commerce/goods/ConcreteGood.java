package com.fenghuangzhujia.eshop.core.commerce.goods;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * 实物商品
 * @author pc
 *
 */
@Entity
@DiscriminatorValue("CONCRETE")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class ConcreteGood extends Good {

}
