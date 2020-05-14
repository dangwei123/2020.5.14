马拉车算法
public class Manacher {
    public String longestPalindrome(String s) {
        int n=s.length()*2+1;
        char[] arr=new char[n];
        int j=0;
        for(int i=0;i<n;i++){
            if((i&1)==0) arr[i]='#';
            else arr[i]=s.charAt(j++);
        }
        int[] palid=new int[n];
        int maxright=0;
        int cir=0;
        int index=0;
        int max=0;
        for(int i=0;i<n;i++){
            if(maxright>i){
                palid[i]=Math.min(palid[2*cir-i],maxright-i);
            }

            while(i+palid[i]<n&&i-palid[i]>=0&&arr[i+palid[i]]==arr[i-palid[i]]){
                palid[i]++;
            }

            if(i+palid[i]>maxright){
                cir=i;
                maxright=i+palid[i];
            }

            if(palid[i]>max){
                max=palid[i];
                index=i;
            }
        }
        int len=max-1;
        int begin=(index-len)/2;
        return s.substring(begin,begin+len);
    }
}


鸡蛋掉落
public class EggDrop {
    public int superEggDrop(int K, int N) {
        int[][] dp=new int[N+1][K+1];
        for(int i=1;i<=N;i++){
            dp[i][1]=i;
        }
        for(int i=1;i<=K;i++){
            dp[1][i]=1;
        }
        for(int i=2;i<=N;i++){
            for(int j=2;j<=K;j++){
                int left=0;
                int right=i;
                while(left<right){
                    int mid=left+(right-left)/2;
                    if(dp[mid-1][j-1]<dp[i-mid][j]){
                        left=mid+1;
                    }else{
                        right=mid;
                    }
                }

                dp[i][j]=Math.max(dp[left-1][j-1],dp[i-left][j])+1;
            }
        }
        return dp[N][K];
    }
	
	
	public int superEggDrop(int K, int N) {
        if(N==1) return 1;
        int[][] dp=new int[N+1][K+1];
        for(int i=1;i<=K;i++){
            dp[1][i]=1;
        }
        int res=-1;
        for(int i=2;i<=N;i++){
            for(int j=1;j<=K;j++){
                dp[i][j]=dp[i-1][j]+dp[i-1][j-1]+1;
            }
            if(dp[i][K]>=N){
                res=i;
                break;
            }
        }
        return res;
    }
}


