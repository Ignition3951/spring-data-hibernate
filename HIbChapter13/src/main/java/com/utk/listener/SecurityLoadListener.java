package com.utk.listener;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.event.internal.DefaultLoadEventListener;
import org.hibernate.event.spi.LoadEvent;

public class SecurityLoadListener extends DefaultLoadEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {

		boolean authorized = MySecurity.isAuthorized(event.getEntityClassName(), event.getEntityId());
		if (!authorized)
			throw new MySecurityException("UnAuthorozed access!!");
		super.onLoad(event, loadType);
	}

	public static class MySecurity {
		static boolean isAuthorized(String entityName, Serializable entityId) {
			return true;
		}
	}

	public static class MySecurityException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MySecurityException(String message) {
			super(message);
		}
	}
}
