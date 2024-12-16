package umbcs681.obsthreadsafe;


public class TableObserver implements Observer<StockEvent> {

	public TableObserver() {
	}

	@Override
	public void update(Observable<StockEvent> sender, StockEvent event) {
		System.out.println(" TableObserver: Ticker: " + event.ticker() + ", Quote: " + event.quote());
	}
}

