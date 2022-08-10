package com.myweb.basic.command;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeBoardVo {
	
	private Integer nb_no;
	
	@NotBlank(message = "작성자는 필수입니다")
	private String nb_id;
	@NotBlank(message = "제목은 필수입니다")
	private String nb_title;
	@NotBlank(message = "내용은 필수입니다")
	private String nb_content;
	private LocalDateTime nb_regdate;
}
