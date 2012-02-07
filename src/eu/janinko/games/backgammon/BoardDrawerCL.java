package eu.janinko.games.backgammon;

public class BoardDrawerCL {
	
	public static void draw(Board b){
		StringBuilder sb = new StringBuilder();
		sb.append("--1---2---3---4---5---6---BW----7---8---9--10--11--12--\n");
		for(int i=1; i<=5; i++){
			drawTopStoneLine(i,b,sb);
			sb.append("|\n");
		}
		drawTopNumberLines(b,sb);
		sb.append("|\n");
		sb.append("=======================================================\n");
		drawBottomNumberLines(b,sb);
		sb.append("|\n");
		for(int i=5; i>=1; i--){
			drawBottomStoneLine(i,b,sb);
			sb.append("|\n");
		}
		sb.append("-24--23--22--21--20--19---BB---18--17--16--15--14--13--\n");
		
		System.out.print(sb);
	}

	private static void drawBottomStoneLine(int i, Board b, StringBuilder sb) {
		for(int j=23; j>17; j--){
			drawSegment(i,j,b,sb);
		}
		sb.append("|| ");
		if(b.getBarCount(Stone.Color.BLACK) >= i){
			sb.append(Stone.BLACK);
		}else{
			sb.append(' ');
		}
		sb.append(" |");
		for(int j=17; j>11; j--){
			drawSegment(i,j,b,sb);
		}
	}

	private static void drawTopStoneLine(int i, Board b, StringBuilder sb) {
		for(int j=0; j<6; j++){
			drawSegment(i,j,b,sb);
		}
		sb.append("|| ");
		if(b.getBarCount(Stone.Color.WHITE) >= i){
			sb.append(Stone.WHITE);
		}else{
			sb.append(' ');
		}
			
		sb.append(" |");
		for(int j=6; j<12; j++){
			drawSegment(i,j,b,sb);
		}
	}

	private static void drawBottomNumberLines(Board b, StringBuilder sb) {
		for(int j=23; j>17; j--){
			drawSegmentNumberH(j,b,sb);
		}
		sb.append("|| ");
		if(b.getBarCount(Stone.Color.BLACK) >= 10){
			sb.append(1);
		}else if(b.getBarCount(Stone.Color.BLACK) > 5){
			sb.append(b.getBarCount(Stone.Color.BLACK));
		}else{
			sb.append(' ');
		}
		sb.append(" |");
		for(int j=17; j>11; j--){
			drawSegmentNumberH(j,b,sb);
		}

		sb.append("|\n");

		for(int j=23; j>17; j--){
			drawSegmentNumberL(j,b,sb);
		}
		sb.append("|| ");
		if(b.getBarCount(Stone.Color.BLACK) >= 10){
			sb.append(b.getBarCount(Stone.Color.BLACK)-10);
		}else{
			sb.append(' ');
		}
		sb.append(" |");
		for(int j=17; j>11; j--){
			drawSegmentNumberL(j,b,sb);
		}
	}

	private static void drawTopNumberLines(Board b, StringBuilder sb) {
		for(int j=0; j<6; j++){
			drawSegmentNumberH(j,b,sb);
		}
		sb.append("|| ");
		if(b.getBarCount(Stone.Color.WHITE) >= 10){
			sb.append(1);
		}else if(b.getBarCount(Stone.Color.WHITE) > 5){
			sb.append(b.getBarCount(Stone.Color.WHITE));
		}else{
			sb.append(' ');
		}
		sb.append(" |");
		for(int j=6; j<12; j++){
			drawSegmentNumberH(j,b,sb);
		}

		sb.append("|\n");

		for(int j=0; j<6; j++){
			drawSegmentNumberL(j,b,sb);
		}
		sb.append("|| ");
		if(b.getBarCount(Stone.Color.WHITE) >= 10){
			sb.append(b.getBarCount(Stone.Color.WHITE)-10);
		}else{
			sb.append(' ');
		}
		sb.append(" |");
		for(int j=6; j<12; j++){
			drawSegmentNumberL(j,b,sb);
		}
	}

	private static void drawSegmentNumberH(int j, Board b, StringBuilder sb) {
		sb.append("| ");
		if(b.getStoneCount(j) >= 10){
			sb.append(1);
		}else if(b.getStoneCount(j) > 5){
			sb.append(b.getStoneCount(j));
		}else{
			sb.append(' ');
		}
		sb.append(" ");		
	}

	private static void drawSegmentNumberL(int j, Board b, StringBuilder sb) {
		sb.append("| ");
		if(b.getStoneCount(j) >= 10){
			sb.append(b.getStoneCount(j)-10);
		}else{
			sb.append(' ');
		}
		sb.append(" ");		
	}

	private static void drawSegment(int i, int j, Board b, StringBuilder sb) {
		sb.append("| ");
		if(b.getStoneCount(j) >= i){
			sb.append(b.getStone(j));
		}else{
			sb.append(' ');
		}
		sb.append(" ");
	}
	

}
