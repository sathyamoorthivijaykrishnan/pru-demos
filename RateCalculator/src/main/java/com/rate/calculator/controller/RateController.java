package com.rate.calculator.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.rate.calculator.model.Rate;
import com.rate.calculator.service.RateDataService;

@Controller
public class RateController {

	private String fileName = "rates.csv";
	private String headerKey = "Content-Disposition";
	private String headerValue = String.format("attachment; filename=\"%s\"", fileName);
	private String[] header = { "effDate", "product", "indexName", "cdscOption", "band", "capRate", "mnCapRtCDSC",
			"mnCapRtPCDSC", "contractYr", "mvaInd" };

	@Autowired
	protected RateDataService service;

	@GetMapping("/rates")
	public String goToFileDownload(ModelMap model) {
		return "rates";

	}

	@RequestMapping(value = "/download")
	public String downloadFile(HttpServletResponse response) throws IOException {

		response.setContentType("text/csv");
		response.setHeader(headerKey, headerValue);
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		csvWriter.writeHeader(header);

		List<Rate> rates = service.getRates();
		for (Rate rate : rates) {
			csvWriter.write(rate, header);
		}
		csvWriter.close();
		return "rates";
	}

	@RequestMapping(value = "/view")
	public ModelAndView viewFile() throws IOException {

		ModelAndView mav = new ModelAndView("viewFile");

		List<Rate> rates = service.getRates();
		mav.addObject("header", header);
		mav.addObject("values", rates);

		return mav;
	}
	
	@RequestMapping(value = "/rates/api")
	@ResponseBody
	public List<Rate> rates() throws IOException {
	 return service.getRates();		
	}
}
