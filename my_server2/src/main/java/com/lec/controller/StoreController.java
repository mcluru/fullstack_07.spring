package com.lec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {
	
	@GetMapping("/store_phone")
	public String phoneMain() {
		return "store/store_phone";
	}
	
	@GetMapping("/store_bluetooth")
	public String bluetoothMain() {
		return "store/store_bluetooth";
	}
	
	@GetMapping("/store_tok")
	public String tokMain() {
		return "store/store_tok";
	}
	
	@GetMapping("/store_keyring")
	public String keyringMain() {
		return "store/store_keyring";
	}

}
