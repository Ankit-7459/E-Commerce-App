package com.nt.global;

import java.util.ArrayList;
import java.util.List;

import com.nt.Model.ProductInfo;

public class GlobalData {

	public static List<ProductInfo> cart;
	
	static{
		cart=new ArrayList<>
		();
	}
}
