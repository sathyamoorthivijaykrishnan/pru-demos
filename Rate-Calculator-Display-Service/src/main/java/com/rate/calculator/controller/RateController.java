package com.rate.calculator.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rate.calculator.dao.RateRepository;
import com.rate.calculator.model.Rate;

@RestController
public class RateController {

	@Autowired
	protected RateRepository rateRepository;

	@GetMapping(value = "/getRates")
	public List<Rate> getRates() {
		return rateRepository.findAll();
	}

	@GetMapping(value = "/getRates/{product}")
	public List<Rate> getRatesByProduct(@PathVariable("product") String product) {
		return rateRepository.findByProduct(product);

	}

	@GetMapping(value = "/dbToFile")
	public String createFlatFile() throws IOException {

		String csvFileName = "D:\\FiaRates\\rates.csv";
		ICsvBeanWriter csvWriter = null;
		try {
			csvWriter = new CsvBeanWriter(new FileWriter(csvFileName), CsvPreference.STANDARD_PREFERENCE);

			final String[] header = { "effDate", "product", "indexName", "cdscOption", "band", "capRate", "mnCapRtCDSC",
					"mnCapRtPCDSC", "contractYr", "mvaInd" };

			// write the header
			csvWriter.writeHeader(header);

			List<Rate> rates = rateRepository.findAll();
			for (Rate rate : rates) {
				csvWriter.write(rate, header);
			}
			csvWriter.close();

		} finally {
			if (csvWriter != null) {
				csvWriter.close();
			}
		}
		return "CannexRateFile Generation success !!";

	}

	@GetMapping(value = "/dbToJSON")
	public String createJSONFile() throws IOException {
		/*
		 * JSONObject jSONObject; List<Rate> rates = rateRepository.findAll(); try
		 * (FileWriter file = new FileWriter("D:\\FiaRates\\rates.json")) {
		 * 
		 * for (Rate rate : rates) {jSONObject.put("effDate", rate.getEffDate());
		 * jSONObject.put("product", rate.getProduct()); jSONObject.put("indexName",
		 * rate.getIndexName()); jSONObject.put("cdscOption", rate.getCdscOption());
		 * jSONObject.put("band", rate.getBand()); jSONObject.put("capRate",
		 * rate.getCapRate()); jSONObject.put("mnCapRtCDSC", rate.getMnCapRtCDSC());
		 * jSONObject.put("mnCapRtPCDSC", rate.getMnCapRtPCDSC());
		 * jSONObject.put("contractYr", rate.getContractYr()); jSONObject.put("mvaInd",
		 * rate.getMvaInd());}
		 * 
		 * jSONObject = new JSONObject(); jSONObject.put("rates", rates);
		 * file.write(jSONObject.toJSONString()); file.flush(); }catch(
		 * 
		 * IOException e) { e.printStackTrace(); }
		 * 
		 * return"SIMON Rates success !!";
		 * 
		 */

		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
		bodyMap.add("rates", getUserFileResource());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8085/upload", HttpMethod.POST,
				requestEntity, String.class);
		System.out.println("response status: " + response.getStatusCode());
		System.out.println("response body: " + response.getBody());
		return "{"+response.getStatusCode()+"}/{"+response.getBody()+"}";

	}

	public Resource getUserFileResource() throws IOException {

		List<Rate> rates = rateRepository.findAll();

		ObjectMapper objectMapper = new ObjectMapper();
		// Set pretty printing of json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String arrayToJson = objectMapper.writeValueAsString(rates);
		System.out.println("1. Convert List of rate objects to JSON :");
		System.out.println(arrayToJson);

		Path tempFile = Files.createTempFile("rates", ".json");
		Files.write(tempFile, arrayToJson.getBytes());
		System.out.println("uploading: " + tempFile);
		File file = tempFile.toFile();
		// to upload in-memory bytes use ByteArrayResource instead
		return new FileSystemResource(file);
	}

	@PostMapping(value = "/upload")
	public String uploadFile(@RequestBody List<Rate> rates) {
		rateRepository.saveAll(rates);
		return "upload successful";
	}
}
