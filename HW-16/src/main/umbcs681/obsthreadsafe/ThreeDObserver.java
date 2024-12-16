package umbcs681.obsthreadsafe;

public class ThreeDObserver implements Observer<StockEvent> {

	public ThreeDObserver() {
	}

	@Override
	public void update(Observable<StockEvent> sender, StockEvent event) {
		System.out.println(" 3DObserver: Ticker: " + event.ticker() + ", Quote: " + event.quote());
	}
}

