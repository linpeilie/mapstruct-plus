<#-- @ftlvariable name="" type="io.github.linpeilie.processor.enhance.model.SpringDelayInjectMapperReference" -->
private <@includeModel object=type/> ${variableName} = SpringContextUtils.getBean("${variableName}", <@includeModel object=type/>.class);