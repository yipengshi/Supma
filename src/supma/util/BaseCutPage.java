package supma.util;




public class BaseCutPage{

	private static final long serialVersionUID = 8L;
	
	public BaseCutPage(){

	}
	private static final String OPTION_PRE="PRE";
	private static final String OPTION_NEXT="NEXT";
	private static final String OPTION_BEGIN="BEGIN";
	private static final String OPTION_AFTER="AFTER";
	private static final String OPTION_CURRENT="CURRENT";
	int allCount=0;
	int allPage=0;
	int onePageCount=0;
	int prePageStart=0;
	int currentPageStart=0;
	int currentPageNo=0;
	int prePageNo=0;
	String option="";
	
	public void init(int _allCount,int _onePageCount, String _prePageNo,String _option){
		allCount=_allCount;
		onePageCount=_onePageCount;
		if(_prePageNo==null || "".equals(_prePageNo)){
			prePageNo=0;
		}else{
			prePageNo=Integer.parseInt(_prePageNo);
		}
		option=_option;
		
		if(allCount==0){
			allPage=1;
			currentPageNo=1;
			currentPageStart=1;
			return;
		}
		
		int tempPagecount1=allCount/onePageCount;
		int tempPagecount2=allCount%onePageCount;
		if(tempPagecount2!=0){
			allPage=tempPagecount1+1;
		}else{
			allPage=tempPagecount1;
		}
		
		if(option==null || "".equals(option)){
			currentPageNo=1;
			currentPageStart=1;
			return;
		}else if(OPTION_CURRENT.equals(option)){
			currentPageNo=prePageNo;
			if(prePageNo==0){
				currentPageStart=1;
			}else{
				currentPageStart=(prePageNo-1)*onePageCount+1;
			}
			return;
		}
		
		if(OPTION_BEGIN.equals(option)){
			currentPageNo=1;
			currentPageStart=1;
		}else if(OPTION_AFTER.equals(option)){
			currentPageNo=allPage;
			if(allPage==1){
				currentPageStart=1;
			}else{
				currentPageStart=(allPage-1)*onePageCount+1;
			}
		}else if(OPTION_NEXT.equals(option)){
			if(allPage==1){
				currentPageNo=1;
				currentPageStart=1;
				return;
			}
			if(allPage==prePageNo){
				if(prePageNo==0){
					currentPageNo=1;
					currentPageStart=1;
				}else if(allPage==1){
					currentPageNo=1;
					currentPageStart=1;
				}else{
					currentPageNo=prePageNo;
					currentPageStart=(allPage-1)*onePageCount+1;
				}
				return;
			}else{
				if(prePageNo==0){
					currentPageNo=1;
					currentPageStart=1;
				}else{
					currentPageNo=prePageNo+1;
					currentPageStart=(prePageNo)*onePageCount+1;
				}
			}
			
		}else if(OPTION_PRE.equals(option)){
			
			if(allPage==1){
				currentPageNo=1;
				currentPageStart=1;
				return;
			}
			if(allPage==prePageNo){
				if(prePageNo==0){
					currentPageNo=1;
					currentPageStart=1;
				}else{
					currentPageNo=prePageNo-1;
					currentPageStart=(currentPageNo-1)*onePageCount+1;
				}
				return;
			}else{
				if(prePageNo==0){
					currentPageNo=1;
					currentPageStart=1;
				}else if(prePageNo==1){
					currentPageNo=1;
					currentPageStart=1;
				}else{
					currentPageNo=prePageNo-1;
					currentPageStart=(currentPageNo-1)*onePageCount+1;
				}
			}
		}else{
			currentPageNo=1;
			currentPageStart=1;
		}
	}
	
	public int getCurrentPageNo(){
		return currentPageNo;
	}
	public int getCurrentPageStart(){
		return currentPageStart;
	}
	public boolean isFirst(){
		if(currentPageNo==1){
			return true;
		}else{
			return false;
		}
	}
	public boolean isLast(){
		if(currentPageNo==allPage){
			return true;
		}else{
			return false;
		}
	}
	public int getAllPage(){
		return allPage;
	}
}