package sample05;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class SungJukImpl implements SungJuk {
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int total;
	private double avg;
	
	public SungJukImpl() {
		Scanner scan = new Scanner(System.in);
		System.out.print("이름 입력 : ");
		name = scan.next();
		System.out.print("국어 입력 : ");
		kor = scan.nextInt();
		System.out.print("영어 입력 : ");
		eng = scan.nextInt();
		System.out.print("수학 입력 : ");
		mat = scan.nextInt();
	}
	
	@Override
	public void calc() {
		total = kor + eng + mat;
		avg = (double) total / 3.0;
	}

	@Override
	public void display() {
		System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
		System.out.println(this);	// toString() 이나 this
	}
	
	@Override
	public String toString() {
		return name+"\t"+kor+"\t"+eng+"\t"+mat+"\t"+total+"\t"+String.format("%.3f", avg);
	}
	
}
