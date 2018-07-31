package com.community.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {

	// static �ɹ� �ʵ�� SqlSessionFactory type ����
	private static SqlSessionFactory sqlSession;
	// static �ʱ�ȭ
	static {
		// MyBatis ���� xml ������ ��ġ
		String resource = "test/mybatis/Configuration.xml";
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			// SqlSessionFactory ��ü�� �������� ���� �ɹ��ʵ忡 ����
			sqlSession = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
			System.out.println("SqlSessionFactory ��ü ���� ����!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SqlSessionFactory ��ü ���� ����!");
		}
	}

	// static �ɹ� �޼ҵ�
	public static SqlSessionFactory getSqlSession() {
		return sqlSession;
	}
}
