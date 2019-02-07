package org.mirrentools.sd.util.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.mirrentools.sd.common.SdException;
import org.mirrentools.sd.constant.Constant;
import org.mirrentools.sd.models.SdRenderContent;
import org.mirrentools.sd.models.SdTemplate;
import org.mirrentools.sd.util.SdCodeUtil;
import org.mirrentools.sd.util.SdTemplateUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 模板生成工具FreeMarker实现版
 * 
 * @author <a href="http://mirrentools.org">Mirren</a>
 *
 */
public class SdTemplateUtilImplFreeMarker implements SdTemplateUtil {
	/** JUL日志 */
	private final Logger LOG = Logger.getLogger(this.getClass().getName());

	@Override
	public boolean render(String projectPath, String format, SdRenderContent content, SdTemplate template) {
		StringBuilder path = new StringBuilder();
		if (!projectPath.endsWith("/") && !projectPath.endsWith("\\")) {
			path.append(projectPath + "/");
		} else {
			path.append(projectPath);
		}
		if (!template.getSourceFolder().endsWith("/") && !template.getSourceFolder().endsWith("\\")) {
			path.append(template.getSourceFolder() + "/");
		} else {
			path.append(template.getSourceFolder());
		}
		path.append(template.getPackageName().replace(".", "/"));
		String outputDirPath = path.toString();
		if (!outputDirPath.endsWith("/") && !outputDirPath.endsWith("\\")) {
			outputDirPath += "/";
		}
		String outputFilePath = outputDirPath + template.getClassName() + template.getSuffix();
		if (!template.isOverride()) {
			if (new File(outputFilePath).exists()) {
				LOG.info("设置了文件存在不覆盖,文件已经存在,忽略本文件的创建");
				return false;
			}
		}
		Writer writer = null;
		try {
			Configuration config = new Configuration(Configuration.VERSION_2_3_23);
			config.setDirectoryForTemplateLoading(new File(template.getPath()));
			config.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
			config.setDefaultEncoding(Constant.UTF_8);
			Map<String, Object> dataModel = new HashMap<String, Object>();
			dataModel.put("content", content);

			File outDir = new File(outputDirPath);
			if (!outDir.exists()) {
				boolean mkdirs = outDir.mkdirs();
				if (!mkdirs) {
					LOG.warning("执行创建文件输出文件夹结果: " + mkdirs);
				}
			}
			writer = new OutputStreamWriter(new FileOutputStream(outputFilePath), format);
			Template freeTemplate = config.getTemplate(template.getFile());
			freeTemplate.process(dataModel, writer);
			return true;
		} catch (Exception e) {
			throw new SdException(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
					LOG.warning(e.getMessage());
				}
			}
		}
	}

	@Override
	public SdCodeUtil addExtension(String key, Object value) {
		throw new SdException("该方法为备用拓展字段,如果需要使用到该字段可以继承后重写");
	}

	@Override
	public Map<String, Object> getExtensions() {
		throw new SdException("该方法为备用拓展字段,如果需要使用到该字段可以继承后重写");
	}

	@Override
	public SdCodeUtil setExtensions(Map<String, Object> extensions) {
		throw new SdException("该方法为备用拓展字段,如果需要使用到该字段可以继承后重写");
	}

}
