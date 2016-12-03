package com.mondimedia.memory.common;

public enum REQ_TYPE {

	ADD(4), DEL(1), UPDATE(3), PRINT(1), PRINT_ALL(1);
	private String command;

	REQ_TYPE(int size) {
		this.size = size;
	}

	private int size;

	public int getSize() {
		return size;
	}

	public String getCommand() {
		return command;
	}

}
