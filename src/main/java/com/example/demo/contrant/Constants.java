package com.example.demo.contrant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class Constants {

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class MESSAGE {
			public static final String OK = "Yêu cầu thực hiện thành công";
			
			public static final String EXPECTATION_FAILED = "Có lỗi sảy ra!";
	}
}
