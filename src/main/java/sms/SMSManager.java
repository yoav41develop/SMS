package sms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class SMSManager {
	@GetMapping(value = "/subscribe/{phoneNumber}")
		public ResponseEntity<String> subscribe(@PathVariable String phoneNumber)  {
			return ResponseEntity.status(HttpStatus.OK).body(phoneNumber);
		}
	}

