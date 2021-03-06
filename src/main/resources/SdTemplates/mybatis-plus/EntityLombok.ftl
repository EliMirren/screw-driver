<#assign assign_ClassName = content.items.entity.className>
package ${content.items.entity.packageName};

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
<#if content.content.imports??>
	<#list content.content.imports as impt>
import ${impt};
	</#list>
</#if>

/**
 * <#if content.content.remark??>${content.content.remark}</#if>
 * @author 
 */ 
<#if content.content.annotations??>
	<#list content.content.annotations as anno>
${anno}
	</#list>
</#if>
@Data
<#if assign_ClassName != content.content.tableName>@TableName("${content.content.tableName}")</#if>
public class ${assign_ClassName} {
	<#list content.content.fields as item> 
	<#if item.fieldRemark??>/** ${item.fieldRemark} */</#if>
	<#if item.annotations??>
		<#list item.annotations as anno>
	${anno}
		</#list>
	</#if>
	<#if item.primary == true>
	<#if item.autoIncrement==true || item.identity==true >
	@TableId(value="${item.name}",type=IdType.AUTO)
	<#elseif item.fieldName != item.name >
	@TableId(value="${item.name}")
	<#else>
	@TableId
	</#if>
	<#else>
	<#if item.fieldName != item.name>@TableField("${item.name}")</#if>
	</#if>
	private ${item.fieldType} ${item.fieldName}; 
	</#list>
	<#list content.content.additionalField as item> 
	<#if item.fieldRemark??>/** ${item.fieldRemark} */</#if>
	<#if item.annotations??>
		<#list item.annotations as anno>
	${anno}
		</#list>
	</#if>
	private ${item.fieldType} ${item.fieldName}; 
	</#list>
}
