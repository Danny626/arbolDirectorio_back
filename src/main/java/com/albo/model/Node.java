package com.albo.model;

import java.util.List;

public class Node {
	private String label;
	private String data;
	private String icon;
	private String expandedIcon;
	private String collapsedIcon;
	private String type;
	private List<Node> children;

	public Node() {
	}

	public Node(String label, String data, String icon, String expandedIcon, String collapsedIcon, String type, List<Node> children) {
		this.label = label;
		this.data = data;
		this.icon = icon;
		this.expandedIcon = expandedIcon;
		this.collapsedIcon = collapsedIcon;
		this.type = type;
		this.children = children;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getExpandedIcon() {
		return expandedIcon;
	}

	public void setExpandedIcon(String expandedIcon) {
		this.expandedIcon = expandedIcon;
	}

	public String getCollapsedIcon() {
		return collapsedIcon;
	}

	public void setCollapsedIcon(String collapsedIcon) {
		this.collapsedIcon = collapsedIcon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}


}
