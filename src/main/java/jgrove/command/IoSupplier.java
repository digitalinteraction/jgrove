package jgrove.command;

import java.io.IOException;

/**
 * Functional interface for methods which read or write values to Grove sensors.
 * interface is declared so that methods which throw an {@link IOException} can
 * be referenced in a functional style. <br/>
 * 
 * Example: <br/>
 * <p>
 * {@code MyClass.throwingMethod() throws IOException}<br/>
 * can be referenced with<br/>
 * {@code MyClass::throwingMethod}.
 * </p>
 * 
 * @author Johannes Bergmann
 */
@FunctionalInterface
public interface IoSupplier<R> {
	R get() throws IOException;
}
