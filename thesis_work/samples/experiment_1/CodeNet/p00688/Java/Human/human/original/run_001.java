import java.util.Scanner;

class Main{
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		while(true) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int c=sc.nextInt();

			int[] yakusuA=new int[100];
			int[] yakusuC=new int[100];
			int Acounter=0;
			int Ccounter=0;
			if(a==0 && b==0 && c==0) {
				System.exit(0);
			}
			else {
				for(int i=0; i<100; i++) {
					yakusuA[i]=0;
					yakusuC[i]=0;
				}
				if(a==0) {
					Acounter=1;
					yakusuA[0]=0;
				}
				else if(a!=0){
					for(int i=1; i<=Math.abs(a); i++) {
						if(a%i==0) {
							yakusuA[Acounter]=i;
							Acounter++;
						}
					}
				}
				if(c==0) {
					Ccounter=1;
					yakusuC[0]=0;
				}
				else if(c!=0){
					for(int j=1; j<=Math.abs(c); j++) {
						if(c%j==0) {
							yakusuC[Ccounter]=j;
							Ccounter++;
						}
					}
				}
				int[] n_y_a=new int[Acounter*2];
				for(int i=0; i<Acounter; i++) {
					n_y_a[i*2]=yakusuA[i];
					n_y_a[i*2+1]=-yakusuA[i];
				}
				int[] n_y_c=new int[Ccounter*2];
				for(int i=0; i<Ccounter; i++) {
					n_y_c[i*2]=yakusuC[i];
					n_y_c[i*2+1]=-yakusuC[i];
				}
/*
				for(int i=0; i<Acounter*2; i++) {
					System.out.println(i+"A "+n_y_a[i]);
				}

				for(int i=0; i<Ccounter*2; i++) {
					System.out.println(i+"C "+n_y_c[i]);
				}
*/
				boolean ok=false;
				if(a!=0 && c!=0) {
					for(int i=0; i<Acounter*2; i++) {
						for(int j=0; j<Ccounter*2; j++) {
							int tmp1=0,tmp2=0,tmp3=0,tmp4=0;
							if(a!=0) {
								tmp1=n_y_a[i];
								tmp2=a/n_y_a[i];
							}
							if(c!=0) {
								tmp3=n_y_c[j];
								tmp4=c/n_y_c[j];
							}
							if(b==(tmp1*tmp4+tmp2*tmp3)) {
								if(tmp1>tmp2) {
									System.out.println(tmp1+" "+tmp3+" "+tmp2+" "+tmp4);
									ok=true;
								}
								else if(tmp1<tmp2) {
									System.out.println(tmp2+" "+tmp4+" "+tmp1+" "+tmp3);
									ok=true;
								}
								else if(tmp1==tmp2) {
									if(tmp3>=tmp4) {
										System.out.println(tmp2+" "+tmp3+" "+tmp1+" "+tmp4);
										ok=true;
									}
									else if(tmp4>=tmp3) {
										System.out.println(tmp2+" "+tmp4+" "+tmp1+" "+tmp3);
										ok=true;
									}
								}
							}
							if(ok==true) {
								break;
							}
						}
						if(ok==true) {
							break;
						}
					}
				}
				else if(c==0 && a!=0) {
					for(int i=0; i<Acounter*2; i++) {
						int tmp1=n_y_a[i];
						int tmp2=a/n_y_a[i];
						if(b%tmp2==0) {
							if(tmp1>tmp2) {
								System.out.println(tmp1+" "+(b/tmp2)+" "+tmp2+" 0");
								ok=true;
								break;
							}
							else if(tmp1<tmp2) {
								System.out.println(tmp2+" 0 "+(b/tmp2)+" "+tmp1);
								ok=true;
								break;
							}
							else if(tmp1==tmp2) {
								if(b/tmp2<0) {
									System.out.println(tmp2+" 0 "+tmp1+" "+(b/tmp2));
									ok=true;
									break;
								}
								else {
									System.out.println(tmp2+" "+(b/tmp2)+" "+tmp1+" 0");
									ok=true;
									break;
								}
							}
						}
					}
				}
				if(a==0 && c!=0 && b==0) {
					if(n_y_c[0]>(c/n_y_c[0])) {
						System.out.println("0 "+n_y_c[0]+" 0 "+(c/n_y_c[0]));
					}
					else if(n_y_c[0]<(c/n_y_c[0])) {
						System.out.println("0 "+(c/n_y_c[0])+" 0 "+n_y_c[0]);
					}
					ok=true;
				}
				else if(a==0 && c!=0 && b!=0) {
					for(int i=0; i<Ccounter*2; i++) {
						int tmp3=n_y_c[i];
						int tmp4=c/n_y_c[i];
						if(b%tmp3==0) {
							int tmp1=b/tmp3;
							if(tmp1>0) {
								System.out.println(tmp1+" "+tmp3+" 0 "+tmp4);
								ok=true;
								break;
							}
							else if(tmp1<0) {
								System.out.println("0 "+tmp4+" "+tmp1+" "+tmp3);
								ok=true;
								break;
							}
							else if(tmp1==0) {
								if(tmp3>tmp4) {
									System.out.println("0 "+tmp3+" 0 "+tmp4);
									ok=true;
									break;
								}
								else if(tmp4>=tmp3) {
									System.out.println("0 "+tmp3+" 0 "+tmp4);
									ok=true;
									break;
								}
							}
						}
					}
				}
				if(ok==false) {
					System.out.println("Impossible");
				}
			}
		}
	}
}
