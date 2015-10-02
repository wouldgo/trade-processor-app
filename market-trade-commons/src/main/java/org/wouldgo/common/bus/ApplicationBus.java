package org.wouldgo.common.bus;

import org.springframework.stereotype.Service;

import com.google.common.eventbus.Subscribe;

/**
 * <p>Bus abstraction used to decouple the application components.</p>
 * <p>In a bigger (distributed) application this could be a starting point to integrate an external message broker.</p>
 *
 * @author "wouldgo"
 *
 */
@Service
public interface ApplicationBus {

	/**
	 * <p>Tells to the bus that the subscriber object is interested in events.</p>
	 * <p>The events will be delivered to an {@linkplain Subscribe} annotated method that has as
	 * formal parameter the object's class published via the {@link #publish(Object)} method.</p>
	 *
	 * @param <T> Class of the event subscriber.
	 * @param subscriber the subscriber class, this must has at least a {@linkplain Subscribe} annotated method to receive events.
	 */
	public <T> void register(T subscriber);


	/**
	 * <p>Publish an event to the application bus.</p>
	 * <p>All subscribed objects that has a {@linkplain Subscribe} annotated method that has as
	 * formal parameter the event's class will receive the event.</p>
	 *
	 * @param <T> Class of the event.
	 * @param event the event object to publish.
	 */
	public <T> void publish(T event);
}
