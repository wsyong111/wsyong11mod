package com.wsyong11.wsyong11mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface StaticValues {
	String MOD_ID = "wsyong11mod";

	Logger MOD_LOGGER = LogManager.getLogger(MOD_ID);

	interface HARVEST_LEVEL {
		int WOODEN = 0;
		int STONE = 1;
		int IRON = 2;
		int DIAMOND = 3;
		int GOLDEN = 4;
	}
}
