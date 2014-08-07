package com.ranorg.etl.core;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;


public class ETL {

	private static final Logger log = Logger.getLogger(ETL.class);

	public static void main(String[] args) {
		log.debug("ETL started......");
		Poller poller = new Poller();
		ExecutorService threads = null;
		try {
			poller.init();
			threads = Executors.newSingleThreadExecutor();
			Future<?> ft = threads.submit(poller);
			ft.get();
			log.debug("ETL DONE......");
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		} catch (ExecutionException e) {
			log.error(e.getMessage(), e);
		} finally {
			if (threads != null)
				threads.shutdown();
		}
	}
}
