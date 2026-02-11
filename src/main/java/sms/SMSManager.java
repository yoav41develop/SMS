package sms;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

@RestController
public class SMSManager {
	private Object read(int rowNumber) throws IOException, GeneralSecurityException {
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new FileInputStream(System.getenv("LEADS_SHEET_CREDENTIALS_PATH")))
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS_READONLY));
        Sheets service = new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials))
                .setApplicationName("My App")
                .build();
        String spreadsheetId = System.getenv("LEADS_SHEET_ID");
        String range = "data!" + rowNumber + ":" + rowNumber;

        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();

        List<List<Object>> values = response.getValues();

        if (values != null && !values.isEmpty()) {
            List<Object> row = values.get(0);
            return row.get(0);
        }
        
        return "null";
	}
	
	@GetMapping(value = "/subscribe/{row}")
	public ResponseEntity<Object> subscribe(@PathVariable int row)  {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(read(row));
		} catch (IOException | GeneralSecurityException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}