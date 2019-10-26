package org.mirrentools.sd.options.def;

import java.util.Map;

import org.mirrentools.sd.converter.impl.SdTemplateContentConverterDefaultImpl;
import org.mirrentools.sd.converter.impl.sqlite.SdClassConverterSqliteImpl;
import org.mirrentools.sd.converter.impl.sqlite.SdTableContentConverterSqliteImpl;
import org.mirrentools.sd.impl.ScrewDriverTemplateFreeMarkerImpl;
import org.mirrentools.sd.impl.dbutil.SdDbUtilSqliteImpl;
import org.mirrentools.sd.models.SdTemplate;
import org.mirrentools.sd.options.ScrewDriverOptions;
import org.mirrentools.sd.options.SdDatabaseOptions;

/**
 * SQLite版配置
 * 
 * @author <a href="http://mirrentools.org">Mirren</a>
 *
 */
public class ScrewDriverSqliteOptions extends ScrewDriverOptions {
	/**
	 * 初始化一个SQLite版的ScrewDriver配置,用于仅生成SQL
	 * 
	 * 
	 * @param databaseOptions
	 *          数据库连接信息
	 */
	public ScrewDriverSqliteOptions(SdDatabaseOptions databaseOptions) {
		init(null, databaseOptions);
	}

	/**
	 * 初始化一个SQLite版的ScrewDriver配置,用于仅生成代码
	 * 
	 * 
	 * @param templateMaps
	 *          模板集
	 * 
	 */
	public ScrewDriverSqliteOptions(Map<String, SdTemplate> templateMaps) {
		init(templateMaps, null);
	}

	/**
	 * 初始化一个SQLite版的ScrewDriver配置,用于生成代码与SQL
	 * 
	 * @param templateMaps
	 *          模板集
	 * 
	 * @param databaseOptions
	 *          数据库连接信息
	 */
	public ScrewDriverSqliteOptions(Map<String, SdTemplate> templateMaps, SdDatabaseOptions databaseOptions) {
		init(templateMaps, databaseOptions);
	}

	/**
	 * 初始化
	 * 
	 * @param templateMaps
	 *          模板集合
	 * @param databaseOptions
	 *          数据库连接信息
	 */
	private void init(Map<String, SdTemplate> templateMaps, SdDatabaseOptions databaseOptions) {
		super.setTemplateMaps(templateMaps);
		super.setDatabaseOptions(databaseOptions);
		super.setTemplateContentConverter(new SdTemplateContentConverterDefaultImpl());
		super.setTemplateUtil(new ScrewDriverTemplateFreeMarkerImpl());
		super.setBeanConverter(new SdClassConverterSqliteImpl());
		super.setTableConverter(new SdTableContentConverterSqliteImpl());
		super.setDbUtil(new SdDbUtilSqliteImpl(databaseOptions));
	}

}
