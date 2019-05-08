import beans.SpringBeanContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"controller"})
@Import(SpringBeanContext.class)
public class SpringContext {
}
