package org.fai.ExtractAutomationTests;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.fai.pages.login.Login;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

	
	@Test(priority=1,groups={"prerequisites"})
	public void appLogin(Map<String, String> input) {
			String loggedUser = new Login().refreshOnPageLoad()
				.enterUserName(input.get("user name"))
				.enterPassword(input.get("password"))
				.clickLogin().getLoggedUserName();
		Assertions.assertThat(loggedUser).contains(input.get("profile"));

	}
}
