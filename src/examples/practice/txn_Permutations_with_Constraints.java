package examples.practice;


import java.util.Scanner;

/**
 * @author - Nuthalapati,Tejasvi
 * @NetID - txn131430
 */

public class txn_Permutations_with_Constraints {

	static int n, c, v, count = 0, r;
	static int[] A;
	static int[][] constraints;

	public static void permutations(int i) {
		int i1 = 0, i2 = 0, i3 = 0, flg_constraint_matches = 1, type1_case = 0;
		if (i == 0) {
			visit();
			return;
		} else {
			for (int k = 0; k < n; k++) {
				if (A[k] == 0) {
					A[k] = i;
					// do constraint check
					label1: for (i1 = 0; i1 < c; i1++) {
						label2: for (i2 = 0; i2 < n; i2++) {
							if (A[i2] == constraints[i1][0]) {
								flg_constraint_matches = 1;
								break;
							} else if (A[i2] == constraints[i1][1])
							{
								// search for 1st number after in the list
								for (i3 = 0; i3 < n; i3++) {
									if (i3 < i2 && A[i3] == 0) {
										flg_constraint_matches = 1;
										type1_case = 1;
										break label2;
									} else if (A[i3] == constraints[i1][0]) {
										flg_constraint_matches = 0;
										break label1;
									} else {
										flg_constraint_matches = 0;
									}

								}
							}

						}
						if (type1_case == 1) {
							type1_case = 0;
							for (i3 = i2 + 1; i3 < n; i3++) {
								if (A[i3] == constraints[i1][0]) {
									A[k] = 0;
									flg_constraint_matches = 0;
								}
							}

						}
						if (flg_constraint_matches == 0)
							break;
					}
					if (flg_constraint_matches == 1) {
						permutations(i - 1);
						A[k] = 0;
					} else {
						A[k] = 0;
						flg_constraint_matches = 1;
					}

				}
			}
		}
	}

	public static void visit() {
		if (v > 0) 
		{
			for (int i = 0; i < n; i++) {
				System.out.print(A[i] + " ");
			}
			System.out.println();

		}
		count++;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 0, b = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter n,c,v and contraints");
		n = sc.nextInt();
		c = sc.nextInt();
		v = sc.nextInt();
		constraints = new int[c][2];

		for (int i = 0; i < c; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			if (a == b || a < 1 || b < 1 || a > n || b > n) {
				System.out
						.println("Exception: Invalid Constraint: \nEnter constraints following these rules:");
				System.out.println("1.) first number != second number");
				System.out
						.println("1.) first number and second number less than value of n");
			}
			constraints[i][0] = a;
			constraints[i][1] = b;

		}
		sc.close();
		A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = 0;
		}
		double start = System.currentTimeMillis();
		permutations(n);
		double end = System.currentTimeMillis();
		System.out.println(count + " " + Math.round(end - start));
	}

}