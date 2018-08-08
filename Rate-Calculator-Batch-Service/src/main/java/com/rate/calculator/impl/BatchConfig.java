package com.rate.calculator.impl;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.rate.calculator.model.Rate;

@EnableBatchProcessing
@Configuration

public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	protected MongoTemplate mongoTemplate;

	@Bean
	public Job addNewJob() {
		return jobBuilderFactory.get("job").listener(RateJobListener()).start(step()).build();
	}

	@Bean
	public Step step() {
		return stepBuilderFactory.get("step").<Rate, Rate>chunk(1) // important to be one in this case to commit after
																	// every line read
				.reader(reader()).processor(processor()).writer(writer()).build();
	}

	@Bean
	public ItemWriter<Rate> writer() {
		return new RateItemWriter();
	}

	@Bean
	public ItemProcessor<Rate, Rate> processor() {
		return new RateItemProcessor();
	}

	@Bean
	public FlatFileItemReader<Rate> reader() {
		FlatFileItemReader<Rate> reader = new FlatFileItemReader<Rate>();
		reader.setLinesToSkip(1);// first line is title definition
		reader.setResource(new ClassPathResource("rates.csv"));
		reader.setLineMapper(lineMapper());
		return reader;
	}

	@Bean
	public LineMapper<Rate> lineMapper() {
		DefaultLineMapper<Rate> lineMapper = new DefaultLineMapper<Rate>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[] { "effDate", "product", "indexName", "cdscOption", "band", "capRate",
				"mnCapRtCDSC", "mnCapRtPCDSC", "contractYr", "mvaInd" });

		BeanWrapperFieldSetMapper<Rate> fieldSetMapper = new BeanWrapperFieldSetMapper<Rate>();
		fieldSetMapper.setTargetType(Rate.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(rateFieldSetMapper());

		return lineMapper;
	}

	@Bean
	public FieldSetMapper<Rate> rateFieldSetMapper() {
		return new RateFieldSetMapper();
	}

	@Bean
	public JobExecutionListener RateJobListener() {
		return new RateJobListener();
	}

}
