package jgrove.command;

import java.io.IOException;
import java.util.function.Predicate;

/**
 * Command which wraps functions which throw {@link IOException}.
 * 
 * Provides execution of this functions in a loop so that {@link IOException} is
 * catched.
 * 
 * @author Johannes Bergmann
 */
public class Command<R> {

	private final IoSupplier<R> supplier;

	public Command(IoSupplier<R> supplier) {
		this.supplier = supplier;
	}

	protected R execute() throws IOException {
		return supplier.get();
	}

	public final R loopWhile(R resultValue) {
		return loopWhile(resultValue::equals);
	}

	public final R loopWhile(Predicate<R> resultPredicate) {
		boolean keepRunning = true;
		R result = null;
		while (keepRunning) {
			try {
				result = execute();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			keepRunning = resultPredicate.test(result);
		}
		return result;
	}
}
