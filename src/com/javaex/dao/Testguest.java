package com.javaex.dao;

import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestVo;

public class Testguest {

	public static void main(String[] args) {
		GuestDao guestDao = new GuestDao();
	
		List<GuestVo> guestList = guestDao.getGuestList();
		System.out.println(guestList.toString());
	}

}

