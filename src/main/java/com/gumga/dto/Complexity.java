package com.gumga.dto;

public enum Complexity {
	MUITO_FRACO("Muito fraco", "#d9534f"), FRACO("Fraco", "#ff8040"), BOM("Bom", "#e7e11d"), FORTE("Forte", "#00ca33"), MUITO_FORTE("Muito Forte", "#7e17db");
	
	private String descricao;
	private String cssCode;

	private Complexity(String descricao, String cssCode) {
		this.descricao = descricao;
		this.cssCode = cssCode;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public String getCssCode() {
		return cssCode;
	}

	public static final Complexity getComplexityFromScore(Long score) {
		if (score < 20) {
			return Complexity.MUITO_FRACO;
		}
		if (score < 40) {
			return Complexity.FRACO;
		}
		if (score < 60) {
			return Complexity.BOM;
		}
		if (score < 80) {
			return Complexity.FORTE;
		}
		return Complexity.MUITO_FORTE;
	}
	
	public static final String getCssByComplexity(String complexity) {
		for (Complexity c : values()) {
			if (c.getDescricao().equalsIgnoreCase(complexity)) {
				return c.getCssCode();
			}
		}
		return "";
	}
}
