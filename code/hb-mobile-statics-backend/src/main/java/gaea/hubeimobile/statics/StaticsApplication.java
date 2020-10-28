package gaea.hubeimobile.statics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootApplication
@MapperScan("gaea.hubeimobile.statics.mapper")
public class StaticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaticsApplication.class, args);
    }

}
