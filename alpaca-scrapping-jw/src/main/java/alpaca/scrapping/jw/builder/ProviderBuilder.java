package alpaca.scrapping.jw.builder;

import java.util.ArrayList;
import java.util.List;

import alpaca.scrapping.jw.selections.ProviderSelection;

public class ProviderBuilder {
	List<String> providers;

	public ProviderBuilder() {
		this.providers = new ArrayList<String>();
	}

	public void appendProvider(ProviderSelection data) {
		switch (data) {
		case AMAZONPRIME:
			providers.add(ProviderSelection.AMAZONPRIME.getProviderSelection());
			break;
		case HOTSTAR:
			this.providers.add(ProviderSelection.HOTSTAR.getProviderSelection());
			break;
		case NETFLIX:
			this.providers.add(ProviderSelection.NETFLIX.getProviderSelection());
			break;
		}
	}

	public List<String> getProvidersArray() {
		return this.providers;
	}
}
