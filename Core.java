import java.io.FileInputStream;

public class Core
{
	FileInputStream f1;
	
	
	Team[] team = new Team[208];
	Competition competition[][][] = new Competition[208][208][10];
	
	
	int readPlayer(FileInputStream f, Team[] t)
	{
		try
		{
			int c;
			int teamNum = 0;
			int playerNum = 0;
			int sta = 0;//主数据
			int staType = 0;
			for(;;)
			{
				c = f.read();
				if(c == '#')
				{
					break;
				}
			}
			c = '\n';
			//正式数据开始
			for(;;)
			{
				if(c == '\n')//读取新球员信息
				{
		//			t[teamNum].player[playerNum].sta[staType] = sta;
					staType = 0;
					
					sta = 0;
					do
					{
						c = f.read();
						if(c == -1)//文件结束
						{
							return 2;
						}
						if(c != ',')
						{
							sta = sta * 10 + Integer.valueOf((char)c+"");
	//						System.out.println(c);
						}
					}while(c != ',');
					teamNum = sta;
					
					sta = 0;
					do
					{
						c = f.read();
						if(c != ',')
						{
							sta = sta * 10 + Integer.valueOf((char)c+"");
						}
					}while(c != ',');
					playerNum = sta;
					sta = 0;
					
					continue;
				}
				
				c = f.read();
				
				if(c == 13)
				{
					t[teamNum].player[playerNum].sta[staType] = sta;
	//				System.out.println(sta);
					continue;
				}
				if(c == -1)//文件结束
				{
					t[teamNum].player[playerNum].sta[staType] = sta;
					return 2;
				}
				
				if(c != ',' && c != '\n')
				{
					if(c != '-') sta = sta * 10 + Integer.valueOf((char)c+"");
				}
				if(c == ',')
				{
		//			System.out.println(teamNum+"aaaa");
		//			System.out.println(playerNum+"bbbbb");
		//			System.out.println(staType);
		//			System.out.println(t[teamNum].player[playerNum]);
		//			System.out.println(sta);
					t[teamNum].player[playerNum].sta[staType] = sta;
					sta = 0;
					staType++;
				}
				
				
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 1;
	}
	
	
	int readCompetition(FileInputStream f, Competition[][][] com)
	{
		try
		{
			int c;
			int team1 = 0, team2 = 0;
			int sta = 0;//主数据
			int staType = 0;
			for(;;)
			{
				c = f.read();
				if(c == '#')
				{
					break;
				}
			}
			c = '\n';
			//正式数据开始
			for(;;)
			{
				
				//			t[teamNum].player[playerNum].sta[staType] = sta;
				staType = 0;
				
				sta = 0;
				do
				{
					do{c = f.read();}while (c == '\n');
					if(c == -1)//文件结束
					{
						return 2;
					}
					if(c != ',')
					{
						sta = sta * 10 + Integer.valueOf((char)c+"");
//						System.out.println(c);
					}
				}while(c != ',');
				team1 = sta;
				
				sta = 0;
				do
				{
					c = f.read();
					if(c != ',')
					{
						sta = sta * 10 + Integer.valueOf((char)c+"");
					}
				}while(c != ',');
				team2 = sta;
				sta = 0;
				
				
				int time = 0;
				while(com[team1][team2][time].exist) time++;
				com[team1][team2][time].exist = true;//防数据覆盖
				
				staType = 0;
				for(; staType < 4; staType++)
				{
					do
					{
						c = f.read();
						if(c != ',' && c != '=')
						{
							sta = sta * 10 + Integer.valueOf((char)c+"");
						}
					}while(c != ',' && c != '=');
					
					com[team1][team2][time].sta[staType] = sta;
					sta = 0;
				}
				
				do
				{
					c = f.read();
					if(c != ':')
					{
						sta = sta * 10 + Integer.valueOf((char)c+"");
					}
				}while(c != ':');
				f.read();
				
				com[team1][team2][time]._score1 = sta;
				sta = 0;
				
				do
				{
					c = f.read();
					if(c != 13)
					{
						sta = sta * 10 + Integer.valueOf((char)c+"");
					}
				}while(c != 13);
				com[team1][team2][time]._score2 = sta;
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 1;
	}
	
	public static void main(String[] args)
	{
		Core core = new Core();
		for(int i = 0; i < core.team.length; i++)
		{
			core.team[i] = new Team();
			for(int j = 0; j < core.team[i].player.length; j++)
			{
				core.team[i].player[j] = new Player();
			}
		}
		
		for(int i = 0; i < core.competition.length; i++)
		{
			for(int j = 0; j < core.competition[i].length; j++)
			{
				for(int k = 0; k < core.competition[i][j].length; k++)
				{
					core.competition[i][j][k] = new Competition();
				}
			}
		}
		
		try
		{
			core.f1 = new FileInputStream("D:\\赛前数据.csv");
			core.readPlayer(core.f1, core.team);
			
//			System.out.print(core.team[10].player[10].sta[10]);
	/*	
			//以下为测试
			
			for(int i = 0; i < core.team.length; i++)
			{
				for(int j = 0; j < core.team[i].player.length; j++)
				{
					for(int k = 0; k < core.team[i].player[j].sta.length; k++)
					{
						System.out.print(core.team[i].player[j].sta[k]+"\t");
					}
					System.out.println();
				}
			}
			
	*/		
	//		System.out.print("\n\n"+core.team[207].player[15].sta[0]+"\t");
			
			core.f1.close();
			core.f1 = new FileInputStream("D:\\结果数据.csv");
			core.readCompetition(core.f1, core.competition);
			
			for(int i = 0; i < core.competition.length; i++)
			{
				for(int j = 0; j < core.competition[i].length; j++)
				{
					for(int k = 0; k < core.competition[i][j].length; k++)
					{
						if (core.competition[i][j][k].exist)
						{
							System.out.print(core.competition[i][j][k].sta[0]+"\t");
							System.out.print(core.competition[i][j][k].sta[1]+"\t");
							System.out.print(core.competition[i][j][k].sta[2]+"\t");
							System.out.print(core.competition[i][j][k].sta[3]+"\t");
							System.out.print(core.competition[i][j][k].win()+"\t");
							System.out.println();
						}
					}
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
