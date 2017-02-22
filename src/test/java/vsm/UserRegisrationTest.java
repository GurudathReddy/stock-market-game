package vsm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vsm.stockmarket.exception.BaseException;
import com.vsm.stockmarket.exception.UserProfileValidationException;
import com.vsm.stockmarket.mbeans.UserFormBean;
import com.vsm.stockmarket.services.RegistrationService;

public class UserRegisrationTest {

	public static void main(String[] args)
			throws UserProfileValidationException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"META-INF\\spring\\applicationContext.xml");
		RegistrationService registrationService = (RegistrationService) applicationContext
				.getBean("registrationService");

		UserFormBean userFormBean = new UserFormBean();
		userFormBean.setFirstName("Fname");
		userFormBean.setEmail("ranju@vsm.com");
		userFormBean.setPassword("password");
		userFormBean.setConfirmPassword("password");
		registrationService.registerUser(userFormBean);
	}

}
