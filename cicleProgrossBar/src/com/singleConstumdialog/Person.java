package com.singleConstumdialog;

public class Person implements SingleItemName{

	
	public  Person(int id,String name){
		this.Pid=id;
		this.Pname=name;
	}
	
	private  String  Pname;
	private  int  Pid;
	
	
	
	public String getPname() {
		return Pname;
	}



	public void setPname(String pname) {
		Pname = pname;
	}



	public int getPid() {
		return Pid;
	}



	public void setPid(int pid) {
		Pid = pid;
	}



	@Override
	public String getItemname() {
		// TODO Auto-generated method stub
		return Pname;
	}

	
	
}
