package org.uppermodel.translog;

public interface TranslogEvent {

	public int getTime();

	public TranslogProduct newProduct(TranslogProduct product);

}
