#include<stdio.h>
#include<math.h>
void tip()
{
	printf("�����������\n");
	printf(" �������㹫ʽ\n");
	printf("\tF=P*pow((1+i/m),N*m)\n");
	printf(" ������㹫ʽ\n");
	printf("\tP=F/pow((1+i/m),N*m)\n");
	printf(" �������㹫ʽ\n");
	printf("\tL=P*N*i\n\tH=L+P\n");
	printf("\tF:������ֵ\n");
	printf("\tP:����\n");
	printf("\ti:����\n");
	printf("\tH:������\n");
	printf("\tN�����ʻ�ȡʱ���������\n");
	printf("\tm:�긴������(һ�굱�еĹ�������)\n");
}
void menu()
{
	printf("\t\t------------- CHOOSE ------------\n");
	printf("\t\t 1:���㱾�� \n");
	printf("\t\t 2:�����긴����ֵ \n");
	printf("\t\t 3:�������� \n");
	printf("\t\t 4:����ʱ�� \n");
	printf("\t\t 5:�������� \n");
	printf("\t\t 6:�����ֵ \n");
	printf("\t\t 7:�ȶϢ����\n");
	printf("\t\t 0:���� \n");
	printf("\t\t-----------------------------------\n");
	printf("��������Ҫѡ��ļ��㷽ʽ(0~3):\n");
	
}

void benjin()
{
	int N,m;
	double i,F,P;
	printf("������ֵ:");
	scanf("%lf",&F);
	printf("������:");
	scanf("%lf",&i);
	printf("��������:");
	scanf("%d",&N);
	printf("�긴������:");
	scanf("%d",&m);
	P=F/pow((1+i/m),N*m);
	printf("�긴����ֵΪ%.4lf��Ҫ����Ϊ:%.4lf\n",F,P);
}
void fuli()
{
	int N,m;
	double i,F,P;
	printf("���뱾��:");
	scanf("%lf",&P);
	printf("������:");
	scanf("%lf",&i);
	printf("��������:");
	scanf("%d",&N);
	printf("�긴������:");
	scanf("%d",&m);
	F=P*pow((1+i/m),N*m);
	printf("������ֵ:%.4lf\n",F);
}
void danli()
{
	int N;
	double i,H,P,L;
	printf("���뱾��:");
	scanf("%lf",&P);
	printf("������:");
	scanf("%lf",&i);
	printf("��������:");
	scanf("%d",&N);
	L=P*N*i;
	H=L+P;
	printf("��Ϣ��Ϊ:%.4lf\n",H);
}

void shijian()
{
	int N,m;
	double i,F,P;
	printf("���뱾��:");
	scanf("%lf",&P);
	printf("������:");
	scanf("%lf",&i);
	printf("������ֵ:");
	scanf("%lf",&F);
	printf("�긴������:");
	scanf("%d",&m);
	N=(int)(log(F/P)/log(1+i/m))/m;
	printf("ʱ��:%d\n",N);
}
void lilu()
{
	int N,m;
	double i,F,P;
	printf("���뱾��:");
	scanf("%lf",&P);
	printf("��������:");
	scanf("%d",&N);
	printf("������ֵ:");
	scanf("%lf",&F);
	printf("�긴������:");
	scanf("%d",&m);
	i=m*pow(F/P,1.0/N*m)-m;
	printf("����:%.4lf\n",i);
}
void nianjinzhongzhi()//���������ֵ
{
	int N,n;
	double i,F,P;
	printf("���뱾��:");
	scanf("%lf",&P);
	printf("��������:");
	scanf("%d",&N);
	printf("������:");
	scanf("%lf",&i);
	printf("\t\t1:����Ͷ��\n\t\t2:����Ͷ��\n");
A:printf("��ѡ����Ҫ�Ĺ���<1|2>:");
  scanf("%d",&n);
  if(n==1)
  {
	  F=P*(pow(1+i,N)-1)/i;
	  
  }
  else if(n==2)
  {
	  F=N*12*(P*(i/12)+P);
  }
  else
  {
	  printf("��������!����������\n");
	  goto A;
  }
  printf("%d�����ܲ�ֵ:%.4lf\n",N,F);
  
}
void dengebenxi()
{
	int N;
	double i,F,P;
	printf("�����:");
	scanf("%lf",&P);
	printf("��������:");
	scanf("%d",&N);
	printf("������:");
	scanf("%lf",&i);
	F= P*(((i/12)*(pow(1+i/12, N*12)))/(pow(1+i/12, N*12)-1));
	printf("�ȶϢ����:%.2lf\n",F);
}
void main()
{
	int n;
	while(1)
	{
		tip();
		menu();
		scanf("%d",&n);
		if(n==0)
			break;
		switch(n)
		{
		case 1:
			benjin();break;
		case 2:
			fuli();break;
		case 3:
			danli();break;
		case 4:
			shijian();break;
		case 5:
			lilu();break;
		case 6:
			nianjinzhongzhi();break;
		case 7:
			dengebenxi();break;
		case 0:
			n=0;
			break; 
		}
		
	}
}