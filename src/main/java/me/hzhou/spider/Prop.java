/**
 * Copyright (c) 2015 Ovitas Inc, All rights reserved.
 */
package me.hzhou.spider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Description:
 *
 * @author hzhou
 */
public enum Prop {
	INSTANCE;

	private Properties properties = null;

	Prop() {
		InputStream inputStream = null;
		try {
			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties");
			if (inputStream == null) {
				throw new IllegalArgumentException("Properties file not found in classpath");
			}
			properties = new Properties();
			properties.load(new InputStreamReader(inputStream, "UTF-8"));
		} catch (IOException e) {
			throw new RuntimeException("Error loading properties file.", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String get(String key) {
		return properties.getProperty(key);
	}

	public String get(String key, String defaultValue) {
		String value = get(key);
		return (value != null) ? value : defaultValue;
	}

	public Integer getInt(String key) {
		String value = get(key);
		return (value != null) ? Integer.parseInt(value) : null;
	}

	public Integer getInt(String key, Integer defaultValue) {
		String value = get(key);
		return (value != null) ? Integer.parseInt(value) : defaultValue;
	}

	public Long getLong(String key) {
		String value = get(key);
		return (value != null) ? Long.parseLong(value) : null;
	}

	public Long getLong(String key, Long defaultValue) {
		String value = get(key);
		return (value != null) ? Long.parseLong(value) : defaultValue;
	}

	public Boolean getBoolean(String key) {
		String value = get(key);
		return (value != null) ? Boolean.parseBoolean(value) : null;
	}

	public Boolean getBoolean(String key, Boolean defaultValue) {
		String value = get(key);
		return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
	}

	public boolean containsKey(String key) {
		return properties.containsKey(key);
	}

	public Properties getProperties() {
		return properties;
	}
}