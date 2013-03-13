/* 
 * Created on 10-03-2013 17:01:38 by Andrzej Ludwikowski
 */

package info.ludwikowski.example;

import info.ludwikowski.example.Order;
import info.ludwikowski.example.OrderItem;
import info.ludwikowski.generator.proxy.AbstractBuilder;
import info.ludwikowski.generator.proxy.AbstractBuilderFactory;

import java.util.Date;
import java.util.List;



public abstract class AbstractOrderBuilder<B> extends AbstractBuilder<Order, B> {

	public static OrderBuilder create() {
		return AbstractBuilderFactory.createImplementation(OrderBuilder.class);
	}

	public abstract OrderBuilder withItems(List<OrderItem> items);

	public abstract OrderBuilder withCreateDate(Date createDate);

	public abstract OrderBuilder withRealized(boolean realized);

}