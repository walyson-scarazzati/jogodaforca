package pt.jogodaforca.convertEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Conversor para o enum WinStatusEnum
 */
@Converter(autoApply = true)
public class StatusConverter implements
		AttributeConverter<StatusEnum, Integer> {

	@Override
	public Integer convertToDatabaseColumn(StatusEnum attribute) {
		return attribute != null ? attribute.getId() : null;
	}

	@Override
	public StatusEnum convertToEntityAttribute(Integer dbData) {
		return dbData != null ? StatusEnum.from(dbData) : null;
	}

}
