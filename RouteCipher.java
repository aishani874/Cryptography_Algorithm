import java. util.*;
class RouteCipher
{
    public static void main()
    {
        int r,c,n=0;
        String encr="";
        String cipher="";
        Scanner Sc=new Scanner(System.in);
        RouteCipher ob = new RouteCipher();
        System.out.println("Enter Plain Text:");
        String pt=Sc.nextLine();
        pt=pt.toUpperCase();
        System.out.println("Plain Text = "+pt);
        int l=pt.length();
        StringTokenizer st= new StringTokenizer(pt);
        c=st.countTokens();
        
        String hash="";
        long h = 0;
        long p = 31;     //prime number
        long m = 1_000_000_009L;   //large prime for modulo
        long p_pow = 1;
        for(int i=0;i<l;i++)      //Hashing function
        {
            char ch=pt.charAt(i);
            if(ch!= ' ') 
            {
                h =(h+(ch-'A'+1)*p_pow)%m;
                p_pow=(p_pow*p)%m;
            }
        }
        hash = Long.toHexString(h).toUpperCase();
        
        r = (int)(hash.length()/c)+1; //no. of rows for matrix
        int x = (r*c)-hash.length();     
        System.out.println("X = "+x);
        Random random = new Random();   //to generate random letters for padding
        for(int i=0;i<x;i++)      //adding padding letters
        {
            char alpha = (char)(random.nextInt(26) + 'A');
            hash=hash+alpha;
        }
        System.out.println("Hash value = "+hash);
        //System.out.println("R = "+r+", C = "+c);            //debug
        
        char plain_H[][]=new char[r][c];
        for(int i=0;i<c;i++)  //storing hashed plain text in matrix
        {
            for(int j=0;j<r;j++)
            {
                plain_H[j][i]=hash.charAt(n);
                n++;
            }
        }
        System.out.println("Route Plan :");
        for(int i=0;i<r;i++)  //printing plain text matrix
        {
            for(int j=0;j<c;j++)
                System.out.print(plain_H[i][j]+" ");
            System.out.println();
        }
        
        System.out.println("Route Key :\n\t1.Spiral outwards\n\t2.Clockwise\n\t3.Begin at the centre\n\t4.x+c cypher (where c=no. of words in plain text)");
        int t=r-1,y=-1,z=1,k=r*c,row=r-1,col=c;
        while(k>=1)    //forming spiral text
        {
            for(int i=0;i<col;i++)
            {
                y=y+z;
                encr=plain_H[t][y]+encr;
                k--;
            }
            z=-z;
            for(int i=0;i<row;i++)
            {
                t=t+z;
                encr=plain_H[t][y]+encr;
                k--;
            }
            row--; col--;
        }
        System.out.println("Routed text : "+encr);
        
        for(int i=0;i<encr.length();i++)   //x+c ciphering
        {
            int ascii=(int)encr.charAt(i)+c;
            if((int)encr.charAt(i)>=48 && (int)encr.charAt(i)<=57 && ascii>57)
                ascii=47+(ascii-57);
            else if(ascii>90)
                ascii=64+(ascii-90);
            cipher=cipher+(char)ascii;
        }
        System.out.println("Cipher text : "+cipher);
        String decr=ob.decrypt(cipher,c);
        System.out.println("Comparison:");
        System.out.println("Decrypted Text : "+decr);
        System.out.println("Padded Hash : "+hash);
    }
    String decrypt(String cipher,int c) 
    {
        String rev_route = "";
        for (int i=0;i<cipher.length();i++) //reversing the x+c cipher
        {
            int ascii=(int)cipher.charAt(i)-c;
            if (cipher.charAt(i)>='0' && cipher.charAt(i)<='9') 
            {
                if(ascii<48) 
                    ascii=58-(48-ascii);
            } 
            else if(cipher.charAt(i)>='A' && cipher.charAt(i)<='Z') 
            {
                if(ascii<65) 
                    ascii=91-(65-ascii);
            }
            rev_route=rev_route+(char)ascii;
        }
        System.out.println("Reverse ciphered Text : "+rev_route);
        int len=rev_route.length();
        int r=len/c;
        char decrypt[][]=new char[r][c];
        int t=r-1,y=-1,z=1,k=r*c,row=r-1,col=c;
        int pos=len-1; 
        while(k>=1)    //reverse spiralling
        {
            for(int i=0;i<col;i++) 
            {
                y=y+z;
                decrypt[t][y]=rev_route.charAt(pos--);
                k--;
            }
            z=-z;
            for(int i=0;i<row;i++) 
            {
                t=t+z;
                decrypt[t][y]= rev_route.charAt(pos--);
                k--;
            }
            row--; col--;
        }
        for(int i=0;i<r;i++)  //printing reverser routing matrix
        {
            for(int j=0;j<c;j++)
                System.out.print(decrypt[i][j]+" ");
            System.out.println();
        }
        String decry = "";
        for(int i=0;i<c;i++)    //reverse routing the matrix
        {
            for(int j=0;j<r;j++)
                decry+= decrypt[j][i];
        }
        return decry;    //returning the obtained padded hash value
    }
}