package alpaca.scrapping.jw.factory;

import alpaca.scrapping.jw.builder.ProviderBuilder;
import alpaca.scrapping.jw.builder.RequestBuilder;

public class BuilderSupervisor {

	private RequestBuilder requestBuilder;
	private ProviderBuilder providerBuilder;

	public RequestBuilder getRequestBuilder() {
		return (requestBuilder == null) ? requestBuilder = new RequestBuilder() : requestBuilder;
	}

	public ProviderBuilder getProviderBuilder() {
		return (providerBuilder == null) ? providerBuilder = new ProviderBuilder() : providerBuilder;
	}
}
