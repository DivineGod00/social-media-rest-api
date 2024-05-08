package com.application.restApi.social.media.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.social.media.controller.SocialMediaController;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class TestCase {

	@Autowired
	private SocialMediaController controller;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
