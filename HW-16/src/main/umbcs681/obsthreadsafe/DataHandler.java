package umbcs681.obsthreadsafe;

import java.util.concurrent.atomic.AtomicBoolean;

public class DataHandler implements Runnable {
	private String ticker;
	private StockQuoteObservable observable;
	private double quote;
	private AtomicBoolean done = new AtomicBoolean(false);

	public DataHandler(StockQuoteObservable observable, String ticker, double quote) {
		this.observable = observable;
		this.ticker = ticker;
		this.quote = quote;
	}

	public void setDone(){done.set(true);}

	@Override
	public void run() {
		for(int i = 0; i< 1; i++) {
			if(done.get()){
				System.out.println("Stopped DataHandlerRunnable");
				break;
			}
			observable.changeQuote(ticker, quote);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				continue;
			}
		}
	}

}
