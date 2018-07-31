package com.community.controller;

/*
 *  ����Ͻ� ���� ó�� �� �̵��� ��� ������
 *  �����̷�Ʈ �̵������� ���� ���θ� ������ ��ü
 */
public class ActionForward {
	// �̵� ���
	private String path;
	// �����Ϸ�Ʈ �̵� ����
	private boolean isRedirect;

	// ������
	public ActionForward(String path) {
		this.path = path;
	}

	// ������2
	public ActionForward(String path, boolean isRedirect) {
		this.path = path;
		this.isRedirect = isRedirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
}
