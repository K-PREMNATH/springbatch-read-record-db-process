package com.config;

import com.dto.CustomerDetail;
import com.dto.CustomerFileDTO;
import com.mapper.CustomerRowMapper;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public Job job(@Qualifier("step1") Step step1) {
        return jobBuilderFactory.get("job")
                .start(step1)
                .build();
    }

    @Bean("step1")
    public Step step1(@Autowired FixedLengthProcessor fixedLengthProcessor,@Autowired FixedLengthFileWriter fixedLengthFileWriter) {
        return stepBuilderFactory.get("step1")
                .<CustomerDetail, CustomerFileDTO>chunk(5)
                .reader(pagingItemReader())
                .processor(fixedLengthProcessor)
                .writer(fixedLengthFileWriter)
                .build();
    }


    @Bean
    public ItemWriter<CustomerDetail> customerItemWriter(){
        return items -> {
            for(CustomerDetail c : items) {
                System.out.println(c.toString());
            }
        };
    }

    @Bean
    public JdbcPagingItemReader<CustomerDetail> pagingItemReader(){
        JdbcPagingItemReader<CustomerDetail> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(this.dataSource);
        reader.setFetchSize(10);
        reader.setRowMapper(new CustomerRowMapper());

        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.ASCENDING);

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("SELECT Id, FirstName,LastName,TotalWage");
        queryProvider.setFromClause("FROM (\n" +
                "    SELECT\n" +
                "        id as Id,\n" +
                "        firstname AS FirstName,\n" +
                "        lastname AS LastName,\n" +
                "        total_wage AS TotalWage\n" +
                "    FROM InternalCustomer\n" +
                "    UNION ALL\n" +
                "    SELECT\n" +
                "        id as Id,\n" +
                "        firstname AS FirstName,\n" +
                "        lastname AS LastName,\n" +
                "        (basic_wage + allowance)  AS TotalWage\n" +
                "    FROM ExternalCustomer\n" +
                ") AS CombinedCustomers");
        queryProvider.setSortKeys(sortKeys);
        reader.setQueryProvider(queryProvider);

        return reader;
    }

}
