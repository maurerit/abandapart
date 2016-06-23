package com.eveonline.api;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("unittest")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestApplication.class)
public abstract class BaseApiTestCase {

}
