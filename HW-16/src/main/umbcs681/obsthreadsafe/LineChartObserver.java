package umbcs681.obsthreadsafe;

public class LineChartObserver implements Observer<StockEvent> {

	public LineChartObserver() {
	}

	private StockEvent event;

	@Override
	public void update(Observable<StockEvent> sender, StockEvent event) {
		System.out.println(" LineChartObserver: Ticker: " + event.ticker() + ", Quote: " + event.quote());
		this.event = event;
	}

	public StockEvent getEvent(){
		return event;
	}
}

