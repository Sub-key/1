package fljd;

//�����������㷨
public class Demo_test {
	//����
		public double Single(double P, double i, int N) {
			// TODO Auto-generated method stub
			double D = P * (1 + i * N); // ����
			return D;
		}
	//����
		public double Compound(double P, double i, int m, int N) {
			// TODO Auto-generated method stub
			double F = P * (Math.pow((1 + i / m), N * m)); // ����
			return F;
		}
		//�������ֵ
			public double NRate(double P, double i, double N) {//������
				// TODO Auto-generated method stub
				double F = P * (Math.pow(1 + i, N) - 1) / i;
				return F;
			}

		/*	public double MRate(double P, double i, double N) {//������
				// TODO Auto-generated method stub
				double F = P * (Math.pow(1 + i / 12.0, N * 12) - 1) / (i / 12);
				return F;
			}*/
			
			//�󱾽�	
			public double BenJin(double F, double i, int N) {
				// TODO Auto-generated method stub
				double P = F / Math.pow(1 + i, N); // ����
				return P;
			}
			//������
				public double LiLv(double P, double F, int N) {
					// TODO Auto-generated method stub
					double i = (Math.pow(F / P, 1.0 / N)) - 1;
					return i;
				}

				public double TurnLiLv(int n, int f) {
					// TODO Auto-generated method stub
					double I = 72.0 / (n / f); // 72����
					return I;
				}
				//���ڸ�Ϣ
			public double pay(double P, double i, double N) {
						// TODO Auto-generated method stub
						double H = P* (((i / 12) * (Math.pow(1 + i / 12, N * 12))) / (Math.pow(
										1 + i / 12, N * 12) - 1));

						return H;
					}
			//����������	
			public double NianXian(double P,double F,double i){
				int N=(int) ((Math.log(F) / Math.log(1 + i)) - (Math.log(P) / Math
							.log(1 + i)));
					return N;
					
				}

	}

