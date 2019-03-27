package com.example.sid.pdfGenratorDemo.registry;

public interface ServiceRegistry {
	public <T> AdaptorService<T> getService(String serviceName);

}
