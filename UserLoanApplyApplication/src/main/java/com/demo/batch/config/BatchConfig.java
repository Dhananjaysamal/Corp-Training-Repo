package com.demo.batch.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.demo.batch.entity.UserLoanAccountEntity;
import com.demo.batch.processor.UserLoanAccountBatchProcessor;
import com.demo.bean.User;
import com.demo.repository.UserLoanAccountRepo;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	@Autowired
    private JobBuilderFactory jobBuilderFactory;
 
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
	
	  @Autowired
	  EntityManagerFactory entityManagerFactory;
	 
    
    @Autowired
    UserLoanAccountRepo userLoanAccountRepo;
    
//    @Autowired
//    UserLoanAccountService userLoanAccountService;
    
    @Value("classPath:UserDetails.csv")
    private Resource inputResource;
    
    
    @Bean
    public Job readCSVFileJob(JobExecutionListener listener) {
        return jobBuilderFactory
                .get("readCSVFileJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step())
                .end()
                .build();
    }
   

    @Bean
    public Step step() {
        return stepBuilderFactory
                .get("step")
                .<User, UserLoanAccountEntity>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
   
    @Bean
    public FlatFileItemReader<User> reader() {
        FlatFileItemReader<User> itemReader = new FlatFileItemReader<User>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(inputResource);
        return itemReader;
    }
    
    @Bean
    public ItemProcessor<User, UserLoanAccountEntity> processor() {
        return new UserLoanAccountBatchProcessor();
    }
     
    
    @Bean
    public LineMapper<User> lineMapper() {
        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<User>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[] {"UserName", "Gender", "Age","PAN" ,"AdharaNumber","Salary"});
        lineTokenizer.setIncludedFields(new int[] {0, 1, 2 ,3,4,5});
        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<User>();
        fieldSetMapper.setTargetType(User.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
 
    @Bean
    public JpaItemWriter<UserLoanAccountEntity> writer() {
    	JpaItemWriter<UserLoanAccountEntity> itemWriter = new JpaItemWriter<UserLoanAccountEntity>();
//        itemWriter.setDataSource(dataSource());
//        itemWriter.setSql("INSERT INTO EMPLOYEE (ID, FIRSTNAME, LASTNAME) VALUES (:id, :firstName, :lastName)");
//        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<UserLoanAccount>());
    	
    	itemWriter.setEntityManagerFactory(entityManagerFactory);
    	return itemWriter;
    }
    
    @Bean
    public JobExecutionListener listener() {
        return new JobExecutionListener() {
 
 
            @Override
            public void beforeJob(JobExecution jobExecution) {
               
            }
 
            @Override
            public void afterJob(JobExecution jobExecution) {
                if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                   System.out.println("!!! JOB FINISHED! Time to verify the results");
                    userLoanAccountRepo.findAll().
                            forEach(userLoanAccount -> System.out.println("Found <" + userLoanAccount + "> in the database."));
                }
            }
        };
    }
}
