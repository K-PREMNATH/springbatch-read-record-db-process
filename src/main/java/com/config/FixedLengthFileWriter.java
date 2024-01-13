package com.config;

import com.dto.CustomerDetail;
import com.dto.CustomerFileDTO;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.FormatterLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FixedLengthFileWriter implements ItemWriter<CustomerFileDTO> {

    private FlatFileItemWriter<CustomerFileDTO> writer;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution){
        writer = createFileItemWriter("src/main/resources/","ABC.txt");
    }

    private FlatFileItemWriter<CustomerFileDTO> createFileItemWriter(String dir, String fileName) {
        FlatFileItemWriter<CustomerFileDTO> fileWriter  = new FlatFileItemWriter<>();
        fileWriter.setResource(new FileSystemResource(dir+fileName));

        BeanWrapperFieldExtractor<CustomerFileDTO> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"recordType","firstname","lastname","totalWage"});
        fieldExtractor.afterPropertiesSet();
        FormatterLineAggregator<CustomerFileDTO> formatterLineAggregator = new FormatterLineAggregator<>();
        formatterLineAggregator.setFieldExtractor(fieldExtractor);
        formatterLineAggregator.setFormat("%s%s%s%s");
        fileWriter.setLineAggregator(formatterLineAggregator);

        return fileWriter;
    }

    @Override
    public void write(List<? extends CustomerFileDTO> items) throws Exception {
        writer.open(new ExecutionContext());
        writer.write(items);
    }

    @AfterStep
    public void afterStep(StepExecution stepExecution){
        writer.close();
    }
}
