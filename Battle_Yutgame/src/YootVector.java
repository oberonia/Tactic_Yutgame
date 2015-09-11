import java.util.Vector;
//곧 사라질 GARBAGE 클래스입니당



public class YootVector {
    YootVector() {
       v[0] = new Vector<Integer>(20); //
        v[1] = new Vector<Integer>(7);
        v[2] = new Vector<Integer>(7);
        
        for (int i=0;i<20;i++)
           v[0].add(v1[i]);
        for (int i=0;i<7;i++)
           v[1].add(v2[i]);
        for (int i=0;i<7;i++)
           v[2].add(v3[i]);
       
    }
    private int v1[] = {23,19,14,10,6,5,4,3,2,1,7,11,16,20,24,25,26,27,28,29}; //20
     private int v2[] = {6,9,13,15,17,21,24}; //7
     private int v3[] = {1,8,12,15,18,22,29}; //7
     @SuppressWarnings("unchecked")
	private Vector<Integer> v[] = new Vector[3];
     
     public int IsOnPan(int mal) {
        if(v[2].indexOf(mal)!=-1)
           return 2;
        else if(v[1].indexOf(mal)!=-1)
           return 1;
        else if(v[0].indexOf(mal)!=-1)
           return 0;
        else return -99;
     }
     public int MoveMal(int mal) { //오류처리 안함
       switch(IsOnPan(mal)) {
       case 2:
          return v[2].elementAt(v[2].indexOf(mal)+1);
       case 1:
          return v[1].elementAt(v[1].indexOf(mal)+1);
       case 0:
          return v[0].elementAt(v[0].indexOf(mal)+1);
       default:
          return -1;
       
       }
     }
     
}