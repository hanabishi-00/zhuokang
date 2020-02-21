package Service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "Mapper")
public class JpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class,args);
    }
}
