package com.rim.jpa.mariadb.utils;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class StringsUtils implements Serializable {

	private static final long serialVersionUID = 3019341322672778593L;

	public String getStringFromCharacter(String text, String character, int position) {
		String stringRecovered = "";
		String[] stringArray = text.split(character);

		stringRecovered = stringArray[position];

		return stringRecovered;
	}

}
