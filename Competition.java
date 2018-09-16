
public class Competition 
{
	int team1;
	int team2;
	boolean exist;
	int[] sta = new int[4];
	
	int _score1;
	int _score2;
	
	boolean win()
	{
		if(_score1 < _score2) return true;
		return false;
	}
}
