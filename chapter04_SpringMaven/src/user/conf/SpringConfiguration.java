package user.conf;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 환경설정파일 (외부 라이브러리 잡을 때)
public class SpringConfiguration {

	@Bean(name = "dataSource") // 이 메소드는 빈의 이름을 메스드명으로 인식한다
	public BasicDataSource getBasicDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		basicDataSource.setUsername("java");
		basicDataSource.setPassword("dkdlxl");
		basicDataSource.setMaxTotal(20);
		basicDataSource.setMaxIdle(3);

		return basicDataSource;
	}
}
