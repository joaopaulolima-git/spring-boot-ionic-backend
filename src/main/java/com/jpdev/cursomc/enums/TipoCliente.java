package com.jpdev.cursomc.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Fisíca"),
	PESSSOAJURIDICA(2, "Pessoa Jurídica"); 
	
	private Integer cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente x : TipoCliente.values()) {
			if(cod.equals(x.cod)) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalido" + cod);
		
	}

}
