package com.boxfox.dms.apply.dto;

public class RewardApplyDTO {
	private String id;
	private String target;
	private String content;

	public RewardApplyDTO() {
	}

	public RewardApplyDTO(String id, String target, String content) {
		this.id = id;
		this.target = target;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
