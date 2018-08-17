package com.rate.calculator.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.rate.calculator.model.Rate;
import com.rate.calculator.service.RateDataService;
import com.rate.calculator.service.RateLoadService;

@RequestMapping("/")
@Controller
public class RateController {

	private String fileName = "rates.csv";
	private String headerKey = "Content-Disposition";
	private String headerValue = String.format("attachment; filename=\"%s\"", fileName);
	private String[] header = { "effDate", "product", "indexName", "cdscOption", "band", "capRate", "mnCapRtCDSC",
			"mnCapRtPCDSC", "contractYr", "mvaInd" };

	@Autowired
	protected RateDataService dataService;

	@Autowired
	protected RateLoadService loadService;

	@GetMapping("/rates")
	public String goToFileDownload(ModelMap model) {
		return "rates";
	}

	@GetMapping("/fileUpload")
	public String goToFileUpload(ModelMap model) {
		return "uploadFile";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadFile(@PathVariable("file") MultipartFile file, ModelMap model) {

		if (file.isEmpty()) {
			model.put("message", "Please upload the file");
			return "uploadFile";
		}
		try {

			InputStream inputStream = file.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));					

			CSVReader csvReader = new CSVReaderBuilder(bufferedReader).withSkipLines(1).build();
			List<String[]> allRows = csvReader.readAll();
			System.out.println("contents read from file... "+allRows);
			String status = loadService.loadRates(allRows);
			System.out.println("upload status... "+status);
			model.put("message", status);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "uploadFile";
	}

	@RequestMapping(value = "/download")
	public ModelAndView downloadFile(HttpServletResponse response) throws IOException {
		
		ModelAndView mav = new ModelAndView("rates");

		response.setContentType("text/csv");
		response.setHeader(headerKey, headerValue);
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		csvWriter.writeHeader(header);

		List<Rate> rates = dataService.getRates();
		for (Rate rate : rates) {
			csvWriter.write(rate, header);
		}
		csvWriter.close();
		return mav;
	}

	@RequestMapping(value = "/view")
	public ModelAndView viewFile() throws IOException {

		ModelAndView mav = new ModelAndView("viewFile");

		List<Rate> rates = dataService.getRates();
		mav.addObject("header", header);
		mav.addObject("values", rates);

		return mav;
	}

	@RequestMapping(value = "/rates/api")
	@ResponseBody
	public List<Rate> rates() throws IOException {
		return dataService.getRates();
	}
}
