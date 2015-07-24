package client;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerObject implements Serializable {
	private static final long serialVersionUID = 3;

	private int xCoordinate = 400;
	private int yCoordinate = 200;
	private int xMove;
	private int yMove;

	private Boolean faceDown = true;
	private Boolean faceUp = false;
	private Boolean faceLeft = false;
	private Boolean faceRight = false;
	private Boolean cross = false;
	private Boolean block = false;

	private ArrayList<String> usernames;
	private String message;
	private String username = "undefined";
	private Boolean refreshCoordinates = false;


	public void setRefreshCoordinates(Boolean refreshCoordinates){
		this.refreshCoordinates = refreshCoordinates;
	}
	
	public Boolean getRefreshCoordinates(){
		return refreshCoordinates;
	}
	
	public void setXCoordinate(int xCoordinate){
		this.xCoordinate = xCoordinate;
	}
	
	public int getXCoordinate(){
		return xCoordinate;
	}
	
	public void setYCoordinate(int yCoordinate){
		this.yCoordinate = yCoordinate;
	}
	
	public int getYCoordinate(){
		return yCoordinate;
	}
	
	public void setArrayList(ArrayList<String> usernames){
		this.usernames = usernames;
		
	}
	
	public void addArrayList(){
		if(username != "undefined" && usernames.indexOf(username) < 0){
			usernames.add(username);
		}
	}
	
	public ArrayList<String> getArrayList(){
		return usernames;
	}
	
	public void setXMove(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove(){
		return xMove;
	}
	
	public void setYMove(int yMove){
		this.yMove = yMove;
	}
	
	public int getYMove(){
		return yMove;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return message;
	}
	
	public void setCross(Boolean cross){
		this.cross = cross;
	}
	public Boolean getCross(){
		return cross;
	}
	
	public void setBlock(Boolean block){
		this.block = block;
	}
	public Boolean getBlock(){
		return block;
	}
	
	public void setFaceRight(Boolean faceRight){
		this.faceRight = faceRight;
	}
	public Boolean getFaceRight(){
		return faceRight;
	}
	
	public void setFaceLeft(Boolean faceLeft){
		this.faceLeft = faceLeft;
	}
	public Boolean getFaceLeft(){
		return faceLeft;
	}
	
	public void setFaceUp(Boolean faceUp){
		this.faceUp = faceUp;
	}
	public Boolean getFaceUp(){
		return faceUp;
	}
	
	public void setFaceDown(Boolean faceDown){
		this.faceDown = faceDown;
	}
	public Boolean getFaceDown(){
		return faceDown;
	}
	
	
	
	
	
	
	
	///EXTRA METHODS TO TEST FOR LAGGGGG
	public void setXMove1(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove1(){
		return xMove;
	}
	public void setXMove2(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove2(){
		return xMove;
	}
	public void setXMove3(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove3(){
		return xMove;
	}
	public void setXMove4(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove4(){
		return xMove;
	}
	public void setXMove5(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove5(){
		return xMove;
	}
	public void setXMove6(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove6(){
		return xMove;
	}
	public void setXMove7(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove7(){
		return xMove;
	}
	public void setXMove8(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove8(){
		return xMove;
	}
	public void setXMove9(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove9(){
		return xMove;
	}
	public void setXMove10(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove10(){
		return xMove;
	}
	public void setXMove11(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove11(){
		return xMove;
	}
	public void setXMove12(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove12(){
		return xMove;
	}
	public void setXMove13(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove13(){
		return xMove;
	}
	public void setXMove14(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove15(){
		return xMove;
	}
	public void setXMove15(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove16(){
		return xMove;
	}
	public void setXMove16(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove17(){
		return xMove;
	}
	public void setXMove17(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove18(){
		return xMove;
	}
	public void setXMove18(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove19(){
		return xMove;
	}
	public void setXMove19(int xMove){
		this.xMove = xMove;
	}
	
	public void setXMove20(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove20(){
		return xMove;
	}
	public void setXMove21(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove21(){
		return xMove;
	}
	public void setXMove22(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove22(){
		return xMove;
	}
	public void setXMove23(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove23(){
		return xMove;
	}
	public void setXMove24(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove25(){
		return xMove;
	}
	public void setXMove25(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove26(){
		return xMove;
	}
	public void setXMove26(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove27(){
		return xMove;
	}
	public void setXMove27(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove28(){
		return xMove;
	}
	public void setXMove28(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove29(){
		return xMove;
	}
	public void setXMove29(int xMove){
		this.xMove = xMove;
	}

	public void setXMove30(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove30(){
		return xMove;
	}
	public void setXMove31(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove31(){
		return xMove;
	}
	public void setXMove32(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove32(){
		return xMove;
	}
	public void setXMove33(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove33(){
		return xMove;
	}
	public void setXMove34(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove35(){
		return xMove;
	}
	public void setXMove35(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove36(){
		return xMove;
	}
	public void setXMove36(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove37(){
		return xMove;
	}
	public void setXMove37(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove38(){
		return xMove;
	}
	public void setXMove38(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove39(){
		return xMove;
	}
	public void setXMove39(int xMove){
		this.xMove = xMove;
	}

	public void setXMove40(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove40(){
		return xMove;
	}
	public void setXMove41(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove41(){
		return xMove;
	}
	public void setXMove42(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove42(){
		return xMove;
	}
	public void setXMove43(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove43(){
		return xMove;
	}
	public void setXMove44(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove45(){
		return xMove;
	}
	public void setXMove45(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove46(){
		return xMove;
	}
	public void setXMove46(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove47(){
		return xMove;
	}
	public void setXMove47(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove48(){
		return xMove;
	}
	public void setXMove48(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove49(){
		return xMove;
	}
	public void setXMove49(int xMove){
		this.xMove = xMove;
	}

	public void setXMove50(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove50(){
		return xMove;
	}
	public void setXMove51(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove51(){
		return xMove;
	}
	public void setXMove52(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove52(){
		return xMove;
	}
	public void setXMove53(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove53(){
		return xMove;
	}
	public void setXMove54(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove55(){
		return xMove;
	}
	public void setXMove55(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove56(){
		return xMove;
	}
	public void setXMove56(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove57(){
		return xMove;
	}
	public void setXMove57(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove58(){
		return xMove;
	}
	public void setXMove58(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove59(){
		return xMove;
	}
	public void setXMove59(int xMove){
		this.xMove = xMove;
	}

	public void setXMove60(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove60(){
		return xMove;
	}
	public void setXMove61(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove61(){
		return xMove;
	}
	public void setXMove62(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove62(){
		return xMove;
	}
	public void setXMove63(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove63(){
		return xMove;
	}
	public void setXMove64(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove65(){
		return xMove;
	}
	public void setXMove65(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove66(){
		return xMove;
	}
	public void setXMove66(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove67(){
		return xMove;
	}
	public void setXMove67(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove68(){
		return xMove;
	}
	public void setXMove68(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove69(){
		return xMove;
	}
	public void setXMove69(int xMove){
		this.xMove = xMove;
	}

	public void setXMove70(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove70(){
		return xMove;
	}
	public void setXMove71(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove71(){
		return xMove;
	}
	public void setXMove72(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove72(){
		return xMove;
	}
	public void setXMove73(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove73(){
		return xMove;
	}
	public void setXMove74(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove75(){
		return xMove;
	}
	public void setXMove75(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove76(){
		return xMove;
	}
	public void setXMove76(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove77(){
		return xMove;
	}
	public void setXMove77(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove78(){
		return xMove;
	}
	public void setXMove78(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove79(){
		return xMove;
	}
	public void setXMove79(int xMove){
		this.xMove = xMove;
	}

	public void setXMove80(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove80(){
		return xMove;
	}
	public void setXMove81(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove81(){
		return xMove;
	}
	public void setXMove82(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove82(){
		return xMove;
	}
	public void setXMove83(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove83(){
		return xMove;
	}
	public void setXMove84(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove85(){
		return xMove;
	}
	public void setXMove85(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove86(){
		return xMove;
	}
	public void setXMove86(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove87(){
		return xMove;
	}
	public void setXMove87(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove88(){
		return xMove;
	}
	public void setXMove88(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove89(){
		return xMove;
	}
	public void setXMove89(int xMove){
		this.xMove = xMove;
	}

	public void setXMove90(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove90(){
		return xMove;
	}
	public void setXMove91(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove91(){
		return xMove;
	}
	public void setXMove92(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove92(){
		return xMove;
	}
	public void setXMove93(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove93(){
		return xMove;
	}
	public void setXMove94(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove95(){
		return xMove;
	}
	public void setXMove95(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove96(){
		return xMove;
	}
	public void setXMove96(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove97(){
		return xMove;
	}
	public void setXMove97(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove98(){
		return xMove;
	}
	public void setXMove98(int xMove){
		this.xMove = xMove;
	}
	
	public int getXMove99(){
		return xMove;
	}
	public void setXMove99(int xMove){
		this.xMove = xMove;
	}

	
	
}