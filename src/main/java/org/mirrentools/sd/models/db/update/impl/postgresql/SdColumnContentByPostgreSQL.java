package org.mirrentools.sd.models.db.update.impl.postgresql;

import org.mirrentools.sd.models.db.update.SdAbstractColumnContent;

/**
 * 数据列属性的PostgreSQL实现
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class SdColumnContentByPostgreSQL extends SdAbstractColumnContent {

	@Override
	public String createSQL() {
		StringBuilder sb = new StringBuilder();
		sb.append("`" + getName() + "`");
		sb.append(" " + getType());
		if (getLength() != null) {
			sb.append("(" + getLength() + ")");
		}
		if (isUnsigned()) {
			sb.append(" UNSIGNED");
		}
		if (isNotNull()) {
			sb.append(" NOT NULL");
		}
		if (isAutoIncrement()) {
			sb.append(" AUTO_INCREMENT");
		}
		if (getDefault() != null) {
			if (getDefault() instanceof Number) {
				sb.append(" DEFAULT " + getDefault());
			} else {
				sb.append(" DEFAULT '" + getDefault() + "'");
			}
		}
		if (getRemark() != null) {
			sb.append(" COMMENT '" + getRemark() + "'");
		}
		if (converterExtensions() != null) {
			sb.append(" " + converterExtensions());
		}
		return sb.toString();
	}

	@Override
	public String updateSQL() {
		return " ADD COLUMN " + createSQL();
	}

	@Override
	public String deleteSQL() {
		return " DROP COLUMN " + getName();
	}

}
