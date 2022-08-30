package microdb.demo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired 
	private MainController main;

	@Test
	void contextLoads() {
	}

	@Test
	void adduser() {
		Respuesta res = main.addNewUser("Fabian", "fabiangut20@gmail.com");
		assertThat(res.getError()).isNull();
	}

}