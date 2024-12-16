package umbcs681.obsthreadsafe;

import java.util.concurrent.ConcurrentHashMap;

public class StockQuoteObservable extends Observable<StockEvent> {
	private ConcurrentHashMap<String, Double> stockE = new ConcurrentHashMap<>();

	public void changeQuote(String ticker, double quote) {
		stockE.put(ticker, quote);
		notifyObservers(new StockEvent(ticker, quote));
	}

}

