package com.entity.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

// https://stackoverflow.com/a/24212897/1679643
public class IDAdapter extends XmlAdapter<Integer, Integer> {

	private int counter = 1;

	@Override
	public Integer unmarshal(Integer v) throws Exception {
		return v;
	}

	@Override
	public Integer marshal(Integer v) throws Exception {
		return counter++;
	}

}
