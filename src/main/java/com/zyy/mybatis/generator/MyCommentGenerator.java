package com.zyy.mybatis.generator;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * 该项目主要目的是方便 org.mybatis.generator
 *
 * @author zhangyiyang
 * @date 2019/5/14
 */
public class MyCommentGenerator extends DefaultCommentGenerator {

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        // 生成方法注释
        method.addJavaDocLine("/**");
        String method_name = method.getName();

        if ("deleteByPrimaryKey".equals(method_name)) {
            method.addJavaDocLine(" * 根据主键删除数据库的记录");
        } else if ("insert".equals(method_name)) {
            method.addJavaDocLine(" * 插入数据库记录");
        } else if ("selectByPrimaryKey".equals(method_name)) {
            method.addJavaDocLine(" * 根据主键获取一条数据库记录");
        } else if ("updateByPrimaryKey".equals(method_name)) {
            method.addJavaDocLine(" * 根据主键来更新数据库记录");
        } else if ("selectAll".equals(method_name)) {
            method.addJavaDocLine(" * 获取全部数据库记录");
        }
        method.addJavaDocLine(" *");
        List<Parameter> parameterList = method.getParameters();
        String paramterName;
        for (Parameter parameter : parameterList) {
            paramterName = parameter.getName();
            method.addJavaDocLine(" * @param " + paramterName);
        }
        // addJavadocTag(method, false);
        method.addJavaDocLine(" */");
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        // 类注释，不管用
        String shortName = innerClass.getType().getShortName();
        innerClass.addJavaDocLine("/**");
        innerClass.addJavaDocLine(" * 类注释");
        innerClass.addJavaDocLine(" * " + shortName);
        innerClass.addJavaDocLine(" * 数据库表：" + introspectedTable.getFullyQualifiedTable());
        // addJavadocTag(innerClass, false);
        innerClass.addJavaDocLine(" */");
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        // 类注释，不管用
        String shortName = innerClass.getType().getShortName();
        innerClass.addJavaDocLine("/**");
        innerClass.addJavaDocLine(" * 类注释");
        innerClass.addJavaDocLine(" * " + shortName);
        innerClass.addJavaDocLine(" * 数据库表：" + introspectedTable.getFullyQualifiedTable());
        // addJavadocTag(innerClass, false);
        innerClass.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // 添加字段注释
        StringBuffer sb = new StringBuffer();
        field.addJavaDocLine("/**");
        if (introspectedColumn.getRemarks() != null){
            field.addJavaDocLine(" * " + introspectedColumn.getRemarks());}
        sb.append(" * 表字段 : ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName());
        field.addJavaDocLine(sb.toString());
        // addJavadocTag(field, false);
        field.addJavaDocLine(" */");
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // get方法注释
        StringBuffer sb = new StringBuffer();
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 获取：" + introspectedColumn.getRemarks());
        sb.append(" * 表字段：");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" *");
        sb = new StringBuffer();
        sb.append(" * @return ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName());
        sb.append("：");
        sb.append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());
        // addJavadocTag(method, false);
        method.addJavaDocLine(" */");
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // set方法注释
        StringBuffer sb = new StringBuffer();
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 设置：" + introspectedColumn.getRemarks());
        sb.append(" * 表字段：");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" *");
        Parameter parm = method.getParameters().get(0);
        method.addJavaDocLine(" * @param " + parm.getName());
        sb = new StringBuffer();
        sb.append(" *            ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName());
        sb.append("：");
        sb.append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());
        // addJavadocTag(method, false);
        method.addJavaDocLine(" */");
    }

    @Override
    public void addComment(XmlElement xmlElement) {
        xmlElement.addElement(new TextElement("<!--"));

        StringBuilder sb = new StringBuilder();
        sb.append(MergeConstants.NEW_ELEMENT_TAG);
        xmlElement.addElement(new TextElement(sb.toString()));

        xmlElement.addElement(new TextElement("-->"));
    }
}
