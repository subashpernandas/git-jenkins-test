package com.example.demo.configuration;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import com.example.demo.model.Person;

@Configuration
public class BatchConfiguration {

	//@Bean
	//@StepScope public FlatFileItemReader<Person> reader(@Value("#{jobParameters[fileUploadName]}") String pathToFile) {
		//return new FlatFileItemReaderBuilder<Person>() .name("CSv-reader") .resource(new
			//	PathResource(pathToFile)) .lineMapper(lineMapper()) .linesToSkip(1) .build();}
	

	@Bean 
	public FlatFileItemReader<Person> reader(@Value("${input}") Resource reosurce) { 
		System.out.println("File Name::::Reader:::"+ reosurce.getFilename());
		FlatFileItemReader<Person> flatFileIteamReader = new FlatFileItemReader<Person>();
		flatFileIteamReader.setResource(reosurce);
		flatFileIteamReader.setName("CSV-reader");
		flatFileIteamReader.setLinesToSkip(1);
		flatFileIteamReader.setLineMapper(lineMapper());
		return flatFileIteamReader;
	}



	@Bean
	public LineMapper<Person> lineMapper() {
		DefaultLineMapper<Person> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		System.out.println("Hello LineMapper :::::::::::::::::::");
		delimitedLineTokenizer.setNames(new String[] {"id", "firstName","LastName","email","age"});
		BeanWrapperFieldSetMapper<Person> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<Person>();
		beanWrapperFieldSetMapper.setTargetType(Person.class);
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		return defaultLineMapper;
	}

	@Bean 
	public Job importUserJob(JobBuilderFactory jobBuilderFactory,
			StepBuilderFactory stepBuilderFactory, ItemReader<Person> itemReader,
			ItemProcessor<Person, Person> itemProcessor, ItemWriter<Person> itemWriter) {
		System.out.println("Hello job :::::::::::::::::::");
		Step step = stepBuilderFactory.get("ETL-file-upload"). 
				<Person, Person> chunk(100)
				.reader(itemReader).processor(itemProcessor).writer(itemWriter).build();
		return (Job) jobBuilderFactory.get("ETL-load"). incrementer(new RunIdIncrementer()).start(step).build();

	}

}
