package pt.jogodaforca.convertEnum;

public enum StatusEnum {

	WON(1, "WON"), LOST(-1, "LOST"), PLAYABLE(0, "PLAYABLE");

	private Integer id;
	private String descricao;

	StatusEnum(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return this.id;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public static StatusEnum from(final Integer valor) {
		if (valor == null) {
			throw new IllegalArgumentException();
		}

		for (StatusEnum e : StatusEnum.values()) {
			if (valor.equals(e.getDescricao()) || valor.equals(e.getId())) {
				return e;
			}
		}

		final StringBuilder msg = new StringBuilder("");
		msg.append("Cannot parse into an element of WinStatusEnum: '");
		msg.append(valor);
		msg.append("'");

		throw new IllegalArgumentException(msg.toString());
	}

	public static StatusEnum fromId(final Integer id) {
		if (id == null) {
			throw new IllegalArgumentException();
		}
		for (StatusEnum e : StatusEnum.values()) {
			if (id.equals(e.getId())) {
				return e;
			}
		}

		final StringBuilder msg = new StringBuilder("");
		msg.append("Cannot parse into an element of WinStatusEnum: '");
		msg.append(id);
		msg.append("'");

		throw new IllegalArgumentException(msg.toString());
	}
	
}
